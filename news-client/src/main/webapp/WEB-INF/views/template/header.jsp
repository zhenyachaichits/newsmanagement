<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form action="/news" name="toMain">
    <input type="hidden" name="command" value="getNewsCommand">
    <input type="hidden" name="page" value="1">
</form>

<div>
    <a href="javascript:toMain.submit()" class="logo">
        <i class="material-icons">import_contacts</i>
        NEWS PORTAL</a>
</div>