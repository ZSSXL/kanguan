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

/**
 * 用户管理
 */
$("#user").click(function () {
    loadPage("user");
    sideBarActive("user", "user");
});

/**
 * 会员管理
 */
$("#member-user").click(function () {
    loadPage("member-user");
    sideBarActive("user", "member-user");
});

/**
 * 反馈页面加载
 */
$("#feedback").click(function () {
    loadPage("feedback");
    $("#sidebar-4").addClass("active");
    $("#sidebar-5").removeClass("active");
});

/**
 * 请求页面加载
 */
$("#request").click(function () {
    loadPage("request");
    $("#sidebar-5").addClass("active");
    $("#sidebar-4").removeClass("active");
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
        $("#sidebar-3").attr("class", "active");
        $("#" + idName).attr("class", "active finger");
        if (idName === "user") {
            $("#member-user").removeClass("active");
        } else {
            $("#user").removeClass("active");
        }
    }
}

/**
 * 当前页面
 */
function currentPage() {
    let item = localStorage.getItem("kanguan-current-page");
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
        } else if (item.indexOf("subtitle") >= 0) {
            sideBarActive("subtitle", item);
        } else if (item.indexOf("user") >= 0) {
            sideBarActive("user", item);
        } else {
            if (item === "feedback") {
                $("#sidebar-4").attr("class", "active");
            } else if (item === "request") {
                $("#sidebar-5").attr("class", "active");
            }
        }
    }
}