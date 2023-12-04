<%@page contentType="text/html" pageEncoding="UTF-8"%> <!-- Setting the content type and character encoding -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Food Suppliers</title>
        <link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Link to an external CSS file for styling -->
    </head>
    <body>
        <h1>Food Suppliers</h1> <!-- Header for the page -->
        <% 
            // Retrieving the HTML table string passed from the servlet
            String table = (String) request.getAttribute("table"); 
        %>
        <%= table %> <!-- Displaying the HTML table with food suppliers -->

        <!-- Link to view the food items -->
        <a href="foodSuppliersFoodItems">View the FoodItems</a><br/>
        
        <!-- Back button to return to the previous page -->
        <button onclick="history.back()">Back</button> 
    </body>
</html>
