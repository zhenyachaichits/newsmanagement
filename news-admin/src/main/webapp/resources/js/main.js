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


$(document).ready(function () {
    var path = window.location.pathname;
    $(".page-link[href='" + path + "']").addClass("active");
    var newText = $('.news-author').html().replace(/\s+/g, " ").replace(", )", ")");
    $('.news-author').html(newText);
});

