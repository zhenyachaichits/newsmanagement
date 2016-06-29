$(window).scroll(function () {
    var sticky = $('.sticky'), scroll = $(window).scrollTop();
    if (scroll >= 10) sticky.addClass('fixed');
    else sticky.removeClass('fixed');
});
//
//$(window).on("resize", function () {
//    var panelHeight = $(".navigate").outerHeight();
//    $(".navigate").outerHeight(panelHeight + 15);
//}).resize();

$(".show-comments").click(function () {
    var commentsBtn = $(".show-comments");
    var state = commentsBtn.html();

    switch (state) {
        case "SHOW":
            commentsBtn.html("HIDE");
            $(".comment-holder").show("fast");
        case "HIDE":
            commentsBtn.html("SHOW");
            $(".comment-holder").hide("fast");
    }
});


$(document).ready(function () {
    var path = window.location.pathname;
    $(".page-link[href='" + path + "']").addClass("active");
    var newText = $('.news-author').html().replace(/\s+/g, " ").replace(", )", ")");
    $('.news-author').html(newText);
});


$(document).click(function () {
    $(".edit").show("fast");
    $(".update").hide("fast");
    $(".delete").hide("fast");
    $("form[action='tags/update']").children("input").prop('readonly', true);
    $("form[action='authors/update']").children("input").prop('readonly', true);
});

$(".edit").click(function (e) {
    e.stopPropagation();
    return false;
});

$("input").click(function (e) {
    e.stopPropagation();
    return false;
});

$(".edit").click(function () {
    $(this).hide("fast");
    var nameValue = $(this).attr("name");
    $(".update[name=" + nameValue + "]").show("fast");
    $(".delete[name=" + nameValue + "]").show("fast");
    $("form[name=" + nameValue + "]").children().prop('readonly', false);
});
