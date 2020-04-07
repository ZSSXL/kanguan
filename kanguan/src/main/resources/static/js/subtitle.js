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
                showMovieSubtitle(result.data.records, type);
            } else if (type === "0") {
                showTvSubtitle(result.data.records, type);
            }
        }
    });
}

/**
 * 展示电影字幕概略
 * @param data 数据
 */
function showMovieSubtitle(data, type) {
    $("#movie-subtitle-main-message").empty();
    $.each(data, function (index, item) {
        $("<div class='col-12 col-lg-3 col-xl-3'></div>")
            .append($("<div class='card'></div>")
                .append($("<div class='card-body gradient-sylvia'></div>")
                    .append($("<div class='media align-items-center'></div>")
                        .append($("<div class='media-body'></div>")
                            .append($("<h5 class='mb-0 text-white'></h5>").append(item.count))
                            .append($("<p class='mb-0 text-white omit-1'></p>").append(item.moviesName)))
                        .append($("<div class='w-icon'></div>")
                            .append($("<i class='zmdi zmdi-plus-circle text-white add-subtitle'></i>").attr({
                                "target-id": item.targetId,
                                "type": type
                            }))))))
            .appendTo("#movie-subtitle-main-message");
    })
}

/**
 * 展示电视字幕概略
 * @param data 数据
 */
function showTvSubtitle(data, type) {
    $("#tv-subtitle-main-message").empty();
    $.each(data, function (index, item) {
        $("<div class='col-12 col-lg-3 col-xl-3'></div>")
            .append($("<div class='card'></div>")
                .append($("<div class='card-body gradient-branding'></div>")
                    .append($("<div class='media align-items-center'></div>")
                        .append($("<div class='media-body'></div>")
                            .append($("<h5 class='mb-0 text-white'></h5>").append(item.count))
                            .append($("<p class='mb-0 text-white omit-1'></p>").append(item.moviesName)))
                        .append($("<div class='w-icon'></div>")
                            .append($("<i class='zmdi zmdi-plus-circle text-white add-subtitle'></i>").attr({
                                "target-id": item.targetId,
                                "type": type
                            }))))))
            .appendTo("#tv-subtitle-main-message");
    })

}