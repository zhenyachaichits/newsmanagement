<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url value="/resources/css/main.css" var="mainCSS"/>
<c:url value="https://fonts.googleapis.com/css?family=Oswald:400,300" var="mainFont"/>
<c:url value="https://fonts.googleapis.com/icon?family=Material+Icons" var="icons"/>
<c:url value="/resources/css/dropdown.css" var="dropdownCss"/>

<c:url value="/resources/js/main.js" var="mainJS"/>
<c:url value="http://code.jquery.com/jquery-latest.min.js" var="jQuery"/>
<c:url value="/resources/js/pagination-utils.js" var="paginationUtils"/>
<c:url value="/resources/js/multiple-dropdown.js" var="multipleDropdown"/>

<html>
<head>
    <title>
        News
    </title>

    <link href="${mainFont}" rel="stylesheet" type="text/css">
    <link href="${icons}" rel="stylesheet">
    <link href="${mainCSS}" rel="stylesheet">
    <link href="${dropdownCss}" rel="stylesheet">

</head>

<body>


<header class="sticky">
    <form action="/news" name="toMain">
        <input type="hidden" name="command" value="getNewsCommand">
        <input type="hidden" name="page" value="1">
    </form>

    <div>
        <a href="javascript:toMain.submit()" class="logo">
            <i class="material-icons">import_contacts</i>
            NEWS PORTAL</a>
    </div>
</header>

<div align="center">
    <div class="content-panel login">
        <h1>CHOOSE YOUR ROLE</h1>

        <c:url value="/news" var="adminUrl"/>
        <form action="${adminUrl}" method="post">
            <input type="hidden" name="command" value="getNewsCommand">
            <button> USER</button>
        </form>


        <c:url value="/admin" var="adminUrl"/>
        <form action="${adminUrl}" method="post">
            <button class="edit"> ADMIN</button>
        </form>
    </div>
</div>

<footer>
    <p>2016 EPAM Systems, Inc. All Rights Reserved.</p>
</footer>

<script src="${jQuery}" type="text/javascript"></script>
<script src="${mainJS}" type="text/javascript"></script>
<script src="${paginationUtils}" type="text/javascript"></script>
<script src="${multipleDropdown}" type="text/javascript"></script>

</body>
</html>