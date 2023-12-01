<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Food Suppliers</title>
        <link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Link to CSS file -->
    </head>
    <body>
        <h1>Food Suppliers</h1>
        <% String table = (String) request.getAttribute("table"); %>
        <%= table %>
        
        <a href="read">View the FoodItems</a><br/>
        
          <!-- Back button -->
    <button onclick="history.back()">Back</button> 
    </body>
</html>
