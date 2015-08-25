<%--
  Created by IntelliJ IDEA.
  User: margarita
  Date: 12.08.15
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Your bucket</title>
</head>
<body>

<h1>Your bucket:</h1>
<table border="1">
  <thead><tr>
    <th>Name</th>
    <th>Type</th>
    <th>Price</th>
    <th>Quantity</th>
  </tr></thead>

  <p>Items:</p>
  <c:forEach var="pizza" items="${pizzasInOrder}">
    <tr>
      <td>${pizza.key.name}</td>
      <td>${pizza.key.type}</td>
      <td>${pizza.key.price}</td>
      <td>${pizza.value}</td>
      <td>
        <form method="post" action="/PizzaApp/jsp/pizza/removePizza" >
          <input type="hidden" name="pizzaid" value="${pizza.key.id}" />
          <input type="submit" value="-" />
          <input type="hidden"
                 name="${_csrf.parameterName}"
                 value="${_csrf.token}"/>
        </form>

      </td>
    </tr>
  </c:forEach>
</table>

<p>Total price is ${totalPrice}</p>
<br>
<form method="post" action="/PizzaApp/jsp/pizza/submitOrder/">
  <input type="submit" value="Submit" />
  <input type="hidden"
         name="${_csrf.parameterName}"
         value="${_csrf.token}"/>
</form>
<br>
<a href="/PizzaApp/jsp/pizza/"> back to menu</a>
</body>
</html>
