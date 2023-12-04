<%@page contentType="text/html" pageEncoding="UTF-8"%> <!-- Setting the content type and character encoding -->
<!DOCTYPE html>
<html>
    <head>
        <title>All Food Items</title>
        <link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Link to an external CSS file for styling -->
    </head>
    <body>
        <h1>All Food Items</h1>
        <% 
            // Retrieving the HTML table and supplierId from the request attributes
            String table = (String) request.getAttribute("table");
            String supplierId = (String) request.getAttribute("supplierId");
            // Debug line to print the supplierId in the server logs
            System.out.println("Supplier ID in foodSuppliersFoodItems.jsp: " + supplierId);
        %>
        <%= table %> <!-- Displaying the HTML table with food items -->
        <!-- Link to add a new food item. If supplierId is present, it's appended to the query string -->
        <a href="add<%= (supplierId != null && !supplierId.trim().isEmpty()) ? "?supplierId=" + supplierId : "" %>">Add a new Food Item</a>
        
        <!-- Back button to return to the previous page -->
        <button onclick="history.back()">Back</button> 
    </body>
</html>
