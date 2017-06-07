<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<%@include file="../jspf/header.jspf" %>
<link rel="stylesheet" href="<c:url value="/resources/css/library_species.css"/>">
<body>
<div class="wrapper">
    <%@include file="../jspf/slider.jspf" %>

    <div class="main-panel" id="main">
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
                    <a class="navbar-brand" href="#">Quản lý tài khoản</a>
                </div>
                <div class="collapse navbar-collapse">
                    <%@include file="../jspf/navbar-right.jspf" %>
                </div>
            </div>
        </nav>
        <input type="text" id="search">
        <br>
        <%--open content--%>
        <div class="content">
            <%--open container-fluid--%>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Quản lý tài khoản</h4>
                            </div>
                            <div class="content">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="card">
                                                <div class="header">
                                                    <button type="button" class="btn btn-primary" onclick='createAccount();'>Tạo tài khoản</button>
                                                    <h4 class="title">Danh Sách Tài Khoản</h4>
                                                </div>
                                                <div class="content">
                                                    <table id="account-table" class="table table-hover table-striped">
                                                        <thead>
                                                        <th>Mã</th>
                                                        <th>Họ và tên</th>
                                                        <th>Tên đăng nhập</th>
                                                        <th>Chức vụ</th>
                                                        <th>Số điện thoại</th>
                                                        <th>Email</th>
                                                        <th>Sửa</th>
                                                        </thead>
                                                        <tbody id="table-body">
                                                        <c:forEach items="${list}" var="temp">
                                                            <tr>
                                                                <td>${temp.id}</td>
                                                                <td>${temp.fullName}</td>
                                                                <td>${temp.username}</td>
                                                                <td>${temp.roleName}</td>
                                                                <td>${temp.phonenumber}</td>
                                                                <td>${temp.email}</td>
                                                                <td><a href="#"
                                                                       onclick='updateAccount(${temp.id});'>Sửa</a></td>
                                                            </tr>
                                                        </c:forEach>
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
            </div>
            <%--close container-fluid--%>
        </div>
        <%--close content   --%>
    </div>
</div>
</body>

<div id="myModal" class="modal modal-wide fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title" id="modal-title"></h4>
            </div>
            <div class="modal-body" align="center" id="modal-body">
                <%--Phần giao diện và controller đọc tự code--%>
            </div>
        </div>
    </div>

</div>

<c:if test="${error != null}">
    <script>
        $(document).ready(function () {
            $.simplyToast('<c:out value="${error}"/>', 'danger');
        });
    </script>
</c:if>
<%@include file="../jspf/footer.jspf" %>
<script>
    $(document).ready(function () {
        $('#account-table').dataTable({
            "paging": true,
            "lengthMenu": [[5, 10, 20, -1], [5, 10, 20, "All"]],
            "searching": true,
            "info": true,
            "autoWidth": true,
            // Disable sorting on the first column
            "columnDefs": [{
                "targets": [1, 3, 4, 5],
                "orderable": false
            }],
            "language": {
                "sProcessing": "Đang xử lý...",
                "sLengthMenu": "Xem _MENU_ mục",
                "sZeroRecords": "Không tìm thấy dòng nào phù hợp",
                "sInfo": "Đang xem _START_ đến _END_ trong tổng số _TOTAL_ mục",
                "sInfoEmpty": "Đang xem 0 đến 0 trong tổng số 0 mục",
                "sInfoFiltered": "(được lọc từ _MAX_ mục)",
                "sInfoPostFix": "",
                "sSearch": "Tìm:",
                "sUrl": "",
                "oPaginate": {
                    "sFirst": "Đầu",
                    "sPrevious": "Trước",
                    "sNext": "Tiếp",
                    "sLast": "Cuối"
                }
            }
        });
    });

    function createAccount() {
        $('#myModal').modal('show');
    }

</script>
</html>
