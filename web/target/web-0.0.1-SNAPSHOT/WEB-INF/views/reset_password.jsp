<%--
  Created by IntelliJ IDEA.
  User: duyle
  Date: 3/13/17
  Time: 4:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Title</title>
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/formValidation.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/formValidation.min.css"/>">
    <link href="<c:url value="/resources/css/pe-icon-7-stroke.css"/>" rel="stylesheet"/>
</head>
<body style="background: #2e6da4;">
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-login">
                <div class="text-center">
                    <h3 class="text-center">Quên Mật Khẩu?</h3>
                    <p>Nếu bạn quên mật khẩu - cập nhật ở đây.</p>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form:form id="login-form" action="/resetpassword" commandName="resetPasswordForm"
                                       method="post"
                                       style="display: block;" data-toggle="validator">
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i
                                                class="glyphicon glyphicon-envelope color-blue"></i></span>
                                        <input type="text" name="email" id="login-username" tabindex="1"
                                               class="form-control"
                                               placeholder="Nhập email bạn đã đăng ký" required
                                               pattern=".+@.+\..+"
                                               data-error="Không phải địa chỉ email!!">
                                    </div>
                                    <div class="help-block with-errors"></div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit" tabindex="4"
                                                   class="form-control btn btn-register" value="Xác Nhận">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="text-center">
                                                <a href="<c:url value="/login"/>" tabindex="5"
                                                   class="forgot-password"
                                                   style="text-decoration: none;"><span><<</span>Trở lại</a>
                                            </div>
                                        </div>
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
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="/resources/js/simply-toast.js"></script>
<script src="<c:url value="/resources/js/login.js"/>"></script>
<script src="<c:url value="/resources/js/validator.js"/>"></script>
<script src="<c:url value="/resources/js/validator.min.js"/>"></script>
<c:if test="${error != null}">
    <script>
        $(document).ready(function () {
            $.simplyToast('<c:out value="${error}"/>', 'danger');
        });
    </script>
</c:if>
</body>
</html>
