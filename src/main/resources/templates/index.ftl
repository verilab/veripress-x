<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>VeriPress X</title>
</head>
<body>
<h1>VeriPress X</h1>

<a href="/login">登录</a>
<a href="/register">注册</a>

<#if Session.user??>
    <p>你好，${Session.user.username}</p>
    <p><a href="/logout">注销</a></p>
<#else>
    <p>您还未登录</p>
</#if>

<#if Session.user??>
    <form action="/post/new" method="post">
        <div>
            <label for="new-post-content">发布新分享</label><br>
            <textarea name="content" id="new-post-content" cols="30" rows="10"></textarea>
        </div>
        <button type="submit">发布</button>
    </form>
</#if>

<hr>
<#list posts as post>
    ${post.content}
    <#sep>
        <hr>
    </#sep>
</#list>
<hr>
</body>
</html>