let readStatus = "-1";

$(document).on("click", ".get-feedback-detail", function () {
    let feedbackId = $(this).attr("feedback-id");
    $.ajax({
        url: "/backend/feedback/detail/" + feedbackId,
        type: "get",
        success: function (result) {
            if (result.status === 0) {
                $("#feedback-detail-modal").modal("show");
                showFeedbackDetail(result.data);
            } else {
                Notiflix.Notify.Warning(result.msg);
            }
        }
    });
});

/**
 * 显示反馈详情
 * @param data 反馈信息
 */
function showFeedbackDetail(data) {
    $("#feedback-email").text(data.email);
    $("#feedback-username").text(data.username);
    $("#user-feedback-content").val(data.content);
    $("#read-feedback").attr("feedback-id", data.feedbackId);
}

// 反馈总页数
let feedbackPages;
// 当前页
let currentFeedbackPage;

/**
 * 获取所有的反馈
 * @param read 反馈
 * @param page 当前页
 * @param size 每页大小
 */
function getFeedback(read, page, size) {
    $.ajax({
        url: "/backend/feedback/" + read,
        type: "get",
        data: "page=" + page + "&size=" + size,
        success: function (result) {
            if (result.status === 0) {
                feedbackPages = result.data.pages;
                currentFeedbackPage = result.data.current;
                showAllFeedback(result.data);
                buildFeedbackPageUl(result.data);
                buildFeedbackPageMessage(result.data);
            } else {
                Notiflix.Notify.Warning(result.msg);
            }
        }
    });
}

/**
 * 展示所有的反馈
 * @param data 反馈数据
 */
function showAllFeedback(data) {
    $("#show-feedback-area").empty();
    let records = data.records;
    $.each(records, function (index, record) {
        let numTd = $("<td></td>").append(index + 1);
        let timeTd = $("<td></td>").append(printTimeFormat(parseInt(record.createTime)));
        let btnTd = $("<td></td>");
        let readTd = $("<td></td>");
        if (record.read === 1) {
            readTd.append("已读");
            btnTd
                .append($("<button class='btn btn-sm btn-outline-info btn-round get-feedback-detail'>查看</button>").attr("feedback-id", record.feedbackId));

        } else {
            readTd.append("未读");
            btnTd
                .append($("<button class='btn btn-sm btn-outline-secondary btn-round get-feedback-detail'>查看</button>").attr("feedback-id", record.feedbackId));
        }
        $("<tr></tr>")
            .append(numTd)
            .append(timeTd)
            .append(readTd)
            .append(btnTd)
            .appendTo("#show-feedback-area");
    })
}

/**
 * 构建电影分页信息
 * @param data
 */
function buildFeedbackPageMessage(data) {
    // <p>当前第 <strong>1</strong> 页, 共42页, 1008条数据</p>
    $("#feedback-page-message").empty();
    $("<p>第 </p>")
        .append($("<strong></strong>").append(data.current))
        .append(" 页，共")
        .append(data.pages + "页")
        .appendTo("#feedback-page-message");
}

/**
 * 构建电影分页
 * @param data 数据
 */
function buildFeedbackPageUl(data) {
    $("#feedback-page-ul").empty();

    let ul = $("<ul class='pagination pagination-round'></ul>");
    // 首页
    let firstPageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>").append("首页"));
    // 前一页
    let prePageLi = $("<li class='page-item'></li>")
        .append($("<a class='page-link' href='#'></a>")
            .append($("<i class='fa fa-angle-left'></i>")));
    // 判断是否还有上一页， 没有则disabled
    if (currentFeedbackPage === 1) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        firstPageLi.click(function () {
            getFeedback(readStatus, 1, 24);
        });
        prePageLi.click(function () {
            getFeedback(readStatus, currentFeedbackPage - 1, 24);
        });
    }

    ul.append(firstPageLi)
        .append(prePageLi);

    if (feedbackPages >= 5) {
        if (currentFeedbackPage >= 2) {
            for (i = currentFeedbackPage - 2; i <= currentFeedbackPage + 2; i++) {
                let numLi = $("<li class='page-item'></li>")
                    .append($("<a class='page-link feedback-page-jump'></a>").append(i));
                if (currentFeedbackPage === i) {
                    numLi.addClass("active");
                }
                if (feedbackPages === i) {
                    break;
                }
                ul.append(numLi);
            }
        } else {
            for (i = 1; i <= 5; i++) {
                let numLi = $("<li class='page-item'></li>")
                    .append($("<a class='page-link feedback-page-jump'></a>").append(i));
                if (currentFeedbackPage === i) {
                    numLi.addClass("active");
                }
                ul.append(numLi);
            }
        }
    } else {
        for (i = 1; i <= feedbackPages; i++) {
            let numLi = $("<li class='page-item'></li>")
                .append($("<a class='page-link feedback-page-jump'></a>").append(i));
            if (currentFeedbackPage === i) {
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
    if (currentFeedbackPage === feedbackPages) {
        lastPageLi.addClass("disabled");
        nextPageLi.addClass("disabled");
    } else {
        lastPageLi.click(function () {
            getFeedback(readStatus, feedbackPages, 24);
        });
        prePageLi.click(function () {
            getFeedback(readStatus, currentMoviePage + 1, 24);
        });
    }
    ul.append(nextPageLi)
        .append(lastPageLi)
        .appendTo("#feedback-page-ul");
}

/* 电影页面跳转 */
$(document).on("click", ".feedback-page-jump", function () {
    let page = $(this).text();
    getFeedback(readStatus, page, 30);
});