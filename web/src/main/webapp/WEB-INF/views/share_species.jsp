<%--
  Created by IntelliJ IDEA.
  User: duyle
  Date: 4/13/17
  Time: 9:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<link href="<c:url value="/resources/css/share-species.css"/>" rel="stylesheet">
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
                    <a class="navbar-brand" href="#">Chia sẻ loài</a>
                </div>
                <div class="collapse navbar-collapse">
                    <%@include file="../jspf/navbar-right.jspf" %>
                </div>
            </div>
        </nav>

        <%--open content--%>
        <div class="content">
            <div id="result"></div>
            <%--open container-fluid--%>
            <div class="container-fluid">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="card">
                                <div class="header">
                                    <h4 class="title">Thông tin loài chia sẻ</h4>
                                </div>
                                <c:if test="${error != null}">
                                    <script>
                                        $(document).ready(function () {
                                            $.simplyToast('<c:out value="${error}"/>', 'danger');
                                        });
                                    </script>
                                </c:if>
                                <c:if test="${error == null}">
                                    <div class="content">
                                        <form:form id="form-data" action=""
                                                   data-toggle="validator">
                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Tên tiếng việt</label>
                                                        <input type="text" class="form-control"
                                                               name="vietnameseName"
                                                               pattern="^[0-9a-zA-Z\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+$"
                                                               data-error="Không được viết có dấu và kí tự đặt biệt!!">
                                                    </div>
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Tên khoa học</label>
                                                        <input type="text" class="form-control"
                                                               pattern="^[0-9a-zA-Z\s]+$"
                                                               name="scienceName"
                                                               data-error="Không được viết có dấu và kí tự đặt biệt!!">
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Địa điểm hiện tại</label>
                                                        <div class="input-group">
                                                            <input id="share-location" name="location" type="text"
                                                                   class="form-control"
                                                                   placeholder="" required>
                                                            <span class="input-group-btn">
                                                        <a href="#" class="btn btn-secondary" onclick="showMap()"><i
                                                                class="pe-7s-map-marker"></i>Bản đồ
                                                            </a>
                                                    </span>
                                                        </div>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Đặc điểm sinh sản</label>
                                                        <textarea rows="4" cols="50" class="form-control"
                                                                  name="reproductionTraits"
                                                                  placeholder="" style="resize: none;"></textarea>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Đặc điểm giới tính</label>
                                                        <textarea rows="4" cols="50" class="form-control"
                                                                  name="sexualTraits"
                                                                  placeholder="" style="resize: none;"></textarea>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Đặc điểm khác</label>
                                                        <textarea rows="4" cols="50" class="form-control"
                                                                  name="ortherTraits"
                                                                  placeholder="" style="resize: none;"
                                                        ></textarea>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Tập tính sinh học</label>
                                                        <textarea rows="4" cols="50" class="form-control"
                                                                  name="biologicalBehavior"
                                                                  placeholder=""
                                                                  style="resize: none;"></textarea>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Thức ăn</label>
                                                        <textarea rows="4" cols="50" class="form-control" name="food"
                                                                  placeholder=""
                                                                  style="resize: none;"></textarea>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Kích cỡ</label>
                                                        <textarea rows="4" cols="50" class="form-control"
                                                                  name="mediumSize"
                                                                  placeholder=""
                                                                  style="resize: none;"></textarea>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Xuất xứ</label>
                                                        <textarea rows="4" cols="50" class="form-control" name="origin"
                                                                  placeholder=""
                                                                  style="resize: none;"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Chi</label>
                                                        <select type="text" class="form-control" name="idGenus">
                                                            <option value="" selected>Chọn chi</option>
                                                            <c:forEach var="genus" items="${listGenus}">
                                                                <option value="${genus.id}">${genus.scienceName}
                                                                    - ${genus.vietnameseName}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <button type="button" class="btn btn-info btn-fill pull-right"
                                                    onclick="shareSpecies()">Cập nhật
                                            </button>
                                            <div class="clearfix"></div>
                                        </form:form>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <c:if test="${error == null}">
                            <div class="col-md-4">
                                <div class="card card-user">
                                    <div class="content" align="center">
                                        <div class="row">
                                            <div class="col-md-10 col-md-offset-1">
                                                <div class="form-group">
                                                    <input type="file" id="image" class="form-control" name="image"
                                                           onchange="readURL(this);">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <img id="blah" style="width:100%;"
                                                         src="http://is.tnu.edu.vn/wp-content/themes/motive/images/no_image.jpg"
                                                         alt="your image"/>
                                                </div>
                                            </div>
                                        </div>
                                            <%--<button value="button" class="btn btn-info btn-fill" onclick="show()">--%>
                                            <%--Tải lên--%>
                                            <%--</button>--%>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>
                                <hr>
                            </div>
                        </c:if>
                    </div>

                </div>
            </div>
        </div>
        <%--close container-fluid--%>
    </div>
    <%--close content   --%>
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
<script src="<c:url value="/resources/js/share-species2.js"/>"></script>
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
<script>
    var marker = null;
    var GoogleStreet = L.tileLayer('https://mt0.google.com/vt/lyrs=m&hl=en&x={x}&y={y}&z={z}&s=Ga');
    var map = L.map('map', {
        layers: [GoogleStreet],
        center: new L.LatLng(16.048680, 108.216292),
        crs: L.CRS.EPSG3857,
        tms: true,
        minZoom: 3,
        maxZoom: 18,
        zoom: 15
    });
    var blueIcon = new L.Icon({
        iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-blue.png',
        shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
        iconSize: [25, 41],
        iconAnchor: [12, 41],
        popupAnchor: [1, -34],
        shadowSize: [41, 41]
    });
    navigator.geolocation.watchPosition(showPosition);
    function showPosition(position) {
        var latitude;
        var longitude;
        latitude = position.coords.latitude;
        longitude = position.coords.longitude;
        marker = L.marker([latitude, longitude], {icon: blueIcon}).addTo(map);
        map.setView(new L.LatLng(latitude, longitude), 15);
        $('#share-location').val(latitude.toFixed(6) + " - " + longitude.toFixed(6));
    }
    $('#myModal').on('shown.bs.modal', function () {
        map.invalidateSize();
    });
    map.on('click', function (e) {
        map.removeLayer(marker);
        var location = e.latlng;
        console.log(location);
        marker = L.marker([location.lat, location.lng], {icon: blueIcon}).addTo(map);
        $('#share-location').val(location.lat.toFixed(6) + " - " + location.lng.toFixed(6));
    });
</script>
<c:if test="${error != null}">
    <script>
        $(document).ready(function () {
            $.simplyToast('<c:out value="${error}"/>', 'danger');
        });
    </script>
</c:if>

</html>


