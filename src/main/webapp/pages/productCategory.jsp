<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: OPERATOR
  Date: 29.01.2019
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <style>
        body{
            background-color: #f0ffee;
        }
    </style>
</head>
<body>

<jsp:include page="/pages/views/header.jsp"></jsp:include>
<jsp:include page="/pages/views/menu.jsp"></jsp:include>

<h3>Таблица калорийности</h3>
<c:forEach var="categoryItem" items="${categoryList}">
    <li>
        <a href="${pageContext.request.contextPath}/productList?categoryItemId=${categoryItem.id}">
            <c:out value="${categoryItem.title}"/> </a>
    </li>
</c:forEach>
</body>
</html>
