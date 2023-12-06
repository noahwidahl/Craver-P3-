<%@page import="model.FoodItem"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Food Items</title>
    <style>
        body {
            font-family: 'Times New Roman', Times, serif;
            background-color: #f3f3f3;
            text-align: center;
        }
        h2 {
            color: #4C654B;
            margin-bottom: 30px;
        }
        ul {
            list-style-type: none;
            padding: 0;
            margin: auto;
            width: 50%;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
            padding: 20px;
        }
        li {
            border-bottom: 1px solid #ddd;
            padding: 10px;
            font-size: 18px;
        }
        li:last-child {
            border-bottom: none;
        }
        a {
            text-decoration: none;
            color: #0D0E19;
        }
        a:hover {
            color: #CBBBA0;
        }
        .price {
            float: right;
            font-weight: bold;
        }
    </style>
</head>
<body>

<h2>Food Items</h2>

<% 
    String foodItemsTable = (String) request.getAttribute("foodItemsTable");
    if (foodItemsTable != null && !foodItemsTable.isEmpty()) {
        out.print(foodItemsTable);
    } else {
        %><p>No food items found.</p><%
    } 
%>

</body>
</html>