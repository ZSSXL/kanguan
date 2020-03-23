/* 获取影视剧资源   */
$(document).on("click", ".get-movies-detail", function () {
    let movieId = $(this).attr("movie-id");
    console.log("movieId : " + movieId);
    $.ajax({
        url: baseUrl + "/movies/" + movieId,
        type: "get",
        beforeSend: function (XMLHttpRequest) {
            XMLHttpRequest.setRequestHeader("token", token);
        },
        success: function (result) {
            console.log(result);
        }
    });
});