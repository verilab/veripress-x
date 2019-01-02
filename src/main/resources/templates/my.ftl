<#include "layout.ftl" />

<#macro head>
    <@head_base />
    <title>我的 - VeriPress X</title>
</#macro>

<#macro main>
    <article class="article">
        <h2 class="article-title">我的</h2>
        <div class="content">
            <h2>修改信息</h2>
            <form action="/my/edit" method="post" style="width: 240px;">
                <input type="text" name="nickname" placeholder="新昵称">
                <br>
                <input type="password" name="password" placeholder="新密码">
                <br>
                <input type="submit" value="修改">

                <#if errors??>
                    <div class="errors">
                        <#list errors as error>
                            <span>${error}</span>
                            <#sep><br></#sep>
                        </#list>
                    </div>
                </#if>
            </form>

            <h2>管理文章</h2>
            <table>
                <tr>
                    <th>标题</th>
                    <th>操作</th>
                </tr>
                <#list posts as post>
                    <tr>
                        <td><a href="/post/${post.id}">${post.title}</a></td>
                        <td><a href="/post/${post.id}/delete">删除</a></td>
                    </tr>
                </#list>
            </table>
        </div>
    </article>
</#macro>

<@generate_page />