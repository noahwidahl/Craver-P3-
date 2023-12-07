<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Food Suppliers</title>
    <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet">
    <style>
        /* Add your CSS styles here */
        body {
            font-family: Arial, sans-serif;
        }
        h2 {
            color: #4C654B;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            margin-bottom: 10px;
        }
        a {
            text-decoration: none;
            color: #0D0E19;
        }
        a:hover {
            color: red;
        }
        /* Add other styles from your provided CSS as needed */
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
    
    
<h2>Restauranter</h2>

<ul>
    <% 
    List<String> foodSupplierInfo = (List<String>) request.getAttribute("foodSupplierNames");
    System.out.println("foodSupplier.jsp"); 
    if(foodSupplierInfo != null && !foodSupplierInfo.isEmpty()) {
        //System.out.println("1"); 
        for(String supplierInfo : foodSupplierInfo) {
            //System.out.println("2"); 
            if (supplierInfo.contains(" (")) { // Check if the string contains the expected format
                //System.out.println("3"); 
                String[] parts = supplierInfo.split(" \\("); // Split the string
                if (parts.length == 2) { // Ensure there are exactly two parts
                    //System.out.println("4"); 
                    String name = parts[0];
                    String id = parts[1].replace(")", "");
                    String link = "foodItems?supplierId=" + id;
                    //System.out.println(link); 
                    %><li><a href="<%= link %>"><%= name %></a></li><%
                }
            }
        }
    } else {
        %><p>No food suppliers found.</p><%
    } 
    %>
</ul>

</body>
</html>
