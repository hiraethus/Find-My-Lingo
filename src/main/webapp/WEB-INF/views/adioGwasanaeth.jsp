<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>


<form:form action="${pageContext.request.contextPath}/gwasanaethau/" commandName="gwasanaeth" method="POST">
    <fieldset class="form-group">
        <form:label path="enw">Enw'r Wasanaeth</form:label>
        <form:input path="enw" class="form-control" />
        <form:errors path="enw" cssStyle="color: red;"/>
    </fieldset>
    <fieldset class="form-group">
        <form:label path="categori">Categori</form:label>
        <form:select path="categori" class="form-control">
            <form:options items="${categoris}" itemLabel="categori" itemValue="id" />
        </form:select>
    </fieldset>
    <fieldset class="form-group">
        <form:label path="rhifFfon">Rhif Ffon</form:label>
        <form:input path="rhifFfon" class="form-control" />
        <form:errors path="rhifFfon" cssStyle="color: red;"/>
    </fieldset>
    <fieldset class="form-group">
        <form:label path="ebost">Ebost</form:label>
        <form:input path="ebost" class="form-control" />
        <form:errors path="ebost" cssStyle="color: red;"/>
    </fieldset>
    <fieldset class="form-group">
        <form:label path="disgrifiad">Disgrifiad</form:label>
        <form:textarea path="disgrifiad" rows="5" cols="30" class="form-control" />
        <form:errors path="disgrifiad" cssStyle="color: red;"/>
    </fieldset>

    <h4>Cyfeiriad</h4>
    <fieldset class="form-group">
        <form:label path="cyfeiriadLlinellGyntaf">Llinell Gyntaf</form:label>
        <form:input path="cyfeiriadLlinellGyntaf" class="form-control"/>
        <form:errors path="cyfeiriadLlinellGyntaf" cssStyle="color: red;"/>
    </fieldset>
    <fieldset class="form-group">
        <form:label path="cyfeiriadAilLinell">Ail Linell</form:label>
        <form:input path="cyfeiriadAilLinell" class="form-control"/>
        <form:errors path="cyfeiriadAilLinell" cssStyle="color: red;"/>
    </fieldset>
    <fieldset class="form-group">
        <form:label path="cyfeiriadDinas">Dinas</form:label>
        <form:input path="cyfeiriadDinas" class="form-control"/>
        <form:errors path="cyfeiriadDinas" cssStyle="color: red;"/>
    </fieldset>
    <fieldset class="form-group">
        <form:label path="cyfeiriadSir">Sir</form:label>
        <form:input path="cyfeiriadSir" class="form-control"/>
        <form:errors path="cyfeiriadSir" cssStyle="color: red;"/>
    </fieldset>
    <fieldset class="form-group">
        <form:label path="cyfeiriadCodPost">Cod Post</form:label>
        <form:input path="cyfeiriadCodPost" class="form-control"/>
        <form:errors path="cyfeiriadCodPost" cssStyle="color: red;"/>
    </fieldset>

    <fieldset class="form-group">
        <input type="submit" value="Cyflwyno" class="btn btn-primary" />
    </fieldset>
</form:form>
