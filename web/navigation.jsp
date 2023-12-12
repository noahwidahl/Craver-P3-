<%@page import="model.RegisteredUser"%>
<%-- 
    Document   : Navigation
    Created on : 28. nov. 2023, 15.48.23
    Author     : ajens
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant Map</title>
    <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet">
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
        request.setAttribute("PostNr", userLoggedIn.getAddress());
        request.setAttribute("PostBy", userLoggedIn.getAddress());
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
    
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAylpg3tnqQLq7Jmm8qA-bZXSV70njxzok&libraries=places&callback=initMap" async defer></script>

    <%@ page import="model.FoodSupplier" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="java.util.List" %>
    <% 
        FoodSupplier restaurant = new FoodSupplier(8);
        List<Double> doubleList = restaurant.getCoordinates();
        double Latitude = doubleList.get(0);
        double Longitude = doubleList.get(1);
    %>
    
    
    <script>
        var map;
        var restaurantLocation;
        var directionsService;
        var directionsRenderer;
        var distanceDisplay;

        function initMap() {
            // Set the restaurant's location
            restaurantLocation = { lat: <%= Latitude %>, lng: <%= Longitude %> }; // Replace with your restaurant's coordinates

            // Create a map centered at the restaurant's location
            map = new google.maps.Map(document.getElementById('map'), {
                zoom: 15,
                center: restaurantLocation
            });

            // Create a marker for the restaurant
            var restaurantMarker = new google.maps.Marker({
                position: restaurantLocation,
                map: map,
                title: 'McDonald Aalborg' // Replace with your restaurant's name
            });

            // Create an Autocomplete object for the address input
            var input = document.getElementById('address');
            var autocomplete = new google.maps.places.Autocomplete(input);

            // Initialize DirectionsService and DirectionsRenderer
            directionsService = new google.maps.DirectionsService();
            directionsRenderer = new google.maps.DirectionsRenderer();
            directionsRenderer.setMap(map);

            // Set the distanceDisplay variable after the HTML has fully loaded
            distanceDisplay = document.getElementById('distance');
        }

        function calculateAndDisplayRoute(travelMode) {
            var startAddress = document.getElementById('address').value;

            var request = {
                origin: startAddress,
                destination: restaurantLocation,
                travelMode: travelMode
            };

            directionsService.route(request, function(response, status) {
                if (status === 'OK') {
                    directionsRenderer.setDirections(response);

                    // Extract and display distance
                    var distance = response.routes[0].legs[0].distance.value; // in meters
                    var distanceInKm = distance / 1000;
                    distanceDisplay.textContent = 'Distance: ' + distanceInKm.toFixed(2) + ' km';

                    // Add or update info window with distance at the destination marker
                    if (!map.infoWindow) {
                        map.infoWindow = new google.maps.InfoWindow();
                    }

                    var infoWindowContent = 'Distance: ' + distanceInKm.toFixed(2) + ' km';

                    map.infoWindow.setContent(infoWindowContent);
                    map.infoWindow.setPosition(restaurantLocation);
                    map.infoWindow.open(map);
                } else if (status === 'ZERO_RESULTS') {
                    window.alert('No route found for the given addresses.');
                } else {
                    window.alert('Directions request failed due to ' + status);
                }
            });
        }
    </script>
</head>
<body>
    <!-- The map container -->
    <div id="map" style="height: 700px;"></div>

    <!-- Add a text field for address input -->
    <label for="address">Skriv din adresse:</label>
    <input type="text" id="address" placeholder="Skriv din adresse for at vise afstanden" style="width: 300px;">

    <!-- Add buttons for travel mode -->
    <button onclick="calculateAndDisplayRoute('WALKING')">Vis gå afstand</button> <!--<!-- Button to calculate the distance in walking by calling the calculateAndDisplayRoute function -->
    <button onclick="calculateAndDisplayRoute('DRIVING')">Vis kørsels afstand</button> <!<!-- Button to calculate the distance in driving, such as the one above -->

    <!-- Display the distance -->
    <p id="distance"></p>
</body>
</html>
