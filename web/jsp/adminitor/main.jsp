<%@ page import="com.three.beans.BookCategory" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: zk
  Date: 2019/1/1
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员端</title>
</head>
<body>

    <h3>查看所有分类信息</h3>
    <form action="/category/admin_findAllCategory.action" method="post">
    <input type="submit" value="提交">
    </form>

<%--<h3>分类查询--%>
<%--</h3>--%>
<%--<form action="/book/admin_showBooksByCategory.action" method="post">--%>
    <%--类别：<input type="text" name="category.category"><br>--%>
    <%--<input type="submit" value="提交">--%>
<%--</form>--%>



    <h3>添加分类</h3>
    <form action="/category/addCategory.action" method="post">
    类别：<input type="text" name="bookCategory.category"><br>
    <input type="submit" value="提交">
    </form>


    <h3>删除分类</h3>
    <form action="/category/deleteCategory.action" method="post">
    类别：<input type="text" name="bookCategory.category"><br>
    <input type="submit" value="提交">
    </form>




    <h3>添加图书信息</h3>
    <form action="/book/addBook.action" method="post" enctype="multipart/form-data">
    书名：<input type="text" name="book.name"><br>
    作者：<input type="text" name="book.author">
    价格：<input type="text" name="book.price"><br>
    数量：<input type="text" name="book.num">
    分类：<input type="text" name="category.category">
    <%--图片：<input type="file" value="选择上传的图片" name="file">--%>
    图片：<s:file name="file" label="选择上传的图片"/>
    <input type="submit" value="提交">
    </form>

    <h3>删除书籍信息</h3>
    <form action="/book/deleteBook.action" method="post">
    书名：<input type="text" name="book.name"><br>
    <input type="submit" value="提交">
    </form>






</body>
</html>
