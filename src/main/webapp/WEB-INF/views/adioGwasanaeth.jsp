<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<body>

<form:form action="/gc/gwasanaethau/" commandName="gwasanaeth" method="POST">
    <table>
        <tr>
            <td>Enw'r Wasanaeth</td>
            <td><form:input path="enw" /></td>
        </tr>
    </table>

    <input type="submit" value="submit"/>
</form:form>

</body>
</html>