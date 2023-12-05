<%-- 
    Document   : Profile
    Created on : 26. nov. 2023, 21.15.56
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

<%
   } else if (userRole == 2) {  //Menubar User
%>
        <a href="${pageContext.request.contextPath}/home.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/searchFoodSupplierProfile.jsp">Search</a>
        <a href="${pageContext.request.contextPath}/profile.jsp">User profile</a> 
<%
   }else if (userRole == 3) {  //Menubar guest
%>
        <a href="${pageContext.request.contextPath}/home.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/searchFoodSupplierProfile.jsp">Search</a>
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
    

 <div id="square4" class="square">
  

        <label for="userID">UserID:</label>
        <input type="text" id="userID" name="userID" disabled value="${varUserID}"><br>

        <label for="userName">UserName:</label>
        <input type="text" id="userName" name="userName" disabled value="${varUserName}"><br>

        <label for="userRole">UserRole:</label>
        <input type="text" id="userRole" name="userRole" disabled value="${varUserRoleDescription}"><br>


    </div>
        <div id="square4" class="square">
            <form id="deleteForm" action="/delete" method="post">
            <button type="button" onclick="confirmDelete()">Delete</button>
            
        </form>
            
        </div>  <br> 
        
         </div>
        <div id="square4" class="square">
            <h1>Your ratings</h1>  
            
        </div> <br> 

        <div id="square4" class="square">
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

        xhr.open("POST", "profile", true); //http://localhost:8080/Crave/profile
        xhr.send();
    };
</script>

</body>

</html>
