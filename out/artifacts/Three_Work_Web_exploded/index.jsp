<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: zk
  Date: 2018/12/24
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>注册</h3>





<form action="/user/registe.action" method="post">
    姓名：<input type="text" name="user.username"><br>
    密码：<input type="text" name="user.password">
    phone：<input type="text" name="user.phone"><br>
    email：<input type="text" name="user.email">
    <input type="submit" value="提交">
</form>


    <h3>登陆</h3>
    <form action="/user/login.action" method="post">

        email：<input type="text" name="user.email">
        密码：<input type="text" name="user.password">


        <input type="submit" value="提交">
    </form>

    <h3>展示所有书籍</h3>
    <form action="/book/findAllBooks.action" method="post">



        <input type="submit" value="提交">
    </form>



        <%--<h3>改密码</h3>--%>
        <%--<form action="/user/reFindPassw.action" method="post">--%>
            <%--email：<input type="text" name="user.email">--%>
            <%--密码：<input type="text" name="user.password">--%>
            <%--code：<input type="text" name="user.code">--%>
            <%--<input type="submit" value="提交">--%>
        <%--</form>--%>


<%--<h3>改个人信息</h3>--%>
<%--<form action="/user/updateInfo.action" method="post">--%>
    <%--姓名：<input type="text" name="user.username"><br>--%>
    <%--phone：<input type="text" name="user.phone"><br>--%>
    <%--email：<input type="text" name="user.email">--%>

    <%--<input type="submit" value="提交">--%>
<%--</form>--%>

<%--<h3>删除个人账号</h3>--%>
<%--<form action="/user/deleteUser.action" method="post">--%>
<%--email：<input type="text" name="user.email">--%>
<%--密码：<input type="text" name="user.password">--%>

<%--<input type="submit" value="提交">--%>
<%--</form>--%>


<%--<h3>添加图书信息</h3>--%>
<%--<form action="/book/addBook.action" method="post" enctype="multipart/form-data">--%>
    <%--书名：<input type="text" name="book.name"><br>--%>
    <%--作者：<input type="text" name="book.author">--%>
    <%--价格：<input type="text" name="book.price"><br>--%>
    <%--数量：<input type="text" name="book.num">--%>
    <%--分类：<input type="text" name="category.category">--%>
    <%--&lt;%&ndash;图片：<input type="file" value="选择上传的图片" name="file">&ndash;%&gt;--%>
    <%--图片：<s:file name="file" label="选择上传的图片"/>--%>
    <%--<input type="submit" value="提交">--%>
<%--</form>--%>


    <%--<h3>所有查找书籍</h3>--%>
    <%--<form action="/book/findAllBooks.action" method="post">--%>
    <%--书名：<input type="text" name="book.name"><br>--%>
    <%--<input type="submit" value="提交">--%>
    <%--</form>--%>

<%--<h3>精准查找书籍</h3>--%>
<%--<form action="/book/showOneBookInfo.action" method="post">--%>
    <%--书名：<input type="text" name="book.name"><br>--%>
    <%--<input type="submit" value="提交">--%>
<%--</form>--%>


<%--<h3>书名模糊查找书籍</h3>--%>
<%--<form action="/book/showBooksByProName.action" method="post">--%>
    <%--书名：<input type="text" name="book.name"><br>--%>
    <%--<input type="submit" value="提交">--%>
<%--</form>--%>



<%--<h3>模糊查找书籍</h3>--%>
<%--<form action="/book/showBooksByPro.action" method="post">--%>
    <%--书名：<input type="text" name="book.name"><br>--%>
    <%--作者：<input type="text" name="book.author">--%>
    <%--<input type="submit" value="提交">--%>
<%--</form>--%>



<%--<h3>删除书籍信息</h3>--%>
<%--<form action="/book/deleteBook.action" method="post">--%>
    <%--书名：<input type="text" name="book.name"><br>--%>
    <%--<input type="submit" value="提交">--%>
<%--</form>--%>


<%--<h3>分类查询--%>
<%--</h3>--%>
<%--<form action="/book/showBooksByCategory.action" method="post">--%>
     <%--类别：<input type="text" name="category.category"><br>--%>
    <%--<input type="submit" value="提交">--%>
<%--</form>--%>


<%--<h3>添加分类</h3>--%>
<%--<form action="/category/addCategory.action" method="post">--%>
    <%--类别：<input type="text" name="bookCategory.category"><br>--%>
    <%--<input type="submit" value="提交">--%>
<%--</form>--%>


<%--<h3>删除分类</h3>--%>
<%--<form action="/category/deleteCategory.action" method="post">--%>
    <%--类别：<input type="text" name="bookCategory.category"><br>--%>
    <%--<input type="submit" value="提交">--%>
<%--</form>--%>


<%--<h3>查看所有分类</h3>--%>
<%--<form action="/category/findAllCategory.action" method="post">--%>
    <%--<input type="submit" value="提交">--%>
<%--</form>--%>







</body>
</html>
