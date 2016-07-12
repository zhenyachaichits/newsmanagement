<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div>
    <div class="delete-box" align="center">
        <div align="center"><b class="items-count">0</b> items selected</div>
        <form name="deleteNews" action="newsManagement/deleteNews.do" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <button class="delete-news"><i class="material-icons">delete_sweep</i></button>
    </div>

    <c:forEach items="${newsData}" var="newsEntry">
        <div class="content-panel news-content-box" id="${newsEntry.news.newsId}">

            <div class="holder">

            </div>

            <div class="holder">
                <div class="news-title">
                    <a href="/news/${newsEntry.news.newsId}" class="title">${newsEntry.news.title}</a>
                    <a href="/newsManagement/${newsEntry.news.newsId}" class="option">
                        <i class="material-icons">edit</i>
                    </a>
                </div>

                <fmt:formatDate pattern="yyyy-MM-dd" value="${newsEntry.news.creationDate}" var="creationDate"/>
                <div class="news-date">${creationDate} </div>
            </div>


            <c:if test="${not empty(newsEntry.authors)}">
                <div class="holder">
                    <div class="news-author">(by
                        <c:forEach items="${newsEntry.authors}" var="author">
                            ${author.authorName},
                        </c:forEach>
                        )
                    </div>
                </div>
            </c:if>

            <div class="news-content">${newsEntry.news.shortText}</div>

            <div class="holder">
                <div class="news-tags">
                    <c:if test="${not empty(newsEntry.tags)}">
                        <c:forEach items="${newsEntry.tags}" var="tag">
                            #${tag.tagName}
                        </c:forEach>
                    </c:if>
                </div>

                <div class="news-comment-count">
                    Comments(${fn:length(newsEntry.comments)})
                </div>
            </div>
        </div>
    </c:forEach>
</div>