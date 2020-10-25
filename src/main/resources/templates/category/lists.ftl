<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
<#include "../common/nav.ftl">
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table">
                    <thead>
                    <tr>
                        <th>类目Id</th>
                        <th>类目名称</th>
                        <th>type</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list productCategoryList as category>
                            <tr>
                                <td>${category.getCategoryId()}</td>
                                <td>${category.getCategoryName()}</td>
                                <td>${category.getCategoryType()}</td>
                                <td><a href="/sell/seller/category/index?categoryId=${category.getCategoryId()}">修改</a></td>
                            </tr>
                    </#list>

                    </tbody>
                </table>
            </div>
        </div>
    </div>


</div>
</body>
</html>
