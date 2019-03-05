<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 03/03/19
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet" type="text/css">
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
        <form class="login-form" method="post"
              action="${pageContext.request.contextPath}/dispatcher?command=registration">
            <input type="text" name="name" placeholder="Name" required/>
            <input type="text" name="surname" placeholder="Surname" required/>
            <input type="text" name="address" placeholder="Address" required/>
            <input type="text" name="telephone" placeholder="Telephone" required/>
            <input type="email" name="email" placeholder="E-mail" required/>
            <input type="password" name="password" placeholder="Password" required/>
            <button>Register</button>
            <p class="message">Already registered?</p>
            <button form="login">Sign In</button>
        </form>
        <form class="login-page" id="login" method="post"
              action="${pageContext.request.contextPath}/dispatcher?command=login_redirect&state=new">
        </form>
    </div>
</div>
</body>
</html>
