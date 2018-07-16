<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title>注册绑定</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/mlogin/css/weui.css" />

</head>
<body>
<!-- 代码 开始 -->
<div class="weui-cells__title"></div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">账户</label></div>
            <div class="weui-cell__bd weui-cell_primary"><input type="tel" class="weui-input" placeholder="手机号码"/></div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">身份证号</label></div>
            <div class="weui-cell__bd weui-cell_primary"><input type="text" class="weui-input" placeholder="身份证号"/></div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">推广码</label></div>
            <div class="weui-cell__bd weui-cell_primary"><input type="text" class="weui-input" placeholder="推广码"/></div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">密码</label></div>
            <div class="weui-cell__bd weui-cell_primary"><input type="password" class="weui-input" placeholder="不少于6位"/></div>
        </div>
        <input type="hidden" id="openid"/>
        <input type="hidden" id="nickname"/>
    </div>
    <div class="weui-cells__tips"></div>
    <div class="weui-btn-area">
        <a class="weui-btn weui-btn_primary" href="javascript:" id="subBtn">确定登录</a>
    </div>
<!-- 代码 结束 -->
</body>
<script type="text/javascript">
    $(function(){
        $('#subBtn').on('click', function(){
        	alert("登录");
        });
    });
</script>
</html>

