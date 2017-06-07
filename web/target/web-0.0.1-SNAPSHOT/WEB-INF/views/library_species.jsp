<%--
  Created by IntelliJ IDEA.
  User: duyle
  Date: 3/17/17
  Time: 10:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<%@include file="../jspf/header.jspf" %>
<link rel="stylesheet" href="<c:url value="/resources/css/library_species.css"/>">
<style>

</style>
<body>
<div class="wrapper">
    <%@include file="../jspf/slider.jspf" %>

    <div class="main-panel" id="main">
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
                    <a class="navbar-brand" href="#">Thư viện loài</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-left">
                        <li>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-filter"></i>
                            </a>
                            <ul id="login-dp" class="dropdown-menu">
                                <li>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div id="checkboxes">
                                                <input type="radio" class="checkbox-filter" name="filter" value="0"
                                                       checked="checked">Tất
                                                cả<br>
                                                <input type="radio" class="checkbox-filter" name="filter" value="1">Động
                                                vật quý hiếm<br>
                                                <input type="radio" class="checkbox-filter" name="filter"
                                                       value="2">Động
                                                vật thường<br>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <%@include file="../jspf/navbar-right.jspf" %>
                </div>
            </div>
        </nav>
        <input type="text" id="search">
        <br>
        <%--open content--%>
        <div class="content">
            <%--open container-fluid--%>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Thư viện loài</h4>
                            </div>
                            <div class="content" id="list-species">
                            </div>
                            <br>
                            <div align="center">
                                <a href="#" id="load_more">Xem thêm...</a>
                            </div>
                            <br>
                        </div>
                    </div>
                </div>
            </div>
            <%--close container-fluid--%>
        </div>
        <%--close content   --%>
    </div>
    <a class="scrollToTop" href="#" title="Top" style="display: inline;">
        <i class="fa fa-arrow-up fa-2x" aria-hidden="true"></i></a>
</div>
</body>
<c:if test="${error != null}">
    <script>
        $(document).ready(function () {
            $.simplyToast('<c:out value="${error}"/>', 'danger');
        });
    </script>
</c:if>
<%@include file="../jspf/footer.jspf" %>
<script src="<c:url value="/resources/js/library_species.js"/>"></script>

</html>


