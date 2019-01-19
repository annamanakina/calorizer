<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: OPERATOR
  Date: 08.01.2019
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Category</title>
</head>
<body>

<table style="width:50%; margin-left: auto; margin-right: auto;  border: 3px solid rebeccapurple;">

    <caption> product list </caption>
    <thead >
    <tr>
        <th>Продукт</th>
        <th>Белки</th>
        <th>Жиры</th>
        <th>Углеводы</th>
        <th>Калории</th>
    </tr>
    </thead>

    <c:forEach var="list" items="${requestScope.productList}">
        <tr >
            <td> <c:out value="${list.title}"/> </td>
            <td> <c:out value="${list.proteins}"/> </td>
            <td> <c:out value="${list.fats}"/> </td>
            <td> <c:out value="${list.carbohydrates}"/> </td>
            <td> <c:out value="${list.calories}"/> </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
