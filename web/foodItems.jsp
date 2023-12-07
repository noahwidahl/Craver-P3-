<%@page import="model.FoodItem"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Food Items</title>
    <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Times New Roman', Times, serif;
            background-color: #f3f3f3;
            text-align: center;
        }
        h2 {
            color: #4C654B;
            margin-bottom: 30px;
        }
        ul {
            list-style-type: none;
            padding: 0;
            margin: auto;
            width: 50%;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
            padding: 20px;
        }
        li {
            border-bottom: 1px solid #ddd;
            padding: 10px;
            font-size: 18px;
        }
        li:last-child {
            border-bottom: none;
        }
        a {
            text-decoration: none;
            color: #0D0E19;
        }
        a:hover {
            color: #CBBBA0;
        }
        .price {
            float: right;
            font-weight: bold;
        }
    </style>
</head>
<body>
<!-- Menu elements start -->    
        <%-- getting the session variables used in the form --%>
    <%
        String userName = (String) session.getAttribute("sessionUserName");
        request.setAttribute("varUserName", userName);
        int userID = (int) session.getAttribute("sessionUserID");
        request.setAttribute("varUserID", userID);
        int userRole = (int) session.getAttribute("sessionUserRole");
        request.setAttribute("varUserRole", userRole);
        String UserRoleDescription = (String) session.getAttribute("sessionUserRoleDescription");
        request.setAttribute("varUserRoleDescription", UserRoleDescription);
    %>
    
    
    <div id="square1" class="square">
    <nav>    

      <%
   if (userRole == 1) { //Menubar Administrator
%>
        <a href="${pageContext.request.contextPath}/home.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/searchFoodSupplierProfile.jsp">Search</a>
        <a href="${pageContext.request.contextPath}/profile.jsp">User profile</a> 
        <a href="${pageContext.request.contextPath}/admin.jsp">Admin</a> 
        <a href="${pageContext.request.contextPath}/foodSupplier">Se alle restauranter her</a> <!-- New Button -->
        <a href="${pageContext.request.contextPath}/navigation.jsp">Navigation her</a>

<%
   } else if (userRole == 2) {  //Menubar User
%>
        <a href="${pageContext.request.contextPath}/home.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/searchFoodSupplierProfile.jsp">Search</a>
        <a href="${pageContext.request.contextPath}/profile.jsp">User profile</a> 
        <a href="${pageContext.request.contextPath}/foodSupplier">Se alle restauranter her</a> <!-- New Button -->
        <a href="${pageContext.request.contextPath}/Navigation.jsp">Navigation her</a>
<%
   }else if (userRole == 3) {  //Menubar guest
%>
        <a href="${pageContext.request.contextPath}/home.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/searchFoodSupplierProfile.jsp">Search</a>
        <a href="${pageContext.request.contextPath}/foodSupplier.jsp">Se alle restauranter her</a> <!-- New Button -->
        <a href="${pageContext.request.contextPath}/foodSupplier">Se alle restauranter her</a> <!-- New Button -->
        <a href="${pageContext.request.contextPath}/Navigation.jsp">Navigation her</a>
<%
   }else if (userRole == 4) {  //Menubar foodsupplier 
%>
        <a href="${pageContext.request.contextPath}/home.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/searchFoodSupplierProfile.jsp">Search</a>
        <a href="${pageContext.request.contextPath}/profile.jsp">User profile</a> 
<%
   } 
%>

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
    String foodItemsTable = (String) request.getAttribute("foodItemsTable");
    if (foodItemsTable != null && !foodItemsTable.isEmpty()) {
        out.print(foodItemsTable);
    } else {
        %><p>No food items found.</p><%
            
    } 
%>

</body>
</html>