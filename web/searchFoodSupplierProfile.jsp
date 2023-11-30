<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet">
</head>

<body>
<!-- Menu elements start -->    
        <%-- Set the userName attribute --%>
    <%
        String userName = "Jakob";
        request.setAttribute("userName", userName);
    %>
    <div id="square1" class="square">
    <nav>    

      <a href="${pageContext.request.contextPath}\home.jsp">Home</a>
      <a href="${pageContext.request.contextPath}\searchFoodSupplierProfile.jsp">Search</a>
      <a href="${pageContext.request.contextPath}\profile.jsp">User profile</a> 
      <div id="user-name-placeholder" class="user-name-placeholder">${userName}</div>

    </nav>
  </div>

  <div id="square2" class="square">

    <p>Søg på din ynglingsingredienser eller din livret og få resultater der passer præcist på din søgning, både hvis du vil finde noget nem aftensmad udefra eller selv vil igang med at kokkererer dit næste mesterværk</p>
  
  </div>

  <div id="square3" class="square">
  </div>

    <script>
        document.getElementById("user-name-placeholder").innerText = "User: ${userName}";
        
    </script>
<!-- Menu elements stop -->
    
    
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
