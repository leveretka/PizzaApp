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
        <title>New/Update pizza</title>
    </head>
    <body>
        <form action="addnew" method="post">
                   <input type="hidden" name="id" value="${pizza.id}"/>
            Name : <input type="text" name="name" value="${pizza.name}"/></br>
            Type : <input type="text" name="type" value="${pizza.type}"/></br>
            Price : <input type="text" name="price" value="${pizza.price}"/></br>
            <input type="submit" value="Create"/></br>
        </form>
    </body>
    
    
</html>