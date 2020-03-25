/* 影视剧专用js */
let totalMoviePages = 0; // 总页数
let currentMoviePage = 0; // 当前电影所在页
let totalTvPages = 0; // 电视剧总页数
let currentTvPage = 0; // 当前电视剧所在页

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
            console.log(result);
            showMovies(result.data, "load-movie-area", "#add-movie-resource-modal");
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
            console.log(result);
            showMovies(result.data, "load-tv-area", "#add-tv-resource-modal");
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
            .append($("<button class='btn btn-info btn-round mb-2'>详情</button>").attr("movie-id", record.movieId))
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
