/* 获取用户数量 */
function getRegisterCount() {
    $.ajax({
        url: "/backend/user/count",
        type: "get",
        success: function (result) {
            if (result.status === 0) {
                console.log(result.data);
                showRegisterCount(result.data);
            } else {
                Notiflix.Notify.Warning(result.msg);
            }
        }
    });
}

let userPages;
let currentUserPage;

// 1 会员 0 非会员 -1 全部
let member = "-1";
// 排序 desc 逆序  asc 顺序
let order = "desc";

/**
 * 展示用户数量
 * @param data 数据
 */
function showRegisterCount(data) {
    $("#total-user-count").text(data.total);
    $("#member-user-count").text(data.member);
    $("#user-count").text(parseInt(data.total) - parseInt(data.member));
}

/**
 * 获取所有用户
 */
function getAllUser(member, order, page, size) {
    $.ajax({
        url: "/backend/user/" + member,
        type: "get",
        data: "page=" + page + "&size=" + size + "&order=" + order,
        success: function (result) {
            if (result.status === 0) {
                userPages = result.data.pages;
                currentUserPage = result.data.current;
                showAllUser(result.data.records);
                buildUserPageMessage(result.data);
                buildUserPageUl(result.data);
            } else {
                Notiflix.Notify.Warning(result.msg);
            }
        }
    });
}

/* 展示所有的用户信息 */
function showAllUser(data) {
    $("#show-user-area").empty();
    $.each(data, function (index, user) {
        let numTd = $("<td></td>").append(index + 1);
        let usernameTd = $("<td></td>").append(user.username);
        let emailTd = $("<td></td>").append(user.email);
        let memberTd = $("<td></td>");
        if (user.member === "0") {
            memberTd.append("否");
        } else {
            memberTd.append("是");
        }
        let createTimeTd = $("<td></td>").append(printTimeFormat(parseInt(user.createTime)));
        let btnTd = $("<td></td>")
            .append($("<button class='btn btn-sm btn-danger btn-round delete-user'>删除</button>").attr("user-id", user.userId));
        $("<tr></tr>")
            .append(numTd)
            .append(usernameTd)
            .append(emailTd)
            .append(memberTd)
            .append(createTimeTd)
            .append(btnTd)
            .appendTo("#show-user-area");
    });
}


/**
 * 构建用户分页信息
 * @param data
 */
function buildUserPageMessage(data) {
    // <p>当前第 <strong>1</strong> 页, 共42页, 1008条数据</p>
    $("#user-page-message").empty();
    $("<p>第 </p>")
        .append($("<strong></strong>").append(data.current))
        .append(" 页，共")
        .append(data.pages + "页")
        .appendTo("#user-page-message");
}

/**
 * 构建用户分页
 * @param data 数据
 */
function buildUserPageUl(data) {
    $("#user-page-ul").empty();

    let ul = $("<ul class='pagination pagination-round'></ul>");
    // 首页
    let firstPageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>").append("首页"));
    // 前一页
    let prePageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>")
            .append($("<i class='fa fa-angle-left'></i>")));
    // 判断是否还有上一页， 没有则disabled
    if (currentUserPage === 1) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        firstPageLi.click(function () {
            getAllUser(member, order, 1, 24);
        });
        prePageLi.click(function () {
            getAllUser(member, order, currentUserPage - 1, 24);
        });
    }

    ul.append(firstPageLi)
        .append(prePageLi);

    if (userPages >= 5) {
        if (currentUserPage >= 2) {
            for (i = currentUserPage - 2; i <= currentUserPage + 2; i++) {
                let numLi = $("<li class='page-item'></li>")
                    .append($("<a class='page-link user-page-jump'></a>").append(i));
                if (currentUserPage === i) {
                    numLi.addClass("active");
                }
                if (userPages === i) {
                    break;
                }
                ul.append(numLi);
            }
        } else {
            for (i = 1; i <= 5; i++) {
                let numLi = $("<li class='page-item'></li>")
                    .append($("<a class='page-link user-page-jump'></a>").append(i));
                if (currentUserPage === i) {
                    numLi.addClass("active");
                }
                ul.append(numLi);
            }
        }
    } else {
        for (i = 1; i <= userPages; i++) {
            let numLi = $("<li class='page-item'></li>")
                .append($("<a class='page-link user-page-jump'></a>").append(i));
            if (currentUserPage === i) {
                numLi.addClass("active");
            }
            ul.append(numLi);
        }
    }

    // 首页
    let lastPageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>").append("尾页"));
    // 前一页
    let nextPageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>")
            .append($("<i class='fa fa-angle-right'></i>")));
    // 判断是否还有上一页， 没有则disabled
    if (currentUserPage === userPages) {
        lastPageLi.addClass("disabled");
        nextPageLi.addClass("disabled");
    } else {
        lastPageLi.click(function () {
            getAllUser(member, order, userPages, 24);
        });
        prePageLi.click(function () {
            getAllUser(member, order, currentMoviePage + 1, 24);
        });
    }
    ul.append(nextPageLi)
        .append(lastPageLi)
        .appendTo("#user-page-ul");
}

/* 电影页面跳转 */
$(document).on("click", ".user-page-jump", function () {
    let page = $(this).text();
    getAllUser(member, order, page, 30);
});