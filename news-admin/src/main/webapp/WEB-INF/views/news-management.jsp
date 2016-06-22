<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div align="center">
    <form:form action="/news/management/add" modelAttribute="newsData" method="post" enctype="utf8">
        <form:input path="news.title" placeholder="Title" value="${newsData.news.title}"/>
        <form:textarea path="news.shortText" placeholder="Description" value="${newsData.news.shortText}"/>
        <form:textarea path="news.fullText" placeholder="Full Text" value="${newsData.news.fullText}"/>

        <c:if test="${newsData.news.title eq null}">
            <form:select path="tagIdList" multiple="true">
                <form:option value="-" label="Please Select" disabled="true"/>
                <form:options items="${tags}" itemValue="tagId" itemLabel="tagName"/>
            </form:select>

            <form:select path="authorIdList" multiple="true">
                <form:option value="-" label="Please Select" disabled="true"/>
                <form:options items="${authors}" itemValue="authorId" itemLabel="authorName"/>
            </form:select>
        </c:if>
        <form:button> SAVE </form:button>
    </form:form>
</div>