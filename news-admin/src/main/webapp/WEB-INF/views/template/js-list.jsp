<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:url value="/resources/js/main.js" var="mainJS"/>
<spring:url value="http://code.jquery.com/jquery-latest.min.js" var="jQuery"/>
<spring:url value="/resources/js/management-tools.js" var="managementTools"/>
<spring:url value="/resources/js/multiple-delete.js" var="multipleDelete"/>
<spring:url value="/resources/js/pagination-utils.js" var="paginationUtils"/>
<spring:url value="/resources/js/multiple-dropdown.js" var="multipleDropdown"/>

<script src="${jQuery}" type="text/javascript"></script>
<script src="${mainJS}" type="text/javascript"></script>
<script src="${managementTools}" type="text/javascript"></script>
<script src="${multipleDelete}" type="text/javascript"></script>
<script src="${paginationUtils}" type="text/javascript"></script>
<script src="${multipleDropdown}" type="text/javascript"></script>