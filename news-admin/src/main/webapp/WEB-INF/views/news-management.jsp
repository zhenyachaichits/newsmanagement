<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="content-panel">
    <div class="holder">
        <form:form modelAttribute="newsData" method="post" enctype="utf8">
            <form:hidden path="news.newsId" value="${newsData.news.newsId}" cssClass="news-text"/>
            <form:input path="news.title" placeholder="Title" value="${newsData.news.title}" cssClass="news-text"/>
            <form:textarea path="news.shortText" placeholder="Description" value="${newsData.news.shortText}"
                           cssClass="news-text"/>
            <form:textarea path="news.fullText" placeholder="Full Text" value="${newsData.news.fullText}"
                           cssClass="news-text full-text"/>

            <div class="holder">
                <div class="select-holder tag">
                    <dl class="dropdown tagIdSet">
                        <dt>
                            <a href="javascript:void(0)" name="tagIdSet">
                                <span class="hidden-title tagIdSet">Select Tags</span>
                                <p class="multiSel tagIdSet"></p>
                            </a>
                        </dt>

                        <dd>
                            <div class="mutliSelect">
                                <ul class="tagIdSet">
                                    <form:checkboxes path="tagIdList" element="li" itemValue="tagId" itemLabel="tagName"
                                                     items="${tags}"/>
                                </ul>
                            </div>
                        </dd>
                    </dl>
                </div>

                <div class="select-holder author">
                    <dl class="dropdown authorIdSet">
                        <dt>
                            <a href="javascript:void(0)" name="authorIdSet">
                                <span class="hidden-title authorIdSet">Select Authors</span>
                                <p class="multiSel authorIdSet"></p>
                            </a>
                        </dt>

                        <dd>
                            <div class="mutliSelect">
                                <ul class="authorIdSet">
                                    <form:checkboxes path="authorIdList" element="li" itemValue="authorId"
                                                     itemLabel="authorName"
                                                     items="${authors}"/>
                                </ul>
                            </div>
                        </dd>
                    </dl>
                </div>

            </div>

            <%--<form:select path="tagIdList" modelAttribute="tagIdList" multiple="true">--%>
            <%--<form:option value="-" label="Please Select" disabled="true"/>--%>
            <%--<form:options items="${tags}" itemValue="tagId" itemLabel="tagName"/>--%>
            <%--</form:select>--%>

            <%--<form:select path="authorIdList" modelAttribute="authorIdList" multiple="true"--%>
            <%--cssClass="select-style">--%>
            <%--<form:option value="-" label="Please Select" disabled="true"/>--%>
            <%--<form:options items="${authors}" itemValue="authorId" itemLabel="authorName"/>--%>
            <%--</form:select>--%>

            <form:button formaction="/newsManagement/save"> SAVE </form:button>
        </form:form>
    </div>
</div>
