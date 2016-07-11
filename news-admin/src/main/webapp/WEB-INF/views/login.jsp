<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div align="center">
    <div class="content-panel">

        <c:url value="/j_spring_security_check" var="loginUrl"/>
        <form action="${loginUrl}" method="post">
            <input name="username" value="${SPRING_SECURITY_LAST_USERNAME}" placeholder="Login"/>
            <input type="password" name="password" placeholder="Password"/>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button> SIGN IN</button>
        </form>
    </div>
</div>
