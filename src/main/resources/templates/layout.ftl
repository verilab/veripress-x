<#macro head_base>
    <#include "head.html">
</#macro>

<#macro head>
    <@head_base />
    <title><#if title??>${title}<#else>VeriPress X</#if></title>
</#macro>

<#macro foot_base>
    <#include "foot.html">
</#macro>

<#macro foot><@foot_base /></#macro>

<#macro header>
    <#include "header.html">
</#macro>

<#macro footer>
    <#include "footer.html">
</#macro>

<#macro sidebar>
    <#include "sidebar.ftl">
</#macro>

<#macro main>

</#macro>

<#macro generate_page>
    <html lang="zh_CN">
    <head>
        <@head />
    </head>
    <body>
    <div class="container">
        <header class="row header">
            <@header />
        </header>

        <div class="row body">
            <div class="row page">
                <main class="column main">
                    <@main />
                </main>

                <aside class="column sidebar">
                    <@sidebar />
                </aside>
            </div>
        </div>

        <footer class="row footer">
            <@footer />
        </footer>
    </div>

    <@foot />
    </body>
    </html>
</#macro>