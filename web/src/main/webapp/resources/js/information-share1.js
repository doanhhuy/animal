/**
 * Created by duyle on 4/5/17.
 */

var listShare;

$(document).ajaxStart(function () {
    $.LoadingOverlay("show");
});
$(document).ajaxComplete(function () {
    $.LoadingOverlay("hide");
});

$(document).ready(function () {
    showSpecies();
    var height = $(window).height() - 190;
    console.log(height);
    $('#list-share').css("max-height", height);
    $('#detail-share').css("max-height", height);
});

function showSpecies() {
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=UTF-8",
        url: window.location.origin + "/species/list/share",
        encoding: "UTF-8",
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            if (data != null) {
                listShare = data;
                var regex = new RegExp("Bạn chưa đăng nhập!");
                console.log(regex.test(data));
                var result = "";
                if (regex.test(data) == true) {
                    var result = "<script>window.location.replace(\"/\");</script>";
                    $("#list-share").append(result);
                } else {
                    for (var key in data) {
                        // console.log(key);
                        var obj = data[key];
                        result += "<div class=\"w3-container\" style=\"height: auto\">"
                            + "<div class=\"w3-card-4 index-card\">";
                        if (obj.image == null || obj.image == '') {
                            result += "<a href=\"#\" onclick='showDetail(" + obj.id + ")'><img" +
                                " src=\"http://is.tnu.edu.vn/wp-content/themes/motive/images/no_image.jpg\" width=\"150px\" height=\"150px\" alt=\"Avatar\"";
                        } else {
                            var img = obj.image.split(",");
                            result += "<a href=\"#\" onclick='showDetail(" + obj.id + ")'><img" +
                                " src=\"http://localhost:8081/resources/images/" + img[0] + "\" width=\"150px\" height=\"150px\" alt=\"Avatar\"";
                        }
                        result += " class=\"w3-left w3-margin-right\"></a>"
                            + "<div align=\"left\">"
                            + "<a href=\"#\" onclick='showDetail(" + obj.id + ")'><p  class=\"science-name\"" +
                            "     style='padding-top: 10px;'>Nơi phát hiện: ";
                        if (obj.locationName == null) {
                            result += "chưa rỏ</p></a>";
                        } else {
                            result += obj.locationName + "</p></a>";
                        }
                        result += "<p class=\"science-name\">Tọa độ: " + obj.latitude + " - " + obj.longitude + "</p>";
                        if (obj.status == 0) {
                            result += "<p style='text-align: right;font-style: italic;font-size: 10pt;margin-right:" +
                                " 3px;'>Đợi phê" +
                                " duyệt</p>";
                        } else {
                            result += "<p class=\"science-name\"style='text-align: right;font-style: italic;font-size: 10pt;margin-right:" +
                                " 3px;'>Đã phê duyệt</p>";
                        }
                        result += "</div>"
                            + "</div>"
                            + "</div>";
                    }
                    // console.log(result);
                    $("#list-share").append(result);
                }
            } else {
                var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>";
                $("#list-share").append(result);
            }
        },
        error: function (e) {
            var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>";
            $("#list-share").append(result);
            console.log("ERROR: ", e);
        }
    });
}

function showDetail(id) {
    $("body").off("load");
    var result = "";
    var temp;
    for (var i = 0; i < listShare.length; i++) {
        temp = listShare[i];
        if (temp.id == id) {
            console.log(listShare[i].id);
            result += "<tr><td class=\"td-content\"><p class=\"p-title\"" +
                ">Thông" +
                " tin cơ bản" +
                " </p><p" +
                " class=\"p-category\"><strong>Tên thường gọi: </strong>";
            if (temp.vietnameseName != null) {
                result += temp.vietnameseName;
            } else {
                result += "chưa có";
            }
            result += "</p><p class=\"p-category\"><strong>Tên khoa học: </strong>";
            if (temp.scienceName != null) {
                result += temp.scienceName;
            } else {
                result += "chưa có";
            }
            result += "</p><p class=\"p-category\"><strong>Phân cấp theo chi: </strong>";
            if (temp.scienceNameGenus != null) {
                result += temp.scienceNameGenus;
            } else {
                result += "chưa có";
            }
            if (temp.vietnameseNameGenus != null) {
                result += " - " + temp.vietnameseNameGenus;
            } else {
                result += " - chưa có";
            }
            result += "</p></td></tr><tr><td class=\"td-content\"><p class=\"p-title\"" +
                " >" +
                " Nơi cư trú</p><a id=\"showHabitat\" href=\"#\" onclick='showHabitat(" + temp.id + ")'><i class=\"fa" +
                " fa-map" +
                " fa-2x\"" +
                " aria-hidden=\"true\"></i></a>" +
                "</tr><tr><td class=\"td-content\"><p class=\"p-title\"" +
                " >Đặc" +
                " điểm chung" +
                " </p><p" +
                " class=\"p-category\"><strong>Đặc điểm sinh sản: </strong>";
            if (temp.reproductionTraits != null) {
                result += temp.reproductionTraits;
            } else {
                result += "chưa có";
            }
            result += "</p><p class=\"p-category\"><strong>Đặc điểm khác: </strong>";
            if (temp.ortherTraits != null) {
                result += temp.ortherTraits;
            } else {
                result += "chưa có";
            }
            result += "</p><p class=\"p-category\"><strong>Tập tính sinh học: </strong>";
            if (temp.biologicalBehavior != null) {
                result += temp.biologicalBehavior;
            } else {
                result += "chưa có";
            }
            result += "</p><p class=\"p-category\"><strong>Thức ăn: </strong>";
            if (temp.food != null) {
                result += temp.food;
            } else {
                result += "chưa có";
            }
            result += "</p><p class=\"p-category\"><strong>Kích cỡ: </strong>";
            if (temp.mediumSize != null) {
                result += temp.mediumSize;
            } else {
                result += "chưa có";
            }
            result += " </td></td></tr>";
            $('#table-body').html(result);
            break;
        }
    }
}

function showHabitat(idSpecies) {
    $('#myModal').modal('show');
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=UTF-8",
        url: window.location.origin + "/species/habitat/",
        encoding: "UTF-8",
        data: {
            idSpecies: idSpecies
        },
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            var result = "<script> for(var i=0; i<marker.length; i++) {" +
                "map.removeLayer(marker[i]);} ";
            if (data != null) {
                for (var key in data) {
                    var obj = data[key];
                    result += "var newMaker = L.marker([" + obj.latitude + "," + obj.longitude + "], {icon:" +
                        " redIcon}).addTo(map); marker.push(newMaker);";
                }
                result += "$('#myModal').on('shown.bs.modal', function () { map.invalidateSize();" +
                    "}); </script>";
                console.log(result);
                $('#script').html(result);
            } else {
                result += "$('#myModal').on('shown.bs.modal', function () { map.invalidateSize();" +
                    "}); </script>";
                $('#script').html(result);
            }
        },
        error: function (e) {
            var result = "<script> ";
            result += "$('#myModal').on('shown.bs.modal', function () { map.invalidateSize();" +
                "}); </script>";
            $('#script').html(result);
            console.log("ERROR: ", e);
        }
    });
}
