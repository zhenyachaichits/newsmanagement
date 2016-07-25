<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div align="center">
    <form:form modelAttribute="user" action="/user" method="post" enctype="utf8">
        <form:input path="userName"/>
        <form:input path="login"/>
        <form:password path="password"/>
        <form:button name="submit"/>
    </form:form>
</div>
</body>
</html>
