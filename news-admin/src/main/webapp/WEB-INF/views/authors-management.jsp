<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div align="center">

    <div class="add-panel" align="center">
        <form:form modelAttribute="authorData" action="/authors/save" method="post" enctype="utf8">
            <form:input path="authorName" placeholder="Author name"/>
            <form:input path="expiredDate" type="date" placeholder="Expiration date"/>
            <form:button> ADD </form:button>
        </form:form>
    </div>

    <div class="content-panel" align="center">
        <h1 class="title">ALL AUTHORS</h1>
        <c:forEach items="${authors}" var="author" varStatus="loop">
            <fmt:formatDate pattern="yyyy-MM-dd" value="${author.expiredDate}" var="date"/>

            <form:form modelAttribute="authorData" action="authors/save.do" method="post" enctype="utf8" name="${loop.index}">
                <form:input path="authorName" value="${author.authorName}" cssClass="medium"
                            placeholder="Author name" readonly="true"/>
                <form:input path="expiredDate" type="date" value="${date}" cssClass="medium"
                            placeholder="Expiration date" readonly="true"/>
                <form:hidden path="authorId" value="${author.authorId}" />
                <form:button type="button" class="medium edit editor" name="${loop.index}"> EDIT </form:button>
                <form:button class="medium update" name="${loop.index}" hidden="true">
                    UPDATE
                </form:button>
                <form:button formaction="authors/expire.do" class="medium delete" name="${loop.index}" hidden="true">
                    EXPIRE
                </form:button>
            </form:form>
        </c:forEach>
    </div>

    <spring:url value="/resources/js/management-tools.js" var="managementJS"/>
    <script src="${managementJS}" type="text/javascript"></script>
</div>
