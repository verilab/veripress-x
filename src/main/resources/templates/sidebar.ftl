<div class="sidebar-widgets">
    <div class="widget">
        <h4>
            <#if Session.user??>
                欢迎，${Session.user.nickname}
            <#else>
                导航
            </#if>
        </h4>
        <nav>
            <ul>
                <#include "nav.ftl">
            </ul>
        </nav>
    </div>
</div>