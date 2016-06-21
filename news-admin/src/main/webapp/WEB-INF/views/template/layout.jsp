<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>News Portal</title>
</head>
<body>
<div class="container" style="border: #C1C1C1 solid 1px; border-radius:10px;">
    <!-- Header -->
    <tiles:insertAttribute name="header"/>
    <!-- Menu Page -->
    <div class="span-5  border" style="height:400px;background-color:#FCFCFC;">
        <tiles:insertAttribute name="menu"/>
    </div>
    <!-- Body Page -->
    <div class="span-19 last">
        <tiles:insertAttribute name="body"/>
    </div>
    <!-- Footer Page -->
    <tiles:insertAttribute name="footer"/>
</div>
</body>
</html>
