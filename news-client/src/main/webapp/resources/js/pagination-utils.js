$(document).ready(function () {
    var currentPage = parseInt($("li > a.active").html());
    var pagesCount = $("li.pagination-box").length;

    $(".pagination-box").slice(currentPage + 5, pagesCount).remove();
    $(".pagination-box").slice(0, currentPage - 1 - 5).remove();
});

function submitNewPage(page) {
    $("input[name='page']").val(page);
    searchForm.submit();
}