<#include "layout.ftl" />

<#macro head>
    <@head_base />
    <title>注册 - VeriPress X</title>
</#macro>

<#macro main>
    <article class="article">
        <h2 class="article-title">注册</h2>
        <div class="content">
            <form action="/register" method="post" style="width: 240px;">
                <input type="text" name="username" placeholder="用户名">
                <br>
                <input type="text" name="nickname" placeholder="昵称">
                <br>
                <input type="password" name="password" placeholder="密码">
                <br>
                <input type="submit" value="注册">

                <#if errors??>
                    <div class="errors">
                        <#list errors as error>
                            <span>${error}</span>
                            <#sep><br></#sep>
                        </#list>
                    </div>
                </#if>
            </form>
        </div>
    </article>
</#macro>

<@generate_page />