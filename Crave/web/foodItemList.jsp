<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Food Items List</title>
        <link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Link to CSS file -->
    </head>
    <body>
        <h1>Food Items</h1>
        <% 
            String supplierId = request.getParameter("supplierId");
            if (supplierId == null || supplierId.trim().isEmpty()) {
                supplierId = ""; // Default value if supplierId is not present
            }
        %>
        
        <%= request.getAttribute("table") %> <!-- Display the HTML table -->

        <a href="add<%= !supplierId.isEmpty() ? "?supplierId=" + supplierId : "" %>">Add a new Food Item</a>
        
          <!-- Back button -->
    <button onclick="history.back()">Back</button> 
    </body>
</html>
