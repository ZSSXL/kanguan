/* 获取热门影视剧 */
function getAllHotMovies() {
    getAllHotMovie();
    getAllHotTv();
}

/**
 * 获取所有热门电影
 */
function getAllHotMovie() {
    $.ajax({
        url: baseUrl + "/hot/movie",
        type: "get",
        beforeSend: function (XMLHttpRequest) {
            XMLHttpRequest.setRequestHeader("token", token);
        },
        success: function (result) {
            showHotMovie(result.data);
        }
    });
}

/**
 * 获取所有热门电视剧
 */
function getAllHotTv() {
    $.ajax({
        url: baseUrl + "/hot/tv",
        type: "get",
        beforeSend: function (XMLHttpRequest) {
            XMLHttpRequest.setRequestHeader("token", token);
        },
        success: function (result) {
            console.log(result);
            showHotTv(result.data);
        }
    });
}

/**
 * 展示影视剧
 * @param data 分页数据
 */
function showHotMovie(data) {
    $("#load-movie-area").empty();
    $.each(data, function (index, item) {
        let coverDiv = $("<div class='overlay-img'></div>").attr("style", "background-image: url(" + item.cover + ")");
        let contentDiv = $("<div class='box-content'></div>")
            .append($("<h3 class='title omit-2 m-2'></h3>").append(item.name + " (" + item.premiere + ")"))
            .append($("<p class='mb-0 extra-small-font text-white omit-1 m-2'></p>").append("by " + item.director))
            .append($("<span class='post omit-6'></span>").append(item.introduction));
        let btnDiv = $("<div class='icon'></div>")
            .append($("<button class='btn btn-white btn-round get-movies-detail mb-2'>查看详情</button>").attr("movie-id", item.movieId));
        $("<div class='col-12 col-lg-3 col-xl-2'></div>").append($("<div class='box'></div>")
            .append(coverDiv)
            .append(contentDiv)
            .append(btnDiv)).appendTo("#load-hot-movie");
    });
}

/**
 * 展示影视剧
 * @param data 分页数据
 */
function showHotTv(data) {
    $("#load-hot-tv").empty();
    $.each(data, function (index, item) {
        let coverDiv = $("<div class='overlay-img'></div>").attr("style", "background-image: url(" + item.cover + ")");
        let contentDiv = $("<div class='box-content'></div>")
            .append($("<h3 class='title omit-2 m-2'></h3>").append(item.name + " (" + item.premiere + ")"))
            .append($("<p class='mb-0 extra-small-font text-white omit-1 m-2'></p>").append("by " + item.director))
            .append($("<span class='post omit-6'></span>").append(item.introduction));
        let btnDiv = $("<div class='icon'></div>")
            .append($("<button class='btn btn-white btn-round get-movies-detail mb-2'>查看详情</button>").attr("movie-id", item.movieId));
        $("<div class='col-12 col-lg-3 col-xl-2'></div>").append($("<div class='box'></div>")
            .append(coverDiv)
            .append(contentDiv)
            .append(btnDiv)).appendTo("#load-hot-tv");
    });
}