<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<div class="">
    <table class="table table-striped">
        <thead>
            <th>Enw</th>
            <th>Math</th>
            <th>Dinas</th>
        </thead>
        <tbody>

            <c:forEach var="gwasanaeth" items="${gwasanaethau}">
            <tr>
                <td>
                    <a href='<c:url value="${pageContext.request.contextPath}/gwasanaethau/id/${gwasanaeth.id}" />'>${gwasanaeth.enw}</a>
                </td>
                <td><code>Categori (TODO)</code></td>
                <td>${gwasanaeth.cyfeiriadDinas}</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>