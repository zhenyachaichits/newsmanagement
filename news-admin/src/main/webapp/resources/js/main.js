$(window).scroll(function () {
    var sticky = $('.sticky'), scroll = $(window).scrollTop();
    if (scroll >= 10) sticky.addClass('fixed');
    else sticky.removeClass('fixed');
});


$(".show-comments").click(function () {
    var showTag = '<i class="material-icons">expand_more</i>';
    var hideTag = '<i class="material-icons">expand_less</i>';

    var commentsBtn = $(this);
    var state = commentsBtn.html();

    switch (state) {
        case showTag:
            commentsBtn.html(hideTag);
            $(".comment-holder").show("fast");
            break;
        case hideTag:
            commentsBtn.html(showTag);
            $(".comment-holder").hide("fast");
            break;
    }
});


$(".reset").click(function () {
    $("input[type='checkbox']").attr('checked', false);
    searchForm.submit();
});

$(document).ready(function () {
    var path = window.location.pathname;

    if (path === "/") {
        path = "/news";
    }

    $(".page-link[href='" + path + "']").addClass("active");

    $('.news-author').each(function () {
        var newText = $(this).html().replace(/\s+/g, " ").replace(", )", ")");
        $(this).html(newText);
    });

    $('.news-content').each(function () {
        var newText = $(this).html().replace(/\n/g, "<br />");
        $(this).html(newText);
    });
});

$(".error").focus(function() {
   $(this).removeClass("error");
});