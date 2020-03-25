/**
 * 获取所有热门影视剧
 */
function getAllHotMovies() {
    getAllTv(1, 20);
    getAllMovie(1, 20);
}

/* 分页获取电影 */
function getAllHotMovie(page, size) {
    $.ajax({
        url: "/backend/hot/movie",
        type: "get",
        success: function (result) {
            console.log(result);
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
            console.log(result);
        }
    });
}