<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 04/03/19
  Time: 09:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile</title>
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/itempage.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="header.html" %>
<c:if test="${not empty error}">
    <div class="alert alert-danger">
        <c:out value="${error}"/>
    </div>
</c:if>
<div class="login-page">
    <div class="form">
        <form class="login-form" method="post"
              action="${pageContext.request.contextPath}/dispatcher?command=profile_update">
            <p></p>
            <input type="text" name="name" value="${profile.user.name}" required/>
            <input type="text" name="surname" value="${profile.user.surname}" required/>
            <input type="text" name="address" value="${profile.address}" required/>
            <input type="text" name="telephone" value="${profile.telephone}" required/>
            <input type="email" name="email" value="${profile.user.email}" readonly required/>
            <input type="password" name="old-password" placeholder="Old Password"/>
            <input type="password" name="new-password" placeholder="New Password"/>
            <button>Update</button>
        </form>
    </div>
</div>
</body>
</html>
