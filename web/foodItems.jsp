<!-- Importing java libraries -->  
<%@page import="model.RegisteredUser"%>
<%@page import="model.FoodItem"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Food Items</title>
    <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet">

</head>
<body>
    <!-- Menu elements start -->    
    <%-- getting the session variables used in the system --%>
    <%
        int userRole = 0;

        try {
            RegisteredUser userLoggedIn = (RegisteredUser) session.getAttribute("sessionUserObject");
            userRole = userLoggedIn.getUserRoleID();
            request.setAttribute("varUserName", userLoggedIn.getUserName());
        } catch (Exception ex) {
            userRole = 3;
            request.setAttribute("varUserName", "Guest");
        }
    %>
    
    
    <div id="square1" class="square">
    <nav>    
<!-- Menu elements loaded depending of userRole -->   
    <%
        if (userRole == 1) { //Menubar Administrator
    %>
        <a href="${pageContext.request.contextPath}/home.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/foodSupplier">Se alle restauranter her</a> <!-- New Button -->
        <a href="${pageContext.request.contextPath}/navigation.jsp">Navigation her</a>
        <a href="${pageContext.request.contextPath}/profile.jsp">User profile</a> 
        <a href="${pageContext.request.contextPath}/admin.jsp">Admin</a> 
    <%
        } else if (userRole == 2) {  //Menubar User
    %>
        <a href="${pageContext.request.contextPath}/home.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/foodSupplier">Se alle restauranter her</a> <!-- New Button -->
        <a href="${pageContext.request.contextPath}/navigation.jsp">Navigation her</a>
        <a href="${pageContext.request.contextPath}/profile.jsp">User profile</a> 
    <%
        }else if (userRole == 3) {  //Menubar guest
    %>
        <a href="${pageContext.request.contextPath}/home.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/foodSupplier">Se alle restauranter her</a> <!-- New Button -->
        <a href="${pageContext.request.contextPath}/navigation.jsp">Navigation her</a>
    <%
        }else if (userRole == 4) {  //Menubar foodsupplier 
    %>
        <a href="${pageContext.request.contextPath}/home.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/profile.jsp">User profile</a> 
    <%
        } 
    %>
    
     <!-- Placeholder and script to load userName --> 
      <div id="user-name-placeholder" class="user-name-placeholder">${varUserName}</div>
    <script> 
        document.getElementById("user-name-placeholder").innerText = "User: ${varUserName}";
        
    </script>
            <a href="${pageContext.request.contextPath}/login.jsp" class="logout-button" id="logout-button">Logout</a>
    </nav>
  </div>

  <div id="square2" class="square">

    <p>Søg på din ynglingsingredienser eller din livret og få resultater der passer præcist på din søgning, både hvis du vil finde noget nem aftensmad udefra eller selv vil igang med at kokkererer dit næste mesterværk</p>
  
  </div>

  <div id="square3" class="square">
  </div>


<!-- Menu elements stop -->
    

<h2>Food Items</h2>

<% 
    //Reguest attributes for trhe foodItemsTable, and showcasing it on the webpage
    String foodItemsTable = (String) request.getAttribute("foodItemsTable");
    if (foodItemsTable != null && !foodItemsTable.isEmpty()) {
        out.print(foodItemsTable);
    } else {
        %><p>No food items found.</p><%
            
    } 
%>

</body>
</html>