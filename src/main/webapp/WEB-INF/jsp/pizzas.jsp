<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizzas List</title>
    </head>
    <body>
        <p>
            Hello, ${name}! <br>
            Your authorities: ${authorities}.<br>
            Accumulative card ballance: ${ballance} <br>
            <a href="/PizzaApp/jsp/order/my"> Show my orders</a>
            <%--<form action="/PizzaApp/jsp/order/my" method="get">--%>
                <%--<input type="submit" value="Show my orders">--%>
            <%--</form>--%>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a href="/PizzaApp/jsp/order/"> Show all orders</a>
            </sec:authorize>

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
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <form method="get" action="edit" >
                            <input type="hidden" name="pizzaid" value="${pizza.id}" />
                            <input type="submit" value="Edit" />
                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>
                        </form>
                        </sec:authorize>
                        <form method="post" action="add" >
                            <input type="hidden" name="pizzaid" value="${pizza.id}" />
                            <input type="submit" value="Add" />
                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>
                        </form>

                    </td>
                </tr>            
            </c:forEach>
        </table>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <a href="create"> Create new pizza </a>
        </sec:authorize>
        <br/>
        <form method="get" action="previewOrder/">
            <input type="submit" value="Bucket" />
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
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
