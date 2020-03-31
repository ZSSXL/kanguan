/* 影视剧专用js */
// 当前电影页数
let moviePages;
// 当前页
let currentMoviePage;
// 当前电影页数
let tvPages;
// 当前页
let currentTvPage;

/**
 * 获取所有影视剧
 */
function getAllMovies() {
    getAllTv(1, 20);
    getAllMovie(1, 20);
}

/* 分页获取电影 */
function getAllMovie(page, size) {
    $.ajax({
        url: "/backend/movies/movie",
        data: "page=" + page + "&size=" + size,
        type: "get",
        success: function (result) {
            moviePages = result.data.pages;
            currentMoviePage = result.data.current;
            showMovies(result.data, "load-movie-area", "#add-movie-resource-modal");
            buildMoviePageMessage(result.data);
            buildMoviePageUl(result.data);
        }
    });
}

/**
 * 分页获取电视剧
 */
function getAllTv(page, size) {
    $.ajax({
        url: "/backend/movies/tv",
        data: "page=" + page + "&size=" + size,
        type: "get",
        success: function (result) {
            currentTvPage = result.data.current;
            tvPages = result.data.pages;
            showMovies(result.data, "load-tv-area", "#add-tv-resource-modal");
            buildTvPageMessage(result.data);
            buildTvPageUl(result.data);
        }
    });
}

/**
 * 展示影视剧
 * @param data 分页数据
 * @param idName idName
 * @param modal modal
 */
function showMovies(data, idName, modal) {
    $("#" + idName).empty();
    const records = data.records;
    $.each(records, function (index, record) {
        let coverDiv = $("<div class='overlay-img'></div>").attr("style", "background-image: url(" + record.cover + ")");
        let contentDiv = $("<div class='box-content'></div>")
            .append($("<h3 class='title omit-2 m-2'></h3>").append(record.name + " (" + record.premiere + ")"))
            .append($("<p class='mb-0 extra-small-font text-white omit-3 m-2'></p>").append("by " + record.director))
            .append($("<span class='post omit-9'></span>").append(record.introduction));
        let btnDiv = $("<div class='icon'></div>")
            .append($("<button class='btn btn-info btn-round mb-2 show-detail-movies'>详情</button>").attr("movie-id", record.movieId))
            .append($("<button class='btn btn-primary btn-round mb-2 add-resource-btn' data-toggle='modal'>添加资源</button>")
                .attr({"movies-id": record.movieId, "movies-type": record.type, "data-target": modal}))
            .append($("<button class='btn btn-danger recommend-resource btn-round mb-2'></button>")
                .append($("<span class='fa fa-thumbs-o-up'>推荐</span>")).attr({
                    "object": record.movieId,
                    "movies-type": record.type
                }));
        $("<div class='col-12 col-lg-4 col-xl-3'></div>").append($("<div class='box card'></div>")
            .append(coverDiv)
            .append(contentDiv)
            .append(btnDiv)).appendTo("#" + idName);
    });
}

/* 添加资源 */
$(document).on("click", ".add-resource-btn", function () {
    let type = $(this).attr("movies-type");
    let movieId = $(this).attr("movies-id");
    if (parseInt(type) === 1) {
        // 添加电影资源
        $("#add-movie-resource").attr("movie-id", movieId);
    } else if (parseInt(type) === 0) {
        // 添加电视剧资源
        $("#add-tv-resource").attr("tv-id", movieId);
    }
});

/**
 * 推荐资源
 */
$(document).on("click", ".recommend-resource", function () {
    let object = $(this).attr("object");
    let type = $(this).attr("movies-type");
    let data = {object, type};
    $.ajax({
        url: "/backend/hot",
        contentType: "application/json;charset=utf-8",
        type: "post",
        dataType: "json",
        data: JSON.stringify(data),
        success: function (result) {
            if (result.status === 0) {
                Notiflix.Notify.Success("推荐成功");
            } else {
                Notiflix.Notify.Warning(result.msg);
            }
        }
    });
});

/* 显示影视剧详情 */
$(document).on("click", ".show-detail-movies", function () {
    let movieId = $(this).attr("movie-id");
    console.log("movieId " + movieId);
    $.ajax({
        url: "/backend/resource/" + movieId,
        type: "get",
        success: function (result) {
            sessionStorage.setItem("kanguan-resource-data", JSON.stringify(result.data));
            $("#page-load-area").load("detail.html");
        }
    });
});

/**
 * 构建电影分页信息
 * @param data
 */
function buildMoviePageMessage(data) {
    // <p>当前第 <strong>1</strong> 页, 共42页, 1008条数据</p>
    $("#movie-page-message").empty();
    $("<p>第 </p>")
        .append($("<strong></strong>").append(data.current))
        .append(" 页，共")
        .append(data.pages + "页")
        .appendTo("#movie-page-message");
}

/**
 * 构建电影分页
 * @param data 数据
 */
