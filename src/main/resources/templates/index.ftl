<#include "layout.ftl" />

<#macro main>
    <#list posts as post>
        <article class="article">
            <h2 class="article-title"><a href="/post/${post.id}">${post.title}</a></h2>
            <div class="content">
                ${post.contentHtml}
            </div>
        </article>
    <#else>
        <div class="article">
            <p>😶这里是空的。</p>
        </div>
    </#list>

    <div class="article pager-container">
        <#if prevPageUrl?? || nextPageUrl??>
            <ul class="pager">
                <#if prevPageUrl??>
                    <li class="previous">
                        <a href="${prevPageUrl}" class="button">上一页</a>
                    </li>
                </#if>
                <#if nextPageUrl??>
                    <li class="next">
                        <a href="${nextPageUrl}" class="button">下一页</a>
                    </li>
                </#if>
            </ul>
        </#if>
    </div>
</#macro>

<@generate_page />