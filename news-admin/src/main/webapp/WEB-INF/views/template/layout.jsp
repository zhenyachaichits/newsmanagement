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

<body>

<spring:htmlEscape defaultHtmlEscape="true" />

<header class="sticky">
    <tiles:insertAttribute name="header"/>
</header>

<menu>
    <tiles:insertAttribute name="menu"/>
</menu>

<main>
    <tiles:insertAttribute name="body"/>
</main>

<footer>
    <tiles:insertAttribute name="footer"/>
</footer>

<tiles:insertAttribute name="jsList"/>

</body>
</html>
