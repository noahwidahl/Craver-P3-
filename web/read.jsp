<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>

<body>
    <button type="button" onclick="alert('Button Clicked!')">Click me</button>
    
    <form action="read" method="post">
        <label for="newValue">New Value:</label>
        <input type="text" id="newValueId" name="newValueName">
        <button type="submit">Update Table</button>
    </form>
    
    <h1>Hello World2!</h1>

    <% String tableValue = (String) request.getAttribute("table"); %>
    <%= tableValue %>
</body>
</html>
