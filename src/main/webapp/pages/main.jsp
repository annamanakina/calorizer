<%--
  Created by IntelliJ IDEA.
  User: OPERATOR
  Date: 08.01.2019
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <meta content="width=device-width, initial-scale=1">
    <c:url value="/resources/css/main_page_style.css" var="cssURL"/>
    <link rel="stylesheet" type="text/css" href="${cssURL}">

    <title>Title</title>

    <script>
        $(function () {
            $("#tags").autocomplete({
                source: ${categoryList1.all}
            });
        });
    </script>
</head>
<body>

<jsp:include page="/pages/views/header.jsp"></jsp:include>
<jsp:include page="/pages/views/menu.jsp"></jsp:include>

<jsp:useBean id="categoryList1" scope="request" class="com.calorizer.db.ProductCategoryDAO"/>

<!-- <button class="tablink" onclick="openPage('Home', this, '#677f64')">Home</button>
<button class="tablink" onclick="openPage('Calorie_table', this, '#677f64')" id="defaultOpen">Calorie table</button>
<button class="tablink" onclick="openPage('MyProfile', this, '#677f64')">MyProfile</button>
<button class="tablink" onclick="openPage('About', this, '#677f64')">About</button> -->

<div id="Home" class="tabcontent" style=" text-align: center;">
    <div id="block_product_editing" style="display: inline-block; margin: 0 auto; ">
        <h3>Add product to the table</h3>
        <form name="addCategory" action="addProduct" method="post">

            <p>Выберите категорию продукта: </p>
            <input id="productCategoryInput" name="addProductCategoryInput" type="text" list="categoryList"/>
            <datalist name="listItem" id="categoryList">
                <c:forEach var="categoryItem" items="${categoryList1.all}">
                    <option name="categoryOption" value="${categoryItem.title}" id="${categoryItem.id}">
                    </option>
                </c:forEach>
            </datalist>

            <p>Выберите продукт: </p>
            <input id="productInput" name="addProductInput" type="text" placeholder="Enter product">
            <input class="add_input" id="proteinsInput" name="addProteinsInput" type="number" min="0" step="0.01">
            <input class="add_input" id="fatsInput" name="addFatsInput" type="number" min="0" step="0.01">
            <input class="add_input" id="carbohydratesInput" type="number" name="addCarbohydratesInput" min="0"
                   step="0.01">
            <input class="add_input" id="caloriesInput" name="addCaloriesInput" type="number" min="0" step="0.01">
            <input type="submit" name="addNewProduct" value="Add">
            <p> ${resultMessage}</p>
        </form>
    </div>
</div>

<div id="Calorie_table" class="tabcontent" style="margin: 0 auto; border: 1px solid mediumvioletred;">
    <div id="catalog" style="display: inline-block; border: 1px solid mediumvioletred;">
        <h3>Таблица калорийности</h3>
        <c:forEach var="categoryItem" items="${categoryList1.all}">
            <li>
                <a href="category?categoryItemId=${categoryItem.id}"> <c:out value="${categoryItem.title}"/> </a>
            </li>
        </c:forEach>
    </div>
</div>

<div id="MyProfile" class="tabcontent" >
    <!-- todo -->
    <h3>Личный кабинет</h3>
    <jsp:include page="diet.jsp"/>
</div>

<div id="About" class="tabcontent">
    <p name="error"> <c:out value="${empty error_message ? \"\" : error_message}"></c:out></p>
    <jsp:include page="usersettings.jsp"/>
</div>


<!-- jsp:include page="header.jsp" />-->
 <script>
    function openPage(pageName, elmnt, color) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].style.backgroundColor = "";
        }
        document.getElementById(pageName).style.display = "block";
        elmnt.style.backgroundColor = color;
    }

    // Get the element with id="defaultOpen" and click on it
    document.getElementById("defaultOpen").click();
</script>

</body>
</html>
