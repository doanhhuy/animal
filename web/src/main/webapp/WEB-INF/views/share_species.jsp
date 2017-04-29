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
                    <a class="navbar-brand" href="#">Chia sẻ loài</a>
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
                                    <h4 class="title">Thông tin loài chia sẻ</h4>
                                </div>
                                <div class="content">
                                    <div class="row">
                                        <div class="col-md-10 col-md-offset-1">
                                            <div class="form-group">
                                                <label>Tên tiếng việt</label>
                                                <input type="text" class="form-control"
                                                       name="fullName" value=""
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
                                                       name="birthday" data-error="Không được viết có dấu và kí tự đặt biệt!!">
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-10 col-md-offset-1">
                                            <div class="form-group">
                                                <label>Địa điểm hiện tại</label>
                                                <div class="input-group">
                                                    <input type="text" class="form-control" placeholder="">
                                                    <span class="input-group-btn">
                                                        <button class="btn btn-secondary" type="button">Bản đồ!</button>
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
                                                <input type="text" class="form-control" name="email"
                                                       placeholder="Company" value=""
                                                       data-error="Không phải địa chỉ email!!">
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-10 col-md-offset-1">
                                            <div class="form-group">
                                                <label>Đặc điểm giới tính</label>
                                                <input type="text" class="form-control" name="address"
                                                       placeholder="Home Address"
                                                       value=""
                                                       data-error="Không được viết có dấu và kí tự đặt biệt!!">
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-10 col-md-offset-1">
                                            <div class="form-group">
                                                <label>Đặc điểm khác</label>
                                                <input type="text" class="form-control" name="phonenumber"
                                                       placeholder="City"
                                                       value=""
                                                       data-error="Không phải số điện thoại!!">
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-10 col-md-offset-1">
                                            <div class="form-group">
                                                <label>Tập tính sinh học</label>
                                                <input type="text" class="form-control" name="roleName" disabled
                                                       placeholder="City"
                                                       value="">
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-10 col-md-offset-1">
                                            <div class="form-group">
                                                <label>Thức ăn</label>
                                                <input type="text" class="form-control" name="detail"
                                                       value="${accountAPI.detail}">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-10 col-md-offset-1">
                                            <div class="form-group">
                                                <label>Kích cỡ</label>
                                                <input type="text" class="form-control" name="detail"
                                                       value="">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-10 col-md-offset-1">
                                            <div class="form-group">
                                                <label>Xuất xứ</label>
                                                <input type="text" class="form-control" name="detail"
                                                       value="">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-10 col-md-offset-1">
                                            <div class="form-group">
                                                <label>Chi</label>
                                                <input type="text" class="form-control" name="detail"
                                                       value="">
                                            </div>
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-info btn-fill pull-right">Cập nhật
                                    </button>
                                    <div class="clearfix"></div>
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
                                        Tải lên
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
<script src="<c:url value="/resources/js/share-species.js"/>"></script>

<c:if test="${error != null}">
    <script>
        $(document).ready(function () {
            $.simplyToast('<c:out value="${error}"/>', 'danger');
        });
    </script>
</c:if>

</html>


