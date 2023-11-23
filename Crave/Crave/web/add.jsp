<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a new Food item</title>
    </head>
    <body>
        <h1>Add A new Food item!</h1>
        
        <form name="addForm" action="addFoodItem" method="get">
            <label>FoodItemName</label>
            <input type="text" name="FoodItemName" value="" />
            <br>
            <label>Price</label>
            <input type="number" name="Price" value="" /><br>
            <br>
            <input type="submit" name="submit" value="Submit"/>
        </form>            
    </body>
</html>
