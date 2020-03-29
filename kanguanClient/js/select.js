let selectType = "";
let selectStyle = "";
let selectRegion = "";
let selectPremiere = "";

/**
 * 获取筛选条件
 */
$(document).on("click", ".select-movies", function () {
    $(this).addClass("on").siblings().removeClass("on");
    let selectName = $(this).attr("name");
    let rel = $(this).attr("rel");
    if (selectName === "type") {
        selectType = rel;
    } else if (selectName === "style") {
        selectStyle = rel;
    } else if (selectName === "region") {
        selectRegion = rel;
    } else if (selectName === "premiere") {
        selectRegion = rel;
    }
    let data = {selectType, selectStyle, selectPremiere, selectRegion};
    console.log(data);
    selectMovies();

});

function selectMovies() {
    $.ajax({
        url: baseUrl + "/movies/select",
        data: "selectType=" + selectType + "&selectStyle=" + selectStyle +
            "&selectRegion=" + selectRegion + "&selectPremiere=" + selectPremiere,
        type: "get",
        beforeSend: function (XMLHttpRequest) {
            XMLHttpRequest.setRequestHeader("token", token);
        },
        success: function (result) {
            console.log(result);
        }
    });
}
