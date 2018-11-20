<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<c:set var="httpMethod"><tiles:getAsString name='formMethod' /></c:set>
<form:form action="${flowExecutionUrl}" modelAttribute="aService" method='${httpMethod}'>
    <form:hidden path="id" />

    <div class="row">
        <div class="col-md-6">
            <h4><spring:message code="form.address" /></h4>
            <fieldset class="form-group">
                <form:label path="cyfeiriadLlinellGyntaf"><spring:message code="form.first.line" /></form:label>
                <form:input path="cyfeiriadLlinellGyntaf" class="form-control"/>
                <form:errors path="cyfeiriadLlinellGyntaf" cssStyle="color: red;"/>
            </fieldset>
            <fieldset class="form-group">
                <form:label path="cyfeiriadAilLinell"><spring:message code="form.second.line" /></form:label>
                <form:input path="cyfeiriadAilLinell" class="form-control"/>
                <form:errors path="cyfeiriadAilLinell" cssStyle="color: red;"/>
            </fieldset>
            <fieldset class="form-group">
                <form:label path="cyfeiriadDinas"><spring:message code="form.city" /></form:label>
                <form:input path="cyfeiriadDinas" class="form-control"/>
                <form:errors path="cyfeiriadDinas" cssStyle="color: red;"/>
            </fieldset>
            <fieldset class="form-group">
                <form:label path="cyfeiriadSir"><spring:message code="form.county" /></form:label>
                <form:input path="cyfeiriadSir" class="form-control"/>
                <form:errors path="cyfeiriadSir" cssStyle="color: red;"/>
            </fieldset>
            <fieldset class="form-group">
                <form:label path="cyfeiriadCodPost"><spring:message code="form.postcode" /></form:label>
                <form:input path="cyfeiriadCodPost" class="form-control"/>
                <form:errors path="cyfeiriadCodPost" cssStyle="color: red;"/>
            </fieldset>
        </div>
            <div class="col-md-6">
                <h4><spring:message code="form.details" /></h4>
                <fieldset class="form-group">
                    <form:label path="rhifFfon"><spring:message code="form.phonenumber" /></form:label>
                    <form:input path="rhifFfon" class="form-control" />
                    <form:errors path="rhifFfon" cssStyle="color: red;"/>
                </fieldset>
                <fieldset class="form-group">
                    <form:label path="ebost"><spring:message code="form.email" /></form:label>
                    <form:input path="ebost" class="form-control" />
                    <form:errors path="ebost" cssStyle="color: red;"/>
                </fieldset>
            </div>
    </div>
    <input style="display: none" id="submit" type="submit" name="_eventId_setServiceAddress" />
</form:form>

<nav style="padding-top: 1em" aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li class="page-item">
      <a class="page-link" href="${flowExecutionUrl}&_eventId=previous">Previous</a>
    </li>
    <li class="page-item">
      <a class="page-link" href="#" onclick="next()">Next</a>
    </li>
  </ul>
</nav>

<script type="text/javascript">
    var next = () => {
        $('#submit').click();
    };
</script>
