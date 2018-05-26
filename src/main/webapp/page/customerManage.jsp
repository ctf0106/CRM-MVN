<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery.print.min.js"></script>

<script type="text/javascript">
 
 var url;
 
 $(function(){
		$("#companyID").combobox({
			 url:'../company/comboList.do',
			    valueField:'id',
			    textField:'name'
		});
	});
 
 function searchCustomer(){
	 $("#dg").datagrid('load',{
		"khno":$("#s_khno").val(),
		"name":$("#s_name").val()
	 });
 }
 
 function openCustomerAddDialog(){
	 $("#dlg").dialog("open").dialog("setTitle","添加客户信息");
	 url="${pageContext.request.contextPath}/customer/save.do";
 }
 
 function openCustomerModifyDialog(){
	 var selectedRows=$("#dg").datagrid("getSelections");
	 if(selectedRows.length!=1){
		 $.messager.alert("系统提示","请选择一条要编辑的数据！");
		 return;
	 }
	 var row=selectedRows[0];
	 $("#dlg").dialog("open").dialog("setTitle","编辑客户信息");
	 $("#fm").form("load",row);
	 url="${pageContext.request.contextPath}/customer/save.do?id="+row.id;
 }
 
 function saveCustomer(){
	 $("#fm").form("submit",{
		url:url,
		onSubmit:function(){
			var name=$("#name").val();
			 var isExist=isExistName(name);
			 if(!isExist){
				 alert("用户名已经存在!");
				return false; 
			 }
			var companyID=$("#companyID").combobox("getValue");
			if(companyID==null || companyID==''){
				alert("请归属的公司");
				return false;
			}
			
			 return $(this).form("validate");
		},
		success:function(result){
			var result=eval('('+result+')');
			if(result.success){
				$.messager.alert("系统提示","保存成功！");
				resetValue();
				$("#dlg").dialog("close");
				$("#dg").datagrid("reload");
			}else{
				$.messager.alert("系统提示","保存失败！");
				return;
			}
		}
	 });
 }
 
 function isExistName(name){
		 $.ajaxSettings.async = false
		 var flag;
		 $.post("${pageContext.request.contextPath}/user/isExistName.do",{'name':name},function(result){
				 flag=result;	 
		},"json");
		 return flag;
 }
 
 function resetValue(){
	 $("#name").val("");
	 $("#address").val("");
	 $("#postCode").val("");
	 $("#phone").val("");
	 $("#fund").val("");
	 $("#financing").val("");
	 $("#companyID").val("");
	 $("#companyID").html("请选择...");
 }
 
 function closeCustomerDialog(){
	 $("#dlg").dialog("close");
	 resetValue();
 }
 
 function deleteCustomer(){
	 var selectedRows=$("#dg").datagrid("getSelections");
	 if(selectedRows.length==0){
		 $.messager.alert("系统提示","请选择要删除的数据！");
		 return;
	 }
	 var strIds=[];
	 for(var i=0;i<selectedRows.length;i++){
		 strIds.push(selectedRows[i].id);
	 }
	 var ids=strIds.join(",");
	 $.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
		if(r){
			$.post("${pageContext.request.contextPath}/customer/delete.do",{ids:ids},function(result){
				if(result.success){
					 $.messager.alert("系统提示","数据已成功删除！");
					 $("#dg").datagrid("reload");
				}else{
					$.messager.alert("系统提示","数据删除失败，请联系系统管理员！");
				}
			},"json");
		} 
	 });
 }
 /**
 显示二维码
 */
 function formatOper(value, row, index){  
    if(row.qrcode){  
        return "<img style='width:96px;height:96px;' id=printQrCode_"+row.id+" border='1' src='data:image/gif;base64,"+row.qrcode+"'/>";
    }  
}
 function showBarCoder(value, row, index){  
	    if(row.barcode){  
	        return "<img style='width:350px;height:70px;' id=printBarCode_"+row.id+" border='1' src='data:image/gif;base64,"+row.barcode+"'/>";
	    }  
	}
 
 function qrprint(value, row, index){
	 return "<input type='button' value='打印' onClick=\"doPrint('"+row.id+"')\" >"
 }
 function doPrint(id){
	 $("#printBarCode_"+id+"").print({
		    globalStyles:false,//是否包含父文档的样式，默认为true
		    mediaPrint:false,//是否包含media='print'的链接标签。会被globalStyles选项覆盖，默认为false
		    stylesheet:null,//外部样式表的URL地址，默认为null
		    noPrintSelector:".no-print",//不想打印的元素的jQuery选择器，默认为".no-print"
		    iframe:true,//是否使用一个iframe来替代打印表单的弹出窗口，true为在本页面进行打印，false就是说新开一个页面打印，默认为true
		    append:null,//将内容添加到打印内容的后面
		    prepend:null,//将内容添加到打印内容的前面，可以用来作为要打印内容
		    deferred:
		 $.Deferred()//回调函数
		});
 };
 
 
    
