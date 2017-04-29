$(".modal-wide").on("show.bs.modal", function () {
    var height = $(window).height() - 120;
    $(this).find(".modal-body").css("max-height", height);
});
$(document).ajaxStart(function () {
    $.LoadingOverlay("show");
});
$(document).ajaxStop(function () {
    $.LoadingOverlay("hide");
});
