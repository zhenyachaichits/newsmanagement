<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div align="center">
    <table>
        <tr>
            <th>Tags</th>
        </tr>
        <c:forEach items="${tags}" var="author">
            <tr>
                <td>
                    <form:form modelAttribute="tagData" method="post" enctype="utf8">
                        <form:input path="tagName" value="${author.tagName}" />
                        <form:input path="tagId" value="${author.tagId}" type="hidden" />
                        <form:button formaction="tags/update" class="small"> UPDATE </form:button>
                        <form:button formaction="tags/delete" class="small delete"> DELETE </form:button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div align="center">
    <form:form modelAttribute="tagData" action="/tags/add" method="post" enctype="utf8">
        <form:input path="tagName"/>
        <form:button class="small"> ADD </form:button>
    </form:form>
</div>