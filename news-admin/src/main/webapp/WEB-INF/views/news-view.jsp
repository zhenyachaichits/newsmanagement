<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div>
    <c:forEach items="${newsData}" var="newsEntry">
        <div class="content-panel">
            <div class="holder">
                <div class="news-title">
                    <a href="/newsManagement/${newsEntry.news.newsId}" class="title">${newsEntry.news.title}</a>
                </div>
                <fmt:formatDate pattern="yyyy-MM-dd" value="${newsEntry.news.creationDate}" var="creationDate"/>
                <div class="news-date">${creationDate}</div>
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


            <p>${newsEntry.news.shortText}</p>
            <p>
                <c:if test="${not empty(newsEntry.tags)}">
                    <c:forEach items="${newsEntry.tags}" var="tag">
                        #${tag.tagName}
                    </c:forEach>
                </c:if>
            </p>
        </div>
    </c:forEach>
</div>