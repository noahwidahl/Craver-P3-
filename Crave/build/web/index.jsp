<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Food Item!</title>
        <link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Link to CSS file -->
    </head>
    <body>
        <h1>FoodItem!</h1>
        Welcome
        Today is: <%= new java.util.Date().toString()%>
        
        <a href="foodsuppliersservlet">View the Food Suppliers</a>
    </body>
</html>
