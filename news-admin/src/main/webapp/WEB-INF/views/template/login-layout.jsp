<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        <tiles:insertAttribute name="title"/>
    </title>

    <tiles:insertAttribute name="cssList"/>

</head>

<body class="login-page">

<header class="sticky">
    <tiles:insertAttribute name="header"/>
</header>

<tiles:insertAttribute name="body"/>

<footer>
    <tiles:insertAttribute name="footer"/>
</footer>

<tiles:insertAttribute name="jsList"/>

</body>
</html>
