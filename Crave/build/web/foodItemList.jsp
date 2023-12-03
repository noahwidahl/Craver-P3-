<%@page contentType="text/html" pageEncoding="UTF-8"%> <!-- Setting the content type and character encoding -->
<!DOCTYPE html>
<html>
    <head>
        <title>Food Items List</title>
        <link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Link to an external CSS file for styling -->
    </head>
    <body>
        <h1>Food Items</h1> <!-- Header for the page -->
        <% 
            // Retrieving the supplierId parameter from the request
            String supplierId = request.getParameter("supplierId");
            // Setting supplierId to an empty string if it's not present or empty
            if (supplierId == null || supplierId.trim().isEmpty()) {
                supplierId = ""; 
            }
        %>
        
        <%= request.getAttribute("table") %> <!-- Displaying the HTML table with food items -->

        <!-- Link to add a new food item. If supplierId is present, it's appended to the query string -->
        <a href="add<%= !supplierId.isEmpty() ? "?supplierId=" + supplierId : "" %>">Add a new Food Item</a>
        
        <!-- Back button to return to the previous page -->
        <button onclick="history.back()">Back</button> 
    </body>
</html>
