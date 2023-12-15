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

    <%-- Getting session variable of FoodItem --%>    
    <%
        FoodItem specificFoodItem = (FoodItem) session.getAttribute("sessionFoodItemID");
        request.setAttribute("varFoodItemID", specificFoodItem.getFoodItemID());
        request.setAttribute("varFoodItemName", specificFoodItem.getFoodItemName());
        request.setAttribute("varPrice", specificFoodItem.getPrice());  // Assuming you have a getUserRole() method in RegisteredUser
    %>
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

    <%-- Setting up a form to interact with FoodItemServlet.java --%>
    <form action="foodItem" method="post">
            <label for="foodItemID">Food Item ID:</label>
            <span id="foodItemID">${varFoodItemID}</span><br>
            
            <label for="foodItemName">Food Item Name:</label>
            <span id="foodItemName">${varFoodItemName}</span><br>
            
            <label for="price">Price:</label>
            <span id="price">${varPrice}</span><br><br>
            
            
            <label for="multiLineInput">Submit rating her!</label><br>
            
            <label for="rating">Rate from 1 to 5:</label><br>
            <select id="rating" name="rating">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3" selected>3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select><br>
            
            <label for="multiLineInput">Enter comment here:</label><br>
            <textarea id="multiLineInput" name="multiLineInputCommentSection" rows="4" cols="50" maxlength="250" placeholder="Type your text here... Max 250 chars"></textarea><br>
            
            <input type="submit" value="Submit rating">
        </form>

</body>
</html>