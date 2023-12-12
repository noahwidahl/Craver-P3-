<%-- 
    Document   : Profile
    Created on : 26. nov. 2023, 21.15.56
    Author     : Bokaj
--%>
<%@page import="model.RegisteredUser"%>
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
        <%-- getting the session variables used in the form --%>
    <%
        RegisteredUser userLoggedIn = (RegisteredUser) session.getAttribute("sessionUserObject");
        int userRole = userLoggedIn.getUserID();
        request.setAttribute("varUserName", userLoggedIn.getUserName());
        request.setAttribute("varUserID", userRole);
        request.setAttribute("varUserRole", userLoggedIn.getUserRole());  // Assuming you have a getUserRole() method in RegisteredUser
        request.setAttribute("varUserRoleDescription", userLoggedIn.getUserRoleDescription());  // Assuming you have a getUserRoleDescription() method
        request.setAttribute("Addresse", userLoggedIn.getAddress());
        request.setAttribute("PostNr", userLoggedIn.getPostNr());
        request.setAttribute("PostBy", userLoggedIn.getPostBy());
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
    


  

        <label for="userID">UserID:</label>
        <input type="text" id="userID" name="userID" disabled value="${varUserID}"><br>

        <label for="userName">UserName:</label>
        <input type="text" id="userName" name="userName" disabled value="${varUserName}"><br>

        <label for="userRole">UserRole:</label>
        <input type="text" id="userRole" name="userRole" disabled value="${varUserRoleDescription}"><br>



        
        <form action="profile" method="post">
            <!-- Adding a hidden input to identify the action -->
             <label for="LabelAddresse">Addresse:</label>
            <input type="text" id="Addresse" name="Addresse" value="${Addresse}"><br>

            <label for="PostNr">PostNr</label>
            <input type="text" id="PostNr" name="PostNr" value="${PostNr}"><br>

            <label for="PostBy">PostBy</label>
            <input type="text" id="PostBy" name="PostBy" value="${PostBy}"><br><br>
            
            <input type="hidden" name="Updateaction" value="updateInfo">
            <button id="UpdateInfo" type="submit" style="background-color: lightgreen;">Update info</button>
            
        </form><br> 
            
        <form action="profile" method="post">
            <input type="hidden" name="DeleteUseraction" value="DeleteUser">
            <button id="BtnDeleteUser" type="submit" style="background-color: red;">Delete user</button>   
        </form>    
            
            <br> 
        
        
        
            <h1>Your ratings</h1>  
            
      

    
            <div id="tableContent" ></div>
    
    
<% 
    String tableValue = (String) request.getAttribute("table");
    
    if (tableValue != null) {
%>
    <%= tableValue %>
<%
    }
%>
   
 
    <script>
    window.onload = function () {
        var xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    // Log the response text when the request is successful
                    console.log("Response received:", xhr.responseText);

                    // Extract the table content from the response
                    const htmlContent = xhr.responseText;

                    // Create a temporary container element
                    const container = document.createElement('div');
                    container.innerHTML = htmlContent;
                    //console.log(container);
                    // Find the table element and extract its content
                    const tableElement = container.querySelector('table');
                    //console.log(container);
                    if (tableElement) {
                        // Get the HTML content of the table
                        tableContent = tableElement.innerHTML;
                        
                        // Replace 'Good taste!' with 'Excellent taste!'
                        const oldValue = '<tbody>';
                        const newValue = '<table border=1>';
                        tableContent = tableContent.replace(new RegExp(oldValue, 'g'), newValue);
                        //console.log(tableContent);
                    } else {
                        //console.log('Table not found in the HTML content.');
                    }

                    // Update the content of the 'tableContent' div with the response
                    document.getElementById("tableContent").innerHTML = tableContent.replace("","");
                } else {
                    // Log an error message if the request fails
                    console.error("Error in the request. Status:", xhr.status);
                }
            }
        };

        xhr.open("GET", "profile", true); //http://localhost:8080/Crave/profile
        xhr.send();
    };
</script>

</body>

</html>