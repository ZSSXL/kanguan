/* 获取影视剧资源   */
$(document).on("click", ".get-movies-detail", function () {
    let movieId = $(this).attr("movie-id");
    $.ajax({
        url: baseUrl + "/movies/" + movieId,
        type: "get",
        beforeSend: function (XMLHttpRequest) {
            XMLHttpRequest.setRequestHeader("token", token);
        },
        success: function (result) {
            sessionStorage.setItem("kanguan-resource-data", JSON.stringify(result.data));
            $("#page-load-area").load("resource.html");
        }
    });
});