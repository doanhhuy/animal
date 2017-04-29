<%--
  Created by IntelliJ IDEA.
  User: duyle
  Date: 05/03/2017
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
<%--<table>--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<th>Mã ngành</th>--%>
<%--<th>Kí hiệu</th>--%>
<%--<th>Danh pháp khoa học</th>--%>
<%--<th>Giới</th>--%>
<%--<th>Mã người tạo</th>--%>
<%--<th>Năm phát hiện</th>--%>
<%--<th>Tên Tiếng Việt</th>--%>
<%--<th>Người phát hiện</th>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--<tbody>--%>
<%--<c:forEach items="${phylumForms}" var="temp">--%>
<%--<tr>--%>
<%--<td>${temp.id}</td>--%>
<%--<td>${temp.discovererName}</td>--%>
<%--</tr>--%>
<%--</c:forEach>--%>
<%--</tbody>--%>
<%--</table>--%>
<input type="button" onclick="searchAjax(1)">
<div id="ajax-response">
</div>
<script src="<c:url value="/resources/js/jquery-1.10.2.js"/>" type="text/javascript"></script>
<script>

    function searchAjax(id) {

        $.ajax({
            type: "GET",
            contentType: "application/json; charset=UTF-8",
            url: "${home}species/habitat",
            encoding: "UTF-8",
            data: {
                id: id
            },
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                if (data != null) {
                    for (var obj in data) {
                        result = "<div class=\"w3-container\" style=\"height: auto\">"
                            + "<div class=\"w3-card-4 index-card\">"
                                + "<img src=\"\" width=\"150px\" height=\"150px\" alt=\"Avatar\""
                                +" class=\"w3-left w3-margin-right\">"
                                + "<div align=\"left\" style='padding-top: 5px;'>"
                                    + "<p class=\"vietnamese-name\"></p>"
                                    + "<p class=\"science-name\">Mang</p>"
                                    + "<p class=\"science-name\">Mang</p>"
                                    + "<p class=\"science-name\">Mang</p>"
                                +"</div>"
                                + "</div>"
                            + "</div>";
                    }
                    $("#ajax-response").html(data[0].vietnameseNameFamily);
                } else {
                    var result = "<h3 style='color:red'> No person found </h3>";
                    $("#ajax-response").html(result);
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    }
</script>
</body>
</html>
