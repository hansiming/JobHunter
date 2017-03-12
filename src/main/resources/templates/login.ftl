<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title>工作寻</title>
    <link href="css/login.css" type="text/css" rel="stylesheet">
</head>
<body>

<div class="login">
    <div class="message">工作寻-管理登录</div>
    <div id="darkbannerwrap"></div>


    <#--<input id="contextPath" type="hidden" value="${request.contextPath}">-->
    <input name="action" value="login" type="hidden">
    <input id="username" placeholder="用户名" required="" type="text">
    <hr class="hr15">
    <input id="password" placeholder="密码" required="" type="password">
    <hr class="hr15">
    <input value="登录" id="loginInput" style="width:100%;" type="submit" onclick="login()">
    <hr class="hr20">
    <span id="loginInfo"></span>


</div>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
    function login() {

        var userName = $("#username").val();
        var password = $("#password").val();
        $("#loginInput").val("登录中...");
        $("#loginInput").attr("disable", "disable");

        $.ajax({
            type : 'POST',
            url : 'doLogin',
            data : {userName : userName, password : password},
            dataType : 'json',
            success : function (data) {
                if (data.status == 1) {
                    loginInfo(data.info, "green");
                    window.location.href='/index';
                } else if (data.status == 2) {
                    loginInfo(data.info, "red");
                    $("#loginInput").val("登录");
                    $("#loginInput").attr("disable", "");
                }
            }
        })
    }

    function loginInfo(info, color) {
        $("#loginInput").text(info);
        $("#loginInput").style("color:" + color);
    }
</script>
</body>
</html>