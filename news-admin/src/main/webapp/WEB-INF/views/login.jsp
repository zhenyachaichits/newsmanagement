<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div align="center">
    <div class="content-panel login">
        <h1>HELLO!</h1>
        <c:url value="/j_spring_security_check" var="loginUrl"/>
        <form action="${loginUrl}" method="post">
            <div>
                <input name="username" value="${sessionScope.SPRING_SECURITY_LAST_USERNAME}"
                       placeholder="Login" required/>
            </div>
            <div>
                <input type="password" name="password" placeholder="Password" required/>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div>
                <button> SIGN IN</button>
            </div>
        </form>

        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
            <font color="red">
                Your login attempt was not successful.
                <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
            </font>
        </c:if>

    </div>
</div>
