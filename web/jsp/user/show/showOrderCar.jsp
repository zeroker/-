<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="com.three.beans.Order" %>
<%@ page import="com.three.beans.OrderItem" %><%--
  Created by IntelliJ IDEA.
  User: zk
  Date: 2019/1/1
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
</head>
<body>
<%
           Order order = (Order) ActionContext.getContext().get("car_order");
           int sum = 0;
           %>
        联系方式：<%=order.getTelephone() %>
        地址：<%=order.getAddress() %>
        <%
            for(int i=0; i<order.getOrderItems().size(); i++){
                OrderItem orderItem = order.getOrderItems().get(i);
                if(orderItem.getStatus() == 1){
        %>
        <img src="/file/<%=orderItem.getBook().getPic()%>">
        书名：<%=orderItem.getBook().getName() %><br>
        作者：<%=orderItem.getBook().getAuthor() %>
        价格：<%=orderItem.getBook().getPrice() %>
        版本：<%=orderItem.getBook().getVersion() %>
        数量：<%=orderItem.getCount() %>

        <%--<button value=""></button>--%>

        <%
            sum += orderItem.getSubtotal();
            }}
        %>

        总价格：<%=sum %>

</body>
</html>
