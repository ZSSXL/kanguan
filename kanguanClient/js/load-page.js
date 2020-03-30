/* 界面加载 */
// 网站首页
$("#home").click(function () {
    loadPage("home");
    getAllHotMovies();
    sideBarActive("home");
});

// 影视剧查看页面加载
$("#movie").click(function () {
    loadPage("movie");
    getAllMovie(1, 24);
    sideBarActive("movie");
});

// 字幕查看页面加载
$("#tv").click(function () {
    loadPage("tv");
    getAllTv(1, 24);
    sideBarActive("tv");
});

// 检索页面
$("#select").click(function () {
    loadPage("select");
    selectMovies(1, 30);
    sideBarActive("select");
});

/**
 * 打开个人中心
 */
$("#personal-center").click(function () {
    $("#page-load-area").load("center.html");
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
    if (pageName === "home") {
        $("#movie").attr("class", "nav-link");
        $("#tv").attr("class", "nav-link");
        $("#select").attr("class", "nav-link");
    } else if (pageName === "tv") {
        $("#home").attr("class", "nav-link");
        $("#movie").attr("class", "nav-link");
        $("#select").attr("class", "nav-link");
    } else if (pageName === "movie") {
        $("#home").attr("class", "nav-link");
        $("#tv").attr("class", "nav-link");
        $("#select").attr("class", "nav-link");
    } else if (pageName === "select") {
        $("#home").attr("class", "nav-link");
        $("#movie").attr("class", "nav-link");
        $("#tv").attr("class", "nav-link");
    }
    $("#" + pageName).attr("class", "nav-link active");

}

/**
 * 当前页面
 */
function currentPage() {
    let item = localStorage.getItem("kanguan-current-page");
    getPersonalInfo();
    if (item === null) {
        loadPage("home");
        sideBarActive("home");
    } else {
        loadPage(item);
        sideBarActive(item);
        if (item === "movie") {
            getAllMovie(1, 24);
        } else if (item === "tv") {
            getAllTv(1, 24);
        } else if (item === "home") {
            getAllHotMovies();
        } else if (item === "select") {
            selectMovies(1, 30);
        }
    }
}