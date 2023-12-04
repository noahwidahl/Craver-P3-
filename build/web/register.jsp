<%-- 
    Document   : Login
    Created on : 10. nov. 2023, 11.28.59
    Author     : timmadsen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Collections" %>

<%
    // Check if there is an error parameter in the URL
    String errorParam = request.getParameter("error");

    // Create a mapping of error codes to error messages
    Map<String, String> errorMessages = new HashMap<>();
    errorMessages.put("1", "Udfyld venligst alle felter.");

    // Get the error message based on the error code TEST
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
            
            <form action="register" method="post">
                
                <label>Brugernavn</label><br/>
                <input type="text" placeholder="Indtast brugernavn" name="uname"><br/>
                
                <label>Password</label><br/><!-- comment -->
                <input type="password" placeholder="Indtast password" name="pword"><br/>
                
                <label>Email</label><br/>
                <input type="email" placeholder="Indtast email" name="email"><br/>
                
                <input type="submit" value="Register"><br/>
                <a href="login.jsp">Allerede medlem?</a><br/>
                
            </form>
        </div>
    </body>
</html>
