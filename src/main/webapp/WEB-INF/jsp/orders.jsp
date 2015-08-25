<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Orders List</title>
</head>
<body>

<h1>List of orders</h1>

  <c:forEach var="order" items="${orders}">

  <table border="1">
    <thead><tr>
      <th>ID</th>
      <th>Customer</th>
      <th>Total Price</th>
    </tr></thead>
    <tr>
      <td>${order.id}</td>
      <td>${order.customer.name}</td>
      <td>${order.totalCost}</td>

      <table border="1">
        <thead><tr>
          <th>Pizza</th>
          <th>Price</th>
          <th>Quantity</th>
        </tr></thead>

        <c:forEach var="entry" items="${order.pizzas}">
          <tr>
            <td>${entry.key.name}</td>
            <td>${entry.key.price}</td>
            <td>${entry.value}</td>
          </tr>


        </c:forEach>

      </table>
      <br>

    </tr>
  </c:forEach>
</table>

<a href="/PizzaApp/">back</a>

<c:url var="logoutUrl" value="/logout"/>
<form method="post" action="${logoutUrl}">
  <input type="submit" value="Log out" />
  <input type="hidden"
         name="${_csrf.parameterName}"
         value="${_csrf.token}"/>
</form>



</body>
</html>
