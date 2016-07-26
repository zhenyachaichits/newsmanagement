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

    <div class="search-panel center">

        <form:form modelAttribute="searchCriteria" action="/news" method="get" name="searchForm"
                   enctype="utf8">
            <div class="holder">
                <div class="criteria-holder">
                    <div class="select-holder tag search">
                        <dl class="dropdown tagIdSet">
                            <dt>
                                <a href="javascript:void(0)" name="tagIdSet">
                                    <span class="hidden-title tagIdSet">Select Tags</span>
                                    <p class="multiSel tagIdSet"></p>
                                </a>
                            </dt>

                            <dd>
                                <div class="multiSelect">
                                    <ul class="tagIdSet">
                                        <form:checkboxes path="tagIdSet" element="li" itemValue="tagId"
                                                         itemLabel="tagName"
                                                         items="${tags}"/>
                                    </ul>
                                </div>
                            </dd>
                        </dl>
                    </div>

                    <div class="select-holder author search">
                        <dl class="dropdown authorIdSet">
                            <dt>
                                <a href="javascript:void(0)" name="authorIdSet">
                                    <span class="hidden-title authorIdSet">Select Authors</span>
                                    <p class="multiSel authorIdSet"></p>
                                </a>
                            </dt>

                            <dd>
                                <div class="multiSelect">
                                    <ul class="authorIdSet">
                                        <form:checkboxes path="authorIdSet" element="li" itemValue="authorId"
                                                         itemLabel="authorName"
                                                         items="${authors}"/>
                                    </ul>
                                </div>
                            </dd>
                        </dl>
                    </div>
                </div>


                <input type="hidden" name="page" value=""/>
                <form:button class="right"> SEARCH </form:button>
                <form:button class="right edit reset" type="reset"> RESET </form:button>
            </div>
        </form:form>

    </div>

    <c:forEach items="${newsData}" var="newsEntry">
        <div class="content-panel news-content-box" id="${newsEntry.news.newsId}">

            <div class="holder">

            </div>

            <div class="holder">
                <div class="news-title">
                    <c:url value="/news/${newsEntry.news.newsId}" var="viewNews"/>
                    <a href="${viewNews}" class="title">${newsEntry.news.title}</a>
                    <c:url value="/newsManagement/${newsEntry.news.newsId}" var="editNews"/>
                    <a href="${editNews}" class="option">
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


    <div class="pagination-holder">
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li><a href="javascript:submitNewPage(${currentPage - 1})">
                    <i class="material-icons">navigate_before</i>
                </a></li>
            </c:if>

            <c:forEach begin="1" end="${pagesCount}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="pagination-box"><a class="active" href="#">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="pagination-box"><a href="javascript:submitNewPage(${i})">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt pagesCount}">
                <li><a href="javascript:submitNewPage(${currentPage + 1})"><i
                        class="material-icons">navigate_next</i></a></li>
            </c:if>
        </ul>
    </div>

</div>