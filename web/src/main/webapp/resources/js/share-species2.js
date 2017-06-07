/**
 * Created by duyle on 4/13/17.
 */

$(document).ajaxStart(function () {
    $.LoadingOverlay("show");
});

$(document).ajaxStop(function () {
    $.LoadingOverlay("hide");
});

function showMap() {
    $('#myModal').modal('show');
}

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

function shareSpecies() {
    $("body").off("load");
    console.log('click');
    if ($('#image').val() == "" || $('#image').val() == null) {
        $(document).ready(function () {
            $.simplyToast('Bạn chưa chọn hình!', 'danger');
        });
    } else {
        var myForm = new FormData();
        myForm.append("file", $('#image')[0].files[0]);
        var form_data = $('#form-data').serialize();
        $.ajax({
            type: "POST",
            url: window.location.origin + "/sharespecies?" + form_data,
            data: myForm,
            encoding: "UTF-8",
            processData: false,
            contentType: false,
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                if (data != null) {
                    var regex = new RegExp("Bạn chưa đăng nhập!");
                    console.log(regex.test(data));
                    var result = "";
                    if (regex.test(data) == true) {
                        var result = "<script>" + "$(document).ready(function () " +
                            "{$.simplyToast('Bạn chưa đăng nhập!', 'danger');" +
                            "}); " + "window.location.replace(\"/login\");</script>";
                        $("#result").html(result);
                    } else {
                        regex = new RegExp("Thêm thất bại!");
                        if (regex.test(data) == true) {
                            var result = "<script>" +
                                "$(document).ready(function () " +
                                "{$.simplyToast('Chia sẻ không thành công!', 'danger');" +
                                "}); " +
                                "</script>";
                            $("#result").html(result);
                        } else {
                            var result = "<script>" +
                                "$(document).ready(function () " +
                                "{$.simplyToast('Chia sẻ thành công! Vui lòng chờ phê duyệt "
                                + "từ chuyên gia!', 'success');" +
                                "}); " +
                                "window.location.replace(\"/sharespecies\");</script>";
                            $("#result").html(result);
                        }
                    }
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