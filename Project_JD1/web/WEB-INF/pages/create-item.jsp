<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 04/03/19
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Item Adding</title>
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css">

</head>
<body>
<%@include file="header.html" %>
<c:if test="${not empty messages['unique']}">
    <div class="alert alert-danger">
        <c:out value="${messages['unique']}"/>
    </div>
</c:if>
<div class="login-page">
    <div class="form">
        <form class="login-form" method="post"
              action="${pageContext.request.contextPath}/dispatcher?command=add_item">
            <c:if test="${not empty messages['name']}">
                <div class="alert alert-danger">
                    <c:out value="${messages['name']}"/>
                </div>
            </c:if>
            <input type="text" name="name" placeholder="Item name" required/>
            <c:if test="${not empty messages['description']}">
                <div class="alert alert-danger">
                    <c:out value="${messages['description']}"/>
                </div>
            </c:if>
            <input type="text" name="description" placeholder="Item description" required/>
            <c:if test="${not empty messages['uniqueNumber']}">
                <div class="alert alert-danger">
                    <c:out value="${messages['uniqueNumber']}"/>
                </div>
            </c:if>
            <input type="text" name="uniqueNumber" placeholder="Item unique number" required/>
            <c:if test="${not empty messages['price']}">
                <div class="alert alert-danger">
                    <c:out value="${messages['price']}"/>
                </div>
            </c:if>
            <input type="text" name="price" placeholder="Item price: 00.00" required/>
            <button>Create</button>
        </form>
    </div>
</div>
</body>
</html>
