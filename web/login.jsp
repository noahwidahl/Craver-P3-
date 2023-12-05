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
                <input type="text" placeholder="Indtast brugernavn" name="uname"><br/><br/>
                
                <label>Password</label><br/><!-- comment -->
                <input type="password" placeholder="Indtast password" name="pword"><br/><br/>
                
                <input type="submit" value="Login as user" name="loginUser">
                <input type="submit" value="Login as guest" name="loginGuest"><br/><br/>
                
                <!-- Add hidden fields to identify which button was pressed -->
                <input type="hidden" name="loginType" value="">
                    <script>
                    // JavaScript function to set the value of the hidden field based on the pressed button
                    function setLoginType(type) {
                        document.getElementsByName("loginType")[0].value = type;
                    }

                    // Attach the function to each submit button
                    document.getElementsByName("loginUser")[0].addEventListener("click", function() {
                        setLoginType("user");
                    });
                    document.getElementsByName("loginGuest")[0].addEventListener("click", function() {
                        setLoginType("guest");
                    });
                    </script>
                
                <a href="register.jsp">Ikke medlem?</a><br/><br/>
                <a href="registerFoodSupplier.jsp">Foodsupplier</a><br/>
  
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
