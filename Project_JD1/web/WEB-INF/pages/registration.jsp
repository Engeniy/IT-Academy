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
            <c:if test="${not empty messages['name']}">
                <div class="alert alert-danger">
                    <c:out value="${messages['name']}"/>
                </div>
            </c:if>
            <input type="text" name="name" placeholder="Name" maxlength="31" required/>
            <c:if test="${not empty messages['surname']}">
                <div class="alert alert-danger">
                    <c:out value="${messages['surname']}"/>
                </div>
            </c:if>
            <input type="text" name="surname" placeholder="Surname" maxlength="31" required/>
            <c:if test="${not empty messages['address']}">
                <div class="alert alert-danger">
                    <c:out value="${messages['address']}"/>
                </div>
            </c:if>
            <input type="text" name="address" placeholder="Address" maxlength="63" required/>
            <c:if test="${not empty messages['telephone']}">
                <div class="alert alert-danger">
                    <c:out value="${messages['telephone']}"/>
                </div>
            </c:if>
            <input type="text" name="telephone" placeholder="Telephone: +375291112233"  maxlength="13" required/>
            <c:if test="${not empty messages['email']}">
                <div class="alert alert-danger">
                    <c:out value="${messages['email']}"/>
                </div>
            </c:if>
            <input type="email" name="email" placeholder="E-mail" maxlength="31" required/>
            <c:if test="${not empty messages['password']}">
                <div class="alert alert-danger">
                    <c:out value="${messages['password']}"/>
                </div>
            </c:if>
            <input type="password" name="password" placeholder="Password" maxlength="15" required/>
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
