<%--
  Created by IntelliJ IDEA.
  User: OPERATOR
  Date: 28.01.2019
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:url value="/resources/css/styles.css" var="cssURL"/>
    <link rel="stylesheet" type="text/css" href="${cssURL}">
</head>
<body>

<jsp:include page="/pages/views/header.jsp"></jsp:include>
<jsp:include page="/pages/views/menu.jsp"></jsp:include>

<p style="color: red;">${errorString}</p>

<form name="register_form" action="${pageContext.request.contextPath}/register" method="post"
      style="display: block; height: 400px; width: 50%;  margin:auto; border: 1px solid #7b9978; border-radius: 20px;
      box-sizing: border-box; background-color: #ceffc9; position: absolute;
        top: 40%;
        left: 50%;
        margin-right: -50%;
       transform: translate(-50%, -40%); ">

    <legend style="font-size: 20pt;">Register user:</legend>

    <input name="firstName" class="user_input" type="text" placeholder="First Name" style="height: 15%; width: 70%; margin-top: 10px;  border: 1px solid #4a8440;
        margin-left: 15px; margin-right: 5px;
        border-radius: 10px; font-size: 16pt">
    <input name="lastName" class="user_input" type="text" placeholder="Last Name" style="height: 15%; width: 70%; margin-top: 10px;  border: 1px solid #4a8440;
        margin-left: 15px; margin-right: 5px;
        border-radius: 10px; font-size: 16pt">
    <input name="userName" class="user_input" type="text" placeholder="Login" style="height: 15%; width: 70%; margin-top: 10px;  border: 1px solid #4a8440;
        margin-left: 15px; margin-right: 5px;
        border-radius: 10px; font-size: 16pt">
    <input name="password" class="user_input" type="password" placeholder="Password" style="height: 15%; width: 70%; margin-top: 10px;  border: 1px solid #4a8440;
        margin-left: 15px; margin-right: 5px;
        border-radius: 10px; font-size: 16pt">
    <input name="confirm_password" class="user_input" type="password" placeholder="Confirm password" style="height: 15%; width: 70%; margin-top: 10px;  border: 1px solid #4a8440;
        margin-left: 15px; margin-right: 5px;
        border-radius: 10px; font-size: 16pt">
    <input value="Registration" class="user_input" type="submit" style="text-align: center; width: 100px; border: 1px solid #ceffc9; border-radius: 7px; height: 10%; background-color: #526650;
        font-size: 12pt;
        color: #ceffc9;
    ">

</form>

</body>
</html>
