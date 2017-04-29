<%--
  Created by IntelliJ IDEA.
  User: duyle
  Date: 06/03/2017
  Time: 03:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Sign-Up/Login Form</title>
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
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="#" class="active" id="login-form-link">Login</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="#" id="register-form-link">Register</a>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form:form id="login-form" commandName="loginForm" action="/login" method="post"
                                       style="display: block;" data-toggle="validator">
                                <div class="form-group">
                                    <input type="text" name="username" id="login-username" tabindex="1"
                                           class="form-control"
                                           placeholder="Tên đăng nhập" required
                                           pattern="^[0-9a-zA-Z\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+$"
                                           data-error="Không được viết có dấu và kí tự đặt biệt!!">
                                    <div class="help-block with-errors"></div>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" tabindex="2"
                                           class="form-control" placeholder="Mật khẩu" required
                                           pattern=".{6,}"
                                           data-error="Mật khẩu tối thiểu 6 ký tự!!">
                                    <div class="help-block with-errors"></div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                                <%--<button value="button" class="form-control btn btn-login" id="login-submit"--%>
                                                <%--tabindex="4" onclick="login()">--%>
                                                <%--Đăng Nhập--%>
                                                <%--</button>--%>
                                            <input type="submit" name="login-submit" id="login-submit" tabindex="4"
                                                   class="form-control btn btn-login" value="Đăng Nhập">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="text-center">
                                                <a href="<c:url value="/resetpassword"/>" tabindex="5"
                                                   class="forgot-password">Quên mật khẩu?</a>
                                                &nbsp&nbsp&nbsp
                                                <a href="<c:url value="/"/>" tabindex="5"
                                                   class="forgot-password"
                                                   style="text-decoration: none;"><span><<</span>Trở lại</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form:form>

                            <form:form id="register-form" action="/register" commandName="registerForm" method="post"
                                       role="form" style="display: none;" data-toggle="validator">
                                <div class="form-group">
                                    <input type="text" name="username" id="username" tabindex="1" class="form-control"
                                           placeholder="Tên đăng nhập" required
                                           pattern="^[0-9a-zA-Z\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+$"
                                           data-error="Không được viết có dấu và kí tự đặt biệt!!">
                                    <div class="help-block with-errors"></div>
                                </div>
                                <div class="form-group">
                                    <input type="email" name="email" id="email" tabindex="1" class="form-control"
                                           placeholder="Email" required
                                           pattern=".+@.+\..+"
                                           data-error="Không phải địa chỉ email!!">
                                    <div class="help-block with-errors"></div>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="password" tabindex="2"
                                           class="form-control" placeholder="Mật khẩu" required
                                           pattern=".{6,}"
                                           data-error="Mật khẩu tối thiểu 6 ký tự!!">
                                    <div class="help-block with-errors"></div>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="confirm-password" id="confirm-password" tabindex="2"
                                           class="form-control" placeholder="Nhập lại mật khẩu" required
                                           data-match="#password"
                                           pattern=".{6,}"
                                           data-error="Mật khẩu nhập lại không đúng!!">
                                    <div class="help-block with-errors"></div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="register-submit" id="register-submit"
                                                   tabindex="4" class="form-control btn btn-register"
                                                   value="Đăng Ký">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="text-center">
                                                <a href="<c:url value="/"/>" tabindex="5"
                                                   class="forgot-password"
                                                   style="text-decoration: none;"><span><<</span>Trở lại</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <div id="result"></div>
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
<script src="<c:url value="/resources/js/loadingoverlay.min.js"/>"></script>
<script src="<c:url value="/resources/js/loadingoverlay_progress.min.js"/>"></script>
<script>
    $(document).ready(function () {
        $('#login-form').submit(function (e) {
            e.preventDefault();
            login();
        })
    });

    $(document).ajaxStart(function () {
        $.LoadingOverlay("show");
    });
    $(document).ajaxComplete(function () {
        $.LoadingOverlay("hide");
    });

    function login() {
        var str = $("#login-form").serialize();
        $.ajax({
            type: "POST",
            url: "${home}/login",
            data: str,
            encoding: "UTF-8",
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                $('#result').html(data);
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    }

</script>
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

<c:if test="${loginForm.username != null}">
    <script>
        $(function () {
            $("#login-form").delay(100).fadeIn(100);
            $("#register-form").fadeOut(100);
            $("#login-username").val('<c:out value="${loginForm.username}"/>');
            $('#register-form-link').removeClass('active');
            $('#login-form-link').removeClass('active');
        });
    </script>
</c:if>

<c:if test="${registerForm.username != null}">
    <script>
        $(function () {
            $("#register-form").delay(100).fadeIn(100);
            $("#login-form").fadeOut(100);
            $("#username").val('<c:out value="${registerForm.username}"/>');
            $("#email").val('<c:out value="${registerForm.email}"/>');
            $('#login-form-link').removeClass('active');
            $('#register-form-link').addClass('active');
        });
    </script>
</c:if>
</body>
</html>
