// 当前电影页数
let moviePages;
// 当前页
let currentMoviePage;

/* 分页获取电影 */
function getAllMovie(page, size) {
    $.ajax({
        url: baseUrl + "/movies/movie",
        data: "page=" + page + "&size=" + size,
        type: "get",
        beforeSend: function (XMLHttpRequest) {
            XMLHttpRequest.setRequestHeader("token", token);
        },
        success: function (result) {
            moviePages = result.data.pages;
            currentMoviePage = result.data.current;
            showMovie(result.data);
            buildMoviePageMessage(result.data);
            buildMoviePageUl(result.data);
        }
    });
}

/**
 * 展示影视剧
 * @param data 分页数据
 */
function showMovie(data) {
    $("#load-movie-area").empty();
    const records = data.records;
    $.each(records, function (index, record) {
        let coverDiv = $("<div class='overlay-img'></div>").attr("style", "background-image: url(" + record.cover + ")");
        let contentDiv = $("<div class='box-content'></div>")
            .append($("<h3 class='title omit-2 m-2'></h3>").append(record.name + " (" + record.premiere + ")"))
            .append($("<p class='mb-0 extra-small-font text-white omit-1 m-2'></p>").append("by " + record.director))
            .append($("<span class='post omit-6'></span>").append(record.introduction));
        let btnDiv = $("<div class='icon'></div>")
            .append($("<button class='btn btn-white btn-round get-movies-detail mb-2'>查看详情</button>").attr("movie-id", record.movieId));
        $("<div class='col-12 col-lg-3 col-xl-2'></div>").append($("<div class='box card'></div>")
            .append(coverDiv)
            .append(contentDiv)
            .append(btnDiv)).appendTo("#load-movie-area");
    });
}

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

$(document).on("click", ".movie-page-jump", function () {
    let page = $(this).text();
    getAllMovie(page, 24);
});