/**
 * 获取所有热门影视剧
 */
function getAllHotMovies() {
    getAllHotTv(1, 20);
    getAllHotMovie(1, 20);
}

/* 分页获取电影 */
function getAllHotMovie(page, size) {
    $.ajax({
        url: "/backend/hot/movie",
        type: "get",
        success: function (result) {
            showHotMovies(result.data, "load-hot-movie");
        }
    });
}

/**
 * 分页获取电视剧
 */
function getAllHotTv(page, size) {
    $.ajax({
        url: "/backend/hot/tv",
        type: "get",
        success: function (result) {
            showHotMovies(result.data, "load-hot-tv");
        }
    });
}

/**
 * 展示热门影视剧
 * @param data 分页数据
 * @param idName idName
 */
function showHotMovies(data, idName) {
    $("#" + idName).empty();
    $.each(data, function (index, item) {
        let coverDiv = $("<div class='overlay-img'></div>").attr("style", "background-image: url(" + item.cover + ")");
        let contentDiv = $("<div class='box-content'></div>")
            .append($("<h3 class='title omit-2 m-2'></h3>").append(item.name + " (" + item.premiere + ")"))
            .append($("<p class='mb-0 extra-small-font text-white omit-3 m-2'></p>").append("by " + item.director))
            .append($("<span class='post omit-9'></span>").append(item.intro2duction));
        let btnDiv = $("<div class='icon'></div>")
            .append($("<button class='btn btn-white btn-round mb-2 cancel-hot'>取消推荐</button>").attr("hot-id", item.hotId));
        $("<div class='col-12 col-lg-4 col-xl-3'></div>").append($("<div class='box card'></div>")
            .append(coverDiv)
            .append(contentDiv)
            .append(btnDiv)).appendTo("#" + idName);
    });
}

/**
 * 取消推荐
 */
$(document).on("click", ".cancel-hot", function () {
    let hotId = $(this).attr("hot-id");
    $.ajax({
        url: "/backend/hot/" + hotId,
        type: "delete",
        success: function (result) {
            if (result.status === 0) {
                Notiflix.Notify.Success("取消成功");
                getAllHotMovies();
            } else {
                Notiflix.Notify.Warning(result.msg);
            }
        }
    });
});