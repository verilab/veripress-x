<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>注册</title>
</head>
<body>
<form action="/register" method="post">
    <div>
        <label for="register-username">用户名:</label>
        <input type="text" id="register-username" name="username">
    </div>
    <div>
        <label for="register-password">密码:</label>
        <input type="password" id="register-password" name="password">
    </div>
    <button type="submit">注册</button>
    <#if errors??>
        <div class="error" style="color: red">
            <#list errors as error>
                <div>${error}</div>
            </#list>
        </div>
    </#if>
</form>
</body>
</html>