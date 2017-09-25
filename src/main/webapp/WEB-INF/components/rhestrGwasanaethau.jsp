<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<c:if test="${not empty param.dinas}">
<div class="alert alert-info" role="alert">
    <p><spring:message code="filter.city" arguments="${param.dinas}" /> <a href='<c:url value="/">
        <c:forEach var="prm" items="${param}">
            <c:if test="${prm.key != 'dinas'}">
                <c:param name="${prm.key}" value="${prm.value}"/>
            </c:if>
        </c:forEach>
    </c:url>'><spring:message code='filter.reset' /></a></p>
</div>
</c:if>

<c:if test="${not empty param.categori}">
<div class="alert alert-warning" role="alert">
    <p><spring:message code="filter.categori" arguments="${param.categori}" /> <a href='<c:url value="/">
        <c:forEach var="prm" items="${param}">
            <c:if test="${prm.key != 'categori'}">
                <c:param name="${prm.key}" value="${prm.value}"/>
            </c:if>
        </c:forEach>
    </c:url>'><spring:message code='filter.reset' /></a></p>
</div>
</c:if>

<c:if test="${not empty param.searchTerm}">
<div class="alert alert-warning" role="alert">
    <p><spring:message code="filter.searchterm" arguments="${param.searchTerm}" /> <a href='<c:url value="/">
        <c:forEach var="prm" items="${param}">
            <c:if test="${prm.key != 'searchTerm'}">
                <c:param name="${prm.key}" value="${prm.value}"/>
            </c:if>
        </c:forEach>
    </c:url>'><spring:message code='filter.reset' /></a></p>
</div>
</c:if>

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
                    <a class="${gwasanaeth.categori.categori}" href='<c:url value="">
                        <c:param name="categori" value="${gwasanaeth.categori.categori}" />
                        <c:forEach var="prm" items="${param}">
                            <c:if test="${prm.key != 'categori'}">
                                <c:param name="${prm.key}" value="${prm.value}"/>
                            </c:if>
                        </c:forEach>
                        </c:url>'>
                        <spring:message code="${gwasanaeth.categori.categori}" />
                    </a>
                </td>
                <td>
                    <a href='<c:url value="">
                        <c:param name="dinas" value="${gwasanaeth.cyfeiriadDinas}" />
                        <c:forEach var="prm" items="${param}">
                            <c:if test="${prm.key != 'dinas'}">
                                <c:param name="${prm.key}" value="${prm.value}"/>
                            </c:if>
                        </c:forEach>
                    </c:url>'>
                        ${gwasanaeth.cyfeiriadDinas}
                    </a>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>


</c:if>