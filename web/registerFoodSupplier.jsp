<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Importing java libraries --> 
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Collections" %>

<%
    // Check if there is an error parameter in the URL
    String errorParam = request.getParameter("error");

    // Create a mapping of error codes to error messages
    Map<String, String> errorMessages = new HashMap<>();
    errorMessages.put("1", "Udfyld venligst alle felter.");

    // Get the error message based on the error code
    String errorMessage = errorMessages.get(errorParam);
    if (errorMessage == null) {
        errorMessage = ""; // Default to an empty string if the error code is not recognized
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .registercontainer{
                width: 100%;
                height: auto;
                text-align: center;
            }
            .error-message {
                color: red;
            }
        </style>
    </head>
    <body>
        <div class="registercontainer">
            <p class="error-message"><%= errorMessage %></p>
            <%-- Form to interact with RegisterFoodSupplier.java servlet --%>
            <form action="registerFoodSupplier" method="post">
                <label>System brugernavn</label><br/>
                <input type="text" placeholder="Indtast brugernavn" name="FoodSupplierUsername" required><br/>
                
                <label>Password</label><br/><!-- comment -->
                <input type="password" placeholder="Indtast password" name="FoodsupplierPassword" required><br/>
                
                <label>Email</label><br/>
                <input type="email" placeholder="Indtast email" name="FoodsupplierEmail" required><br/>
                
                <label>Virksomhedsnavn</label><br/>
                <input type="text" placeholder="Indtast virksomhedsnavn" name="FoodsupplierName" required><br/>
                
                <label>Address</label><br/>
                <input type="text" placeholder="Indtast address" name="FoodsupplierAddress"><br/>
                
                <label>PostNr</label><br/>
                <input type="text" placeholder="Indtast postNr" name="FoodsupplierPostNr"><br/>
                
                <label>PostBy</label><br/>
                <input type="text" placeholder="Indtast postBy" name="FoodsupplierCity"><br/>
                
                <label>Kontaktnummer</label><br/>
                <input type="text" placeholder="Indtast kontaktnummer" name="FoodsupplierPhoneNumber"><br/>
                
                <label>Link til egen hjemmeside</label><br/>
                <input type="text" placeholder="Indtast link" name="FoodsupplierExternalLink"><br/>
                 
                <label for="category">Vælg en kategori:</label>
                <select id="FoodSupplierCategoryID" name="FoodSupplierCategoryID">
                  <option value="2">Recipe Site</option>
                  <option value="1">Restaurant</option>
                </select>
                
                <input type="submit" value="Register"><br/>
            </form>
        </div>
    </body>
</html>
