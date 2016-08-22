$(".dropdown dt a").on('click', function () {
    var name = $(this).attr('name');
    $("ul." + name).slideToggle('fast');
});

$(".dropdown dd ul li a").on('click', function () {
    $(".dropdown dd ul").hide('fast');
});

function getSelectedValue(id) {
    return $("#" + id).find("dt a span.value").html();
}

$(document).bind('click', function (e) {
    var $clicked = $(e.target);
    if (!$clicked.parents().hasClass("dropdown")) $(".dropdown dd ul").hide();
});

$(document).ready(function() {
    var selected = $("input:checked");
    for (var i = 0; i < selected.length; i++) {
        var name = $(selected[i]).attr('name');
        var id = $(selected[i]).attr('id');
        var title = $("label[for=" + id +"]").html() + ",";
        var html = '<span title="' + title + '">' + title + '</span>';

        $('p.' + name).append(html);
        $("span." + name).hide();
    }
});

$('.multiSelect input[type="checkbox"]').on('click', function () {

    var id = $(this).attr('id');
    var name = $(this).attr('name');
    var title = $("label[for=" + id +"]").html() + ",";

    if ($(this).is(':checked')) {
        var html = '<span title="' + title + '">' + title + '</span>';
        $('p.' + name).append(html);
        $("span." + name).hide();
    } else {
        $('span[title="' + title + '"]').remove();
        var ret = $(".hidden-title." + name);
        if ($("input[name='" + name + "']:checked").length === 0) {
            $(".hidden-title." + name).show();
        }
        $('a[name=' + name + ']').append(ret);
    }
});