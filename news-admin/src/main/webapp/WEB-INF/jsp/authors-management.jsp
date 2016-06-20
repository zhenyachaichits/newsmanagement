<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Authors Management</title>
</head>
<body>
<div align="center">
    <table>
        <tr>
            <th>Authors</th>
        </tr>
        <c:forEach items="${authors}" var="author">
            <tr>
                <td>
                    <form:form modelAttribute="authorData" method="post" enctype="utf8">
                        <form:input path="authorName" value="${author.authorName}"/>


                        <fmt:formatDate pattern="dd.MM.yyyy"
                                        value="${author.expiredDate}"/>
                        <form:input path="expiredDate" value="${author.expiredDate}"/>
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
        <form:input path="authorName"/>
        <form:input path="expiredDate" type="date"/>
        <form:button> Add </form:button>
    </form:form>
</div>
</body>
</html>
