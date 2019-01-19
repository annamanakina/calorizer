<%--
  Created by IntelliJ IDEA.
  User: OPERATOR
  Date: 09.01.2019
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Daily diet</title>

</head>
<body>

<jsp:useBean id="products" scope="request" class="com.calorizer.db.ProductDAO"/>
<jsp:useBean id="notes" scope="application" class="com.calorizer.db.NoteDAO"/>
<jsp:useBean id="now" class="java.util.Date"/>

<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" var="theFormattedDate"/> <br/>

<form action="getNotesByDate" method="get">
<p >Сегодня:</p>
<input type="date" id="todayDate" name="selectedDate"
       value="${empty selectedDate ? theFormattedDate : selectedDate}">
<input type="submit" name="button_show_byDate" value="Отобразить"> <br/>
</form>

<form name="AddProduct" action="product" method="post">

    <p>Product: </p>
    <input id="productInput" name="productUserInput" type="text" list="datalist"/>
    <datalist name="listItem" id="datalist">
        <c:forEach var="productItem" items="${products.all}">
            <option name="prodOpt" value="${productItem.title}" id="${productItem.id}">
            </option>
        </c:forEach>
    </datalist>
    <p>Weight: </p>
    <input id="weightInput" name="weightUserInput" type="number">
    <input type="submit" name="button_add_product" value="Добавить">
    <input type="reset" value="Сбросить">
</form>

<table id="test" border="1" style="border-collapse: collapse;">
    <tr>
        <th>№</th>
        <th>Продукт</th>
        <th>Вес, г</th>
        <th>Белки, гр</th>
        <th>Жиры, гр</th>
        <th>Углеводы, гр</th>
        <th>Калории, ккал</th>
    </tr>

    <c:forEach var="item" items="${ empty selectedDate ? notes.getByDate(theFormattedDate) : notes.getByDate(selectedDate)}" varStatus="status">
        <tr>
            <td><c:out value="${status.count }"/></td>
            <td><c:out value="${item.product.title }"/></td>
            <td><c:out value="${item.weight}"/></td>
            <td><c:out value="${item.proteinsPerWeight}"/></td>
            <td><c:out value="${item.fatsPerWeight}"/></td>
            <td><c:out value="${item.carbohydratesPerWeight}"/></td>
            <td><c:out value="${item.caloriesPerWeight}"/></td>
            <td>
                <form action="deletenote" method="post">
                    <input type="hidden" name="delete_button_id2" value="${notes.getById(item.id).id}">
                    <input type="submit" name="delete_button" value="Удалить" style="vertical-align: center"/>
                </form>
            </td>
        </tr>
    </c:forEach>

    <tr>
        <th></th>
        <th>Итого:</th>
        <th><c:out value="${productWeight}"/></th>
        <th><c:out value="${proteinsSum}"/></th>
        <th><c:out value="${fatsSum}"/></th>
        <th><c:out value="${carbohydratesSum}"/></th>
        <th><c:out value="${caloriesSum}"/></th>

    </tr>

</table>
</body>
</html>