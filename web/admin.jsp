<!-- Importing java libraries -->    
<%@page import="model.RegisteredUser"%>
<%-- 
    Document   : Home
    Created on : 26. nov. 2023, 21.16.05
    Author     : Bokaj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!Doctype HTML>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CraveConnect</title>
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

  
  <div class="square">
    <p class="title">Disse står til godkendelse</p>
  </div>
    <!-- form interact with Admin.java servlet -->
    <form action="admin" method="post">
        <button type="submit">Søg</button>
    </form>
    <script>
    function reloadPage() {
        location.reload();
    }
</script>
    
    <%! // Should be a servlet, but can't find a way to automatic load using a servlet. Found a way, but no time to change it....%>
    <%@ page import="java.sql.ResultSet" %>
    <%@ page import="java.sql.SQLException" %>
    <%@ page import="java.util.HashMap" %>
    <%@ page import="dbHelpers.ReadQuery" %>
    
    <%! // Declaration block for variables and methods
    private ResultSet results;
    private ReadQuery readQuery;
    String tableValue = "";
    %>
    <%
            // Initialization block
            results = null;
            readQuery = new ReadQuery();

            // Getting data as a results object from ReadQuery dbHelper 
            String query = "select * from craveconnect.v_FoodsuppliersPending;";
            results = readQuery.readTableData(query);
            
            //Using a HashMap to dyniamicly make buttons, key = button name, value = button text
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("BtnDeny", "Deny");
            hashMap.put("BtnApprove", "Approve");
            //hashMap.put("test", "test");
            
            //Creating the HTML table and showcasing on the admin.jsp
            String table = readQuery.outputResultAsHtmlTableWithButtons(results,hashMap,"admin");
            tableValue = table;
    %>
    <%= tableValue %>
    
    
</body>

</html>