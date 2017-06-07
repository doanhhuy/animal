/**
 * Created by duyle on 5/2/17.
 */
$(document).ajaxStart(function () {
    $.LoadingOverlay("show");
});
$(document).ajaxComplete(function () {
    $.LoadingOverlay("hide");
    $('#approve-table').dataTable({
        "paging": true,
        "lengthMenu": [[5, 10, 20, -1], [5, 10, 20, "All"]],
        "searching": true,
        "info": true,
        "autoWidth": true,
        // Disable sorting on the first column
        "columnDefs": [{
            "targets": [0, 1, 2, 4, 5],
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
$(document).ready(function () {
    loadTableList();
});

function loadTableList() {
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=UTF-8",
        url: window.location.origin + "/species/list/approve",
        encoding: "UTF-8",
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            if (data != null) {
                var result = "";
                var regex = new RegExp("Bạn chưa đăng nhập!");
                console.log(regex.test(data));
                var result = "";
                if (regex.test(data) == true) {
                    var result = "<script>" + "$(document).ready(function () " +
                        "{$.simplyToast('Bạn chưa đăng nhập!', 'danger');" +
                        "}); " + "window.location.replace(\"/login\");</script>";
                    $("#table-body").html(result);
                } else {
                    for (var key in data) {
                        var obj = data[key];
                        result += "<tr><td>";
                        if (obj.scienceName == null || obj.scienceName == "") {
                            result += "chưa rỏ";
                        } else {
                            result += obj.scienceName;
                        }
                        result += "</td><td>";
                        if (obj.vietnameseName == null || obj.vietnameseName == "") {
                            result += "chưa rỏ";
                        } else {
                            result += obj.vietnameseName;
                        }
                        result += "</td><td>" + obj.latitude + " - " + obj.longitude + "</td><td>" + obj.nameCreator + "</td><td><a" +
                            " href=\"#\" onclick='approveSpecies(" + obj.id + ");'><span class=\"glyphicon" +
                            " glyphicon-ok\"></span></a></td><td><a href=\"#\"><span" +
                            " class=\"glyphicon glyphicon-remove\" onclick='removeSpecies(" + obj.id + ");'></span></a></td></tr>";
                    }
                    $("#table-body").html(result);
                }
            } else {
                var result = "<script>$(document).ready(function () {$.simplyToast(\'Không có loài cần duyệt\'," +
                    " \'danger\');});</script>";
                $("#table-body").html(result);
            }
        },
        error: function (e) {
            var result = "<script>$(document).ready(function () {$.simplyToast(\'Không có loài cần phê duyệt\'," +
                " \'danger\');});</script>";
            $("#table-body").html(result);
            console.log("ERROR: ", e);
        }
    });
}

