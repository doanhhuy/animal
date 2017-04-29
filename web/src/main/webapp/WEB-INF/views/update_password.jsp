<%--
  Created by IntelliJ IDEA.
  User: duyle
  Date: 3/14/17
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
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
<body>
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
                            <form:form action="/updatepassword/${email}" commandName="resetPasswordForm"
                                       method="post"
                                       style="display: block;" data-toggle="validator">
                                <div class="form-group">
                                    <input type="text" name="code" id="username" tabindex="1" class="form-control"
                                           placeholder="Mã xác nhận" required
                                           pattern="[0-9]{7}"
                                           data-error="Không được viết có dấu và kí tự đặt biệt!!">
                                    <div class="help-block with-errors"></div>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="password" tabindex="2"
                                           class="form-control" placeholder="Mật khẩu mới" required
                                           pattern=".{6,}"
                                           data-error="Mật khẩu tối thiểu 6 ký tự!!">
                                    <div class="help-block with-errors"></div>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="confirm-password" id="confirm-password" tabindex="2"
                                           class="form-control" placeholder="Nhập lại mật khẩu mới" required
                                           data-match="#password"
                                           pattern=".{6,}"
                                           data-error="Mật khẩu nhập lại không đúng!!">
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
                                                   style="text-decoration: none;"><span><<</span>Đăng nhập</a>
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
<% if (request.getParameter("error") != null) {
    int error = Integer.valueOf(request.getParameter("error"));
    if (error == 0) {
%>
<script>
    $(document).ready(function () {
        $.simplyToast('Email không tồn tại!', 'danger');
    });
</script>
<% } else if (error == 1) { %>
<script>
    $(document).ready(function () {
        $.simplyToast('Link này đã hết hạn!', 'danger');
    });
</script>
<%} else if (error == 2) {%>
<script>
    $(document).ready(function () {
        $.simplyToast('Mã kích hoạt không đúng!', 'danger');
    });
</script>
<%
        }
    }
%>
</body>
</html>
