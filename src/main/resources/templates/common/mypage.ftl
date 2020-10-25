<div class="mydiv" id="mypage">
    <ul class="pagination" style="float:right">
        <#if currentPage lte 1>
            <li class="disabled" ><a href="#">上一页</a></li>
        <#else >
            <li><a href="${url}?size=${size}&page=${currentPage-1}" id="front-page">上一页</a></li>
        </#if>
        <#--                    判断是否过长 超过10页算过长-->
        <#if totalPages lte 10>
            <#list 1..totalPages as index>
                <#if currentPage==index>
                    <li  class="disabled"><a href="#" ><u>${index}</u></a></li>
                <#else>
                    <li  ><a href="${url}?size=${size}&page=${index}">${index}</a></li>
                </#if>
            </#list>
        <#else >
        <#--                   超过10页就要开始判定，如果-->
            <#assign x=1 y=1>
            <#list 1..totalPages as index>
                <#if ((currentPage-1) lte index && index lte (currentPage+1) )||index ==1 ||index==totalPages>
                    <#if currentPage==index>
                        <li class="disabled"><a href="#" ><u>${index}</u></a></li>
                    <#else>
                        <li  ><a href="${url}?size=${size}&page=${index}">${index}</a></li>
                    </#if>
                <#else >
                    <#if x==1 && y==1>
                        <#if currentPage gte 4>
                            <li class="disabled"><a href="#">..</a></li>
                            <#assign x=0>
                        <#else >
                            <#if (currentPage lte totalPages-3) &&(index==totalPages-1)>
                                <li class="disabled"><a href="#">..</a></li>
                                <#assign y=0>
                            </#if>
                        </#if>

                    <#else >
                        <#if x==0 &&y==1>
                            <#if (currentPage lte totalPages-3) &&(index==totalPages-1)>
                                <li class="disabled"><a href="#">..</a></li>
                                <#assign y=0>
                            </#if>

                        </#if>

                    </#if>
                </#if>
            </#list>
        </#if>


        <#if currentPage gte totalPages>
            <li class="disabled"><a href="#">下一页</a></li>
        <#else >
            <li><a href="${url}?size=${size}&page=${currentPage+1}" id="next-page">下一页</a></li>
        </#if>

    </ul>
</div>

<#--<script>-->
<#--&lt;#&ndash;    这里还是不要在js里写了  因为有可能没有<a id="front-page">等 &ndash;&gt;-->
<#--    &lt;#&ndash;let front_page_url="${url}?size=${size}&page=${currentPage-1}";&ndash;&gt;-->
<#--    &lt;#&ndash;let next_page_url="${url}?size=${size}&page=${currentPage+1}";&ndash;&gt;-->
<#--    &lt;#&ndash;document.getElementById("next-page").setAttribute("href",next_page_url);&ndash;&gt;-->
<#--    &lt;#&ndash;document.getElementById("front-page").setAttribute("href",front_page_url);&ndash;&gt;-->


<#--</script>-->
<style type="text/css">
    .mydiv{
        position: absolute;
        left: 700px;
        bottom: 100px;
        margin:0 auto;
        background:rgba(100,100,100, 0.8)

    }
</style>
<#--<script type="text/javascript">-->
<#--    window.onscroll= window.onresize = window.onload = function (){-->
<#--        var getDiv = document.getElementById('mypage');-->
<#--        var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;-->

<#--        getDiv.style.left= document.documentElement.clientWidth - getDiv.offsetWidth+'px';-->
<#--        getDiv.style.top = document.documentElement.clientHeight-getDiv.offsetHeight +scrollTop +'px';-->
<#--    }-->

<#--</script>-->