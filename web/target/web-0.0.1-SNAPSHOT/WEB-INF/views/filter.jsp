<%--
  Created by IntelliJ IDEA.
  User: duyle
  Date: 3/24/17
  Time: 6:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<%@include file="../jspf/header.jspf" %>
<link rel="stylesheet" href="<c:url value="/resources/css/filter.css"/>">
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
                    <a class="navbar-brand" href="#">Lọc theo phân cấp</a>
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
                        <div class="col-md-12">
                            <div class="card">
                                <div class="header">
                                    <h4 class="title">Lọc theo phân cấp</h4>
                                </div>
                                <div class="content">

                                    <div id="exTab3" class="container" style="width: 100%;">
                                        <ul class="nav nav-tabs">
                                            <li class="active">
                                                <a href="#1" data-toggle="tab">Theo từng cấp</a>
                                            </li>
                                            <li><a href="#2" data-toggle="tab">Theo thứ tự cấp</a>
                                            </li>
                                        </ul>

                                        <div class="tab-content" id="content" style="padding: 3px 0px">
                                            <div class="tab-pane active" id="1">
                                                <div class="container-fluid">
                                                    <div class="row">
                                                        <div class="col-md-7" style="padding-left: 0px;">
                                                            <div class="card">
                                                                <div class="content" id="list-species"
                                                                     style="display:block;overflow-y:auto; padding: 0px;">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-5">
                                                            <div class="card card-user">
                                                                <div class="header">
                                                                    <h5 class="title">Chọn phân cấp</h5>
                                                                </div>
                                                                <div class="content" align="center">
                                                                    <div class="row">
                                                                        <div class="col-md-12">
                                                                            <div class="form-group">
                                                                                <select id="type" class="form-control"
                                                                                        onclick="loadData();" required
                                                                                        data-error="Phải chọn phân cấp!!">
                                                                                    <option selected value="0">Chọn phân
                                                                                        cấp
                                                                                    </option>
                                                                                    <option value="1">Ngành</option>
                                                                                    <option value="2">Lớp</option>
                                                                                    <option value="3">Bộ</option>
                                                                                    <option value="4">Họ</option>
                                                                                    <option value="5">Chi</option>
                                                                                </select>
                                                                                <div class="help-block with-errors"></div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="row">
                                                                        <div class="col-md-12">
                                                                            <div class="form-group" id="select-data">
                                                                                <select class="form-control">
                                                                                    <option selected value="">Phải chọn
                                                                                        phân cấp
                                                                                    </option>
                                                                                </select>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <input type="button"
                                                                           class="btn btn-info btn-fill"
                                                                           onclick="show()"
                                                                           value="Tìm kiếm">
                                                                    <div class="clearfix"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                            <div class="tab-pane" id="2">
                                                <div class="container-fluid">
                                                    <div class="row">
                                                        <div class="col-md-7" style="padding-left: 0px;">
                                                            <div class="card">
                                                                <div class="content" id="list-species1"
                                                                     style="display:block;overflow-y:auto; padding: 0px;">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-5">
                                                            <form:form id="search-form" action=""
                                                                       data-toggle="validator">
                                                                <div class="card card-user">
                                                                    <div class="header">
                                                                        <h5 class="title">Chọn phân cấp</h5>
                                                                    </div>
                                                                    <div class="content" align="center">
                                                                        <div class="row">
                                                                            <div class="col-md-12">
                                                                                <div class="form-group">
                                                                                    <select id="phylum"
                                                                                            class="form-control"
                                                                                            onchange="loadClass();"
                                                                                            required
                                                                                            data-error="Phải chọn ngành!!">
                                                                                        <option selected value="">Chọn
                                                                                            ngành
                                                                                        </option>
                                                                                        <c:if test="${phylumAPIS != null}"/>
                                                                                        <c:forEach var="obj"
                                                                                                   items="${phylumAPIS}">
                                                                                            <option value="${obj.id}">${obj.scienceName}
                                                                                                - ${obj.vietnameseName}
                                                                                            </option>
                                                                                        </c:forEach>
                                                                                    </select>
                                                                                    <div class="help-block with-errors"></div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="row">
                                                                            <div class="col-md-12">
                                                                                <div class="form-group" id="class">
                                                                                    <select class="form-control">
                                                                                        <option selected value="">Phải
                                                                                            chọn
                                                                                            ngành
                                                                                        </option>
                                                                                    </select>
                                                                                    <div class="help-block with-errors"></div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="row">
                                                                            <div class="col-md-12">
                                                                                <div class="form-group" id="order">
                                                                                    <select class="form-control">
                                                                                        <option selected value="">Phải
                                                                                            chọn
                                                                                            lớp
                                                                                        </option>
                                                                                    </select>
                                                                                    <div class="help-block with-errors"></div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="row">
                                                                            <div class="col-md-12">
                                                                                <div class="form-group" id="family">
                                                                                    <select class="form-control">
                                                                                        <option selected value="">Phải
                                                                                            chọn
                                                                                            bộ
                                                                                        </option>
                                                                                    </select>
                                                                                    <div class="help-block with-errors"></div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="row">
                                                                            <div class="col-md-12">
                                                                                <div class="form-group">
                                                                                    <div class="form-group" id="genus">
                                                                                        <select class="form-control">
                                                                                            <option selected value="">
                                                                                                Phải
                                                                                                chọn lớp
                                                                                            </option>
                                                                                        </select>
                                                                                        <div class="help-block with-errors"></div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <input type="submit"
                                                                               class="btn btn-info btn-fill"
                                                                               onclick="showSpecies()"
                                                                               value="Tìm kiếm">
                                                                        <div class="clearfix"></div>
                                                                    </div>
                                                                </div>
                                                            </form:form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
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
</div>
</body>
<%@include file="../jspf/footer.jspf" %>
<script src="../../resources/js/filter.js"></script>
<script src="../../resources/js/filter3.js"></script>
</html>
