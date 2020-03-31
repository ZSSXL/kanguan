/* 界面加载 */

// 影视剧查看页面加载
$("#movies").click(function () {
    loadPage("movies");
    sideBarActive("movies", "movies");
    getAllMovies();
});

// 添加影视剧页面加载
$("#add-movies").click(function () {
    loadPage("add-movies");
    sideBarActive("movies", "add-movies");
});

// 影视剧推荐页面加载
$("#hot-movies").click(function () {
    loadPage("hot-movies");
    sideBarActive("movies", "hot-movies");
    getAllHotMovies();
});

// 字幕查看页面加载
$("#subtitle").click(function () {
    loadPage("subtitle");
    sideBarActive("subtitle", "subtitle");
});

// 添加字幕加载
$("#add-subtitle").click(function () {
    loadPage("add-subtitle");
    sideBarActive("subtitle", "add-subtitle");
});

$("#feedback").click(function () {
    console.log("feedback");
    loadPage("feedback");
    $("#sidebar-4").addClass("active");
});

$("#request").click(function () {
    console.log("request");
    loadPage("request");
    $("#sidebar-5").addClass("active");
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
 * @param idName
 */
function sideBarActive(pageName, idName) {
    // sidebar-1
    if (pageName === "movies") {
        $("#sidebar-1").attr("class", "active");
        $("#" + idName).attr("class", "active finger");
        if (idName === "movies") {
            $("#add-movies").attr("class", "finger");
            $("#hot-movies").attr("class", "finger");
        } else if (idName === "add-movies") {
            $("#movies").attr("class", "finger");
            $("#hot-movies").attr("class", "finger");
        } else {
            $("#movies").attr("class", "finger");
            $("#add-movies").attr("class", "finger");
        }
        // sidebar-2
    } else if (pageName === "subtitle") {
        $("#sidebar-2").attr("class", "active");
        $("#" + idName).attr("class", "active finger");
        if (idName === "subtitle") {
            $("#add-" + idName).attr("class", "finger");
        } else {
            $("#subtitle").attr("class", "finger");
        }
        // sidebar-3
    } else if (pageName === "user") {
        console.log("active: user");
    } else {
        $("#" + pageName).attr("class", "active");
        console.log("active: other");
    }
}

/**
 * 当前页面
 */
function currentPage() {
    let item = localStorage.getItem("kanguan-current-page");
    console.log(item);
    if (item === null) {
        loadPage("movies");
        getAllMovies();
    } else {
        loadPage(item);
        if (item.indexOf("movies") >= 0) {
            if (item === "movies") {
                getAllMovies();
            } else if (item === "hot-movies") {
                getAllHotMovies();
            }
            sideBarActive("movies", item);
        } else {
            sideBarActive("subtitle", item);
        }
    }
}