// 总页数
let addMovieSubtitlePages;
// 当前页
let currentAddMovieSubtitlePage;
// 电视剧总页数
let addTvSubtitlePages;
// 电视剧当前页
let currentAddTvSubtitlePage;

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
            if (type === "1") {
                addMovieSubtitlePages = result.data.pages;
                currentAddMovieSubtitlePage = result.data.current;
                showMovieSubtitleToAdd(result.data.records);
                buildAddMovieSubtitlePageUl(result.data);
                buildAddMovieSubtitlePageMessage(result.data);
            } else if (type === "0") {
                addTvSubtitlePages = result.data.pages;
                currentAddTvSubtitlePage = result.data.current;
                showTvSubtitleToAdd(result.data.records);
                buildAddTvSubtitlePageMessage(result.data);
                buildAddTvSubtitlePageUl(result.data);
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
 * 构建电影分页信息
 * @param data
 */
function buildAddMovieSubtitlePageMessage(data) {
    // <p>当前第 <strong>1</strong> 页, 共42页, 1008条数据</p>
    $("#add-movie-subtitle-page-message").empty();
    $("<p>第 </p>")
        .append($("<strong></strong>").append(data.current))
        .append(" 页，共")
        .append(data.pages + "页")
        .appendTo("#add-movie-subtitle-page-message");
}

/**
 * 构建电影分页
 * @param data 数据
 */
function buildAddMovieSubtitlePageUl(data) {
    $("#add-movie-subtitle-page-ul").empty();

    let ul = $("<ul class='pagination pagination-round'></ul>");
    // 首页
    let firstPageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>").append("首页"));
    // 前一页
    let prePageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>")
            .append($("<i class='fa fa-angle-left'></i>")));
    // 判断是否还有上一页， 没有则disabled
    if (currentAddMovieSubtitlePage === 1) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        firstPageLi.click(function () {
            getSubtitleMainMessageToAdd("1", 1, 24);
        });
        prePageLi.click(function () {
            getSubtitleMainMessageToAdd("1", currentAddMovieSubtitlePage - 1, 24);
        });
    }

    ul.append(firstPageLi)
        .append(prePageLi);

    if (addMovieSubtitlePages >= 5) {
        if (currentAddMovieSubtitlePage >= 2) {
            for (i = currentAddMovieSubtitlePage - 2; i <= currentAddMovieSubtitlePage + 2; i++) {
                let numLi = $("<li class='page-item'></li>")
                    .append($("<a class='page-link add-movie-subtitle-page-jump'></a>").append(i));
                if (currentAddMovieSubtitlePage === i) {
                    numLi.addClass("active");
                }
                if (addMovieSubtitlePages === i) {
                    break;
                }
                ul.append(numLi);
            }
        } else {
            for (i = 1; i <= 5; i++) {
                let numLi = $("<li class='page-item'></li>")
                    .append($("<a class='page-link add-movie-subtitle-page-jump'></a>").append(i));
                if (currentAddMovieSubtitlePage === i) {
                    numLi.addClass("active");
                }
                ul.append(numLi);
            }
        }
    } else {
        for (i = 1; i <= addMovieSubtitlePages; i++) {
            let numLi = $("<li class='page-item'></li>")
                .append($("<a class='page-link add-movie-subtitle-page-jump'></a>").append(i));
            if (currentAddMovieSubtitlePage === i) {
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
    if (currentAddMovieSubtitlePage === addMovieSubtitlePages) {
        lastPageLi.addClass("disabled");
        nextPageLi.addClass("disabled");
    } else {
        lastPageLi.click(function () {
            getSubtitleMainMessageToAdd("1", addMovieSubtitlePages, 24);
        });
        prePageLi.click(function () {
            getSubtitleMainMessageToAdd("1", currentAddMovieSubtitlePage + 1, 24);
        });
    }
    ul.append(nextPageLi)
        .append(lastPageLi)
        .appendTo("#add-movie-subtitle-page-ul");
}

/* 电影页面跳转 */
$(document).on("click", ".add-movie-subtitle-page-jump", function () {
    let page = $(this).text();
    getSubtitleMainMessageToAdd("1", page, 24);
});


/**
 * 构建电影分页信息
 * @param data
 */
function buildAddTvSubtitlePageMessage(data) {
    // <p>当前第 <strong>1</strong> 页, 共42页, 1008条数据</p>
    $("#add-tv-subtitle-page-message").empty();
    $("<p>第 </p>")
        .append($("<strong></strong>").append(data.current))
        .append(" 页，共")
        .append(data.pages + "页")
        .appendTo("#add-tv-subtitle-page-message");
}

/**
 * 构建电影分页
 * @param data 数据
 */
function buildAddTvSubtitlePageUl(data) {
    $("#add-tv-subtitle-page-ul").empty();

    let ul = $("<ul class='pagination pagination-round'></ul>");
    // 首页
    let firstPageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>").append("首页"));
    // 前一页
    let prePageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>")
            .append($("<i class='fa fa-angle-left'></i>")));
    // 判断是否还有上一页， 没有则disabled
    if (currentAddTvSubtitlePage === 1) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        firstPageLi.click(function () {
            getSubtitleMainMessageToAdd("0", 1, 24);
        });
        prePageLi.click(function () {
            getSubtitleMainMessageToAdd("0", currentAddTvSubtitlePage - 1, 24);
        });
    }

    ul.append(firstPageLi)
        .append(prePageLi);

    if (addTvSubtitlePages >= 5) {
        if (currentAddTvSubtitlePage >= 2) {
            for (i = currentAddTvSubtitlePage - 2; i <= currentAddTvSubtitlePage + 2; i++) {
                let numLi = $("<li class='page-item'></li>")
                    .append($("<a class='page-link add-tv-subtitle-page-jump'></a>").append(i));
                if (currentAddTvSubtitlePage === i) {
                    numLi.addClass("active");
                }
                if (addTvSubtitlePages === i) {
                    break;
                }
                ul.append(numLi);
            }
        } else {
            for (i = 1; i <= 5; i++) {
                let numLi = $("<li class='page-item'></li>")
                    .append($("<a class='page-link add-tv-subtitle-page-jump'></a>").append(i));
                if (currentAddTvSubtitlePage === i) {
                    numLi.addClass("active");
                }
                ul.append(numLi);
            }
        }
    } else {
        for (i = 1; i <= addTvSubtitlePages; i++) {
            let numLi = $("<li class='page-item'></li>")
                .append($("<a class='page-link add-tv-subtitle-page-jump'></a>").append(i));
            if (currentAddTvSubtitlePage === i) {
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
    if (currentAddTvSubtitlePage === addTvSubtitlePages) {
        lastPageLi.addClass("disabled");
        nextPageLi.addClass("disabled");
    } else {
        lastPageLi.click(function () {
            getSubtitleMainMessageToAdd("0", addTvSubtitlePages, 24);
        });
        prePageLi.click(function () {
            getSubtitleMainMessageToAdd("0", currentAddTvSubtitlePage + 1, 24);
        });
    }
    ul.append(nextPageLi)
        .append(lastPageLi)
        .appendTo("#add-tv-subtitle-page-ul");
}

/* 电视剧页面跳转 */
$(document).on("click", ".add-tv-subtitle-page-jump", function () {
    let page = $(this).text();
    getSubtitleMainMessageToAdd("0", page, 24);
});


/**
 * 添加字幕资源
 */
$(document).on("click", ".add-subtitle", function () {
    let targetId = $(this).attr("target-id");
    $("#add-subtitle-btn").attr("target-id", targetId);
    $("#add-subtitle-modal").modal("show");
});