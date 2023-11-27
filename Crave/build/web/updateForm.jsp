<%@page import="model.FoodItem"%> <%-- Importing the FoodItem model class --%>
<% 
    // Retrieving the FoodItem object from the request attribute
    FoodItem FoodItem = (FoodItem) request.getAttribute("FoodItem"); 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> <%-- Setting the content type and encoding for the page --%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> <%-- Specifying the character encoding for the HTML document --%>
        <title>Update a FoodItem</title> <%-- Title of the web page --%>
    </head>
    <body>
        <h1>Update a FoodItem</h1> <%-- Main heading of the page --%>
        
        <%-- Form for updating a FoodItem. Ensure the action attribute matches the URL of mine update servlet --%>
        <form name="UpdateForm" action="UpdateFoodItem" method="get">
            <table class="update"> <%-- Table layout for the form inputs --%>
                <tr>
                    <td class="right">FoodItemID:</td>
                    <td><input type="text" name="FoodItemID" value="<%= FoodItem.getFoodItemID() %>" readonly /></td>
                    <%-- Displaying the FoodItem ID. It's set to readonly to prevent changes --%>
                </tr>
                <tr>
                    <td class="right">FoodItemName:</td>
                    <td><input type="text" name="FoodItemName" value="<%= FoodItem.getFoodItemName() %>" /></td>
                    <%-- Input field for FoodItem name --%>
                </tr>
                <tr>
                    <td class="right">Price:</td>
                    <td><input type="number" name="Price" value="<%= FoodItem.getPrice() %>" /></td>
                    <%-- Input field for FoodItem price --%>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="reset" name="reset" value="Clear" /></td>
                    <%-- Reset button to clear the form --%>
                    <td><input type="submit" name="submit" value="Update" /></td>
                    <%-- Submit button to update the FoodItem --%>
                </tr>
            </table>
        </form>
    </body>
</html>
