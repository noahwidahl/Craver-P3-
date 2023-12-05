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
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAylpg3tnqQLq7Jmm8qA-bZXSV70njxzok&libraries=places&callback=initMap" async defer></script>
    <script>
        var map;
        var restaurantLocation;
        var directionsService;
        var directionsRenderer;
        var distanceDisplay;

        function initMap() {
            // Set the restaurant's location
            restaurantLocation = { lat: 57.048869, lng: 9.921543 }; // Replace with your restaurant's coordinates

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
