<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url value="/resources/js/main.js" var="mainJS"/>
<c:url value="http://code.jquery.com/jquery-latest.min.js" var="jQuery"/>
<c:url value="/resources/js/pagination-utils.js" var="paginationUtils"/>
<c:url value="/resources/js/multiple-dropdown.js" var="multipleDropdown"/>

<script src="${jQuery}" type="text/javascript"></script>
<script src="${mainJS}" type="text/javascript"></script>
<script src="${paginationUtils}" type="text/javascript"></script>
<script src="${multipleDropdown}" type="text/javascript"></script>