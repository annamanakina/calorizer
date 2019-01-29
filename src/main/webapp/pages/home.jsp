
<%--
  Created by IntelliJ IDEA.
  User: OPERATOR
  Date: 28.01.2019
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <style>

        body{
            background-color: #f0ffee;
        }
        input{
            display: inline-block;
            margin-bottom: 20px;
            margin-right: 5px;
            padding: 10px;
            width: 150px;
        }
        #calculate{
            height: 30px;
            text-align: center;
            line-height: 2.5;
            padding: 0 20px;
        }

        p {
            display: inline-block;
        }
        #sex {
            margin-right: 40px;
        }

        .indicator{
            display: block;
        }
        select{
            height: 30px;
        }

    </style>
</head>
<body>

<jsp:include page="/pages/views/header.jsp"></jsp:include>
<jsp:include page="/pages/views/menu.jsp"></jsp:include>
<jsp:useBean id="categoryList" scope="request" class="com.calorizer.db.ProductCategoryDAO"/>

<h3>Главная</h3>

<form name="calculateIndicators_form" action="calculate" method="post">
    <div style="display: inline-block; width: 40%; padding: 20px;  background-color: #f0ffee; ">
        <p id="sex"> Пол: </p>
        <input type="radio" name="gender" value="MALE" checked style="display: inline-block; width: 50px;">
        <p>  Ж </p>
        <input type="radio" name="gender" value="FEMALE" style="display: inline-block; width: 50px;"> <p> М </p><br>
        <p>  Возраст, лет: </p>
        <input type="number" name="age" style="height: 20px; width: 80px;"> <br>
        <p> Рост, см: </p>
        <input type="number" name="height" style="height: 20px; width: 80px;">
        <p> Вес, кг: </p>
        <input type="number" name="weight" style="height: 20px; width: 80px;"><br>
        <p id="lifestyle"> Уровень активности: </p>

        <select name="lifestyle" id="datalist">
            <option name="lifestyleOption" value="SEDENTARY">Sedentary lifestyle</option>
            <option name="lifestyleOption" value="MILD">Slightly active lifestyle</option>
            <option name="lifestyleOption" value="MODERATE">Moderately active lifestyle</option>
            <option name="lifestyleOption" value="HEAVY">Active lifestyle</option>
            <option name="lifestyleOption" value="EXTREME">Very active lifestyle</option>

        </select>

        <input id="calculate" type="submit" name="button" value="Calculate"/>

    </div>
     <div style="display: inline-block; width: 50%;  " >
        <fmt:setLocale value="en_US"/>
        <p class="indicator" name="mbi"> BMI (body mass index):
            <fmt:formatNumber maxFractionDigits = "2" value="${empty mbi ? \"\": mbi}" type="number"/>  </p>
        <p class="indicator" name="daily_intake"> Daily calorie intakes:
            <fmt:formatNumber maxFractionDigits = "1" value="${empty daily_intake ? \"\": daily_intake}" type="number"/> </p>
        <p class="indicator" name="bmr"> Basal metabolic rate:
            <fmt:formatNumber maxFractionDigits = "1" value="${empty bmr ? \"\" : bmr}" type="number"/>  </p>

    </div>

</form>

<div id="block_product_editing" style="display: inline-block; margin: 0 auto; ">
<h3>Добавить продукт в таблицу</h3>
<form name="addCategory" action="addProduct" method="post">

    <p>Выберите категорию продукта: </p>
    <input id="productCategoryInput" name="addProductCategoryInput" type="text" list="categoryList"/>
    <datalist name="listItem" id="categoryList">
        <c:forEach var="categoryItem" items="${categoryList.all}">
            <option name="categoryOption" value="${categoryItem.title}" id="${categoryItem.id}">
            </option>
        </c:forEach>
    </datalist>
    <br>
    <p>Выберите продукт: </p>
    <input id="productInput" name="addProductInput" type="text" placeholder="название продукта">
    <input class="add_input" id="proteinsInput" name="addProteinsInput" type="number" min="0" step="0.01" placeholder="белки, г">
    <input class="add_input" id="fatsInput" name="addFatsInput" type="number" min="0" step="0.01" placeholder="жиры, г">
    <input class="add_input" id="carbohydratesInput" type="number" name="addCarbohydratesInput" min="0" placeholder="углеводы, г"
           step="0.01">
    <input class="add_input" id="caloriesInput" name="addCaloriesInput" type="number" min="0" step="0.01" placeholder="калории, ккал"><br>
    <input type="submit" name="addNewProduct" value="Добавить">
    <p> ${resultMessage}</p>
</form>
</div>

</body>
</html>
