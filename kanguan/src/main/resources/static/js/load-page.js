/* 界面加载 */

// 影视剧查看页面加载
$("#movies-check").click(function () {
    $("#page-load-area").load("/movies");
});

// 添加影视剧页面加载
$("#movies-add").click(function () {
    $("#page-load-area").load("/add-movies");
});

// 字幕查看页面加载
$("#subtitle-check").click(function () {
    $("#page-load-area").load("/subtitle");
});

// 添加字幕加载
$("#subtitle-add").click(function () {
    $("#page-load-area").load("/add-subtitle");
});


/**
 * 设置active
 * @param pageName
 */
function sideBarActive(pageName) {
    alert("hello load page" + pageName);
}