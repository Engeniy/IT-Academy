<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 03/03/19
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Orders</title>
    <link href="${pageContext.request.contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/itempage.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="header.html" %>
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
<div class="grid-orders">
    <c:forEach items="${orders}" var="order">
        <div class="product">
            <form method="post">
                <p class="product-title">Name: <c:out value="${order.item.name}"/></p>
                <p class="product-desc">Unique number: <c:out value="${order.item.uniqueNumber}"/></p>
                <p class="product-desc" type="date">Date: <c:out value="${order.dateOfCreation}"/></p>
                <p class="product-desc" type="date">State: <c:out value="${order.state}"/></p>
                <p class="product-desc" type="date">Quantity: <c:out value="${order.quantity}"/></p>
                <p type="number" class="product-price">Sum: <c:out value="${order.sum}"/></p>
            </form>
        </div>
    </c:forEach>
</div>
<ul class="pagination, mypagination">
    <c:forEach begin="1" var="page" end="${pages}">
        <li class="page-item" style="margin: 20px 20px; display: inline">
            <a class="page-link"
               href="${pageContext.request.contextPath}/dispatcher?command=orders&page=${page}"><c:out
                    value="${page}"/></a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
