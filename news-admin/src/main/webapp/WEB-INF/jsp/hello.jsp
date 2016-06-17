<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<table>
    <tr>
        <th>Title</th>
    </tr>
    <c:forEach items="${news}" var="newsEntry">
        <tr>
            <td>${newsEntry.title}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
