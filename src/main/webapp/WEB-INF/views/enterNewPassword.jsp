<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<div class="row">
    <div class="jumbotron">
        <h2><spring:message code="registration.reset.password" /></h2>
        <p><spring:message code="registration.reset.provide.password" /></p>

        <c:if test="${not empty registrationException}">
            <c:choose>
                <c:when test="${registrationException == 'INVALID_USERNAME_EMAIL'}">
                    <div class="alert alert-danger" role="alert"><spring:message code="registration.email.not.found" /></div>
                </c:when>
                <c:when test="${registrationException == 'UNMATCHED_PASSWORDS'}">
                    <div class="alert alert-danger" role="alert"><spring:message code="registration.reset.password.not.matching" /></div>
                </c:when>
                <c:when test="${registrationException == 'PASSWORD_TOKEN_EXPIRED'}">
                    <div class="alert alert-danger" role="alert"><spring:message code="registration.reset.password.token.expired" /></div>
                </c:when>
            </c:choose>

        </c:if>
        <form:form action="${pageContext.request.contextPath}/login/reset/token/${token}" modelAttribute="registrationDetails" method="POST" class="form-signin">
                <fieldset class="form-group">
                    <form:label path="username"><spring:message code="login.email.address" /></form:label>
                    <form:input path="username" class="form-control" />
                </fieldset>
                <fieldset class="form-group">
                    <form:label path="password"><spring:message code="login.password" /></form:label>
                    <form:input path="password" type="password" class="form-control" />
                </fieldset>
                <fieldset class="form-group">
                    <form:label path="passwordSecondTimeEntered"><spring:message code="login.password.reenter" /></form:label>
                    <form:input path="passwordSecondTimeEntered" type="password" class="form-control" />
                </fieldset>

                <fieldset class="form-group">
                    <input type="submit" value="<spring:message code='registration.reset.password' />" class="btn btn-primary" />
                </fieldset>
        </form:form>
    </div>
</div>