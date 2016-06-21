<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div align="center">
    <c:forEach items="${newsData}" var="newsEntry">
        <p><a href="/news">${newsEntry.news.title}</a></p>
        <p>${newsEntry.news.shortText}</p>
    </c:forEach>
</div>