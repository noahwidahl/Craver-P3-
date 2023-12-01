<%@page import="model.FoodItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update a FoodItem</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Link to CSS file -->
</head>
<body>
    <h1>Update a FoodItem</h1>
    <% 
        FoodItem foodItem = (FoodItem) request.getAttribute("FoodItem");
        if (foodItem != null) {
    %>
    <form name="UpdateForm" action="UpdateFoodItem" method="POST">
        <input type="hidden" name="FoodItemID" value="<%= foodItem.getFoodItemID() %>" />
        <input type="hidden" name="FoodSupplierID" value="<%= foodItem.getFoodSupplierID() %>" />
        <table class="update">
            <tr>
                <td class="right">FoodItemName:</td>
                <td><input type="text" name="FoodItemName" value="<%= foodItem.getFoodItemName() %>" /></td>
            </tr>
            <tr>
                <td class="right">Price:</td>
                <td><input type="number" name="Price" step="0.01" value="<%= foodItem.getPrice() %>" /></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="reset" value="Clear" /></td>
                <td><input type="submit" value="Update" /></td>
            </tr>
        </table>
    </form>
    <% } else { %>
        <p>FoodItem not found or invalid ID provided.</p>
    <% } %>
    
      <!-- Back button -->
    <button onclick="history.back()">Back</button> 
</body>
</html>
