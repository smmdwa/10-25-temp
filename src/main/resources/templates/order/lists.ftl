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
                                        <th>订单Id</th>
                                        <th>姓名</th>
                                        <th>手机号</th>
                                        <th>地址</th>
                                        <th>金额</th>
                                        <th>订单状态</th>
        <#--                                <th>支付方式</th>-->
                                        <th>支付状态</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#list orderDTOPage.content as orderDTO>
                                    <tr>
                                            <td>${orderDTO.getOrderId()}</td>
                                            <td>${orderDTO.getBuyerName()}</td>
                                            <td>${orderDTO.getBuyerPhone()}</td>
                                            <td>${orderDTO.getBuyerAddress()}</td>
                                            <td>${orderDTO.getOrderAmount()}</td>
                                            <td>${orderDTO.getOrderStatusEnum(orderDTO.getOrderStatus()).getMsg()}</td>
                                            <td>${orderDTO.getPayStatusEnum(orderDTO.getPayStatus()).getMsg()}</td>
                                            <td><a href="/sell/seller/order/details?orderId=${orderDTO.getOrderId()}">详情</a></td>
                                            <#if orderDTO.getOrderStatusEnum(orderDTO.getOrderStatus()).getMsg() =="新订单">
                                                <td><a href="/sell/seller/order/cancel?orderId=${orderDTO.getOrderId()}">取消</a></td>
                                            </#if>

                                    </tr>
                                    </#list>
                                    </tbody>
                                </table>


                            </div>
                        </div>
                    </div>

<#--            分页-->
            <#assign url="/sell/seller/order/lists" totalPages=orderDTOPage.getTotalPages() >

            <#include "../common/mypage.ftl">


        </div>


    </body>
</html>

<div class="modal fade" id="mymodal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body" id="new_order">
                您的新订单已经来了!
            </div>
            <div class="modal-footer">
                <button type="button" onclick="location.reload()" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>

    </div>

</div>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.10.0/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    var webSocket=null;
    if('WebSocket' in window){
        webSocket=new WebSocket('ws://localhost:8088/sell/webSocket');
    }else {
        alert('该浏览器不支持websocket!')
    }
    webSocket.onopen=function (event) {
        console.log('建立连接')
    }
    webSocket.onclose=function (event) {
        console.log('中断连接')
    }
    webSocket.onmessage=function (event) {
        console.log("收到消息："+event.data);
        $('#mymodal').modal('show');
        document.getElementById('new_order').innerHTML=event.data;
    }


</script>