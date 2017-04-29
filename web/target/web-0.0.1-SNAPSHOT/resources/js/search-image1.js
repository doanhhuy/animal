/**
 * Created by duyle on 4/1/17.
 */
$(document).ajaxStart(function () {
    $.LoadingOverlay("show");
});
$(document).ajaxComplete(function () {
    $.LoadingOverlay("hide");
});

function readURL(input) {
    var nimeType = input.files[0]['type'];
    if (nimeType.split('/')[0] == 'image') {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#blah')
                    .attr('src', e.target.result);
            };

            reader.readAsDataURL(input.files[0]);
        }
    } else {
        $(document).ready(function () {
            $.simplyToast('File được chọn không phải hình!', 'danger');
        });
    }
}
;
$(document).ready(function () {
    var height = $(window).height() - 190;
    console.log(height);
    $('#list-species').css("max-height", height);
});

function show() {
    console.log($('#image').val());
    if ($('#image').val() == "" || $('#image').val() == null) {
        $(document).ready(function () {
            $.simplyToast('Bạn chưa chọn hình!', 'danger');
        });
    } else {
        var myForm = new FormData();
        myForm.append("file", $('#image')[0].files[0]);
        $.ajax({
            type: "POST",
            url: window.location.origin + "/searchimage",
            data: myForm,
            encoding: "UTF-8",
            processData: false,
            contentType: false,
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
                        var img = obj.image.split(",");
                        console.log(obj.image);
                        result += "<a href=\"/detailspecies/" + obj.id +
                            "\"><img src=\"http://localhost:8081/resources/images/" + img[0] + "\" width=\"150px\" height=\"150px\" alt=\"Avatar\"";
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
                    var result = "<h5 style='color:red' aglin='center'> Không tìm thấy dữ liệu! </h5>";
                    $("#list-species").html(result);
                }
            },
            error: function (e) {
                var result = "<h5 style='color:red' aglin='center'> Không tìm thấy dữ liệu! </h5>";
                $("#list-species").html(result);
            }
        });
    }
}