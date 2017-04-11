<%--
  Created by IntelliJ IDEA.
  User: Super_hao
  Date: 2017/4/7
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我想应该需要一个标题</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<script>
    function suoyou() {
        alert("dianjile");
        var x = document.getElementsByTagName("a");
        x[0].click();
    }
</script>
<div class="waibu">
    <div class="kuangkuang">
        <p style="text-align: left">账号登陆:</p>
        <form action="./hehe" method="post">
            <input type="text" name="user" class="user-input" style="background: url(pic/login-locked-icon.png) no-repeat 15px center" placeholder="请输入用户名">
            <input type="password" name="name" class="user-input" style="background: url(pic/login-key-icon.png) no-repeat 15px center" placeholder="请输入密码">
            <input type="submit" value="提交" name="nm_iframe" class="tijiao" >
        </form>
    </div>
</div>
<a href="./hehe">suoyou</a>
</body>
</html>
