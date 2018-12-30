<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登录</title>
</head>
<body>
<form action="/login" method="post">
    <div>
        <label for="login-username">用户名:</label>
        <input type="text" id="login-username" name="username">
    </div>
    <div>
        <label for="login-password">密码:</label>
        <input type="password" id="login-password" name="password">
    </div>
    <button type="submit">登录</button>
    <#if errors??>
        <div style="color: red;">
            <#list errors as error>
                <div>${error}</div>
            </#list>
        </div>
    </#if>
</form>
</body>
</html>