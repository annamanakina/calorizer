<%--
  Created by IntelliJ IDEA.
  User: OPERATOR
  Date: 15.01.2019
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>My settings</title>
   <style>
       input{
           display: inline-block;
           margin-bottom: 20px;
           margin-right: 5px;
           padding: 10px;
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

<h3> My settings</h3>


<form name="usersettings_form" action="usersettings" method="post">
    <div style="display: inline-block; border:1px solid blue; width: 40%; padding: 20px;  background-color: #ceffc9; ">
        <p id="sex"> Sex: </p>
        <input type="radio" name="gender" value="male" checked style="display: inline-block">
        <p>  Male </p>
        <input type="radio" name="gender" value="female"> <p> Female </p><br>
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
