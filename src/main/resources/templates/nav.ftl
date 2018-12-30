<li><a href="/">首页</a></li>
<#if Session.user??>
    <li><a href="/post/new">发布</a></li>
    <li><a href="/my">我的</a></li>
    <li><a href="/logout">注销</a></li>
<#else>
    <li><a href="/login">登录</a></li>
    <li><a href="/register">注册</a></li>
</#if>