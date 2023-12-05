<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Food Suppliers</title>
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

<h2>Food Suppliers</h2>

<% 
    List<String> foodSupplierInfo = (List<String>) request.getAttribute("foodSupplierInfo");
    if(foodSupplierInfo != null && !foodSupplierInfo.isEmpty()) {
        %><ul><%
        for(String supplierInfo : foodSupplierInfo) {
            // Extract supplier ID from the combined info string
            int openBracketIndex = supplierInfo.lastIndexOf(" (");
            int closeBracketIndex = supplierInfo.lastIndexOf(")");
            if (openBracketIndex != -1 && closeBracketIndex != -1 && closeBracketIndex > openBracketIndex) {
                String name = supplierInfo.substring(0, openBracketIndex);
                int id = Integer.parseInt(supplierInfo.substring(openBracketIndex + 2, closeBracketIndex));
                String link = "foodsupplier?supplierId=" + id;
                %><li><a href="<%= link %>"><%= name %></a></li><%
            }
        }
        %></ul><%
    } else {
        %><p>No food suppliers found.</p><%
    } 
%>

</body>
</html>
