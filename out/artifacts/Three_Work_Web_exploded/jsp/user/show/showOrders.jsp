<%@ page import="com.three.beans.Order" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="java.util.List" %>
<%@ page import="com.three.beans.Book" %>
<%@ page import="com.three.beans.OrderItem" %><%--
  Created by IntelliJ IDEA.
  User: zk
  Date: 2019/1/1
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>所有的订单信息</title>
</head>
<body>
    <%
        List<Order> list = (List<Order>) ActionContext.getContext().get("orders");
        for(int i=0; i<list.size(); i++){
            Order order = list.get(i);
    %>
    联系方式：<%=order.getTelephone() %>
    地址：<%=order.getAddress() %>
    总价格：<%=order.getTotal() %>
            <%
                for(OrderItem orderItem:order.getOrderItems()){
    %>
    <img src="/file/<%=orderItem.getBook().getPic()%>">
    书名：<%=orderItem.getBook().getName() %><br>
    作者：<%=orderItem.getBook().getAuthor() %>
    价格：<%=orderItem.getBook().getPrice() %>
    版本：<%=orderItem.getBook().getVersion() %>
    数量：<%=orderItem.getCount() %>


    <%--<button value=""></button>--%>

    <%}
        }
    %>
</body>
</html>
