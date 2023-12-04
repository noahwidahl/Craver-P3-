<%-- 
    Document   : login
    Created on : 13. nov. 2023, 00.00.14
    Author     : timmadsen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login Page</title>
        <style>
            .logincontainer{
                width: 100%;
                height: auto;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="logincontainer">
            <form action="login" method="post">
                <label>Brugernavn</label><br/>
                <input type="text" placeholder="Indtast brugernavn" name="uname"><br/>
                
                <label>Password</label><br/><!-- comment -->
                <input type="password" placeholder="Indtast password" name="pword"><br/>
                
                <input type="submit" value="Login"><br/>
                <a href="register.jsp">Ikke medlem?</a><br/>
                
            </form>
            <%-- Display error message if username or password is invalid --%>
            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
                <p style="color: red;"><%= error %></p>
            <%
                }
            %>
        </div>
    </body>
</html>
