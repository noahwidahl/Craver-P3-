

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <button type="button" onclick="alert('Button Clicked!')">Click me</button>
    <% String table = (String) request.getAttribute("table"); %>
    
    <form action="TableUpdateServlet" method="post">
        <label for="newValue">New Value:</label>
        <input type="text" id="newValueId" name="newValueName">
        <button type="submit">Update Table</button>
    </form>
    
    <body>
        <h1>Hello World2!</h1>
        <%= table %>
    </body>
</html>
