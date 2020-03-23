/* 界面加载 */

// 影视剧查看页面加载
$("#movie").click(function () {
    loadPage("movie");
    getAllMovie(1,20);
    sideBarActive("movie");
});

// 字幕查看页面加载
$("#tv").click(function () {
    loadPage("tv");
    getAllTv(1, 20);
    sideBarActive("tv");
});

/**
 * 加载页面
 * @param idName id名
 */
function loadPage(idName) {
    $("#page-load-area").load(idName + ".html");
    localStorage.setItem("kanguan-current-page", idName);
}


/**
 * 设置active
 * @param pageName
 */
function sideBarActive(pageName) {
    if (pageName === "tv") {
        $("#movie").attr("class", "nav-link");
    } else if (pageName === "movie") {
        $("#tv").attr("class", "nav-link");
    }
    $("#" + pageName).attr("class", "nav-link active");

}

/**
 * 当前页面
 */
function currentPage() {
    let item = localStorage.getItem("kanguan-current-page");
    if (item === null) {
        loadPage("movies");
        sideBarActive("movie");
    } else {
        loadPage(item);
        sideBarActive(item);
        if (item === "movie") {
            getAllMovie(1, 20);
        } else {
            getAllTv(1, 20);
        }
    }
}