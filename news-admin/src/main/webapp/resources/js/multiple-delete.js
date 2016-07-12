Array.prototype.remove = function () {
    var what, a = arguments, L = a.length, ax;
    while (L && this.length) {
        what = a[--L];
        while ((ax = this.indexOf(what)) !== -1) {
            this.splice(ax, 1);
        }
    }
    return this;
};


var newsToBeDeleted = [];

$(".news-content-box").click(function () {

    var id = $(this).attr("id");

    if ($(this).hasClass('active')) {
        newsToBeDeleted.remove(id);
    } else {
        newsToBeDeleted.push(id);
    }


    $("input[name='newsIds']").remove();
    $("form[name='deleteNews']").append(createInputsHtml(newsToBeDeleted));
    $(this).toggleClass("active");

    var selectedCount = newsToBeDeleted.length;
    $(".items-count").html(selectedCount);

    if (selectedCount > 0) {
        $(".delete-news").show("fast");
    } else {
        $(".delete-news").hide("fast");
    }
});

$(".delete-news").click(function(){
    deleteNews.submit()
});

function createInputsHtml(array) {
    var html = "";
    array.forEach(function(item) {
        html += "<input type='hidden' name='newsIds' value='" + item + "'>";
    });

    return html;
}
