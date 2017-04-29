<%--
  Created by IntelliJ IDEA.
  User: duyle
  Date: 3/20/17
  Time: 5:23 PM
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
<link rel="stylesheet" href="<c:url value="/resources/css/detail_species.css"/>">

<style>
    .carousel-inner > .item > img,
    .carousel-inner > .item > a > img {
        width: 100%;
        margin: auto;
    }
</style>
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
                    <a class="navbar-brand" href="#">Thông tin cá nhân</a>
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
                        <div class="col-md-7">
                            <div class="card">
                                <div class="header">
                                    <h4 class="title">Thông tin cá nhân</h4>
                                </div>
                                <div class="content">
                                    <c:if test="${accountAPI != null}">
                                        <form:form action="/updateaccount" commandName="accountForm" method="post"
                                                   acceptCharset="utf-8" data-toggle="validator">
                                            <input type="hidden" name="id" value="${accountAPI.id}">
                                            <input type="hidden" name="idMember" value="${accountAPI.idMember}">
                                            <input type="hidden" name="idRole" value="${accountAPI.idRole}">
                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Họ và tên</label>
                                                        <input type="text" class="form-control"
                                                               name="fullName" value="${accountAPI.fullName}" required
                                                               pattern="^[0-9a-zA-Z\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+$"
                                                               data-error="Không được viết có dấu và kí tự đặt biệt!!">
                                                    </div>
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Ngày sinh</label>
                                                        <input type="text" class="form-control"
                                                               name="birthday"
                                                               id="birthday"
                                                               value="<fmt:formatDate pattern="dd-MM-yyyy"
                                                                    value="${accountAPI.birthday}" />"
                                                               data-inputmask="'alias': 'yyyy-mm-dd'" required data-mask
                                                               required data-error="Phải chọn ngày sinh !!">
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Địa chỉ email</label>
                                                        <input type="text" class="form-control" name="email"
                                                               placeholder="Company" value="${accountAPI.email}"
                                                               required
                                                               pattern=".+@.+\..+"
                                                               data-error="Không phải địa chỉ email!!">
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Địa chỉ</label>
                                                        <input type="text" class="form-control" name="address"
                                                               placeholder="Home Address"
                                                               value="${accountAPI.address}" required
                                                               pattern="^[0-9a-zA-Z\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+$"
                                                               data-error="Không được viết có dấu và kí tự đặt biệt!!">
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Số điện thoại</label>
                                                        <input type="text" class="form-control" name="phonenumber"
                                                               placeholder="City"
                                                               value="${accountAPI.phonenumber}" required
                                                               pattern="^[0-9a-zA-Z\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+$"
                                                               data-error="Không phải số điện thoại!!">
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Chức vụ</label>
                                                        <input type="text" class="form-control" name="roleName" disabled
                                                               placeholder="City"
                                                               value="${accountAPI.roleName}">
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Ghi chú</label>
                                                        <input type="text" class="form-control" name="detail"
                                                               value="${accountAPI.detail}">
                                                    </div>
                                                </div>
                                            </div>
                                            <button type="submit" class="btn btn-info btn-fill pull-right">Cập nhật
                                            </button>
                                            <div class="clearfix"></div>
                                        </form:form>
                                    </c:if>
                                    <c:if test="${accountAPI== null}">
                                        <form:form action="/updateaccount" commandName="accountForm" method="post"
                                                   acceptCharset="utf-8" data-toggle="validator">
                                            <input type="hidden" name="id" value="">
                                            <input type="hidden" name="idMember" value="">
                                            <input type="hidden" name="idRole" value="$">
                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Họ và tên</label>
                                                        <input type="text" class="form-control"
                                                               name="fullName" value="" required
                                                               pattern="^[0-9a-zA-Z\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+$"
                                                               data-error="Không được viết có dấu và kí tự đặt biệt!!">
                                                    </div>
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Ngày sinh</label>
                                                        <input type="text" class="form-control"
                                                               name="birthday"
                                                               id="birthday"
                                                               value=""
                                                               data-inputmask="'alias': 'yyyy-mm-dd'" required data-mask
                                                               required data-error="Phải chọn ngày sinh !!">
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Địa chỉ email</label>
                                                        <input type="text" class="form-control" name="email"
                                                               value=""
                                                               required
                                                               pattern=".+@.+\..+"
                                                               data-error="Không phải địa chỉ email!!">
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Địa chỉ</label>
                                                        <input type="text" class="form-control" name="address"
                                                               value="" required
                                                               pattern="^[0-9a-zA-Z\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+$"
                                                               data-error="Không được viết có dấu và kí tự đặt biệt!!">
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Số điện thoại</label>
                                                        <input type="text" class="form-control" name="phonenumber"
                                                               value="" required
                                                               pattern="^[0-9a-zA-Z\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+$"
                                                               data-error="Không phải số điện thoại!!">
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Chức vụ</label>
                                                        <input type="text" class="form-control" name="roleName" disabled
                                                               value="">
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-10 col-md-offset-1">
                                                    <div class="form-group">
                                                        <label>Ghi chú</label>
                                                        <input type="text" class="form-control" name="detail"
                                                               value="">
                                                    </div>
                                                </div>
                                            </div>
                                            <button type="submit" class="btn btn-info btn-fill pull-right">Cập nhật
                                            </button>
                                            <div class="clearfix"></div>
                                        </form:form>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5">
                            <div class="card card-user">
                                <div class="image">
                                    <img src="https://ununsplash.imgix.net/photo-1431578500526-4d9613015464?fit=crop&fm=jpg&h=300&q=75&w=400"
                                         alt="..."/>
                                </div>
                                <div class="content">
                                    <div class="author">
                                        <img class="avatar border-gray"
                                             src="<c:url value="../../resources/img/faces/member.png" />"
                                             alt="..."/>
                                    </div>
                                    <%--<form:form action="/changepassword" commandName="accountForm" method="post">--%>
                                    <input type="hidden" name="id" value="${accountAPI.id}">
                                    <div class="row">
                                        <div class="col-md-10 col-md-offset-1">
                                            <div class="form-group">
                                                <label>Tên đăng nhập</label>
                                                <input type="text" class="form-control" disabled
                                                       name="fullName" value="${accountAPI.username}">
                                            </div>
                                        </div>
                                    </div>
                                    <div align="center">
                                        <button type="button" onclick="showUpdateForm()"
                                                class="btn btn-info btn-fill">Cập nhật mật
                                            khẩu
                                        </button>
                                    </div>
                                    <div class="clearfix"></div>
                                    <%--</form:form>--%>
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
<script src="<c:url value="/resources/js/moment.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js" />"></script>
<script src="<c:url value="/resources/js/user-profile.js"/>"></script>
<script src="/resources/js/simply-toast.js"></script>

