<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form action="/gc/gwasanaethau/" commandName="gwasanaeth" method="POST">
    <table class="table">
        <tr>
            <td>Enw'r Wasanaeth</td>
            <td><form:input path="enw" /></td>
            <td><form:errors path="enw" cssStyle="color: red;"/></td>
        </tr>
        <tr>
            <td>Rhif Ffon</td>
            <td><form:input path="rhifFfon" /></td>
            <td><form:errors path="rhifFfon" cssStyle="color: red;"/></td>
        </tr>
        <tr>
            <td>E-bost</td>
            <td><form:input path="ebost" /></td>
            <td><form:errors path="ebost" cssStyle="color: red;"/></td>
        </tr>
        <tr>
            <td>Disgrifiad</td>
            <td><form:textarea path="disgrifiad" rows="5" cols="30" /></td>
            <td><form:errors path="disgrifiad" cssStyle="color: red;"/></td>
        </tr>
    </table>

    <h4>Cyfeiriad</h4>

    <table class="table">
            <tr>
                <td>Llinell Gyntaf</td>
                <td><form:input path="cyfeiriadLlinellGyntaf" /></td>
                <td><form:errors path="cyfeiriadLlinellGyntaf" cssStyle="color: red;"/></td>
            </tr>
            <tr>
                <td>Ail linell</td>
                <td><form:input path="cyfeiriadAilLinell" /></td>
                <td><form:errors path="cyfeiriadAilLinell" cssStyle="color: red;"/></td>
            </tr>
            <tr>
                <td>Dinas</td>
                <td><form:input path="cyfeiriadDinas" /></td>
                <td><form:errors path="cyfeiriadDinas" cssStyle="color: red;"/></td>
            </tr>
            <tr>
                <td>Sir</td>
                <td><form:input path="cyfeiriadSir" /></td>
                <td><form:errors path="cyfeiriadSir" cssStyle="color: red;"/></td>
            </tr>
            <tr>
                <td>Cod Post</td>
                <td><form:input path="cyfeiriadCodPost" /></td>
                <td><form:errors path="cyfeiriadCodPost" cssStyle="color: red;"/></td>
            </tr>
    </table>

    <input type="submit" value="Cyflwyno"/>
</form:form>
