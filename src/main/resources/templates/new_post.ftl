<#include "layout.ftl" />

<#macro head>
    <@head_base />
    <link rel="stylesheet" href="/css/simplemde.min.css">
    <script src="/js/simplemde.min.js"></script>
    <title>发布新文章 - VeriPress X</title>
</#macro>

<#macro main>
    <article class="article">
        <h2 class="article-title">发布新文章</h2>
        <div class="content">
            <form action="/post/new" method="post" style="width: 100%;">
                <input type="text" name="title" placeholder="标题">
                <br>
                <textarea id="new-post-content-area" name="content" placeholder="你想分享什么内容？"></textarea>
                <script>
                    var simplemde = new SimpleMDE({
                        element: document.getElementById("new-post-content-area"),
                        forceSync: true,
                        spellChecker: false
                    });
                </script>
                <br>
                <input type="submit" value="发布">

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