// 当前电影页数
let tvPages;
// 当前页
let currentTvPage;

/* 分页获取电影 */
function getAllTv(page, size) {
    $.ajax({
        url: baseUrl + "/movies/tv",
        data: "page=" + page + "&size=" + size,
        type: "get",
        beforeSend: function (XMLHttpRequest) {
            XMLHttpRequest.setRequestHeader("token", token);
        },
        success: function (result) {
            currentTvPage = result.data.current;
            tvPages = result.data.pages;
            showTv(result.data);
            buildTvPageMessage(result.data);
            buildTvPageUl(result.data);
        }
    });
}

/**
 * 展示影视剧
 * @param data 分页数据
 */
function showTv(data) {
    $("#load-tv-area").empty();
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
            .append(btnDiv)).appendTo("#load-tv-area");
    });
}


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

$(document).on("click", ".tv-page-jump", function () {
    let page = $(this).text();
    getAllTv(page, 24);
});