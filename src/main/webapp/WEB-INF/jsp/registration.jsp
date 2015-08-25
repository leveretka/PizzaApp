<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
    Document   : create
    Created on : Aug 10, 2015, 3:20:45 PM
    Author     : andrii
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Registration</title>
</head>
<body>
<h1>Registration form</h1>
<form action="register" method="post">
  Full name : <input type="text" name="fullname"/></br>
  login     : <input type="text" name="login"/></br>
  password  : <input type="password" name="pass"/></br>
  <input type="submit" value="Register"/></br>
    <sec:csrfInput/>
</form>
<a href="/PizzaApp/">back</a>
</body>


</html>
