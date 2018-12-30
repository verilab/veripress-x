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

<#if Session.user??>
    <p>你好，${Session.user.nickname}</p>
    <p><a href="/logout">注销</a></p>
<#else>
    <p>您还未登录</p>
    <p>
        <a href="/login">登录</a>
        <a href="/register">注册</a>
    </p>
</#if>

<#if Session.user??>
    <form action="/post/new" method="post">
        <div>
            <label for="new-post-content">发布新分享</label><br>
            <textarea name="content" id="new-post-content" cols="30" rows="10"></textarea>
        </div>
        <button type="submit">发布</button>

        <#if errors??>
            <div style="color: red;">
                <#list errors as error>
                    <div>${error}</div>
                </#list>
            </div>
        </#if>
    </form>
</#if>

<hr>
<#list posts as post>
    <div>${post.user.nickname} (发布于 ${post.publishDate}):</div>
    <div>
        ${post.contentHtml}
    </div>
    <div><a href="/post/${post.id}">查看</a></div>
    <#sep>
        <hr>
    </#sep>
</#list>
<hr>
<#if prevPageUrl??>
    <a href="${prevPageUrl}">上一页</a>
</#if>
<#if nextPageUrl??>
    <a href="${nextPageUrl}">下一页</a>
</#if>
</body>
</html>