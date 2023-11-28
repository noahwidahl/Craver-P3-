<%-- 
    Document   : read
    Created on : 15. nov. 2023, 12.06.11
    Author     : sorennygaardjensen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <% String table = (String) request.getAttribute("table"); %>
    
    <body>
        <h1>FoodItem</h1>
        
        <%= table %>
        
        <a href ="add">Add a new Food Item</a>
    </body>
</html>
