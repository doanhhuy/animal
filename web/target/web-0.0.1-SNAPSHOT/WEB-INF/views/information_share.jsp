<%--
  Created by IntelliJ IDEA.
  User: duyle
  Date: 4/5/17
  Time: 9:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<%@include file="../jspf/header.jspf" %>
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
<link rel="stylesheet" href="<c:url value="/resources/css/information-share.css"/>">
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
                    <a class="navbar-brand" href="#">Thông tin chia sẻ</a>
                </div>
                <div class="collapse navbar-collapse">
                    <%@include file="../jspf/navbar-right.jspf" %>
                </div>
            </div>
        </nav>
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-6">
                            <div class="card">
                                <div class="header">
                                    <h4 class="title">Thông tin chia sẻ</h4>
                                </div>
                                <div class="content" id="list-share" style="display:block;overflow-y:auto;">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card">
                                <div class="header">
                                    <h4 class="title">Chi tiết loài</h4>
                                </div>
                                <div class="content" id="detail-share" style="display:block;overflow-y:auto;">
                                    <div class="table-full-width">
                                        <table class="table">
                                            <tbody id="table-body">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="myModal" class="modal modal-wide fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-body" align="center" id="modal-body">
                    <div id="map"></div>
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

<script src="<c:url value="/resources/js/information-share1.js"/>"></script>

<script>
    var marker = new Array();
    var GoogleStreet = L.tileLayer('https://mt0.google.com/vt/lyrs=m&hl=en&x={x}&y={y}&z={z}&s=Ga');
    var map = L.map('map', {
        layers: [GoogleStreet],
        center: new L.LatLng(16.048680, 108.216292),
        crs: L.CRS.EPSG3857,
        tms: true,
        minZoom: 3,
        maxZoom: 18,
        zoom: 10
    });
    var redIcon = new L.Icon({
        iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-red.png',
        shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
        iconSize: [25, 41],
        iconAnchor: [12, 41],
        popupAnchor: [1, -34],
        shadowSize: [41, 41]
    });

</script>
<div id="script"></div>
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
</html>