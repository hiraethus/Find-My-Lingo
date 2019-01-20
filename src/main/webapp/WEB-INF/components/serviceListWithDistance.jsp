<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
            <th>Distance <em>(km)</em></th>
        </thead>
        <tbody>
            <c:forEach var="gwasanaethDistPair" items="${gwasanaethau}">
            <c:set var="gwasanaeth" value="${gwasanaethDistPair.gwasanaeth}" />
            <tr>
                <td>
                    <a href='<c:url value="/id/${gwasanaeth.id}" />'>${gwasanaeth.enw}</a>
                </td>
                <td>
                    ${gwasanaeth.categori.categori}
                    <span class="${gwasanaeth.categori.categori}"></span>
                </td>
                <td>
                    ${gwasanaeth.cyfeiriadDinas}
                </td>
                <td>
                    <fmt:formatNumber var="dist" maxFractionDigits="2" value="${gwasanaethDistPair.distance}" />
                    ${dist}
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>


</c:if>