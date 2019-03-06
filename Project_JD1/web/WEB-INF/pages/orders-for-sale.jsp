<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 04/03/19
  Time: 14:14
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
<form class="menu-main" method="post" action="${pageContext.request.contextPath}/dispatcher?command=choose_state">
    <select name="filter-state">
        <option value="ALL">ALL</option>
        <option value="NEW">NEW</option>
        <option value="REVIEWING">REVIEWING</option>
        <option value="IN_PROGRESS">IN_PROGRESS</option>
        <option value="DELIVERED">DELIVERED</option>
    </select>
    <button>Choose</button>
</form>
<c:if test="${not empty message}">
    <div class="alert alert-success notification">
        <c:out value="${message}"/>
    </div>
</c:if>
<c:if test="${not empty error}">
    <div class="alert alert-danger notification">
        <c:out value="${error}"/>
    </div>
</c:if>
<div class="grid-orders">
    <c:forEach items="${orders}" var="order">
        <div class="product" style="">
            <form method="post" action="${pageContext.request.contextPath}/dispatcher?command=update_state">
                <input name="id" value="${order.id}" hidden>
                <p class="product-title">Name: ${order.item.name}</p>
                <p class="product-desc">Unique number: ${order.item.uniqueNumber}</p>
                <p class="product-desc" type="date">Date: ${order.dateOfCreation}</p>
                <p class="product-desc" type="email">Email: ${order.user.email}</p>
                <p class="product-desc" type="text">Current state: ${order.state}</p>
                <p class="product-desc" type="text">Choose new state:
                <select name="state">
                    <option value="NEW">NEW</option>
                    <option value="REVIEWING">REVIEWING</option>
                    <option value="IN_PROGRESS">IN_PROGRESS</option>
                    <option value="DELIVERED">DELIVERED</option>
                    <option selected value="${order.state}">${order.state}</option>
                </select>
                </p>
                <p class="product-desc" type="text">Quantity: ${order.quantity}</p>
                <button>Update</button>
                <p type="number" class="product-price">Sum: ${order.sum}</p>
            </form>
        </div>
    </c:forEach>
</div>
<ul class="pagination, mypagination">
    <c:forEach begin="1" var="page" end="${pages}">
        <li class="page-item, mypagination">
            <a class="page-link"
               href="${pageContext.request.contextPath}/dispatcher?command=orders&page=${page}">${page}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>