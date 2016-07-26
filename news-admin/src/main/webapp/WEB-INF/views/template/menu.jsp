<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="section">
    <c:url value="/news" var="newsLink"/>
    <a href="${newsLink}" class="page-link"> <i class="material-icons">view_headline</i> NEWS LIST </a>
</div>

<div class="section">
    <c:url value="/newsManagement" var="managementLink"/>
    <a href="${managementLink}" class="page-link"> <i class="material-icons">add</i> ADD NEWS </a>
</div>

<div class="section">
    <c:url value="/authors" var="authorsLink"/>
    <a href="${authorsLink}" class="page-link"> <i class="material-icons">perm_identity</i> AUTHORS </a>
</div>

<div class="section">
    <c:url value="/tags" var="tagsLink"/>
    <a href="${tagsLink}" class="page-link"> <i class="material-icons">grade</i> TAGS </a>
</div>