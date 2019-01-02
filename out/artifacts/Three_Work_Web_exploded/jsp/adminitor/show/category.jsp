<%@ page import="com.three.beans.BookCategory" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="java.util.List" %>
<%@ page import="com.three.beans.Book" %><%--
  Created by IntelliJ IDEA.
  User: zk
  Date: 2019/1/1
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分类</title>
</head>
<body>

        <h3>分类信息</h3>
        <form>
        <%
        List<BookCategory> list_categoty = (List<BookCategory>) ActionContext.getContext().get("category_list");
        for(BookCategory category:list_categoty){
            %>
                分类：<%= category.getCategory()%>
            <%
                List<Book> bookList = category.getBooks();
                for(Book book:bookList){

        %>
                <img src="/file/<%=book.getPic()%>">
                书名：<%=book.getName() %><br>
                作者：<%=book.getAuthor() %>
                价格：<%=book.getPrice() %>
                版本：<%=book.getVersion() %>
        <%--<a href=""></a>--%>
        <%--<button value=""></button>--%>

        <%}}
        %>

</body>
</html>
