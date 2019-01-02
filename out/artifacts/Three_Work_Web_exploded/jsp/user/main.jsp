<%@ page import="com.three.beans.Book" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="com.three.beans.BookCategory" %><%--
  Created by IntelliJ IDEA.
  User: zk
  Date: 2019/1/1
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户端</title>
</head>
<body>
<%--===========================================================================================================--%>
<%--展示所有分类--%>

<h3>分类信息</h3>


<form>
        <%
                List<BookCategory> list_categoty = (List<BookCategory>) ActionContext.getContext().get("category_list");
                for(BookCategory category:list_categoty){
        %>
        分类：<%= category.getCategory()%>
        <%--<a href=""></a>--%>
        <%--<button value=""></button>--%>

        <%}
        %>
</form>



        <h3>分类查询
        </h3>
        <form action="/book/showBooksByCategory.action" method="post">
        类别：<input type="text" name="category.category"><br>
        <input type="submit" value="提交">
        </form>

        <h3>返回主页面
        </h3>
        <form action="/book/findAllBooks.action" method="post">

                <input type="submit" value="提交">
        </form>

<h3>0删除购物车里的书</h3>
<form action="/orderItem/deleteOrderItem.action" method="post">
        用户名（email）<input type="text" name="user.email"><br>
        书名：<input type="text" name="book.name"><br>
        <input type="submit" value="提交">
</form>


<h3>1查看所有订单信息</h3>
<form action="/order/findAllOrder.action" method="post">
        用户名（email）<input type="text" name="user.email"><br>
        <input type="submit" value="提交">
</form>


<h3>1查看购物车</h3>
        <form action="/order/findCarOrder.action" method="post">
                用户名（email）<input type="text" name="user.email"><br>
                <input type="submit" value="提交">
        </form>

<h3>1提交订单</h3>
<form action="/order/addOrder.action" method="post">
        用户名（email）<input type="text" name="user.email"><br>
        <input type="submit" value="提交">
</form>

<h3>1买书</h3>
<form action="/orderItem/addOrderItem.action" method="post">
        用户名（email）<input type="text" name="user.email"><br>
        书名：<input type="text" name="book.name"><br>
        书数量：<input type="text" name="book.num"><br>
        <input type="submit" value="提交">
</form>



<h3>1添加手机还有地址信息</h3>
<form action="/order/addOrderInfo.action" method="post">
        用户名（email）<input type="text" name="user.email"><br>
        手机号：<input type="text" name="phone"><br>
        地址：<input type="text" name="address">
        <input type="submit" value="提交">
</form>
<%--==================================================================================================--%>

        <h3>模糊查找书籍</h3>
        <form action="/book/showBooksByPro.action" method="post">
                书名：<input type="text" name="book.name"><br>
                作者：<input type="text" name="book.author">
                <input type="submit" value="提交">
        </form>

        <%--展示所有书籍查看书籍--%>
        <form>
        <%
        List<Book> list = (List<Book>) ActionContext.getContext().get("list");
        for(Book book:list){
        %>
                <img src="/file/<%=book.getPic()%>">
                书名：<%=book.getName() %><br>
                作者：<%=book.getAuthor() %>
                价格：<%=book.getPrice() %>
                版本：<%=book.getVersion() %>

                <%--<button value=""></button>--%>

        <%}
        %>
        </form>


        <h3>改个人信息</h3>
        <form action="/user/updateInfo.action" method="post">
        姓名：<input type="text" name="user.username"><br>
        phone：<input type="text" name="user.phone"><br>
        email：<input type="text" name="user.email">

        <input type="submit" value="提交">
        </form>

        <h3>删除个人账号</h3>
        <form action="/user/deleteUser.action" method="post">
        email：<input type="text" name="user.email">
        密码：<input type="text" name="user.password">

        <input type="submit" value="提交">
        </form>




</body>
</html>
