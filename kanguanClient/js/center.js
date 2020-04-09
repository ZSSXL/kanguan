/**
 * 删除订阅
 */
$(document).on("click", ".delete-request", function () {
    let requestId = $(this).attr("request-id");
    $.ajax({
        url: baseUrl + "/request/" + requestId,
        type: "delete",
        beforeSend: function (XMLHttpRequest) {
            XMLHttpRequest.setRequestHeader("token", token);
        },
        success: function (result) {
            if (result.status === 0) {
                Notiflix.Notify.Success("删除成功");
                getPersonRequest();
            } else {
                Notiflix.Notify.Warning(result.msg);
            }
        }
    });
});

/**
 * 删除订阅
 */
$(document).on("click", ".delete-sub", function () {
    let subId = $(this).attr("sub-id");
    $.ajax({
        url: baseUrl + "/subscription/" + subId,
        type: "delete",
        beforeSend: function (XMLHttpRequest) {
            XMLHttpRequest.setRequestHeader("token", token);
        },
        success: function (result) {
            if (result.status === 0) {
                Notiflix.Notify.Success("删除成功");
                getSubscription();
            } else {
                Notiflix.Notify.Warning(result.msg);
            }
        }
    });
});

/**
 * 查看资源
 */
$(document).on("click", ".check-resource-in-sub", function () {
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
