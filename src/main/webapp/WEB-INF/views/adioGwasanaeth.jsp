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

    <input type="submit" value="Cyflwyno"/>
</form:form>
