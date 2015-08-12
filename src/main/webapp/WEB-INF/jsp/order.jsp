<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Make new Order</title>
  </head>
  <body>
    <h1>Make New Order</h1>
    <form action="addToOrder" method="post">

        <select multiple name="pizza" size="10">

          <option selected value="SELECT">SELECT</option>
          <c:forEach var="item" items="${pizzas}">
            <option value="${item.id}">${item.name}</option>
          </c:forEach>

        </select>

        <input name="qnt" type="number" value="1"/>
      <br>

      <input type="submit" value="add" />
    </form>
  </body>
</html>
