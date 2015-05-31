<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>
<body>
<div class="container">
    <spring:url value="/resources/img/bugfix.jpg" var="errorImage"/>
    <img src="${errorImage}"/>

    <h2>Oops! Something went wrong...</h2>

    <p>${exception.message}</p>
</div>
</body>
</html>
