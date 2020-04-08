/**
 * 获取所有的字幕概略
 * @param type 类型
 * @param page 当前页
 * @param size 每页大小
 */
function getSubtitleMainMessage(type, page, size) {
    $.ajax({
        url: "/backend/subtitle/" + type,
        data: "page=" + page + "&size=" + size,
        type: "get",
        success: function (result) {
            console.log(result);
            if (type === "1") {
                showMovieSubtitle(result.data.records);
            } else if (type === "0") {
                showTvSubtitle(result.data.records);
            }
        }
    });
}

/**
 * 展示电影字幕概略
 * @param data 数据
 */
function showMovieSubtitle(data) {
    $("#movie-subtitle-main-message").empty();
    $.each(data, function (index, item) {
        $("<div class='col-12 col-lg-3 col-xl-3'></div>")
            .append($("<div class='card'></div>")
                .append($("<div class='card-body gradient-ohhappiness'></div>")
                    .append($("<div class='media align-items-center'></div>")
                        .append($("<div class='media-body'></div>")
                            .append($("<h5 class='mb-0 text-primary'></h5>").append(item.count))
                            .append($("<p class='mb-0 text-white omit-1'></p>").append(item.moviesName)))
                        .append($("<div class='w-icon'></div>")
                            .append($("<i class='zmdi zmdi-edit text-white get-subtitles'></i>").attr("target-id", item.targetId))))))
            .appendTo("#movie-subtitle-main-message");
    })
}

/**
 * 展示电视字幕概略
 * @param data 数据
 */
function showTvSubtitle(data) {
    $("#tv-subtitle-main-message").empty();
    $.each(data, function (index, item) {
        $("<div class='col-12 col-lg-3 col-xl-3'></div>")
            .append($("<div class='card'></div>")
                .append($("<div class='card-body gradient-branding'></div>")
                    .append($("<div class='media align-items-center'></div>")
                        .append($("<div class='media-body'></div>")
                            .append($("<h5 class='mb-0 text-primary'></h5>").append(item.count))
                            .append($("<p class='mb-0 text-white omit-1'></p>").append(item.moviesName)))
                        .append($("<div class='w-icon'></div>")
                            .append($("<i class='zmdi zmdi-edit text-white get-subtitles'></i>").attr("target-id", item.targetId))))))
            .appendTo("#tv-subtitle-main-message");
    })
}

/* 获取字幕 */
$(document).on("click", ".get-subtitles", function () {
    let targetId = $(this).attr("target-id");
    console.log("targetId : " + targetId);
    $.ajax({
        url: "/backend/subtitle/list/" + targetId,
        type: "get",
        success: function (result) {
            console.log(result);
            if (result.status === 0) {
                showSubtitleListToTable(result.data);
                $("#subtitle-detail-modal").modal("show");
            } else {
                Notiflix.Notify.Warning(result.msg);
            }
        }
    })
});

/**
 * 展示字幕数据
 * @param data 数据
 */
function showSubtitleListToTable(data) {
    $("#show-subtitles-area").empty();
    if (data.length !== 0) {
        $.each(data, function (index, item) {
            let numTd = $("<td></td>").append(index + 1);
            let nameTd = $("<td></td>").append(item.name);
            let episodeTd = $("<td></td>").append(item.episode);
            let languageTd = $("<td></td>").append(item.language);
            let formatTd = $("<td></td>").append(item.format);
            let btnTd = $("<td></td>")
                .append($("<button class='btn btn-outline-warning btn-sm btn-round'>删除</button>").attr("subtitle-id", item.subtitleId));
            $("<tr></tr>")
                .append(numTd)
                .append(nameTd)
                .append(episodeTd)
                .append(languageTd)
                .append(formatTd)
                .append(btnTd)
                .appendTo("#show-subtitles-area");
        });
    } else {
        $("#no-subtitles").attr("style", "");
    }
}