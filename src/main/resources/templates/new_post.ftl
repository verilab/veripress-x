<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>发布新文章</title>
</head>
<body>
<form action="/post/new" method="post">
    <div>
        <input type="text" name="title" placeholder="标题">
    </div>
    <div>
        <textarea name="content" cols="30" rows="10" placeholder="你想说些什么呢？"></textarea>
    </div>
    <div>
        <button type="submit">发布</button>
    </div>
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