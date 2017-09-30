<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<div class="container">

    <div class="row">
        <div class="col-md-3">
            <span>
                <div class="logo-square">
                    <div class="gwasanaethau-text">GWASANAETHAU</div>
                </div>
                <div class="cymraeg-text">
                    .CYMRU
                </div>
            </span>
        </div>
        <div class="col-md-9" style="overflow: hidden">
            <h1><spring:message code="service.wales" /></h1>
            <p style="padding-top: 1em;"><spring:message code="welcome.add.business" /></p>
            <form method="GET" class="form-inline">
                <div class="form-group" style="width: 100%">
                    <input name="searchTerm" class="form-control" type="text" style="width: calc(80% - 7em);" required />
                    <button class="btn btn-default" type="submit" style="width: 6.5em">
                        <span class="glyphicon glyphicon-search"></span> <spring:message code='frontpage.search' />
                    </button>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <h2><spring:message code="service.list" /></h2>
        <tiles:insertAttribute name="rhestrGwasanaethau" />
    </div>
</div>

