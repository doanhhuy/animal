/**
 * Created by duyle on 3/27/17.
 */
/**
 * Created by duyle on 3/27/17.
 */
/**
 * Created by duyle on 3/26/17.
 */
$(document).ready(function () {
    $("#search-form").on('success.form.fv', function (e) {
        e.preventDefault();
        showSpecies();
    })
});

function loadClass() {
    var id = $('#phylum').val();
    if (id != 0) {
        $.ajax({
            type: "GET",
            contentType: "application/json; charset=UTF-8",
            url: window.location.origin + "/class/phylum/" + id,
            encoding: "UTF-8",
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                if (data != null) {
                    var result = "<select id=\"class-detail\" onclick=\"loadOrder();\" class=\"form-control\" required data-error=\"Phải chọn lớp!!\">" +
                        "<option selected value=\"\">Chọn lớp</option>";
                    for (var key in data) {
                        var obj = data[key];
                        result += "<option value=\"" + obj.id + "\">" + obj.scienceName + "-" + obj.vietnameseName + "</option>";
                    }
                    result += "</select><div class=\"help-block with-errors\"></div>";
                    $('#class').html(result);
                } else {
                    var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>";
                    $("#class").html(result);
                }
            },
            error: function (e) {
                var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>";
                $("#genus").html(result);
                console.log("ERROR: ", e);
            }
        });
    } else {
        var result = "<select class=\"form-control\"><option selected value=\"\">Phải chọn ngành</option></select>";
        $('#class').html(result);
    }
}
function loadOrder() {
    var id = $('#class-detail').val();
    if (id != 0) {
        $.ajax({
            type: "GET",
            contentType: "application/json; charset=UTF-8",
            url: window.location.origin + "/order/class/" + id,
            encoding: "UTF-8",
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                if (data != null) {
                    var result = "<select id=\"order-detail\" onclick=\"loadFamily();\" class=\"form-control\" required data-error=\"Phải chọn bộ!!\">" +
                        "<option selected value=\"\">Chọn bộ</option>";
                    for (var key in data) {
                        var obj = data[key];
                        result += "<option value=\"" + obj.id + "\">" + obj.scienceName + "-" + obj.vietnameseName + "</option>";
                    }
                    result += "</select><div class=\"help-block with-errors\"></div>";
                    console.log(result)
                    $('#order').html(result);
                } else {
                    var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>";
                    $("#order").html(result);
                }
            },
            error: function (e) {
                var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>";
                $("#genus").html(result);
                console.log("ERROR: ", e);
            }
        });
    } else {
        var result = "<select class=\"form-control\"><option selected value=\"\">Phải chọn lớp</option></select>";
        $('#order').html(result);
    }
}

function loadFamily() {
    var id = $('#order-detail').val();
    if (id != 0) {
        console.log(id);
        $.ajax({
            type: "GET",
            contentType: "application/json; charset=UTF-8",
            url: window.location.origin + "/family/order/" + id,
            encoding: "UTF-8",
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                if (data != null) {
                    var result = "<select id=\"family-detail\" onclick=\"loadGenus();\" class=\"form-control\" required data-error=\"Phải chọn họ!!\">" +
                        "<option selected value=\"\">Chọn họ</option>";
                    for (var key in data) {
                        var obj = data[key];
                        result += "<option value=\"" + obj.id + "\">" + obj.scienceName + "-" + obj.vietnameseName + "</option>";
                    }
                    result += "</select><div class=\"help-block with-errors\"></div>";
                    $('#family').html(result);
                } else {
                    var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>";
                    $("#family").html(result);
                }
            },
            error: function (e) {
                var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>";
                $("#genus").html(result);
                console.log("ERROR: ", e);
            }
        });
    } else {
        var result = "<select class=\"form-control\"><option selected value=\"\">Phải chọn bộ</option></select>";
        $('#family').html(result);
    }
}

function loadGenus() {
    var id = $('#family-detail').val();
    if (id != 0) {
        console.log(id);
        $.ajax({
            type: "GET",
            contentType: "application/json; charset=UTF-8",
            url: window.location.origin + "/genus/family/" + id,
            encoding: "UTF-8",
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                if (data != null) {
                    var result = "<select id=\"genus-detail\" onclick=\"showSpecies();\" class=\"form-control\" required data-error=\"Phải chọn chi!!\">" +
                        "<option selected value=\"\">Chọn chi</option>";
                    for (var key in data) {
                        var obj = data[key];
                        result += "<option value=\"" + obj.id + "\">" + obj.scienceName + "-" + obj.vietnameseName + "</option>";
                    }
                    result += "</select><div class=\"help-block with-errors\"></div>";
                    $('#genus').html(result);
                } else {
                    var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>";
                    $("#genus").html(result);
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>";
                $("#genus").html(result);
            }
        });
    } else {
        var result = "<select class=\"form-control\"><option selected value=\"\">Phải chọn bộ</option></select>";
        $('#genus').html(result);
    }
}

function showSpecies() {
    var idType = 5;
    var idData = $('#genus-detail').val();
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
                $("#list-species1").html(result);
            } else {
                var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>"
                $("#list-species1").append(result);
            }
        },
        error: function (e) {
            console.log("ERROR: ", e);
            var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>"
            $("#list-species1").append(result);
        }
    });

}
