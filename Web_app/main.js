
(function () {


/*  Google maps function!
 * Google Maps documentation: http://c	ode.google.com/apis/maps/documentation/javascript/basics.html
 * Geolocation documentation: http://dev.w3.org/geo/api/spec-source.html
 */
$( document ).on( "pageinit", "#map-page", function() {
    var defaultLatLng = new google.maps.LatLng(34.0983425, -118.3267434);  // Default to Hollywood, CA when no geolocation support
    if ( navigator.geolocation ) {
        function success(pos) {
            // Location found, show map with these coordinates
            drawMap(new google.maps.LatLng(pos.coords.latitude, pos.coords.longitude));
        }
        function fail(error) {
            drawMap(defaultLatLng);  // Failed to find location, show default map
        }
        // Find the users current position.  Cache the location for 5 minutes, timeout after 6 seconds
        navigator.geolocation.getCurrentPosition(success, fail, {maximumAge: 500000, enableHighAccuracy:true, timeout: 6000});
    } else {
        drawMap(defaultLatLng);  // No geolocation support, show default map
    }
    function drawMap(latlng) {
        var myOptions = {
            zoom: 10,
            center: latlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById("map-canvas"), myOptions);
        // Add an overlay to the map of current lat/lng
        var marker = new google.maps.Marker({
            position: latlng,
            map: map,
            title: "Greetings!"
        });
    }
});



$(document).delegate('.ui-page', 'pageshow', function () {
    //Your code for each page load here

// search Icon in the header!           
    new $.nd2Search({
        placeholder : "Search",
        defaultIcon : "calendar",
        source : [
              {"label": "Pizza", "value": "AF"},
              {"label": "Spagetti", "value": "AF"},
              {"label": "östermalm", "value": "AF"},

        ],
        fn : function(result) {
            console.log('--- Your custom handling ---');
            console.log('you picked: ');
            console.log(result);
          }
    });
});
    
})();

		