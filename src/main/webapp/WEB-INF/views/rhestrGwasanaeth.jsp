<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<c:if test="${not empty param.dinas}">
<div class="alert alert-info" role="alert">
    <p>Hidlo i ddangos gwasanaethau dinas <em>${param.dinas}</em> yn unig. <a href='<c:url value="/gwasanaethau/">
        <c:forEach var="prm" items="${param}">
            <c:if test="${prm.key != 'dinas'}">
                <c:param name="${prm.key}" value="${prm.value}"/>
            </c:if>
        </c:forEach>
    </c:url>'>Ailosod</a></p>
</div>
</c:if>

<ul>

</ul>
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
                    <a href='<c:url value="/gwasanaethau/id/${gwasanaeth.id}" />'>${gwasanaeth.enw}</a>
                </td>
                <td>${gwasanaeth.categori.categori}</td>
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