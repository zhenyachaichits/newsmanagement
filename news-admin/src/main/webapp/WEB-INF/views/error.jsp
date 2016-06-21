<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<div align="center">
    <h1>We're sorry :(</h1>

    <c:if test="${empty message}">
        <h2>Some system errors were found...</h2>
    </c:if>

    <c:if test="${not empty message}">
        <h2>${message}</h2>
    </c:if>
</div>
</body>
</html>
