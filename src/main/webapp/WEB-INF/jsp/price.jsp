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
    <title>Ordered Successfully</title>
</head>
<body>

  <h1>Congratulations! Your order was saved successfully!</h1>
  <table border="1">
    <thead><tr>
      <th>Name</th>
      <th>Type</th>
      <th>Price</th>
      <th>Quantity</th>
    </tr></thead>

    <p>Items:</p>
    <c:forEach var="pizza" items="${pizzasToShow}">
      <tr>
        <td>${pizza.key.id}</td>
        <td>${pizza.key.type}</td>
        <td>${pizza.key.price}</td>
        <td>${pizza.value}</td>
      </tr>
    </c:forEach>
  </table>

  <p>Total price is ${totalPrice}</p>
</body>
</html>
