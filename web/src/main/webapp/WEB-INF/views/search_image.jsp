<%--
  Created by IntelliJ IDEA.
  User: duyle
  Date: 3/23/17
  Time: 1:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<%@include file="../jspf/header.jspf" %>
<link href="<c:url value="/resources/css/search-image.css"/>" rel="stylesheet">
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
                    <a class="navbar-brand" href="#">Lọc theo hình ảnh</a>
                </div>
                <div class="collapse navbar-collapse">
                    <%@include file="../jspf/navbar-right.jspf" %>
                </div>
            </div>
        </nav>

        <%--open content--%>
        <div class="content">

            <%--open container-fluid--%>
            <div class="container-fluid">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="card">
                                <div class="header">
                                    <h4 class="title">Lọc theo hình ảnh</h4>
                                </div>
                                <div class="content" id="list-species" style="display:block;overflow-y:auto;">
                                </div>
                            </div>
                        </div>
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
                                    <button value="button" class="btn btn-info btn-fill" onclick="show()">
                                        Upload
                                    </button>
                                    <div class="clearfix"></div>
                                </div>
                            </div>
                            <hr>
                        </div>
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
<script src="<c:url value="/resources/js/search-image1.js"/>"></script>

<c:if test="${error != null}">
    <script>
        $(document).ready(function () {
            $.simplyToast('<c:out value="${error}"/>', 'danger');
        });
    </script>
</c:if>

</html>

