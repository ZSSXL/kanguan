/**
 * 获取所有的字幕概略
 * @param type 类型
 * @param page 当前页
 * @param size 每页大小
 */
function getSubtitleMainMessageToAdd(type, page, size) {
    $.ajax({
        url: "/backend/subtitle/" + type,
        data: "page=" + page + "&size=" + size,
        type: "get",
        success: function (result) {
            console.log(result);
            if (type === "1") {
                showMovieSubtitleToAdd(result.data.records);
            } else if (type === "0") {
                showTvSubtitleToAdd(result.data.records);
            }
        }
    });
}

/**
 * 展示电影字幕概略
 * @param data 数据
 */
function showMovieSubtitleToAdd(data) {
    $("#movie-subtitle-main-message-to-add").empty();
    $.each(data, function (index, item) {
        $("<div class='col-12 col-lg-3 col-xl-3'></div>")
            .append($("<div class='card'></div>")
                .append($("<div class='card-body gradient-ohhappiness'></div>")
                    .append($("<div class='media align-items-center'></div>")
                        .append($("<div class='media-body'></div>")
                            .append($("<h5 class='mb-0 text-primary'></h5>").append(item.count))
                            .append($("<p class='mb-0 text-white omit-1'></p>").append(item.moviesName)))
                        .append($("<div class='w-icon'></div>")
                            .append($("<i class='zmdi zmdi-plus-circle text-white add-subtitle'></i>").attr("target-id", item.targetId))))))
            .appendTo("#movie-subtitle-main-message-to-add");
    })
}

/**
 * 展示电视字幕概略
 * @param data 数据
 */
function showTvSubtitleToAdd(data) {
    $("#tv-subtitle-main-message-to-add").empty();
    $.each(data, function (index, item) {
        $("<div class='col-12 col-lg-3 col-xl-3'></div>")
            .append($("<div class='card'></div>")
                .append($("<div class='card-body gradient-branding'></div>")
                    .append($("<div class='media align-items-center'></div>")
                        .append($("<div class='media-body'></div>")
                            .append($("<h5 class='mb-0 text-primary'></h5>").append(item.count))
                            .append($("<p class='mb-0 text-white omit-1'></p>").append(item.moviesName)))
                        .append($("<div class='w-icon'></div>")
                            .append($("<i class='zmdi zmdi-plus-circle text-white add-subtitle'></i>").attr("target-id", item.targetId))))))
            .appendTo("#tv-subtitle-main-message-to-add");
    })
}

/**
 * 添加字幕资源
 */
$(document).on("click", ".add-subtitle", function () {
    let targetId = $(this).attr("target-id");
    $("#add-subtitle-btn").attr("target-id", targetId);
    $("#add-subtitle-modal").modal("show");
});