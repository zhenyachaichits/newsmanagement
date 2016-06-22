<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        <tiles:insertAttribute name="title"/>
    </title>
    <spring:url value="/resources/css/main.css" var="mainCSS"/>

    <link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oswald:400,300' rel='stylesheet' type='text/css'>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="${mainCSS}" rel="stylesheet">
</head>

<body>

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

<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<spring:url value="/resources/js/main.js" var="mainJS"/>
<script src="${mainJS}" type="text/javascript"></script>


</body>
</html>
