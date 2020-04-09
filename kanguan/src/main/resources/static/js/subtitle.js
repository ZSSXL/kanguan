// 总页数
let movieSubtitlePages;
// 当前页
let currentMovieSubtitlePage;
// 电视剧总页数
let tvSubtitlePages;
// 电视剧当前页
let currentTvSubtitlePage;

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
            if (type === "1") {
                movieSubtitlePages = result.data.pages;
                currentMovieSubtitlePage = result.data.current;
                showMovieSubtitle(result.data.records);
                buildMovieSubtitlePageMessage(result.data);
                buildMovieSubtitlePageUl(result.data);
            } else if (type === "0") {
                tvSubtitlePages = result.data.pages;
                currentTvSubtitlePage = result.data.current;
                showTvSubtitle(result.data.records);
                buildTvSubtitlePageMessage(result.data);
                buildTvSubtitlePageUl(result.data);
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


/**
 * 构建电影分页信息
 * @param data
 */
function buildMovieSubtitlePageMessage(data) {
    // <p>当前第 <strong>1</strong> 页, 共42页, 1008条数据</p>
    $("#movie-subtitle-page-message").empty();
    $("<p>第 </p>")
        .append($("<strong></strong>").append(data.current))
        .append(" 页，共")
        .append(data.pages + "页")
        .appendTo("#movie-subtitle-page-message");
}

/**
 * 构建电影分页
 * @param data 数据
 */
function buildMovieSubtitlePageUl(data) {
    $("#movie-subtitle-page-ul").empty();

    let ul = $("<ul class='pagination pagination-round'></ul>");
    // 首页
    let firstPageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>").append("首页"));
    // 前一页
    let prePageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>")
            .append($("<i class='fa fa-angle-left'></i>")));
    // 判断是否还有上一页， 没有则disabled
    if (currentMovieSubtitlePage === 1) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        firstPageLi.click(function () {
            getSubtitleMainMessage("1", 1, 24);
        });
        prePageLi.click(function () {
            getSubtitleMainMessage("1", currentMovieSubtitlePage - 1, 24);
        });
    }

    ul.append(firstPageLi)
        .append(prePageLi);

    if (movieSubtitlePages >= 5) {
        if (currentMovieSubtitlePage >= 2) {
            for (i = currentMovieSubtitlePage - 2; i <= currentMovieSubtitlePage + 2; i++) {
                let numLi = $("<li class='page-item'></li>")
                    .append($("<a class='page-link movie-subtitle-page-jump'></a>").append(i));
                if (currentMovieSubtitlePage === i) {
                    numLi.addClass("active");
                }
                if (movieSubtitlePages === i) {
                    break;
                }
                ul.append(numLi);
            }
        } else {
            for (i = 1; i <= 5; i++) {
                let numLi = $("<li class='page-item'></li>")
                    .append($("<a class='page-link movie-subtitle-page-jump'></a>").append(i));
                if (currentMovieSubtitlePage === i) {
                    numLi.addClass("active");
                }
                ul.append(numLi);
            }
        }
    } else {
        for (i = 1; i <= movieSubtitlePages; i++) {
            let numLi = $("<li class='page-item'></li>")
                .append($("<a class='page-link movie-subtitle-page-jump'></a>").append(i));
            if (currentMovieSubtitlePage === i) {
                numLi.addClass("active");
            }
            ul.append(numLi);
        }
    }

    // 首页
    let lastPageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>").append("尾页"));
    // 前一页
    let nextPageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>")
            .append($("<i class='fa fa-angle-right'></i>")));
    // 判断是否还有上一页， 没有则disabled
    if (currentMovieSubtitlePage === movieSubtitlePages) {
        lastPageLi.addClass("disabled");
        nextPageLi.addClass("disabled");
    } else {
        lastPageLi.click(function () {
            getSubtitleMainMessage("1", movieSubtitlePages, 24);
        });
        prePageLi.click(function () {
            getSubtitleMainMessage("1", currentMovieSubtitlePage + 1, 24);
        });
    }
    ul.append(nextPageLi)
        .append(lastPageLi)
        .appendTo("#movie-subtitle-page-ul");
}

