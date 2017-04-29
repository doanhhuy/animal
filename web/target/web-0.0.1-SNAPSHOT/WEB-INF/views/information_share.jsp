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
                                    <a href="<c:url value="/species/list/share/map"/>">Bản đồ</a>
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

    <div id="modelMap" class="modal modal-wide fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Maps</h4>
                </div>
                <div class="modal-body">
                    <div class="modal-body" id="map-canvas"></div>
                </div>
            </div>
        </div>
    </div>
</body>

<%@include file="../jspf/footer.jspf" %>

<script src="<c:url value="/resources/js/information-share.js"/>"></script>

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