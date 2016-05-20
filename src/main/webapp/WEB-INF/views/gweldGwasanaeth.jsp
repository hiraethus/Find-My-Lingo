<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form action="/gc/gwasanaethau/" commandName="gwasanaeth">
    <table class="table">
        <tr>
            <td>Enw'r Wasanaeth</td>
            <td><form:input path="enw" readonly="true" /></td>
        </tr>
        <tr>
            <td>Rhif Ffon</td>
            <td><form:input path="rhifFfon" readonly="true" /></td>
        </tr>
        <tr>
            <td>E-bost</td>
            <td><form:input path="ebost" readonly="true" /></td>
        </tr>
        <tr>
            <td>Disgrifiad</td>
            <td><form:textarea path="disgrifiad" rows="5" cols="30" readonly="true" /></td>
        </tr>
    </table>
</form:form>
