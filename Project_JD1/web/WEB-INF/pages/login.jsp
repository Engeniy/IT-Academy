<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 03/03/19
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/itempage.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:if test="${not empty error}">
    <div class="alert alert-danger">
        <c:out value="${error}"/>
    </div>
</c:if>
<div class="login-page">
    <div class="form">
        <form class="login-form" method="post" action="${pageContext.request.contextPath}/dispatcher?command=login">
            <input type="text" name="email" placeholder="E-mail" required/>
            <input type="password" name="password" placeholder="Password" required/>
            <button>login</button>
            <p class="message">Not registered?</p>
            <button form="register">Create an account</button>
        </form>
        <form class="login-page" id="register" method="post"
              action="${pageContext.request.contextPath}/dispatcher?command=registration_redirect&state=new">
        </form>
    </div>
</div>
</body>
</html>
