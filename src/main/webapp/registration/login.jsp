<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: OPERATOR
  Date: 07.01.2019
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Login</title>
    <c:url value="/resources/css/styles.css" var="cssURL"/>
    <link rel="stylesheet" type="text/css" href="${cssURL}">
</head>
<body>

<jsp:include page="/pages/views/header.jsp"></jsp:include>
<jsp:include page="/pages/views/menu.jsp"></jsp:include>


<h3>Login Page</h3>
<p style="color: red;">${errorString}</p>

<%-- profile --%>
<form name="login_form" action="${pageContext.request.contextPath}/login" method="post" accept-charset="UTF-8"
     style="display: block;
height: 400px;
width: 50%;  margin:auto; border: 1px solid #7b9978; border-radius: 20px; box-sizing: border-box;
background-color: #ceffc9;
position: absolute;
        top: 40%;
        left: 50%;
        margin-right: -50%;
       transform: translate(-50%, -40%); " >

    <fieldset style="width: 60%; height: 70%;
    border: 1px solid #4a8440; border-radius: 20px; position: absolute;
        top: 5%;
        left: 30%;
        margin-right: -30%;
       transform: translate(-20%, 10%);" >

        <legend style="font-size: 20pt;">Login:</legend>
        <p style="display: inline-flex; font-size: 16pt; width: 20%;"> Username: </p>


        <input name="userName" class="user_input" type="text" style="height: 20%; width: 70%; margin-top: 10px;  border: 1px solid #4a8440;
        margin-left: 15px; margin-right: 5px;
        border-radius: 10px; font-size: 16pt" value="${user.userName}">  <br>

        <p style="display: inline-flex; font-size: 14pt; width: 20%;"> Password: </p>

        <input name="password" class="user_input" type="password" value="${user.password}" style="height: 20%; width: 70%; border: 1px solid #4a8440;
        margin-left: 15px;
        margin-right: 5px; margin-top: 15px; margin-bottom: 15px; border-radius: 10px;"> <br>

        <button style="text-align: center; margin: 0 auto;
        display: block;
        width: 100px; border: 1px solid #ceffc9;
        border-radius: 7px; height: 15%; background-color: #526650;
        font-size: 12pt;
        color: #ceffc9;
    "> Log In </button> <br>
        Remember me:
        <input type="checkbox" name="rememberMe" value= "Y" />
    </fieldset>

</form>


<form action="${pageContext.request.contextPath}/cancelLogin" method="get">
    <input type="submit" value="Cancel" style="position: absolute;
  top: 400px; right: 35%;

        width: 100px; border: 1px solid #ceffc9;
        border-radius: 7px; height: 5%; background-color: #526650;
        font-size: 12pt;
        color: #ceffc9;
    ">

</form>

</body>
</html>
