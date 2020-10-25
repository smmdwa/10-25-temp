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
                    <form role="form" method="post" action="/sell/seller/category/save">

                        <div class="form-group">
                            <label for="exampleInput1">类目名称</label>
                            <input type="text" class="form-control"  name="CategoryName" value="${(productCategory.getCategoryName())!''}"/>
                        </div>
                        <div class="form-group">
                            <label for="exampleInput1">type</label>
                            <input type="text" class="form-control"  name="CategoryType" value="${(productCategory.getCategoryType())!''}"/>
                        </div>

                        <input  hidden type="text" name="CategoryId" value="${(productCategory.getCategoryId())!''}"/>
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>