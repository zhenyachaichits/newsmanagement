<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div align="center">
    <c:forEach items="${newsData}" var="newsEntry">
        <div class="content-panel">
            <p><a href="/newsManagement/${newsEntry.news.newsId}" class="title">${newsEntry.news.title}</a>
                <c:if test="${not empty(newsEntry.authors)}">
                    <i>(by
                        <c:forEach items="${newsEntry.authors}" var="author">
                            ${author.authorName},
                        </c:forEach>
                        )</i>
                </c:if>
            </p>
            <p>${newsEntry.news.shortText}</p>
        </div>
    </c:forEach>
</div>