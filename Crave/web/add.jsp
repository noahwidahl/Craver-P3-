<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add a new Food item</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Link to CSS file -->
</head>
<body>
    <h1>Add A new Food item!</h1>

    <form name="addForm" action="addFoodItem" method="POST">
        <label>FoodItemName</label>
        <input type="text" name="FoodItemName" value="" />
        <br>
        <label>Price</label>
        <input type="number" name="Price" value="" /><br>
        <!-- Hidden field for FoodsupplierID -->
        <% 
            String supplierId = request.getParameter("supplierId");
            if (supplierId == null || supplierId.trim().isEmpty()) {
                supplierId = ""; // Default value if supplierId is not present
            }
        %>
        <input type="hidden" name="FoodsupplierID" value="<%= supplierId %>" />

        <br>
        <input type="submit" name="submit" value="Submit"/>
    </form>         
          <!-- Back button -->
    <button onclick="history.back()">Back</button> 
</body>
</html>
