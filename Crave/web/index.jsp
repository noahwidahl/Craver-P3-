<%@page contentType="text/html" pageEncoding="UTF-8"%> <!-- Setting the content type and character encoding -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Food Item!</title>
        <link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Link to an external CSS file for styling -->
    </head>
    <body>
        <h1>Oversigte over Restauranter</h1> <!-- Header for the page -->
        
        Today is: <%= new java.util.Date().toString()%> <!-- Displaying the current date -->
        
        <a href="foodsuppliersservlet">View the Food Suppliers</a> <!-- Link to view the food suppliers -->
    </body>
</html>
