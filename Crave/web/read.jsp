<%-- 
    Document   : read
    Created on : 15. nov. 2023, 12.06.11
    Author     : sorennygaardjensen
    Description: This JSP page displays a list of food items in a table format and provides a link to add new food items.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%> <%-- Sets the content type and encoding for the page --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> <%-- Specifies the character encoding for the HTML document --%>
        <title>JSP Page</title> <%-- Title of the web page --%>
    </head>
    
    <%-- Retrieving the HTML table containing food items from the request attribute --%>
    <% String table = (String) request.getAttribute("table"); %>
    
    <body>
        <h1>FoodItem</h1> <%-- Main heading of the page --%>
        
        <%-- Displaying the HTML table --%>
        <%= table %>
        
        <%-- Link to add a new food item --%>
        <a href ="add">Add a new Food Item</a>
    </body>
</html>
