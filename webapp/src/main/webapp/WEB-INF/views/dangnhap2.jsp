<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
        <link href="css/loginPage.css" rel="stylesheet">
    </head>

    <body>


        <div class="bg-overlay">
            <form:form class="container" action="JWTLogin" method="POST" >
                <input type="text" name="username" placeholder="username">
                <a class="validateNotice">${errorUsername}</a>
                <input type="password" name="password" placeholder="password">
                <a class="validateNotice">${errorPassword}</a>
                <input type="submit" value="Login">
            </form:form>          
        </div>
                
    </body>

</html>