<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.three.beans.BookCategory" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="com.three.beans.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: zk
  Date: 2018/12/25
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Yes</h1>

<%--分类：${category.category}--%>


    <%--得到所有分类 名称--%>
<%--<%--%>
    <%--ArrayList<BookCategory> list = (ArrayList<BookCategory>) ActionContext.getContext().get("list");--%>
    <%--for(BookCategory bookCategory:list)--%>
        <%--System.out.println(bookCategory.getCategory());--%>
<%--%>--%>



<%--//查看书籍--%>
    <%--<%--%>
        <%--List<Book> list = (List<Book>) ActionContext.getContext().get("list");--%>
        <%--for(Book book:list){--%>
    <%--%>--%>
        <%--<img src="/file/<%=book.getPic()%>">--%>
        <%--书名：<%=book.getName() %>--%>
<%--<%}--%>
<%--%>--%>



</body>
</html>
