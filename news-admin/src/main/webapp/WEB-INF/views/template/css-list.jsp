<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:url value="/resources/css/main.css" var="mainCSS"/>
<spring:url value="https://fonts.googleapis.com/css?family=Oswald:400,300" var="mainFont"/>
<spring:url value="https://fonts.googleapis.com/icon?family=Material+Icons" var="icons"/>

<link href="${mainFont}" rel="stylesheet" type="text/css">
<link href="${icons}" rel="stylesheet">
<link href="${mainCSS}" rel="stylesheet">