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
                <form:errors path="cyfeiriadLlinellGyntaf" element="div" cssClass="alert alert-warning"/>
                <form:input path="cyfeiriadLlinellGyntaf" class="form-control"/>
            </fieldset>
            <fieldset class="form-group">
                <form:label path="cyfeiriadAilLinell"><spring:message code="form.second.line" /></form:label>
                <form:errors path="cyfeiriadAilLinell" element="div" cssClass="alert alert-warning"/>
                <form:input path="cyfeiriadAilLinell" class="form-control"/>
            </fieldset>
            <fieldset class="form-group">
                <form:label path="cyfeiriadDinas"><spring:message code="form.city" /></form:label>
                <form:errors path="cyfeiriadDinas" element="div" cssClass="alert alert-warning"/>
                <form:input path="cyfeiriadDinas" class="form-control"/>
            </fieldset>
            <fieldset class="form-group">
                <form:label path="cyfeiriadSir"><spring:message code="form.county" /></form:label>
                <form:errors path="cyfeiriadSir" element="div" cssClass="alert alert-warning"/>
                <form:input path="cyfeiriadSir" class="form-control"/>
            </fieldset>
            <fieldset class="form-group">
                <form:label path="cyfeiriadCodPost"><spring:message code="form.postcode" /></form:label>
                <form:errors path="cyfeiriadCodPost" element="div" cssClass="alert alert-warning"/>
                <form:input path="cyfeiriadCodPost" class="form-control"/>
            </fieldset>
        </div>
            <div class="col-md-6">
                <h4><spring:message code="form.details" /></h4>
                <fieldset class="form-group">
                    <form:label path="rhifFfon"><spring:message code="form.phonenumber" /></form:label>
                    <form:errors path="rhifFfon" element="div" cssClass="alert alert-warning"/>
                    <form:input path="rhifFfon" class="form-control" />
                </fieldset>
                <fieldset class="form-group">
                    <form:label path="ebost"><spring:message code="form.email" /></form:label>
                    <form:errors path="ebost" element="div" cssClass="alert alert-warning"/>
                    <form:input path="ebost" class="form-control" />
                </fieldset>
            </div>
    </div>
    <input style="display: none" id="submit_next" type="submit" name="_eventId_next" />
    <input style="display: none" id="submit_prev" type="submit" name="_eventId_previous" />
</form:form>

<nav style="padding-top: 1em" aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li class="page-item">
      <a id="prev_pg" class="page-link" href="#">Previous</a>
    </li>
    <li class="page-item">
      <a id="next_pg" class="page-link" href="#">Next</a>
    </li>
  </ul>
</nav>

<script type="text/javascript">
    window.onload = () => {
        document.getElementById("next_pg").onclick = () => {
            document.getElementById("submit_next").click()
        }

        document.getElementById("prev_pg").onclick = () => {
            document.getElementById("submit_prev").click()
        }
    }
</script>
