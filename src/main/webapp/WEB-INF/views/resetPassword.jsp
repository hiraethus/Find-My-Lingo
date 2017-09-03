<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<div class="row">
    <div class="jumbotron">
        <h2><spring:message code="registration.reset.password" /></h2>
        <p><spring:message code="registration.reset.provide.password" /></p>
        <c:if test="${not empty emailNotFoundError}">
            <div class="alert alert-danger" role="alert">${emailNotFoundError}</div>
        </c:if>
        <form:form action="${pageContext.request.contextPath}/mewngofnodi/ailosod" commandName="registrationDetails" method="POST" class="form-signin">
            <fieldset class="form-group">
                <form:label path="username"><spring:message code="login.email.address" /></form:label>
                <form:input path="username" class="form-control" />
            </fieldset>
            <input class="btn btn-primary" type="submit" value="
                <spring:message code='registration.reset.send.email' />
            "></input>
        </form:form>
    </div>
</div>