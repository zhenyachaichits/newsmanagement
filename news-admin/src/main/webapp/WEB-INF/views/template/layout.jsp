<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        <tiles:insertAttribute name="title"/>
    </title>
    <link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oswald:400,300' rel='stylesheet' type='text/css'>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="css/main.css">
</head>

<body>
<header class="sticky">
    <tiles:insertAttribute name="header"/>
</header>
<tiles:insertAttribute name="menu"/>
<tiles:insertAttribute name="body"/>
<tiles:insertAttribute name="footer"/>

<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<script>
    $(window).scroll(function () {
        var sticky = $('.sticky'), scroll = $(window).scrollTop();
        if (scroll >= 80) sticky.addClass('fixed');
        else sticky.removeClass('fixed');
    });
</script>
</body>
</html>
