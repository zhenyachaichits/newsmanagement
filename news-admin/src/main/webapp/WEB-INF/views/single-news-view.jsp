<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${newsData.previousNewsId ne 0}">
    <a href="/news/${newsData.previousNewsId}" class="navigate previous">
        <i class="material-icons">navigate_before</i>
    </a>
</c:if>

<c:if test="${newsData.nextNewsId ne 0}">
    <a href="/news/${newsData.nextNewsId}" class="navigate next">
        <i class="material-icons">navigate_next</i>
    </a>
</c:if>

<div class="content-panel news-holder">

    <div class="holder">
        <div class="news-title">
            <a href="/news/${newsData.news.newsId}" class="title">${newsData.news.title}</a>
        </div>

        <fmt:formatDate pattern="yyyy-MM-dd" value="${newsData.news.creationDate}" var="creationDate"/>
        <div class="news-date">${creationDate}</div>
    </div>

    <c:if test="${not empty(newsData.authors)}">
        <div class="holder">
            <div class="news-author">(by
                <c:forEach items="${newsData.authors}" var="author">
                    ${author.authorName},
                </c:forEach>
                )
            </div>
        </div>
    </c:if>

    <div class="holder">
        <div class="news-content">
            ${newsData.news.fullText}
        </div>
    </div>

    <div class="news-comments-title">
        Comments(${fn:length(newsData.comments)})
        <c:if test="${fn:length(newsData.comments) > 0}">
            <button class="edit show-comments small"><i class="material-icons">expand_less</i></button>
        </c:if>
    </div>

    <c:forEach items="${newsData.comments}" var="comment">
        <div class="holder comment-holder">

            <div class="news-comment-content">
                    ${comment.commentText}
                        <form name="delete${comment.commentId}" action="/comment/deleteComment.do" method="post" hidden>
                            <input type="hidden" name="id" value="${comment.commentId}"/>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                        <a href="javascript:delete${comment.commentId}.submit()" class="option delete-icon">
                            <i class="material-icons">delete</i>
                        </a>
            </div>

            <fmt:formatDate pattern="yyyy-MM-dd 'at' hh:mm a" value="${comment.creationDate}"
                            var="commentCreationDate"/>
            <div class="news-comment-date">${commentCreationDate}</div>
        </div>
    </c:forEach>

    <form:form cssClass="holder comment-holder" modelAttribute="commentData" action="/comment/addComment.do" method="post" enctype="utf8">
        <form:textarea path="commentText" cssClass="comment-text"/>
        <form:hidden path="newsId" value="${newsData.news.newsId}"/>
        <form:button class="medium add-comment"> POST </form:button>
    </form:form>
</div>