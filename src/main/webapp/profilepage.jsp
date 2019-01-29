<%--
  Created by IntelliJ IDEA.
  User: OPERATOR
  Date: 07.01.2019
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>

<jsp:include page="/pages/views/header.jsp"></jsp:include>
<jsp:include page="/pages/views/menu.jsp"></jsp:include>

<H3 > <c:out  value="${empty user ? \"\" : user.userName}"></c:out>   </H3>

<form name="usersettings_form" action="usersettings" method="post">
    <div style="display: inline-block; border:1px solid blue; width: 40%; padding: 20px;  background-color: #ceffc9; ">
        <p id="sex"> Sex: </p>
        <input type="radio" name="gender" value="MALE" checked style="display: inline-block">
        <p>  Male </p>
        <input type="radio" name="gender" value="FEMALE"> <p> Female </p><br>
        <p>  Age, y.o.: </p>
        <input type="number" name="age" style="height: 30px; width: 80px;"> <br>
        <p> Height, cm: </p>
        <input type="number" name="height" style="height: 30px; width: 80px;">
        <p> Weight, kg: </p>
        <input type="number" name="weight" style="height: 30px; width: 80px;"><br>
        <p id="lifestyle"> Lifestyle: </p>

        <select name="lifestyle" id="datalist">
            <option name="lifestyleOption" value="SEDENTARY">Sedentary lifestyle</option>
            <option name="lifestyleOption" value="MILD">Slightly active lifestyle</option>
            <option name="lifestyleOption" value="MODERATE">Moderately active lifestyle</option>
            <option name="lifestyleOption" value="HEAVY">Active lifestyle</option>
            <option name="lifestyleOption" value="EXTREME">Very active lifestyle</option>

        </select>

        <input id="calculate" type="submit" name="button" value="Calculate"/>

    </div>
    <div style="display: inline-block; border:1px solid red; width: 50%;  background-color: aquamarine; " >
        <fmt:setLocale value="en_US"/>
        <p class="indicator" name="mbi"> BMI (body mass index):
            <fmt:formatNumber maxFractionDigits = "2" value="${mbi}" type="number"/>  </p>
        <p class="indicator" name="daily_intake"> Daily calorie intakes:
            <fmt:formatNumber maxFractionDigits = "1" value="${daily_intake}" type="number"/> </p>
        <p class="indicator" name="bmr"> Basal metabolic rate:
            <fmt:formatNumber maxFractionDigits = "1" value="${bmr}" type="number"/>  </p>

    </div>

</form>

</body>
</html>
