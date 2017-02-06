<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<c:if test="${param.allgofnodi ne null}" >
<div class="row">
    <div class="col-lg-12">
        <div class="alert alert-success" role="alert">

            <p><spring:message code="logout.successful" /></p>
        </div>
    </div>
</div>
</c:if>
<c:if test="${not empty registrationSuccessful}" >
<div class="row">
    <div class="col-lg-12">
        <div class="alert alert-success" role="alert">
            ${registrationSuccessful}
        </div>
    </div>
</div>
</c:if>

<div class="jumbotron">

    <div class="row">
        <div>
            <div class="logo-square">
                <div class="gwasanaethau-text">GWASANAETHAU</div>
            </div>
            <div class="cymraeg-text">
                .CYMRU
            </div>
        </div>
        <div class="col-md-8">
            <h1><spring:message code="service.wales" /></h1>
            <p><spring:message code="welcome.add.business" /></p>
            <a href='<c:url value="/adio" />' class="btn btn-primary btn-lg"><spring:message code="service.add" /></a>
        </div>
    </div>
</div>

<h2><spring:message code="service.list" /></h2>
<tiles:insertAttribute name="rhestrGwasanaethau" />
