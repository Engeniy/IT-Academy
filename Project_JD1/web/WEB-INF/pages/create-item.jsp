<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 04/03/19
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Item Adding</title>
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="header.html" %>
<div class="login-page">
    <div class="form">
        <form class="login-form" method="post"
              action="${pageContext.request.contextPath}/dispatcher?command=add_item">
            <input type="text" name="name" placeholder="Item name" required/>
            <input type="text" name="description" placeholder="Item description" required/>
            <input type="text" name="uniqueNumber" placeholder="Item unique number" required/>
            <input type="text" name="price" placeholder="Item price" required/>
            <button>Create</button>
        </form>
    </div>
</div>
</body>
</html>
