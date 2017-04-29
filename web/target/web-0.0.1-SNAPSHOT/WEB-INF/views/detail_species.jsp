<%--
  Created by IntelliJ IDEA.
  User: duyle
  Date: 3/20/17
  Time: 1:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<%@include file="../jspf/header.jspf" %>
<link href="<c:url value="/resources/css/detail_species.css"/>" rel="stylesheet"/>
</head>
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
                    <a class="navbar-brand" href="#">Chi tiết loài</a>
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
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Chi tiết loài</h4>
                            </div>
                            <div class="content">
                                <div class="row">
                                    <c:if test="${message != null}">
                                        <div class="col-md-4">
                                            <div class="card">
                                                <div class="content">
                                                    <div class="carousel slide"
                                                         data-ride="carousel">
                                                        <!-- Wrapper for slides -->
                                                        <div class="carousel-inner" role="listbox">
                                                            <div class="item active">
                                                                <img
                                                                        src="http://is.tnu.edu.vn/wp-content/themes/motive/images/no_image.jpg"
                                                                        alt="Norway" class="my-img"
                                                                >
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-8">
                                            <div class="card ">
                                                <div class="header">
                                                    <p class="p-category"><c:out value="${message}"/></p>
                                                </div>
                                                <div class="content">
                                                    <div class="table-full-width">
                                                        <table class="table">
                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${species != null}">
                                    <c:set var="species" value="${species}"/>
                                    <div class="col-md-4">
                                        <div class="card">
                                            <div class="content">
                                                <div id="myCarousel" class="carousel slide"
                                                     data-ride="carousel">
                                                    <!-- Wrapper for slides -->
                                                    <div class="carousel-inner" role="listbox">
                                                        <c:if test="${species.image == null}">
                                                            <div class="item active">
                                                                <img
                                                                        src="http://is.tnu.edu.vn/wp-content/themes/motive/images/no_image.jpg"
                                                                        alt="Norway" class="my-img"
                                                                >
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${species.image != null}">
                                                            <c:set var="img"
                                                                   value="${fn:split(species.image, ',')}"/>
                                                            <c:forEach var="imgage" items="${img}" varStatus="loop">
                                                                <c:if test="${loop.index == 0}">
                                                                    <div class="item active">
                                                                        <img src="http://localhost:8081/resources/images/${imgage}"
                                                                             alt="Norway"
                                                                             class="my-img">
                                                                    </div>
                                                                </c:if>
                                                                <c:if test="${loop.index != 0}">
                                                                    <div class="item">
                                                                        <img src="http://localhost:8081/resources/images/${imgage}"
                                                                             alt="Norway"
                                                                             class="my-img">
                                                                    </div>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:if>
                                                    </div>

                                                    <!-- Left and right controls -->
                                                    <a class="left carousel-control" href="#myCarousel"
                                                       role="button"
                                                       data-slide="prev">
                                    <span class="glyphicon glyphicon-chevron-left"
                                          aria-hidden="true"></span>
                                                        <span class="sr-only">Trước</span>
                                                    </a>
                                                    <a class="right carousel-control" href="#myCarousel"
                                                       role="button"
                                                       data-slide="next">
                                    <span class="glyphicon glyphicon-chevron-right"
                                          aria-hidden="true"></span>
                                                        <span class="sr-only">Sau</span>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-8">
                                        <div class="table-full-width">
                                            <table class="table">
                                                <tbody>
                                                <tr>
                                                    <td class="td-content">
                                                        <h4 class="h4-title"><c:out
                                                                value="${species.vietnameseName}"/></h4>
                                                        <p class="p-category"><strong>Tên khoa
                                                            học:</strong> ${species.scienceName}</p>
                                                        <p class="p-category"><strong>Tên thường
                                                            gọi:</strong> ${species.otherName}</p>
                                                        <p class="p-category">
                                                            <strong>Chi:</strong> ${species.scienceNameGenus}
                                                            - ${species.vietnameseNameGenus}</p>
                                                        <p class="p-category">
                                                            <strong>Họ:</strong> ${species.vietnameseNameFamily}</p>
                                                        <p class="p-category">
                                                            <strong>Mức độ báo động:</strong> ${species.alertlevel}</p>
                                                        <p class="p-category">
                                                            <strong>Số lượng cá
                                                                thể:</strong> ${species.individualQuantity}</p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="td-content">
                                                        <p class="p-title">Phát hiển bởi </p>
                                                        <p class="p-category">${species.discovererName}</p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="td-content">
                                                        <p class="p-title">Đặc điểm chung </p>
                                                        <p class="p-category"><strong>Đặc điểm sinh sản:</strong>
                                                                ${species.reproductionTraits}</p>
                                                        <p class="p-category"><strong>Đặc điểm giới tính:</strong>
                                                                ${species.sexualTraits}</p>
                                                        <p class="p-category"><strong>Đặc điểm khác:</strong>
                                                                ${species.ortherTraits}</p>
                                                        <p class="p-category"><strong>Tập tính sinh học:</strong>
                                                                ${species.biologicalBehavior}</p>
                                                        <p class="p-category"><strong>Thức ăn:</strong>
                                                                ${species.food}</p>
                                                        <p class="p-category"><strong>Kích cỡ:</strong>
                                                                ${species.mediumSize}</p>
                                                        <p class="p-category"><strong>Xuất xứ:</strong>
                                                                ${species.origin}</p>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                </c:if>
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
</div>
</body>
<%@include file="../jspf/footer.jspf" %>
</html>
