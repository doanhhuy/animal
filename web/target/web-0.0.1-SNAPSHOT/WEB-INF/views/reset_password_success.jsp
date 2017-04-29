<%--
  Created by IntelliJ IDEA.
  User: duyle
  Date: 3/13/17
  Time: 4:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
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
                        <div class="col-lg-12 text-center">
                            <p><c:out value="${message}" /></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="text-center">
                                    <a href="<c:url value="/"/>" tabindex="5"
                                       class="forgot-password"
                                       style="text-decoration: none;"><span><<</span>Trang chủ</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
</body>
</html>
