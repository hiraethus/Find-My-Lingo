<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<c:if test="${empty gwasanaethau}">
    <div class="alert alert-info" role="alert">
        <p><spring:message code="list.empty.notification" /></p>
    </div>
</c:if>

<c:if test="${not empty gwasanaethau}">

<div class="">
    <table class="table table-striped">
        <thead>
            <th><spring:message code="list.name" /></th>
            <th><spring:message code="list.category" /></th>
            <th><spring:message code="list.city" /></th>
        </thead>
        <tbody>
            <c:forEach var="gwasanaeth" items="${gwasanaethau}">
            <tr>
                <td>
                    <a href='<c:url value="/id/${gwasanaeth.id}" />'>${gwasanaeth.enw}</a>
                </td>
                <td>
                    <spring:message code="${gwasanaeth.categori.categori}" />
                    <span class="${gwasanaeth.categori.categori}"></span>
                </td>
                <td>
                    ${gwasanaeth.cyfeiriadDinas}
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>


</c:if>