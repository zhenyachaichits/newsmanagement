
$(document).click(function () {
    $(".edit").show("fast");
    $(".update").hide("fast");
    $(".delete").hide("fast");
    $("form[action='tags/save']").children("input").prop('readonly', true);
    $("form[action='authors/save']").children("input").prop('readonly', true);
});

$(".edit").click(function (e) {
    e.stopPropagation();
    return false;
});

$("input").click(function (e) {
    e.stopPropagation();
    return false;
});

$(".editor").click(function () {
    $(this).hide("fast");
    var nameValue = $(this).attr("name");
    $(".update[name=" + nameValue + "]").show("fast");
    $(".delete[name=" + nameValue + "]").show("fast");
    $("form[name=" + nameValue + "]").children().prop('readonly', false);
});
