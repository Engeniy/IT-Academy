<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 03/03/19
  Time: 14:48
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
</head>
<body>

<ul class="menu-main">
    <li><a href="">Work</a></li>
    <li><a href="">About</a></li>
    <li><a href="">Blog</a></li>
    <li><a href="">Contact</a></li>
</ul>

<div class="grid">
    <c:forEach items="${items}" var="item">
        <div class="product">
            <form>
                <p class="product-title">Name: ${item.name}</p>
                <p class="product-desc">Description: ${item.description}</p>
                <p class="product-desc">Unique number: ${item.uniqueNumber}</p>
                <p class="product-price">Price: ${item.price}</p>
                <%! public int count = 1; %>
                <c:set var="count" value="0"/>
                <button type="button" onclick="<c:set var="count" value="${count += 1}"/>" value="delete">-</button>
                <p><c:out value="${count}"/></p>
                <button type="button" onclick="<%count += 1;%>" value="add">+</button>

            </form>
        </div>
    </c:forEach>
</div>
</body>
</html>
