<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 04/03/19
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Items</title>
    <link href="${pageContext.request.contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/itempage.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="header.html" %>
<ul class="menu-main">
    <li><a href="${pageContext.request.contextPath}/dispatcher?command=create_item">Create Item</a></li>
    <li><a href="${pageContext.request.contextPath}/dispatcher?command=upload">Upload Items</a></li>

</ul>
<c:if test="${not empty message}">
    <div class="alert alert-success">
        <c:out value="${message}"/>
    </div>
</c:if>
<c:if test="${not empty error}">
    <div class="alert alert-danger">
        <c:out value="${error}"/>
    </div>
</c:if>
<div class="grid">
    <c:forEach items="${items}" var="item">
        <div class="product">
            <form method="post" action="${pageContext.request.contextPath}/dispatcher?command=delete_item">
                <label>
                    <input name="item_id" value="${item.id}" hidden>
                </label>
                <p class="product-title">Name: <c:out value="${item.name}"/></p>
                <p class="product-desc">Description: <c:out value="${item.description}"/></p>
                <p class="product-desc">Unique number: <c:out value="${item.uniqueNumber}"/></p>
                <p class="product-price">Price: <c:out value="${item.price}"/></p>
                <button>Delete</button>
            </form>
        </div>
    </c:forEach>
</div>

<ul class="pagination, mypagination">
    <c:forEach begin="1" var="page" end="${pages}">
        <li class="page-item" style="margin: 0 20px; display: inline">
            <a class="page-link"
               href="${pageContext.request.contextPath}/dispatcher?command=items&page=${page}"><c:out
                    value="${page}"/></a>
        </li>
    </c:forEach>
</ul>

</body>
</html>