<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizzas List</title>
    </head>
    <body>
        <p>
            Hello, ${name}! <br>
            Your authorities: ${authorities}.
        </p>

        <table border="1">
            <thead><tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Price</th>
                </tr></thead>

            <c:forEach var="pizza" items="${pizzas}">
                <tr>
                    <td>${pizza.id}</td> 
                    <td>${pizza.name}</td> 
                    <td>${pizza.type}</td>
                    <td>${pizza.price}</td>
                    <td>
                        <form method="get" action="edit" >
                            <input type="hidden" name="pizzaid" value="${pizza.id}" />
                            <input type="submit" value="Edit" />
                        </form>
                        <form method="post" action="add" >
                            <input type="hidden" name="pizzaid" value="${pizza.id}" />
                            <input type="submit" value="Add" />
                        </form>

                    </td>
                </tr>            
            </c:forEach>
        </table>
        <a href="create"> Create new pizza </a> <br/>
        <form method="post" action="submitOrder">
            <input type="submit" value="Submit" />
        </form>

        <c:url var="logoutUrl" value="/logout"/>
        <form method="post" action="${logoutUrl}">
            <input type="submit" value="Log out" />
            <input type="hidden"
                    name="${_csrf.parameterName}"
                    value="${_csrf.token}"/>
        </form>

    </body>
</html>
