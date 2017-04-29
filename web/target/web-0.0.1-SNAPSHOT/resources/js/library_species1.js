/**
 * Created by duyle on 4/1/17.
 */
/**
 * Created by duyle on 3/17/17.
 */

var filter = 0;
var offset = 0;
$(document).ajaxStart(function () {
    $.LoadingOverlay("show");
});

$(document).ajaxStop(function () {
    var wiDth = $('.my-img').css('width');
    $('.my-img').css('height', wiDth);
    $.LoadingOverlay("hide");
});

$(document).ready(function () {

    showSpecies(offset);

    $('.checkbox-filter').on('change', function () {
        showSpecies(0);
    });

    $('#load_more').on('click', function () {
        offset++;
        showSpecies(offset);
    });

    $('#search').keydown(function (e) {
        if (e.keyCode == 13) {
            searchSpecies();
        }
    });

    //Check to see if the window is top if not then display button
    $('#main').scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('.scrollToTop').fadeIn();
        } else {
            $('.scrollToTop').fadeOut();
        }
    });

    //Click event to scroll to top
    $('.scrollToTop').click(function () {
        $('#main').animate({scrollTop: 0}, 800);
        return false;
    });
});

function showSpecies(offset) {
    console.log(filter);
    if ($('.checkbox-filter:checked').val() == filter) {
        $.ajax({
            type: "GET",
            contentType: "application/json; charset=UTF-8",
            url: window.location.origin + "/libraryspecies/" + offset,
            encoding: "UTF-8",
            data: {
                filter: filter
            },
            dataType: 'json',
            timeout: 100000,
            success: function (data) {

                if (data != null) {
                    var result = "";
                    for (var key in data) {
                        // console.log(key);
                        var obj = data[key];
                        if (key % 3 == 0) {
                            result += "<div class=\"row\" align=\"left\">" + "<div class=\"col-md-4\">" +
                                "<div class=\"w3-container\"><div class=\"w3-card-4\">";
                            if (obj.image == null || obj.image == "") {
                                result += "<a href=\"/detailspecies/" + obj.id + "\">" +
                                    "<img src=\"http://is.tnu.edu.vn/wp-content/themes/motive/images/no_image.jpg\" " +
                                    "alt=\"Norway\" class=\"my-img\"></a>";
                            } else {
                                var img = obj.image.split(",");
                                result += "<a href=\"/detailspecies/" + obj.id + "\">" +
                                    "<img src=\"http://localhost:8081/resources/images/" + img[0] +
                                    "\" alt =\"Norway\" class=\"my-img\"></a>";
                            }
                            result += "<div class=\"w3-container w3-center\">" +
                                "<p class=\"vietnamese-name\">" + obj.vietnameseName + "</p> " +
                                "<p class=\"science-name\">Tên khoa học: " + obj.scienceName + "</p> " +
                                "<p class=\"science-name\">Họ: " + obj.vietnameseNameFamily + "</p> " +
                                "<p class=\"science-name\">Chi: " + obj.vietnameseNameGenus + "</p> " +
                                "</div> </div> </div> </div>";
                        } else if (key % 3 == 1) {
                            result += "<div class=\"col-md-4\">" +
                                "<div class=\"w3-container\"><div class=\"w3-card-4\">";
                            if (obj.image == null || obj.image == "") {
                                result += "<a href=\"/detailspecies/" + obj.id + "\">" +
                                    "<img src=\"http://is.tnu.edu.vn/wp-content/themes/motive/images/no_image.jpg\" " +
                                    "alt=\"Norway\" class=\"my-img\"></a>";
                            } else {
                                var img = obj.image.split(",");
                                result += "<a href=\"/detailspecies/" + obj.id + "\">" +
                                    "<img src=\"http://localhost:8081/resources/images/" + img[0] +
                                    "\" alt =\"Norway\" class=\"my-img\"></a>";
                            }
                            result += "<div class=\"w3-container w3-center\">" +
                                "<p class=\"vietnamese-name\">" + obj.vietnameseName + "</p> " +
                                "<p class=\"science-name\">Tên khoa học: " + obj.scienceName + "</p> " +
                                "<p class=\"science-name\">Họ: " + obj.vietnameseNameFamily + "</p> " +
                                "<p class=\"science-name\">Chi: " + obj.vietnameseNameGenus + "</p> " +
                                "</div> </div> </div> </div>";
                        } else if (key % 3 == 2) {
                            result += "<div class=\"col-md-4\">" +
                                "<div class=\"w3-container\"><div class=\"w3-card-4\">";
                            if (obj.image == null || obj.image == "") {
                                result += "<a href=\"/detailspecies/" + obj.id + "\">" +
                                    "<img src=\"http://is.tnu.edu.vn/wp-content/themes/motive/images/no_image.jpg\" " +
                                    "alt=\"Norway\" class=\"my-img\"></a>";
                            } else {
                                var img = obj.image.split(",");
                                result += "<a href=\"/detailspecies/" + obj.id + "\">" +
                                    "<img src=\"http://localhost:8081/resources/images/" + img[0] +
                                    "\" alt =\"Norway\" class=\"my-img\"></a>";
                            }
                            result += "<div class=\"w3-container w3-center\">" +
                                "<p class=\"vietnamese-name\">" + obj.vietnameseName + "</p> " +
                                "<p class=\"science-name\">Tên khoa học: " + obj.scienceName + "</p> " +
                                "<p class=\"science-name\">Họ: " + obj.vietnameseNameFamily + "</p> " +
                                "<p class=\"science-name\">Chi: " + obj.vietnameseNameGenus + "</p> " +
                                "</div> </div> </div> </div> </div> <br>";
                        }
                        if ((key % 3 != 2) && (key == data.length)) {
                            result += "</div> <br>";
                        }
                    }
                    // console.log(result);
                    $("#list-species").append(result);
                    if (offset != 0) {
                        $("#main").animate({scrollTop: $("#load_more").offset().top}, 800);
                    } else {
                        $('#main').animate({scrollTop: 0}, 800);
                    }
                } else {
                    $('#load_more').hide();
                    var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>";
                    $("#list-species").append(result);
                }
            },
            error: function (e) {
                $('#load_more').hide();
                var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>";
                $("#list-species").append(result);
                console.log("ERROR: ", e);
            }
        });
    } else {
        filter = $('.checkbox-filter:checked').val();
        offset = 0;
        $.ajax({
            type: "GET",
            contentType: "application/json; charset=UTF-8",
            url: window.location.origin + "/libraryspecies/" + offset,
            encoding: "UTF-8",
            data: {
                filter: filter
            },
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                if (data != null) {
                    var result = "";
                    for (var key in data) {
                        // console.log(key);
                        var obj = data[key];
                        if (key % 3 == 0) {
                            result += "<div class=\"row\" align=\"left\">" + "<div class=\"col-md-4\">" +
                                "<div class=\"w3-container\"><div class=\"w3-card-4\">";
                            if (obj.image == null || obj.image == "") {
                                result += "<a href=\"/detailspecies/" + obj.id + "\">" +
                                    "<img src=\"http://is.tnu.edu.vn/wp-content/themes/motive/images/no_image.jpg\" " +
                                    "alt=\"Norway\" class=\"my-img\"></a>";
                            } else {
                                var img = obj.image.split(",");
                                result += "<a href=\"/detailspecies/" + obj.id + "\">" +
                                    "<img src=\"http://localhost:8081/resources/images/" + img[0] +
                                    "\" alt =\"Norway\" class=\"my-img\"></a>";
                            }
                            result += "<div class=\"w3-container w3-center\">" +
                                "<p class=\"vietnamese-name\">" + obj.vietnameseName + "</p> " +
                                "<p class=\"science-name\">Tên khoa học: " + obj.scienceName + "</p> " +
                                "<p class=\"science-name\">Họ: " + obj.vietnameseNameFamily + "</p> " +
                                "<p class=\"science-name\">Chi: " + obj.vietnameseNameGenus + "</p> " +
                                "</div> </div> </div> </div>";
                        } else if (key % 3 == 1) {
                            result += "<div class=\"col-md-4\">" +
                                "<div class=\"w3-container\"><div class=\"w3-card-4\">";
                            if (obj.image == null || obj.image == "") {
                                result += "<a href=\"/detailspecies/" + obj.id + "\">" +
                                    "<img src=\"http://is.tnu.edu.vn/wp-content/themes/motive/images/no_image.jpg\" " +
                                    "alt=\"Norway\" class=\"my-img\"></a>";
                            } else {
                                var img = obj.image.split(",");
                                result += "<a href=\"/detailspecies/" + obj.id + "\">" +
                                    "<img src=\"http://localhost:8081/resources/images/" + img[0] +
                                    "\" alt =\"Norway\" class=\"my-img\"></a>";
                            }
                            result += "<div class=\"w3-container w3-center\">" +
                                "<p class=\"vietnamese-name\">" + obj.vietnameseName + "</p> " +
                                "<p class=\"science-name\">Tên khoa học: " + obj.scienceName + "</p> " +
                                "<p class=\"science-name\">Họ: " + obj.vietnameseNameFamily + "</p> " +
                                "<p class=\"science-name\">Chi: " + obj.vietnameseNameGenus + "</p> " +
                                "</div> </div> </div> </div>";
                        } else if (key % 3 == 2) {
                            result += "<div class=\"col-md-4\">" +
                                "<div class=\"w3-container\"><div class=\"w3-card-4\">";
                            if (obj.image == null || obj.image == "") {
                                result += "<a href=\"/detailspecies/" + obj.id + "\">" +
                                    "<img src=\"http://is.tnu.edu.vn/wp-content/themes/motive/images/no_image.jpg\" " +
                                    "alt=\"Norway\" class=\"my-img\"></a>";
                            } else {
                                var img = obj.image.split(",");
                                result += "<a href=\"/detailspecies/" + obj.id + "\">" +
                                    "<img src=\"http://localhost:8081/resources/images/" + img[0] +
                                    "\" alt =\"Norway\" class=\"my-img\"></a>";
                            }
                            result += "<div class=\"w3-container w3-center\">" +
                                "<p class=\"vietnamese-name\">" + obj.vietnameseName + "</p> " +
                                "<p class=\"science-name\">Tên khoa học: " + obj.scienceName + "</p> " +
                                "<p class=\"science-name\">Họ: " + obj.vietnameseNameFamily + "</p> " +
                                "<p class=\"science-name\">Chi: " + obj.vietnameseNameGenus + "</p> " +
                                "</div> </div> </div> </div> </div> <br>";
                        }
                        if ((key % 3 != 2) && (key == data.length)) {
                            result += "</div> <br>";
                        }
                    }
                    // console.log(result);
                    $("#list-species").html(result);
                    if (offset != 0) {
                        $("#main").animate({scrollTop: $("#load_more").offset().top}, 800);
                    } else {
                        $('#main').animate({scrollTop: 0}, 800);
                    }
                } else {
                    $('#load_more').hide();
                    var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>";
                    $("#list-species").append(result);
                }
            },
            error: function (e) {
                var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>";
                $("#list-species").append(result);
                $('#load_more').hide();
                console.log("ERROR: ", e);
            }
        });
    }

}

