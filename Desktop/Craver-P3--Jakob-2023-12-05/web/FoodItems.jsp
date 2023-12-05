<%@page import="model.FoodItem"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Food Items</title>
    <style>
        /* Add your CSS styles here */
        /* Example: */
        body {
            font-family: Arial, sans-serif;
        }
        h2 {
            color: #4C654B;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            margin-bottom: 10px;
        }
        /* Add other styles from your provided CSS as needed */
    </style>
</head>
<body>

<h2>Food Items</h2>

<ul>
    <% 
    List<FoodItem> foodItems = (List<FoodItem>) request.getAttribute("foodItems");
    if(foodItems != null) {
        for(FoodItem item : foodItems) { 
    %>
        <li><%= item.getFoodItemName() %> - Price: <%= item.getPrice() %></li>
    <% 
        }
    } 
    %>
</ul>

</body>
</html>
