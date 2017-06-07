/**
 * Created by duyle on 4/30/17.
 */

$(document).ajaxStart(function () {
    $.LoadingOverlay("show");
});

$(document).ajaxStop(function () {
    $.LoadingOverlay("hide");
});

$(document).ready(function () {
    $('#showHabitat').click(function () {
        $('#myModal').modal('show');
        var idSpecies = $('#id-species').val();
        console.log(idSpecies);
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
                            " redIcon}).addTo(map); marker.push(newMaker); ";
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
    });
});