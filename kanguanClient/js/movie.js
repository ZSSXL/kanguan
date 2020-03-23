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
            console.log(result);
            showMovie(result.data);
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