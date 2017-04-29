/**
 * Created by duyle on 3/24/17.
 */
$(document).ajaxStart(function () {
    $.LoadingOverlay("show");
});
$(document).ajaxComplete(function () {
    $.LoadingOverlay("hide");
});
$(document).ready(function () {
    var height = $(window).height() - 220;
    console.log(height);
    $('#content').css("max-height", height);
    $('#list-species').css("max-height", height - 10);
    $('#list-species').css("border", "none");
});
function loadData() {
    var id = $('#type').val();
    var path = "";
    if (id != 0) {
        if (id == 1) {
            path = "/phylums";
        } else if (id == 2) {
            path = "/classes";
        } else if (id == 3) {
            path = "/orders";
        } else if (id == 4) {
            path = "/families";
        } else {
            path = "/genus";
        }
        console.log(id);
        $.ajax({
            type: "GET",
            contentType: "application/json; charset=UTF-8",
            url: window.location.origin + path,
            encoding: "UTF-8",
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                if (data != null) {
                    var result = "<select id=\"select-detail\" class=\"form-control\">";
                    for (var key in data) {
                        var obj = data[key];
                        result += "<option selected value=\"" + obj.id + "\">" + obj.scienceName + "-" + obj.vietnameseName + "</option>";
                    }
                    result += "</select>";
                    $('#select-data').html(result);
                } else {
                    $(document).ready(function () {
                        $.simplyToast('Không tìm thấy dữ liệu', 'danger');
                    });
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    } else {
        var result = "<select class=\"form-control\"><option selected value=\"\">Phải chọn phân cấp</option></select>";
        $('#select-data').html(result);
    }
}

function show() {
    var idType = $('#type').val();
    var idData = $('#select-detail').val();
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=UTF-8",
        url: window.location.origin + "/species/type",
        encoding: "UTF-8",
        data: {
            idType: idType,
            idData: idData
        },
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            if (data != null) {
                var result = "";
                for (var key in data) {
                    var obj = data[key];
                    result += "<div class=\"w3-container\" style=\"height: auto\">"
                        + "<div class=\"w3-card-4 index-card\">";
                    if (obj.image == null || obj.image == '') {
                        result += "<a href=\"/detailspecies/" + obj.id + "\"><img src=\"http://is.tnu.edu.vn/wp-content/themes/motive/images/no_image.jpg\" width=\"150px\" height=\"150px\" alt=\"Avatar\"";
                    } else {
                        var img = obj.image.split(",");
                        result += "<a href=\"/detailspecies/" + obj.id + "\"><img src=\"http://localhost:8081/resources/images/" + img[0] + "\" width=\"150px\" height=\"150px\" alt=\"Avatar\"";
                    }
                    result += " class=\"w3-left w3-margin-right\"></a>"
                        + "<div align=\"left\">"
                        + "<a href=\"/detailspecies/" + obj.id + "\"><p class=\"vietnamese-name\">" + obj.vietnameseName + "</p></a>"
                        + "<p class=\"science-name\">Tên khoa học: " + obj.scienceName + "</p>"
                        + "<p class=\"science-name\">Họ: " + obj.vietnameseNameFamily + "</p>"
                        + "<p class=\"science-name\">Chi: " + obj.vietnameseNameGenus + "</p>"
                        + "</div>"
                        + "</div>"
                        + "</div>";
                }
                $("#list-species").html(result);
            } else {
                var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>"
                $("#list-species").append(result);
            }
        },
        error: function (e) {
            var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>";
            $("#list-species").append(result);
            console.log("ERROR: ", e);
        }
    });

}