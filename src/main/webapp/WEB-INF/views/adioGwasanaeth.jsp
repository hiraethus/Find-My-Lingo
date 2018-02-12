<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<c:set var="httpMethod"><tiles:getAsString name='formMethod' /></c:set>
<form:form action="${pageContext.request.contextPath}/" modelAttribute="gwasanaeth" method='${httpMethod}'>
    <form:hidden path="id" />

    <div class="row">
        <div class="col-md-6">
            <h4><spring:message code="form.details" /></h4>
            <fieldset class="form-group">
                <form:label path="enw"><spring:message code="form.service.name" /></form:label>
                <form:input path="enw" htmlEscape="false" class="form-control" />
                <form:errors path="enw" cssStyle="color: red;"/>
            </fieldset>
            <fieldset class="form-group">
                <form:label path="categori"><spring:message code="form.category" /></form:label>
                <form:select path="categori" cssClass="form-control">
                    <c:forEach var="c" items="${categoris}">
                    <c:choose>
                        <c:when test="${gwasanaeth.categori.id == c.id}">
                            <form:option value="${c.id}" label="${c.categori}" selected="selected" />
                        </c:when>
                        <c:otherwise>
                            <form:option value="${c.id}" label="${c.categori}" />
                        </c:otherwise>
                    </c:choose>
                    </c:forEach>

                </form:select>
            </fieldset>
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
    </div>

    <div class="row">
        <fieldset class="form-group">
            <form:label path="disgrifiad"><spring:message code="form.description" /></form:label>
            <form:textarea path="disgrifiad" rows="5" cols="30" class="form-control" />
            <form:errors path="disgrifiad" cssStyle="color: red;"/>
        </fieldset>
    </div>

    <div class="row">
        <fieldset class="form-group">
            <input type="submit" value='<spring:message code="form.submit" />' class="btn btn-primary" />
        </fieldset>
    </div>
</form:form>
