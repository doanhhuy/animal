<%--
  Created by IntelliJ IDEA.
  User: duyle
  Date: 4/6/17
  Time: 10:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="../jspf/header.jspf" %>
<link href="<c:url value="/resources/css/index.css"/>" rel="stylesheet"/>
<!-- Bibliothèque de base: Leaflet-->
<link href="<c:url value="/resources/plugins/leaflet/leaflet.css"/>" rel="stylesheet">
<!-- Draw-->
<link href="<c:url value="/resources/plugins/leaflet/leaflet.draw.css"/>" rel="stylesheet">

<!-- Slide menu-->
<link href="<c:url value="/resources/plugins/leaflet/slide_menu/SlideMenu.css"/>" rel="stylesheet">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

<!-- GeoCoder-->
<link href="<c:url value="/resources/plugins/leaflet/Control.OSMGeocoder.css"/>" rel="stylesheet">

<!-- Overview-->
<link href="<c:url value="/resources/plugins/leaflet/overview/MiniMap.css"/>" rel="stylesheet">

<!-- Localisation-->
<link href="<c:url value="/resources/plugins/leaflet/L.Control.Locate.min.css"/>" rel="stylesheet">

<!-- Mouse position-->
<link href="<c:url value="/resources/plugins/leaflet/L.Control.MousePosition.css"/>" rel="stylesheet">

<!-- Navigation Bar-->
<link href="<c:url value="/resources/plugins/leaflet/NavBar/NavBar.css"/>" rel="stylesheet">
<html>
<%@include file="../jspf/header.jspf" %>
<body>
<button type="button" id="back" class="btn btn-info btn-fill">Trờ về</button>
<div id="map"></div>
</body>
<%@include file="../jspf/footer.jspf" %>
<!-- Bibliothèque de base: Leaflet-->
<script src="<c:url value="/resources/plugins/leaflet/leaflet.js"/>" type="text/javascript"></script>
<!-- Draw-->
<script src="<c:url value="/resources/plugins/leaflet/leaflet.draw-src.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/plugins/leaflet/leaflet.draw.js"/>" type="text/javascript"></script>

<!-- Slide menu-->
<script src="<c:url value="/resources/plugins/leaflet/slide_menu/SlideMenu.js"/>" type="text/javascript"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

<!-- GeoCoder-->
<script src="<c:url value="/resources/plugins/leaflet/Control.OSMGeocoder.js"/>" type="text/javascript"></script>

<!-- Overview-->
<script src="<c:url value="/resources/plugins/leaflet/overview/MiniMap.js"/>" type="text/javascript"></script>

<!-- Localisation-->
<script src="<c:url value="/resources/plugins/leaflet/L.Control.Locate.js"/>" type="text/javascript"></script>

<!-- Mouse position-->
<script src="<c:url value="/resources/plugins/leaflet/L.Control.MousePosition.js"/>"
        type="text/javascript"></script>

<!-- Navigation Bar-->
<script src="<c:url value="/resources/plugins/leaflet/NavBar/NavBar.js"/>" type="text/javascript"></script>

<script src="<c:url value="/resources/js/information-share.js"/>"></script>

<script>
    var GoogleStreet = L.tileLayer('https://mt0.google.com/vt/lyrs=m&hl=en&x={x}&y={y}&z={z}&s=Ga');
    var googleSat = L.tileLayer('https://mt0.google.com/vt/lyrs=s&hl=en&x={x}&y={y}&z={z}&s=Ga');
    var OpenStreetMap = L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png');
    ///// Configuration de la map
    var map = L.map('map', {
        layers: [GoogleStreet], /// fond de base
        center: new L.LatLng(16.048680, 108.216292),/// coordonnées
        crs: L.CRS.EPSG3857,
        tms: true,
        minZoom: 3,
        maxZoom: 18,
        zoom: 15
    });

    /////layers de base
    var baseLayers = {
        "Google Street": GoogleStreet,
        "Google Sat": googleSat,
        "OpenStreetMap": OpenStreetMap
    };

    //    var support = L.tileLayer.wms("http://localhost:8088/geoserver/animal/wms", {
    //        layers: 'animal:habitat',
    //        format: 'image/png',
    //        transparent: true,
    //        version: '1.1.1',
    //        attribution: "myattribution"
    //    });

    ///// Groupe layers
    //    var overlays = {
    //        "natural": support
    //    };

    ///// Add the scale control to the map
    L.control.scale().addTo(map);

    ///// Add the geolocate control to the map
    L.control.locate({
        position: 'topleft',  // set the location of the control
        drawCircle: true,  // controls whether a circle is drawn that shows the uncertainty about the location
        follow: false,  // follow the user's location
        setView: true, // automatically sets the map view to the user's location, enabled if `follow` is true
        keepCurrentZoomLevel: false, // keep the current map zoom level when displaying the user's location. (if `false`, use maxZoom)
        stopFollowingOnDrag: false, // stop following when the map is dragged if `follow` is true (deprecated, see below)
        remainActive: false, // if true locate control remains active on click even if the user's location is in view.
        markerClass: L.circleMarker, // L.circleMarker or L.marker
        circleStyle: {},  // change the style of the circle around the user's location
        markerStyle: {},
        followCircleStyle: {},  // set difference for the style of the circle around the user's location while following
        followMarkerStyle: {},
        icon: 'fa fa-map-marker',  // class for icon, fa-location-arrow or fa-map-marker
        iconLoading: 'fa fa-spinner fa-spin',  // class for loading icon
        circlePadding: [0, 0], // padding around accuracy circle, value is passed to setBounds
        metric: true,  // use metric or imperial units
        onLocationError: function (err) {
            alert(err.message)
        },  // define an error callback function
        onLocationOutsideMapBounds: function (context) { // called when outside map boundaries
            alert(context.options.strings.outsideMapBoundsMsg);
        },
        showPopup: true, // display a popup when the user click on the inner marker
        strings: {
            title: "Show me where I am",  // title of the locate control
            popup: "You are within {distance} {unit} from this point",  // text to appear if user clicks on circle
            outsideMapBoundsMsg: "You seem located outside the boundaries of the map" // default message for onLocationOutsideMapBounds
        },
        locateOptions: {}  // define location options e.g enableHighAccuracy: true or maxZoom: 10
    }).addTo(map);

    ///// Add the mouse position to the map
    L.control.mousePosition().addTo(map);

    ///// Ajout des couches de base + couches geoserver
    L.control.layers(baseLayers).addTo(map);
    //    overlays.natural.addTo(map);

    var geojsonlayer;
    var yellowIcon = new L.Icon({
        iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-yellow.png',
        shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
        iconSize: [25, 41],
        iconAnchor: [12, 41],
        popupAnchor: [1, -34],
        shadowSize: [41, 41]
    });
    var redIcon = new L.Icon({
        iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-red.png',
        shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
        iconSize: [25, 41],
        iconAnchor: [12, 41],
        popupAnchor: [1, -34],
        shadowSize: [41, 41]
    });

    <c:forEach var="temp" items="${listShare}">
    L.marker([${temp.latitude}, ${temp.longitude}], {icon: yellowIcon}).addTo(map);
    </c:forEach>

</script>
<script>
    $(document).ready(function () {
        $('#back').on('click', function () {
            var href = window.location.origin + "/species/list/share";
           location.replace(href);
        })
    });
</script>
</html>
