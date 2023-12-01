<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>All Food Items</title>
        <link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Link to CSS file -->
    </head>
    <body>
        <h1>All Food Items</h1>
        <% 
            String table = (String) request.getAttribute("table");
            String supplierId = (String) request.getAttribute("supplierId");
            System.out.println("Supplier ID in read.jsp: " + supplierId); // Debug line
        %>
        <%= table %>
        <a href="add<%= (supplierId != null && !supplierId.trim().isEmpty()) ? "?supplierId=" + supplierId : "" %>">Add a new Food Item</a>
          <!-- Back button -->
    <button onclick="history.back()">Back</button> 
    </body>
</html>
