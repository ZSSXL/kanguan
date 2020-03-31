/**
 * 获取未读反馈数量
 */
function getUnreadFeedbackCount() {
    $.ajax({
        url: "/backend/feedback",
        type: "get",
        success: function (result) {
            if (result.status === 0) {
                $("#feedback-count").text(result.data);
            } else {
                Notiflix.Notify.Warning(result.msg);
            }
        }
    });
}

/**
 * 获取未回复的请求数量
 */
function getUnansweredCount() {
    $.ajax({
        url: "/backend/request",
        type: "get",
        success: function (result) {
            if (result.status === 0) {
                $("#request-count").text(result.data);
            } else {
                Notiflix.Notify.Warning(result.msg);
            }
        }
    });
}