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

/**
 * 搜索影视剧
 */
$("#search-movies").click(function () {
    let name = $("#search-name").val();
    $.ajax({
        url: baseUrl + "/search/" + name,
        type: "get",
        beforeSend: function (XMLHttpRequest) {
            XMLHttpRequest.setRequestHeader("token", token);
        },
        success: function (result) {
            if (result.status === 0) {
                sessionStorage.setItem("kanguan-search-data", JSON.stringify(result.data));
                $("#page-load-area").load("search.html");
            } else {
                Notiflix.Notify.Warning(result.msg);
            }
        }
    });
});
