<%--
  Created by IntelliJ IDEA.
  User: duyle
  Date: 02/03/2017
  Time: 14:12
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


<body>
<div class="wrapper">
    <%@include file="../jspf/slider.jspf" %>
    <div class="main-panel">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#navigation-example-2">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Bản đồ</a>
                </div>
                <div class="collapse navbar-collapse">
                    <%@include file="../jspf/navbar-right.jspf" %>
                </div>
            </div>
        </nav>

        <div id="map"></div>

    </div>
</div>

<div id="myModal" class="modal modal-wide fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title" id="modal-title"></h4>
            </div>
            <div class="modal-body" align="center" id="modal-body">

            </div>
        </div>
    </div>

</div>
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

    var rootUrl = 'http://localhost:8088/geoserver/animal/ows';

    var defaultParameters = {
        service: 'WFS',
        version: '1.0.0',
        request: 'GetFeature',
        typeName: 'animal:habitat',
        maxFeatures: 50,
        outputFormat: 'text/javascript'
        , format_options: 'callback: getJson',
        srsName: 'EPSG:4326'

    };

    var parameters = L.Util.extend(defaultParameters);
    //console.log(rootUrl + L.Util.getParamString(parameters));
    $.ajax({
        jsonp: false,
        url: rootUrl + L.Util.getParamString(parameters),
        dataType: 'jsonp',
        jsonpCallback: 'getJson',
        success: handleJson
    });

    var group = new L.featureGroup().addTo(map);
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
    function handleJson(data) {
        geojsonlayer = L.geoJson(data, {
            onEachFeature: function (feature, my_Layer) {
                my_Layer.on('click', function (e) {
                    show(feature.id, feature.properties.locationname, feature.properties.latitude, feature.properties.longitude);
                });
            },
            pointToLayer: function (feature, latlng) {
                return L.marker(latlng, {icon: redIcon});
            }
        }).addTo(group);
    }
//    L.marker([16.455433, 107.592504], {icon: yellowIcon}).addTo(map);

</script>
<c:if test="${error != null}">
    <script>
        $(document).ready(function () {
            $.simplyToast('<c:out value="${error}"/>', 'danger');
        });
    </script>
</c:if>
<c:if test="${message != null}">
    <script>
        $(document).ready(function () {
            $.simplyToast('<c:out value="${message}"/>', 'success');
        });
    </script>
</c:if>
<script>
    function show(id, locationName, latitude, longitude) {
        var title = locationName + ' (' + latitude + ' - ' + longitude + ")";
        $('#modal-title').html(title);
        var res = id.split(".");
        $.ajax({
            type: "GET",
            contentType: "application/json; charset=UTF-8",
            url: "${home}species/habitat",
            encoding: "UTF-8",
            data: {
                id: res[1]
            },
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                if (data != null) {
                    var result = "";
                    for (var key in data) {
                        var obj = data[key];
                        result += "<div class=\"w3-container\" style=\"height: auto\">"
                            + "<div class=\"w3-card-4 index-card\">";
                        if (obj.image == null || obj.image == '') {
                            result += "<a href=\"/detailspecies/" + obj.id + "\"><img src=\"http://is.tnu.edu.vn/wp-content/themes/motive/images/no_image.jpg\" width=\"150px\" height=\"150px\" alt=\"Avatar\"";
                        } else {
                            var img = obj.image.split(",");
                            result += "<a href=\"/detailspecies/" + obj.id + "\"><img src=\"http://localhost:8081/resources/images/" + img[0] + "\" width=\"150px\" height=\"150px\" alt=\"Avatar\"";
                        }
                        result += " class=\"w3-left w3-margin-right\"></a>"
                            + "<div align=\"left\">"
                            + "<p class=\"vietnamese-name\">" + obj.vietnameseName + "</p>"
                            + "<p class=\"science-name\">Tên khoa học: " + obj.scienceName + "</p>"
                            + "<p class=\"science-name\">Họ: " + obj.vietnameseNameFamily + "</p>"
                            + "<p class=\"science-name\">Chi: " + obj.vietnameseNameGenus + "</p>"
                            + "</div>"
                            + "</div>"
                            + "</div>";
                    }
                    $("#modal-body").html(result);
                    $('#myModal').modal('show');
                } else {
                    $(document).ready(function () {
                        $.simplyToast('Không tìm thấy dữ liệu', 'danger');
                    });
                }
            },
            error: function (e) {
                $(document).ready(function () {
                    $.simplyToast('Không tìm thấy dữ liệu', 'danger');
                });
                console.log("ERROR: ", e);
            }
        });

    }
</script>
<script src="<c:url value="/resources/js/index.js"/>" type="text/javascript"></script>
</html>