/* 电影页面跳转 */
$(document).on("click", ".movie-subtitle-page-jump", function () {
    let page = $(this).text();
    getSubtitleMainMessage("1", page, 24);
});


/**
 * 构建电影分页信息
 * @param data
 */
function buildTvSubtitlePageMessage(data) {
    // <p>当前第 <strong>1</strong> 页, 共42页, 1008条数据</p>
    $("#tv-subtitle-page-message").empty();
    $("<p>第 </p>")
        .append($("<strong></strong>").append(data.current))
        .append(" 页，共")
        .append(data.pages + "页")
        .appendTo("#tv-subtitle-page-message");
}

/**
 * 构建电视剧分页
 * @param data 数据
 */
function buildTvSubtitlePageUl(data) {
    $("#tv-subtitle-page-ul").empty();

    let ul = $("<ul class='pagination pagination-round'></ul>");
    // 首页
    let firstPageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>").append("首页"));
    // 前一页
    let prePageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>")
            .append($("<i class='fa fa-angle-left'></i>")));
    // 判断是否还有上一页， 没有则disabled
    if (currentTvSubtitlePage === 1) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        firstPageLi.click(function () {
            getSubtitleMainMessage("0", 1, 24);
        });
        prePageLi.click(function () {
            getSubtitleMainMessage("0", currentTvSubtitlePage - 1, 24);
        });
    }

    ul.append(firstPageLi)
        .append(prePageLi);

    if (tvSubtitlePages >= 5) {
        if (currentTvSubtitlePage >= 2) {
            for (i = currentTvSubtitlePage - 2; i <= currentTvSubtitlePage + 2; i++) {
                let numLi = $("<li class='page-item'></li>")
                    .append($("<a class='page-link tv-subtitle-page-jump'></a>").append(i));
                if (currentTvSubtitlePage === i) {
                    numLi.addClass("active");
                }
                if (tvSubtitlePages === i) {
                    break;
                }
                ul.append(numLi);
            }
        } else {
            for (i = 1; i <= 5; i++) {
                let numLi = $("<li class='page-item'></li>")
                    .append($("<a class='page-link tv-subtitle-page-jump'></a>").append(i));
                if (currentTvSubtitlePage === i) {
                    numLi.addClass("active");
                }
                ul.append(numLi);
            }
        }
    } else {
        for (i = 1; i <= tvSubtitlePages; i++) {
            let numLi = $("<li class='page-item'></li>")
                .append($("<a class='page-link tv-subtitle-page-jump'></a>").append(i));
            if (currentTvSubtitlePage === i) {
                numLi.addClass("active");
            }
            ul.append(numLi);
        }
    }

    // 首页
    let lastPageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>").append("尾页"));
    // 前一页
    let nextPageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>")
            .append($("<i class='fa fa-angle-right'></i>")));
    // 判断是否还有上一页， 没有则disabled
    if (currentTvSubtitlePage === tvSubtitlePages) {
        lastPageLi.addClass("disabled");
        nextPageLi.addClass("disabled");
    } else {
        lastPageLi.click(function () {
            getSubtitleMainMessage("0", tvSubtitlePages, 24);
        });
        prePageLi.click(function () {
            getSubtitleMainMessage("0", currentTvSubtitlePage + 1, 24);
        });
    }
    ul.append(nextPageLi)
        .append(lastPageLi)
        .appendTo("#tv-subtitle-page-ul");
}

/* 电视剧页面跳转 */
$(document).on("click", ".tv-subtitle-page-jump", function () {
    let page = $(this).text();
    getSubtitleMainMessage("0", page, 24);
});

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