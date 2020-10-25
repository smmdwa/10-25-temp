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
                                <th>订单id</th>
                                <th>订单总金额</th>
                                <th>订单状态</th>
                            </tr>
                            </thead>

                            <tbody>
                            <tr>
                                <td>${orderDTO.getOrderId()}</td>
                                <td>${orderDTO.getOrderAmount()}</td>
                                <td>${orderDTO.getOrderStatusEnum(orderDTO.getOrderStatus()).getMsg()}</td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>商品id</th>
                                <th>商品名称</th>
                                <th>价格</th>
                                <th>数量</th>
                                <th>总额</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list orderDetailList as detail>
                                <tr>
                                    <td>
                                        ${detail.getProductId()}
                                    </td>
                                    <td>
                                        ${detail.getProductName()}
                                    </td>
                                    <td>
                                        ${detail.getProductPrice()}
                                    </td>
                                    <td>
                                        ${detail.getProductQuantity()}
                                    </td>
                                    <td>
                                        ${detail.getProductPrice()* detail.getProductQuantity()}
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
                <#if orderDTO.getOrderStatusEnum(orderDTO.getOrderStatus()).getMsg()=="新订单">
                    <a type="button" class="btn btn-default btn-primary"  href="/sell/seller/order/finish?orderId=${orderDTO.getOrderId()}">完成订单</a>
                    <a type="button" class="btn btn-default btn-danger" href="/sell/seller/order/cancel?orderId=${orderDTO.getOrderId()}">取消订单</a>
                </#if>
                <button type="button" class="btn btn-default" ><a href="/sell/seller/order/lists">返回</a></button>


            </div>

        </div>
    </body>

</html>