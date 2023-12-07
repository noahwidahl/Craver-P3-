<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Food Suppliers</title>
    <style>
        /* Add your CSS styles here */
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
        a {
            text-decoration: none;
            color: #0D0E19;
        }
        a:hover {
            color: red;
        }
        /* Add other styles from your provided CSS as needed */
    </style>
</head>
<body>

<h2>Restauranter</h2>

<ul>
    <% 
    List<String> foodSupplierInfo = (List<String>) request.getAttribute("foodSupplierNames");
    if(foodSupplierInfo != null && !foodSupplierInfo.isEmpty()) {
        for(String supplierInfo : foodSupplierInfo) {
            if (supplierInfo.contains(" (")) { // Check if the string contains the expected format
                String[] parts = supplierInfo.split(" \\("); // Split the string
                if (parts.length == 2) { // Ensure there are exactly two parts
                    String name = parts[0];
                    String id = parts[1].replace(")", "");
                    String link = "FoodItems.jsp?supplierId=" + id;
                    %><li><a href="<%= link %>"><%= name %></a></li><%
                }
            }
        }
    } else {
        %><p>No food suppliers found.</p><%
    } 
    %>
</ul>

</body>
</html>
