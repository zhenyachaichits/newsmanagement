<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div align="center">

    <div class="add-panel" align="center">
        <form:form modelAttribute="tagData" action="/tags/add" method="post" enctype="utf8">
            <form:input path="tagName" placeholder="Tag name"/>
            <form:button> ADD </form:button>
        </form:form>
    </div>

    <div class="content-panel" align="center">
        <h1 class="title">ALL TAGS</h1>
        <c:forEach items="${tags}" var="author" varStatus="loop">
            <form:form modelAttribute="tagData" action="tags/update" method="post" enctype="utf8" name="${loop.index}">
                <form:input path="tagName" value="${author.tagName}" cssClass="medium" readonly="true"/>
                <form:hidden path="tagId" value="${author.tagId}" cssClass="medium"/>
                <form:button type="button" class="medium edit" name="${loop.index}"> EDIT </form:button>
                <form:button class="medium update" name="${loop.index}" hidden="true">
                    UPDATE
                </form:button>
                <form:button formaction="tags/delete" class="medium delete" name="${loop.index}" hidden="true">
                    DELETE
                </form:button>
            </form:form>
        </c:forEach>
    </div>
</div>
