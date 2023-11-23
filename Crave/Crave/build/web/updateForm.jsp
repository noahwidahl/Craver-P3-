<%@page import="model.FoodItem"%>
<% FoodItem FoodItem = (FoodItem) request.getAttribute("FoodItem"); %> <!-- Fixed the attribute name to "FoodItem" -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update a FoodItem</title>
    </head>
    <body>
        <h1>Update a FoodItem</h1>
        
        <form name="UpdateForm" action="UpdateFoodItem" method="get"> <!-- Make sure the action matches your servlet URL -->
            <table class="update">
                <tr>
                    <td class="right">FoodItemID:</td>
                    <td><input type="text" name="FoodItemID" value="<%= FoodItem.getFoodItemID() %>" readonly /></td>
                    <!-- Added readonly attribute to prevent editing of ID -->
                </tr>
                <tr>
                    <td class="right">FoodItemName:</td>
                    <td><input type="text" name="FoodItemName" value="<%= FoodItem.getFoodItemName() %>" /></td>
                </tr>
                <tr>
                    <td class="right">Price:</td>
                    <td><input type="number" name="Price" value="<%= FoodItem.getPrice() %>" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="reset" name="reset" value="Clear" /></td>
                    <td><input type="submit" name="submit" value="Update" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>
