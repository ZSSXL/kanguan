let selectType = "";
let selectStyle = "";
let selectRegion = "";
let selectPremiere = "";

// 当前电影页数
let selectMoviesPages;
// 当前页
let currentSelectMoviesPage;

/**
 * 获取筛选条件
 */
$(document).on("click", ".select-movies", function () {
    $(this).addClass("on").siblings().removeClass("on");
    let selectName = $(this).attr("name");
    let rel = $(this).attr("rel");
    if (selectName === "type") {
        selectType = rel;
    } else if (selectName === "style") {
        selectStyle = rel;
    } else if (selectName === "region") {
        selectRegion = rel;
    } else if (selectName === "premiere") {
        selectPremiere = rel;
    }
    selectMovies(1, 30);

});

/**
 * 检索影视剧
 */
function selectMovies(page, size) {
    $.ajax({
        url: baseUrl + "/movies/select",
        data: "selectType=" + selectType + "&selectStyle=" + selectStyle +
            "&selectRegion=" + selectRegion + "&selectPremiere=" + selectPremiere
            + "&page=" + page + "&size=" + size,
        type: "get",
        beforeSend: function (XMLHttpRequest) {
            XMLHttpRequest.setRequestHeader("token", token);
        },
        success: function (result) {
            selectMoviesPages = result.data.pages;
            currentSelectMoviesPage = result.data.current;
            showSelectResult(result.data.records);
            buildSelectMoviesPageMessage(result.data);
            buildSelectMoviesPageUl(result.data);
        }
    });
}

/**
 * 展示检索结果
 * @param data 检索数据
 */
function showSelectResult(data) {
    $("#load-select-movies-area").empty();
    if (data.length === 0) {
        $("#no-select-result").attr("style", "");
    } else {
        $("#no-select-result").css("display", "none");
    }
    $.each(data, function (index, item) {
        let coverDiv = $("<div class='overlay-img'></div>").attr("style", "background-image: url(" + item.cover + ")");
        let contentDiv = $("<div class='box-content'></div>")
            .append($("<h3 class='title omit-2 m-2'></h3>").append(item.name + " (" + item.premiere + ")"))
            .append($("<p class='mb-0 extra-small-font text-white omit-1 m-2'></p>").append("by " + item.director))
            .append($("<span class='post omit-6'></span>").append(item.introduction));
        let btnDiv = $("<div class='icon'></div>")
            .append($("<button class='btn btn-white btn-round get-movies-detail mb-2'>查看详情</button>").attr("movie-id", item.movieId));
        $("<div class='col-12 col-lg-3 col-xl-2 mt-3'></div>").append($("<div class='box'></div>")
            .append(coverDiv)
            .append(contentDiv)
            .append(btnDiv)).appendTo("#load-select-movies-area");
    });
}

/**
 * 构建检索影视剧分页信息
 * @param data
 */
function buildSelectMoviesPageMessage(data) {
    // <p>当前第 <strong>1</strong> 页, 共42页, 1008条数据</p>
    $("#select-movies-page-message").empty();
    $("<p>第 </p>")
        .append($("<strong></strong>").append(data.current))
        .append(" 页，共")
        .append(data.pages + "页")
        .appendTo("#select-movies-page-message");
}

/**
 * 构建检索影视剧分页
 * @param data 数据
 */
function buildSelectMoviesPageUl(data) {
    $("#select-movies-page-ul").empty();

    let ul = $("<ul class='pagination pagination-round'></ul>");
    // 首页
    let firstPageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>").append("首页"));
    // 前一页
    let prePageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>")
            .append($("<i class='fa fa-angle-left'></i>")));
    // 判断是否还有上一页， 没有则disabled
    if (currentSelectMoviesPage === 1) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        firstPageLi.click(function () {
            selectMovies(1, 30);
        });
        prePageLi.click(function () {
            selectMovies(currentSelectMoviesPage - 1, 30);
        });
    }

    ul.append(firstPageLi)
        .append(prePageLi);

    if (selectMoviesPages >= 5) {
        if (currentSelectMoviesPage >= 2) {
            for (i = currentSelectMoviesPage - 2; i <= currentSelectMoviesPage + 2; i++) {
                let numLi = $("<li class='page-item'></li>")
                    .append($("<a class='page-link select-movies-page-jump'></a>").append(i));
                if (currentSelectMoviesPage === i) {
                    numLi.addClass("active");
                }
                if (selectMoviesPages === i) {
                    break;
                }
                ul.append(numLi);
            }
        } else {
            for (i = 1; i <= 5; i++) {
                let numLi = $("<li class='page-item'></li>")
                    .append($("<a class='page-link select-movies-page-jump'></a>").append(i));
                if (currentSelectMoviesPage === i) {
                    numLi.addClass("active");
                }
                ul.append(numLi);
            }
        }
    } else {
        for (i = 1; i <= selectMoviesPages; i++) {
            let numLi = $("<li class='page-item'></li>")
                .append($("<a class='page-link select-movies-page-jump'></a>").append(i));
            if (currentSelectMoviesPage === i) {
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
    if (currentSelectMoviesPage === selectMoviesPages) {
        lastPageLi.addClass("disabled");
        nextPageLi.addClass("disabled");
    } else {
        lastPageLi.click(function () {
            selectMovies(selectMoviesPages, 30);
        });
        prePageLi.click(function () {
            selectMovies(currentSelectMoviesPage + 1, 30);
        });
    }
    ul.append(nextPageLi)
        .append(lastPageLi)
        .appendTo("#select-movies-page-ul");
}

$(document).on("click", ".select-movies-page-jump", function () {
    let page = $(this).text();
    selectMovies(page, 30);
});
