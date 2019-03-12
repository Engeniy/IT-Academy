<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 04/03/19
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Upload</title>
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">
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
              enctype="multipart/form-data" action="${pageContext.request.contextPath}/upload-xml">
            <p class="message">Upload your items file.</p>
            <input type="file" name="file" required/>
            <button>Upload</button>
        </form>
        <form class="login-page" id="login" method="post"
              action="${pageContext.request.contextPath}/dispatcher?command=login&state=new">
        </form>
    </div>
</div>
</body>
</html>
