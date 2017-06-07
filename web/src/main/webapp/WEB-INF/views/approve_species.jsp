<%--
  Created by IntelliJ IDEA.
  User: duyle
  Date: 5/2/17
  Time: 9:46 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<%@include file="../jspf/header.jspf" %>
<link href="<c:url value="/resources/css/approve_species.css"/>" rel="stylesheet">
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

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Danh Sách Loài Cần Phê Duyệt</h4>
                                <p class="category">Trước khi duyệt cần phải kiểm tra thông tin, và thêm những thông
                                    tin cần thiết nếu thiếu</p>
                            </div>
                            <div class="content">
                                <table id="approve-table" class="table table-hover table-striped">
                                    <thead>
                                    <th>Tên khoa học</th>
                                    <th>Tên tiếng việt</th>
                                    <th>Nơi cư trú</th>
                                    <th>Tên người chia sẻ</th>
                                    <th>Duyệt</th>
                                    <th>Loại bỏ</th>
                                    </thead>
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
</body>
<%@include file="../jspf/footer.jspf" %>
<script src="<c:url value="/resources/js/approve_species1.js"/>"></script>

<c:if test="${error != null}">
    <script>
        $(document).ready(function () {
            $.simplyToast('<c:out value="${error}"/>', 'danger');
        });
    </script>
</c:if>

</html>

