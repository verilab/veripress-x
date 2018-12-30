<#include "layout.ftl" />

<#macro head>
    <@head_base />
    <title>${post.title} - VeriPress X</title>
</#macro>

<#macro main>
    <article class="article">
        <h2 class="article-title">${post.title}</h2>
        <#include "meta.ftl" />
        <div class="content">
            ${post.contentHtml}
        </div>
    </article>
</#macro>

<@generate_page />