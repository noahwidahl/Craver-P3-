<%@page contentType="text/html" pageEncoding="UTF-8"%> <!-- Setting the content type and character encoding -->
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add a new Food item</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Link to an external CSS file for styling -->
</head>
<body>
    <h1>Add A new Food item!</h1>

    <!-- Form for adding a new food item -->
    <form name="addForm" action="addFoodItem" method="POST">
        <!-- Input field for FoodItemName -->
        <label>FoodItemName</label>
        <input type="text" name="FoodItemName" value="" />
        <br>
        <!-- Input field for Price -->
        <label>Price</label>
        <input type="number" name="Price" value="" /><br>

        <!-- Hidden field for FoodsupplierID -->
        <% 
            // Retrieving the FoodsupplierID from the request parameter
            String supplierId = request.getParameter("supplierId");
            // Setting supplierId to an empty string if it's not present or empty
            if (supplierId == null || supplierId.trim().isEmpty()) {
                supplierId = ""; 
            }
        %>
        <input type="hidden" name="FoodsupplierID" value="<%= supplierId %>" />

        <br>
        <!-- Submit button for the form -->
        <input type="submit" name="submit" value="Submit"/>
    </form>         

    <!-- Back button to return to the previous page -->
    <button onclick="history.back()">Back</button> 
</body>
</html>
