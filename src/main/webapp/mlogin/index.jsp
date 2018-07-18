<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title>注册绑定</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/mlogin/css/weui.css" />
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
<!-- 代码 开始 -->
<div class="weui-cells__title">注册账号</div>
    <div class="weui-cells weui-cells_form">
         <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">用户名</label></div>
            <div class="weui-cell__bd weui-cell_primary"><input type="text" class="weui-input" id="userName" placeholder="用户名"/></div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">密码</label></div>
            <div class="weui-cell__bd weui-cell_primary"><input type="password" class="weui-input" id="password" placeholder="不少于6位"/></div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
            <div class="weui-cell__bd weui-cell_primary"><input type="text" class="weui-input" id="trueName" placeholder="姓名"/></div>
        </div>
         <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">手机号</label></div>
            <div class="weui-cell__bd weui-cell_primary"><input type="tel" class="weui-input" id="phone" placeholder="手机号"/></div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">email</label></div>
            <div class="weui-cell__bd weui-cell_primary"><input type="text" class="weui-input" id="email" placeholder="email"/></div>
        </div>
       <!--  <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">推广码</label></div>
            <div class="weui-cell__bd weui-cell_primary"><input type="text" id="promote" class="weui-input" placeholder="推广码"/></div>
        </div> -->
        
        <div class="weui-cell">
         <div class="weui-cell__hd"><label class="weui-label">微信openid</label></div>
      	 <div class="weui-cell__bd weui-cell_primary"> <input type="text" class="weui-input" id="openid" value="${openid}"/></div>
        </div>
       <div class="weui-cell">
         <div class="weui-cell__hd"><label class="weui-label">微信昵称</label></div>
      	 <div class="weui-cell__bd weui-cell_primary"> <input type="text" class="weui-input" id="nickname" value="${nickname}"/></div>
        </div>
    </div>
    <div class="weui-cells__tips"></div>
    <div class="weui-btn-area">
        <a class="weui-btn weui-btn_primary" href="javascript:" id="subBtn">注册</a>
    </div>
     <div id="dialogs">
	     <div class="js_dialog" id="dialogmesg" style="display: none;">
	          <div class="weui-mask"></div>
	          <div class="weui-dialog">
	              <div class="weui-dialog__bd">注册成功</div>
	              <div class="weui-dialog__ft">
	                  <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary">知道了</a>
	              </div>
	          </div>
	      </div>
	      <div class="js_dialog" id="dialogmesg2" style="display: none;">
	          <div class="weui-mask"></div>
	          <div class="weui-dialog">
	              <div class="weui-dialog__bd">注册失败</div>
	              <div class="weui-dialog__ft">
	                  <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary">知道了</a>
	              </div>
	          </div>
	      </div>
     </div>
<!-- 代码 结束 -->
</body>
<script type="text/javascript">
    $(function(){
    	var $dialogmesg = $('#dialogmesg');
    	var $dialogmesg2 = $('#dialogmesg2');
    	$('#dialogs').on('click', '.weui-dialog__btn', function(){
            $(this).parents('.js_dialog').fadeOut(200);
        });
        $('#subBtn').on('click', function(){
        	var userName=$('#userName').val();
        	var password=$('#password').val();
        	var trueName=$('#trueName').val();
        	var phone=$('#phone').val();
        	var email=$('#email').val();
        	var promote=$('#promote').val();
        	var openid=$('#openid').val();
        	var nickname=$('#nickname').val();
        	if(userName=="" || userName==null || userName==undefined){
        		alert("用户名不能为空");
        		return false;
        	}
        	if(password=="" || password==null || password==undefined){
        		alert("密码不能为空");
        		return false;
        	}
        	if(trueName=="" || trueName==null || trueName==undefined){
        		alert("姓名不能为空");
        		return false;
        	}
        	if(phone=="" || phone==null || phone==undefined){
        		alert("手机号不能为空");
        		return false;
        	}
        	var regular=/^[1][3,4,5,7,8][0-9]{9}$/;
            if (!regular.test(phone)) {
            	alert("手机号格式不正确");
                return false;
            } 
        	if(email=="" || email==null || email==undefined){
        		alert("email不能为空");
        		return false;
        	}
        	if(openid=="" || openid==null || openid==undefined){
        		alert("微信openid不能为空");
        		return false;
        	}
        	   $.ajax({
                   type: "post",
                   url: "${pageContext.request.contextPath}/user/save.do",
                   data: {
                	   "userName":userName,
                	   "password":password,
                	   "trueName":trueName,
                	   "phone":phone,
                	   "email":email,
                	   "promote":promote,
                	   "openid":openid,
                	   "nickname":nickname,
                   },
                   dataType: "json",
                   success: function(data){
                	   if(data.success){
                		   $dialogmesg.fadeIn(200);
                		   reset()
                		   window.location.href="${pageContext.request.contextPath}/mlogin/success.jsp";
                	   }else{
                		   $dialogmesg2.fadeIn(200);
                	   }
                   }
               });
        	
        });
        function reset(){
        	var userName=$('#userName').val("");
        	var password=$('#password').val("");
        	var trueName=$('#trueName').val("");
        	var phone=$('#phone').val("");
        	var email=$('#email').val("");
        	var promote=$('#promote').val("");
        }
    });
</script>
</html>

