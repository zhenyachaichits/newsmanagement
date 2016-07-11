<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div>
    <a href="/news" class="logo"><i class="material-icons">import_contacts</i>NEWS PORTAL</a>


    <security:authorize access="isAuthenticated()">
        <c:url var="logoutUrl" value="/j_spring_security_logout"/>
        <div class="logout-holder">
            <form action="${logoutUrl}" method="post">
                <button class="button small logout">LOGOUT</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
        <div class="username-holder">
                ${fn:toUpperCase(pageContext.request.userPrincipal.principal.fullName)}
        </div>
    </security:authorize>

</div>