<c:if test="${message != null}">
    <script>
        $(document).ready(function () {
            $.simplyToast('<c:out value="${message}"/>', 'success');
        });
    </script>
</c:if>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form:form action="/changepassword" commandName="accountForm" method="post" data-toggle="validator">
                <div class="modal-header">
                    <h3 class="modal-title" id="exampleModalLabel">Cập nhật mật khẩu</h3>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="id" value="${accountAPI.id}">
                    <div class="row">
                        <div class="col-md-8 col-md-offset-2" id="div_brand">
                            <div class="form-group">
                                <label>Mật khẩu cũ</label>
                                <input type="password" class="form-control"
                                       name="password" placeholder=""
                                       required
                                       pattern=".{6,}"
                                       data-error="Mật khẩu tối thiểu 6 ký tự!!">
                                <div class="help-block with-errors">
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Mật khẩu mới</label>
                                <input type="password" id="password" class="form-control"
                                       name="newPassword" placeholder=""
                                       required
                                       pattern=".{6,}"
                                       data-error="Mật khẩu tối thiểu 6 ký tự!!">
                                <div class="help-block with-errors">
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Nhập lại mật khẩu mới</label>
                                <input type="password" class="form-control"
                                       name="input_update_assest_types_name" placeholder=""
                                       required
                                       data-match="#password"
                                       pattern=".{6,}"
                                       data-error="Mật khẩu nhập lại không đúng!!">
                                <div class="help-block with-errors">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                    <button type="submit" class="btn btn-info btn-fill pull-right">Cập nhật</button>
                </div>
            </form:form>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#datetimepicker').datetimepicker();
    })
    function showUpdateForm() {
        $('#myModal').modal('show');
    }
</script>

<% if (request.getParameter("error") != null) {
    int error = Integer.valueOf(request.getParameter("error"));
    if (error == 0) {
%>
<script>
    $(document).ready(function () {
        $.simplyToast('Mật khẩu cũ không đúng!', 'danger');
    });
</script>
<% } else if (error == 1) {
%>
<script>
    $(document).ready(function () {
        $.simplyToast('Mật khẩu cũ không đúng!', 'danger');
    });
</script>
<%
        }
    }%>
<c:if test="${error != null}">
    <script>
        $(document).ready(function () {
            $.simplyToast('<c:out value="${error}"/>', 'danger');
        });
    </script>
</c:if>
</html>

