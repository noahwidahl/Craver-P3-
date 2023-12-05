<%-- 
    Document   : index
    Created on : 5. nov. 2023, 09.35.56
    Author     : Bokaj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hello World!</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        Today is: <%= new java.util.Date().toString()%>
        <a href="read">View All Friends</a>
                <a href="Navigation,jsp">Se navigation</a>
    </body>
</html>
