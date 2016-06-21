<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>News Management</title>
</head>
<body>
<body>
<div align="center">
    <form:form action="register" method="post" commandName="userForm">
        <form:input path="title"  />
        <form:textarea path="shortText" />

    </form:form>
</div>
</body>
</html>
