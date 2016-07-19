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
                    <dl class="dropdown tagIdList">
                        <dt>
                            <a href="javascript:void(0)" name="tagIdList">
                                <span class="hidden-title tagIdList">Select Tags</span>
                                <p class="multiSel tagIdList"></p>
                            </a>
                        </dt>

                        <dd>
                            <div class="multiSelect">
                                <ul class="tagIdList">
                                    <form:checkboxes path="tagIdList" element="li" itemValue="tagId" itemLabel="tagName"
                                                     items="${tags}"/>
                                </ul>
                            </div>
                        </dd>
                    </dl>
                </div>

                <div class="select-holder author">
                    <dl class="dropdown authorIdList">
                        <dt>
                            <a href="javascript:void(0)" name="authorIdList">
                                <span class="hidden-title authorIdList">Select Authors</span>
                                <p class="multiSel authorIdList"></p>
                            </a>
                        </dt>

                        <dd>
                            <div class="multiSelect">
                                <ul class="authorIdList">
                                    <form:checkboxes path="authorIdList" element="li" itemValue="authorId"
                                                     itemLabel="authorName"
                                                     items="${authors}"/>
                                </ul>
                            </div>
                        </dd>
                    </dl>
                </div>

            </div>

            <div align="holder">
                <form:button formaction="/newsManagement/save" class="right"> SAVE </form:button>
            </div>

        </form:form>
    </div>
</div>
