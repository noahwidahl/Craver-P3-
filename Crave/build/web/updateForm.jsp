<%@page import="model.FoodItem"%> <!-- Importing the FoodItem model class -->
<%@page contentType="text/html" pageEncoding="UTF-8"%> <!-- Setting the content type and character encoding -->

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update a FoodItem</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Link to an external CSS file for styling -->
</head>
<body>
    <h1>Update a FoodItem</h1>
    <% 
        // Retrieving the FoodItem object passed from the servlet
        FoodItem foodItem = (FoodItem) request.getAttribute("FoodItem");
        // Checking if the FoodItem object is not null
        if (foodItem != null) {
    %>
    <!-- Form for updating a FoodItem -->
    <form name="UpdateForm" action="UpdateFoodItem" method="POST">
        <!-- Hidden fields to hold the FoodItemID and FoodSupplierID -->
        <input type="hidden" name="FoodItemID" value="<%= foodItem.getFoodItemID() %>" />
        <input type="hidden" name="FoodSupplierID" value="<%= foodItem.getFoodSupplierID() %>" />
        <table class="update">
            <tr>
                <td class="right">FoodItemName:</td>
                <!-- Input field for FoodItemName -->
                <td><input type="text" name="FoodItemName" value="<%= foodItem.getFoodItemName() %>" /></td>
            </tr>
            <tr>
                <td class="right">Price:</td>
                <!-- Input field for Price -->
                <td><input type="number" name="Price" step="0.01" value="<%= foodItem.getPrice() %>" /></td>
            </tr>
            <tr>
                <td></td>
                <!-- Buttons for clearing the form and submitting the update -->
                <td><input type="reset" value="Clear" /></td>
                <td><input type="submit" value="Update" /></td>
            </tr>
        </table>
    </form>
    <% } else { %>
        <!-- Message displayed if no FoodItem object is found -->
        <p>FoodItem not found or invalid ID provided.</p>
    <% } %>
    
    <!-- Back button to return to the previous page -->
    <button onclick="history.back()">Back</button> 
</body>
</html>
