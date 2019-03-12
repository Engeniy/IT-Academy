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
        <c:choose>
            <c:when test="${empty currentState}">
                <option selected value="ALL">ALL</option>
            </c:when>
            <c:otherwise>
                <option value="ALL">ALL</option>
            </c:otherwise>
        </c:choose>
        <c:forEach items="${states}" var="state">
            <c:choose>
                <c:when test="${currentState == state}">
                    <option selected value="${state}"><c:out value="${state}"/></option>
                </c:when>
                <c:otherwise>
                    <option value="${state}"><c:out value="${state}"/></option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
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
                <p class="product-title">Name: <c:out value="${order.item.name}"/></p>
                <p class="product-desc">Unique number: <c:out value="${order.item.uniqueNumber}"/></p>
                <p class="product-desc" type="date">Date: <c:out value="${order.dateOfCreation}"/></p>
                <p class="product-desc" type="email">Email: <c:out value="${order.user.email}"/></p>
                <p class="product-desc" type="text">Current state: <c:out value="${order.state}"/></p>
                <p class="product-desc" type="text">Choose new state:
                    <select name="state">
                        <c:forEach items="${states}" var="state">
                            <c:choose>
                                <c:when test="${order.state == state}">
                                    <option selected value="${state}"><c:out value="${state}"/></option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${state}"><c:out value="${state}"/></option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </p>
                <p class="product-desc" type="text">Quantity: <c:out value="${order.quantity}"/></p>
                <button>Update</button>
                <p type="number" class="product-price">Sum: <c:out value="${order.sum}"/></p>
            </form>
        </div>
    </c:forEach>
</div>
<ul class="pagination, mypagination">
    <c:forEach begin="1" var="page" end="${pages}">
        <li class="page-item, mypagination">
            <a class="page-link"
               href="${pageContext.request.contextPath}/dispatcher?command=orders&page=${page}"><c:out
                    value="${page}"/></a>
        </li>
    </c:forEach>
</ul>
</body>
</html>