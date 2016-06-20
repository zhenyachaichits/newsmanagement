<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Tags Management</title>
</head>
<body>
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
                        <form:button formaction="tags/update" > Update </form:button>
                        <form:button formaction="tags/delete"> Delete </form:button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div align="center">
    <form:form modelAttribute="tagData" action="/tags/add" method="post" enctype="utf8">
        <form:input path="tagName"/>
        <form:button> Add </form:button>
    </form:form>
</div>
</body>
</html>
