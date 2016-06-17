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
        <table border="0">
            <tr>
                <td colspan="2" align="center"><h2>Spring MVC Form Demo - Registration</h2></td>
            </tr>
            <tr>
                <td>User Name:</td>
                <td><form:input path="username" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:password path="password" /></td>
            </tr>
            <tr>
                <td>E-mail:</td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td>Birthday (mm/dd/yyyy):</td>
                <td><form:input path="birthDate" /></td>
            </tr>
            <tr>
                <td>Profession:</td>
                <td><form:select path="profession" items="${professionList}" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Register" /></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