</script>
<title>Insert title here</title>
</head>
<body style="margin: 1px">
 <table id="dg" title="客户信息查询" class="easyui-datagrid"
    pagination="true" rownumbers="true"
   url="${pageContext.request.contextPath}/customer/list.do" fit="true" toolbar="#tb">
   <thead data-options="frozen:true">
		<tr>
			<th field="cb" checkbox="true" align="center"></th>
	 		<th field="id" width="50" align="center" hidden="true">编号</th>
	 		<th field="khno" width="150" align="center">客户编号</th>
	 		<th field="name" width="100" align="center">客户名称</th>
	 		<th field="fund" width="100" align="center">基金金额</th>
	 		<th field="financing" width="100" align="center">理财金额</th>
	 		<th field="phone" width="100" align="center">联系电话</th>
		</tr>
	</thead>
	<thead>
		<tr>
	 		<th field="address" width="200" align="center" >客户地址</th>
	 		<th field="postCode" width="100" align="center" >邮政编码</th>
	 		<th field="companyName" width="100" align="center" >归属单位</th>
	 		<th field="qrcode" width="100" align="center" hidden="true">二维码base64</th>
	 		<th field="chakan" width="120" align="center" data-options="field:'id',width:60,align:'center',formatter:formatOper" >二维码</th>
	 		<th field="barcodeshow" width="350" align="center" data-options="field:'id',width:60,align:'center',formatter:showBarCoder" >条形码</th>
	 		<th field="qrprint" width="100" align="center" data-options="field:'id',width:60,align:'center',formatter:qrprint" >打印</th>
		</tr>
	</thead>
 </table>
 <div id="tb">
 	<div>
 		<a href="javascript:openCustomerAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>
 		<a href="javascript:openCustomerModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
 		<a href="javascript:deleteCustomer()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
 	</div>
 	<div>
 		&nbsp;客户编号：&nbsp;<input type="text" id="s_khno" size="20" onkeydown="if(event.keyCode==13) searchCustomer()"/>
 		&nbsp;客户名称：&nbsp;<input type="text" id="s_name" size="20" onkeydown="if(event.keyCode==13) searchCustomer()"/>
 		<a href="javascript:searchCustomer()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
 	</div>
 </div>
 
 <div id="dlg" class="easyui-dialog" style="width:700px;height:450px;padding: 10px 20px" closed="true" buttons="#dlg-buttons">
   
   <form id="fm" method="post">
   	<table cellspacing="8px">
   		<tr>
   			<td>客户名称：</td>
   			<td><input type="text" id="name" name="name" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td>客户地址：</td>
   			<td><input type="text" id="address" name="address" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
   		</tr>
   		<tr>
   			<td>基金金额：</td>
   			<td><input type="text" id="fund" name="fund" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td>理财金额：</td>
   			<td><input type="text" id="financing" name="financing" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
   		</tr>
   		
   		<tr>
   			<td>邮政编码：</td>
   			<td><input type="text" id="postCode" name="postCode" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td>联系电话：</td>
   			<td><input type="text" id="phone" name="phone" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
   		</tr>
   			<tr>
   			<td>归属单位：</td>
   			<td>
   			<input class="easyui-combobox" id="companyID" name="companyID" data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'name',url:'../company/comboList.do'"/><font color="red">*</font>
   			</td>
   		</tr>
   	</table>
   </form>
 </div>
 
 
 <div id="dlg-buttons">
 	<a href="javascript:saveCustomer()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
 	<a href="javascript:closeCustomerDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
 </div>
</body>
</html>