$(window).scroll(function () {
    var sticky = $('.sticky'), scroll = $(window).scrollTop();
    if (scroll >= 10) sticky.addClass('fixed');
    else sticky.removeClass('fixed');
});

$(document).ready(function(){
    var path = window.location.pathname;

    alert(path);

    $(".page-link[href=" + path + "]").addClass("active");

    alert($(".page-link[href=" + path + "]").attr("href"))
});

$(document).click(function () {
    $(".edit").show("fast");
    $(".update").hide("fast");
    $(".delete").hide("fast");
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
});
