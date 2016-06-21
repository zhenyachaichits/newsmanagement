<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div align="center">
    <table>
        <tr>
            <th>Authors</th>
        </tr>
        <c:forEach items="${authors}" var="author">
            <fmt:formatDate pattern="yyyy-MM-dd" value="${author.expiredDate}" var="date"/>
            <tr>
                <td>
                    <form:form modelAttribute="authorData" method="post" enctype="utf8">
                        <form:input path="authorName" value="${author.authorName}" placeholder="Author name" />
                        <form:input path="expiredDate" type="date" value="${date}" placeholder="Expiration date" />
                        <form:input path="authorId" name="id" value="${author.authorId}" type="hidden"/>
                        <form:button formaction="authors/update"> Update </form:button>
                        <form:button formaction="authors/delete"> Delete </form:button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div align="center">
    <form:form modelAttribute="authorData" action="/authors/add" method="post" enctype="utf8">
        <form:input path="authorName"  placeholder="Author name" />
        <form:input path="expiredDate" type="date" placeholder="Expiration date" />
        <form:button> Add </form:button>
    </form:form>
</div>