<!-- Importing java libraries -->  
<%@page import="java.util.List"%>
<%@page import="model.FoodItem"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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


<!-- Søgeformular til at bruge SearchDishesServlet -->
<form action="${pageContext.request.contextPath}/home" method="get" class="search-form">
    <input type="text" id="search-bar" name="searchTerm" placeholder="Skriv en ret eller brug filtrene under til at finde din ret">
    <button type="submit">Søg</button>
</form>

    <!-- Creating tags for categories -->
<div class="category-tags">
    <%
        //Assingning a HashMap to store categoryID and categoryName from FoodItem
        Map<Integer, String> categories = FoodItem.getAllCategories();
        request.setAttribute("categories", categories);
        //If-statement to check if values are empty or not
        if (categories != null && !categories.isEmpty()) {
            // For each loop to create each category and make them interactiveable
            for (Map.Entry<Integer, String> entry : categories.entrySet()) {
                int id = entry.getKey();
                String value = entry.getValue();
                //Creating each href
    %>          
                <a href='<%= request.getContextPath() %>/SearchDishes?category=<%= id %>' class='category-tag'><%= value %></a>
    <%
            }
        }
    %>
</div>
    
<!-- Showcase of all search results -->
    <div class="search-results">
        <% 
            //getting dish data.
            List<FoodItem> dishes = (List<FoodItem>) request.getAttribute("dishes");
            //If-statement to handle data or not
            if (dishes != null && !dishes.isEmpty()) {
                //Creating elements in the table on home.jsp
                out.println("<ul class='dishes-list'>");
                for (FoodItem dish : dishes) {
                    out.println("<li class='dish-item'>");
                    out.println("<img src='" + dish.getLinkToFoodImage() + "' alt='" + dish.getFoodItemName() + "' class='dish-image'>"); // Viser billedet
                    out.println("<div class='dish-details'>");
                    out.println("<h3 class='dish-name'>" + dish.getFoodItemName() + "</h3>"); // Viser navn
                    out.println("<p class='dish-price'>Pris: " + dish.getPrice() + " kr.</p>"); // Viser pris
                    
                    // Add ingredients to show, doesnt work atm?
                    if (dish.getIngredients() != null && !dish.getIngredients().isEmpty()) {
                        out.println("<ul class='dish-ingredients'>");
                        for (String ingredient : dish.getIngredients()) {
                            out.println("<li class='ingredient'>" + ingredient + "</li>");
                        }
                        out.println("</ul>");
                    }

                    out.println("</div>");
                    out.println("</li>");
                }
                out.println("</ul>");
            } else if (request.getParameter("searchTerm") != null) {
            //If nothing to show, give a default messages    
            out.println("<p>Ingen resultater fundet.</p>");
            }
        %>
    </div>

</body>

</html>