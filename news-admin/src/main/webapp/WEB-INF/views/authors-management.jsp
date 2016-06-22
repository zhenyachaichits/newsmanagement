<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div align="center">

    <div class="add-panel" align="center">
        <form:form modelAttribute="authorData" action="/authors/add" method="post" enctype="utf8">
            <form:input path="authorName" placeholder="Author name"/>
            <form:input path="expiredDate" type="date" placeholder="Expiration date"/>
            <form:button> ADD </form:button>
        </form:form>
    </div>

    <div class="content-panel" align="center">
        <h1 class="title">ALL AUTHORS</h1>
        <c:forEach items="${authors}" var="author" varStatus="loop">
            <fmt:formatDate pattern="yyyy-MM-dd" value="${author.expiredDate}" var="date"/>

            <form:form modelAttribute="authorData" method="post" enctype="utf8">
                <form:input path="authorName" value="${author.authorName}" cssClass="medium"
                            placeholder="Author name"/>
                <form:input path="expiredDate" type="date" value="${date}" cssClass="medium"
                            placeholder="Expiration date"/>
                <form:input path="authorId" name="id" value="${author.authorId}" type="hidden"/>
                <form:button type="button" class="medium edit" name="${loop.index}"> EDIT </form:button>
                <form:button formaction="authors/update" class="medium update" name="${loop.index}" hidden="true">
                    UPDATE
                </form:button>
                <form:button formaction="authors/delete" class="medium delete" name="${loop.index}" hidden="true">
                    DELETE
                </form:button>
            </form:form>
        </c:forEach>
    </div>
</div>
