/* request js */

$(document).on("click", ".response-request", function () {
    let requestId = $(this).attr("request-id");
    console.log("document : " + requestId);
    $("#send-response").attr("request-id", requestId);
    $("#add-response-request-modal").modal("show");
});