function searchSpecies() {
    var key = $('#search').val();
    $("body").off("load");
    console.log(key);
    if (key == "" || key == null) {
        $(document).ready(function () {
            $.simplyToast('Không có từ khóa được nhập vào', 'danger');
        });
    } else {
        $.ajax({
            type: "GET",
            contentType: "application/json; charset=UTF-8",
            url: window.location.origin + "/speciessearch",
            encoding: "UTF-8",
            data: {
                key: key
            },
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                if (data != null) {
                    var result = "";
                    for (var key in data) {
                        console.log(key);
                        var obj = data[key];
                        if (key % 3 == 0) {
                            result += "<div class=\"row\" align=\"left\">" + "<div class=\"col-md-4\">" +
                                "<div class=\"w3-container\"><div class=\"w3-card-4\">";
                            if (obj.image == null || obj.image == "") {
                                result += "<a href=\"/detailspecies/" + obj.id + "\">" +
                                    "<img src=\"http://is.tnu.edu.vn/wp-content/themes/motive/images/no_image.jpg\" " +
                                    "alt=\"Norway\" class=\"my-img\"></a>";
                            } else {
                                var img = obj.image.split(",");
                                result += "<a href=\"/detailspecies/" + obj.id + "\">" +
                                    "<img src=\"http://localhost:8081/resources/images/" + img[0] +
                                    "\" alt =\"Norway\" class=\"my-img\"></a>";
                            }
                            result += "<div class=\"w3-container w3-center\">" +
                                "<a href=\"/detailspecies/" + obj.id + "\"><p class=\"vietnamese-name\">" + obj.vietnameseName + "</p></a>" +
                                "<p class=\"science-name\">Tên khoa học: " + obj.scienceName + "</p> " +
                                "<p class=\"science-name\">Họ: " + obj.vietnameseNameFamily + "</p> " +
                                "<p class=\"science-name\">Chi: " + obj.vietnameseNameGenus + "</p> " +
                                "</div> </div> </div> </div>";
                        } else if (key % 3 == 1) {
                            result += "<div class=\"col-md-4\">" +
                                "<div class=\"w3-container\"><div class=\"w3-card-4\">";
                            if (obj.image == null || obj.image == "") {
                                result += "<a href=\"/detailspecies/" + obj.id + "\">" +
                                    "<img src=\"http://is.tnu.edu.vn/wp-content/themes/motive/images/no_image.jpg\" " +
                                    "alt=\"Norway\" class=\"my-img\"></a>";
                            } else {
                                var img = obj.image.split(",");
                                result += "<a href=\"/detailspecies/" + obj.id + "\">" +
                                    "<img src=\"http://localhost:8081/resources/images/" + img[0] +
                                    "\" alt =\"Norway\" class=\"my-img\"></a>";
                            }
                            result += "<div class=\"w3-container w3-center\">" +
                                "<a href=\"/detailspecies/" + obj.id + "\"><p class=\"vietnamese-name\">" + obj.vietnameseName + "</p></a> " +
                                "<p class=\"science-name\">Tên khoa học: " + obj.scienceName + "</p> " +
                                "<p class=\"science-name\">Họ: " + obj.vietnameseNameFamily + "</p> " +
                                "<p class=\"science-name\">Chi: " + obj.vietnameseNameGenus + "</p> " +
                                "</div> </div> </div> </div>";
                        } else if (key % 3 == 2) {
                            result += "<div class=\"col-md-4\">" +
                                "<div class=\"w3-container\"><div class=\"w3-card-4\">";
                            if (obj.image == null || obj.image == "") {
                                result += "<a href=\"/detailspecies/" + obj.id + "\">" +
                                    "<img src=\"http://is.tnu.edu.vn/wp-content/themes/motive/images/no_image.jpg\" " +
                                    "alt=\"Norway\" class=\"my-img\"></a>";
                            } else {
                                var img = obj.image.split(",");
                                result += "<a href=\"/detailspecies/" + obj.id + "\">" +
                                    "<img src=\"http://localhost:8081/resources/images/" + img[0] +
                                    "\" alt =\"Norway\" class=\"my-img\"></a>";
                            }
                            result += "<div class=\"w3-container w3-center\">" +
                                "<a href=\"/detailspecies/" + obj.id + "\"><p class=\"vietnamese-name\">" + obj.vietnameseName + "</p></a> " +
                                "<p class=\"science-name\">Tên khoa học: " + obj.scienceName + "</p> " +
                                "<p class=\"science-name\">Họ: " + obj.vietnameseNameFamily + "</p> " +
                                "<p class=\"science-name\">Chi: " + obj.vietnameseNameGenus + "</p> " +
                                "</div> </div> </div> </div> </div> <br>";
                        }
                        if ((key % 3 != 2) && (key == data.length)) {
                            result += "</div> <br>";
                        }
                    }
                    console.log(result);
                    $("#list-species").html(result);
                    $('#load_more').hide();
                } else {
                    var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>";
                    $("#list-species").append(result);
                }
            },
            error: function (e) {
                var result = "<script>$(document).ready(function () {$.simplyToast(\'Không tìm thấy dữ liệu\', \'danger\');});</script>";
                $("#list-species").append(result);
                $('#load_more').hide();
            }
        });
    }

}