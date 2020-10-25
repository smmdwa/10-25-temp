<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table">
                    <thead>
                    <tr>
                        <th>商品Id</th>
                        <th>名称</th>
                        <th>图片</th>
                        <th>单价</th>
                        <th>库存</th>
                        <th>商品描述</th>

                        <#--                                <th>支付方式</th>-->
                        <th>类目</th>
                        <th>上架信息</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list productInfoPage.content as productInfo>
                        <tr>
                            <td>${productInfo.getProductId()}</td>
                            <td>${productInfo.getProductName()}</td>
                            <td><img src="${productInfo.getProductIcon()}" height="100" width="100"></td>
                            <td>${productInfo.getProductPrice()}</td>
                            <td>${productInfo.getProductStock()}</td>
                            <td>${productInfo.getProductDescription()}</td>
                            <td>${productInfo.getCategoryType()}</td>
                            <#if productInfo.getProductStatus()==1>
                                <td>上架中</td>
                            <#else >
                                <td>已下架</td>
                            </#if>

                            <td><a href="/sell/seller/product/index?productId=${productInfo.getProductId()}">修改</a></td>
                            <#if productInfo.getProductStatus()==0>
                                <td><a href="/sell/seller/product/onsale?productId=${productInfo.getProductId()}">上架</a></td>
                            <#else >
                                <td><a href="/sell/seller/product/unsale?productId=${productInfo.getProductId()}">下架</a></td>
                            </#if>

                        </tr>
                    </#list>
                    </tbody>
                </table>


            </div>
        </div>
    </div>

<#--    分页-->
    <#assign url="/sell/seller/product/lists" totalPages=productInfoPage.getTotalPages() >

    <#include "../common/mypage.ftl">

</div>
</body>
</html>