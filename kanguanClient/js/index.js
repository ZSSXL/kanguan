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

/**
 * 请求资源
 */
$("#send-request").click(function () {
    let name = $("#request-name").val();
    let doubanAddress = $("#douban-address").val();
    let data = {name, doubanAddress};
    $.ajax({
        url: baseUrl + "/request",
        contentType: "application/json;charset=utf-8",
        type: "post",
        beforeSend: function (XMLHttpRequest) {
            XMLHttpRequest.setRequestHeader("token", token);
        },
        dataType: "json",
        data: JSON.stringify(data),
        success: function (result) {
            if (result.status === 0) {
                Notiflix.Notify.Success("提交成功");
                $("#request-name").val("");
                $("#douban-address").val("");
                $("#request-resource-modal").modal("hide");
            } else {
                Notiflix.Notify.Warning(result.msg);
            }
        }
    });
});

/**
 * 请求资源
 */
$("#send-feedback").click(function () {
    let content = $("#feedback-content").val();
    console.log(content);
    $.ajax({
        url: baseUrl + "/feedback",
        contentType: "application/json;charset=utf-8",
        type: "post",
        beforeSend: function (XMLHttpRequest) {
            XMLHttpRequest.setRequestHeader("token", token);
        },
        dataType: "json",
        data: JSON.stringify(content),
        success: function (result) {
            if (result.status === 0) {
                Notiflix.Notify.Success("提交成功");
                $("#feedback-content").val("");
                $("#feedback-modal").modal("hide");
            } else {
                Notiflix.Notify.Warning(result.msg);
            }
        }
    });
});
