<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                <form role="form" method="post" action="/sell/seller/product/save">
                    <div class="form-group">
                        <label for="exampleInput1">商品名称</label>
                        <input type="text" class="form-control"  name="ProductName" value="${(productInfo.getProductName())!''}"/>
                    </div>
                    <div class="form-group">
                        <label for="exampleInput1">价格</label>
                        <input type="text" class="form-control" name="ProductPrice" value="${(productInfo.getProductPrice())!''}" />
                    </div>
                    <div class="form-group">
                        <label for="exampleInput1">库存</label>
                        <input type="text" class="form-control" name="ProductStock" value="${(productInfo.getProductStock())!''}"/>
                    </div>
                    <div class="form-group">
                        <label for="exampleInput1">描述</label>
                        <input type="text" class="form-control"  name="ProductDescription" value="${(productInfo.getProductDescription())!''}"/>
                    </div>
                    <div class="form-group">
                        <label for="exampleInput1">图片</label>
                        <img src="${(productInfo.getProductIcon())!''}" height="100" width="100" <#if !productInfo??>hidden

                        </#if>>
                        <input type="text" class="form-control" name="ProductIcon" value="${(productInfo.getProductIcon())!''}"/>
                    </div>
                    <div class="form-group">
                        <label for="exampleInput1">类目</label>
                        <select name="CategoryType">
                            <#list productCategoryList as category>
                                <option value="${category.getCategoryType()}"
                                    <#if (productInfo??)&&(productInfo.getCategoryType()==category.getCategoryType())>
                                        selected="true"
                                    </#if>
                                >
                                    ${category.getCategoryName()}
                                </option>
                            </#list>
                        </select>
                    </div>
                    <input  hidden type="text" name="ProductId" value="${(productInfo.getProductId())!''}"/>
                    <button type="submit" class="btn btn-default">提交</button>
                </form>

                </div>
            </div>
        </div>
    </div>



</div>
</body>
</html>