function buildMoviePageUl(data) {
    $("#movie-page-ul").empty();

    let ul = $("<ul class='pagination pagination-round'></ul>");
    // 首页
    let firstPageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>").append("首页"));
    // 前一页
    let prePageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>")
            .append($("<i class='fa fa-angle-left'></i>")));
    // 判断是否还有上一页， 没有则disabled
    if (currentMoviePage === 1) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        firstPageLi.click(function () {
            getAllMovie(1, 24);
        });
        prePageLi.click(function () {
            getAllMovie(currentMoviePage - 1, 24);
        });
    }

    ul.append(firstPageLi)
        .append(prePageLi);

    if (moviePages >= 5) {
        if (currentMoviePage >= 2) {
            for (i = currentMoviePage - 2; i <= currentMoviePage + 2; i++) {
                let numLi = $("<li class='page-item'></li>")
                    .append($("<a class='page-link movie-page-jump'></a>").append(i));
                if (currentMoviePage === i) {
                    numLi.addClass("active");
                }
                if (moviePages === i) {
                    break;
                }
                ul.append(numLi);
            }
        } else {
            for (i = 1; i <= 5; i++) {
                let numLi = $("<li class='page-item'></li>")
                    .append($("<a class='page-link movie-page-jump'></a>").append(i));
                if (currentMoviePage === i) {
                    numLi.addClass("active");
                }
                ul.append(numLi);
            }
        }
    } else {
        for (i = 1; i <= moviePages; i++) {
            let numLi = $("<li class='page-item'></li>")
                .append($("<a class='page-link movie-page-jump'></a>").append(i));
            if (currentMoviePage === i) {
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
    if (currentMoviePage === moviePages) {
        lastPageLi.addClass("disabled");
        nextPageLi.addClass("disabled");
    } else {
        lastPageLi.click(function () {
            getAllMovie(moviePages, 24);
        });
        prePageLi.click(function () {
            getAllMovie(currentMoviePage + 1, 24);
        });
    }
    ul.append(nextPageLi)
        .append(lastPageLi)
        .appendTo("#movie-page-ul");
}

/* 电影页面跳转 */
$(document).on("click", ".movie-page-jump", function () {
    let page = $(this).text();
    getAllMovie(page, 24);
});

/**
 * 构建电视剧分页信息
 * @param data
 */
function buildTvPageMessage(data) {
    $("#tv-page-message").empty();
    $("<p>第 </p>")
        .append($("<strong></strong>").append(data.current))
        .append(" 页，共")
        .append(data.pages + "页")
        .appendTo("#tv-page-message");
}

/**
 * 构建电视剧分页
 * @param data 数据
 */
function buildTvPageUl(data) {
    $("#tv-page-ul").empty();

    let ul = $("<ul class='pagination pagination-round'></ul>");
    // 首页
    let firstPageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>").append("首页"));
    // 前一页
    let prePageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>")
            .append($("<i class='fa fa-angle-left'></i>")));
    // 判断是否还有上一页， 没有则disabled
    if (currentTvPage === 1) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        firstPageLi.click(function () {
            getAllTv(1, 24);
        });
        prePageLi.click(function () {
            getAllTv(currentTvPage - 1, 24);
        });
    }

    ul.append(firstPageLi)
        .append(prePageLi);

    if (tvPages >= 5) {
        if (currentTvPage >= 2) {
            for (i = currentTvPage - 2; i <= currentTvPage + 2; i++) {
                let numLi = $("<li class='page-item'></li>")
                    .append($("<a class='page-link tv-page-jump'></a>").append(i));
                if (currentMoviePage === i) {
                    numLi.addClass("active");
                }
                if (tvPages === i) {
                    break;
                }
                ul.append(numLi);
            }
        } else {
            for (i = 1; i <= 5; i++) {
                let numLi = $("<li class='page-item'></li>")
                    .append($("<a class='page-link tv-page-jump'></a>").append(i));
                if (currentTvPage === i) {
                    numLi.addClass("active");
                }
                ul.append(numLi);
            }
        }
    } else {
        for (i = 1; i <= tvPages; i++) {
            let numLi = $("<li class='page-item'></li>")
                .append($("<a class='page-link tv-page-jump'></a>").append(i));
            if (currentTvPage === i) {
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
    if (currentTvPage === tvPages) {
        lastPageLi.addClass("disabled");
        nextPageLi.addClass("disabled");
    } else {
        lastPageLi.click(function () {
            getAllTv(tvPages, 24);
        });
        prePageLi.click(function () {
            getAllTv(currentTvPage + 1, 24);
        });
    }
    ul.append(nextPageLi)
        .append(lastPageLi)
        .appendTo("#tv-page-ul");
}

/* 电视剧页面跳转 */
$(document).on("click", ".tv-page-jump", function () {
    let page = $(this).text();
    getAllTv(page, 24);
});
