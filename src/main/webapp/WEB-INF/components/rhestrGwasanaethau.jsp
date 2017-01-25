<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<c:if test="${empty gwasanaethau}">
    <div class="alert alert-info" role="alert">
        <p>Gwag! Ychwanegwch gwasanaethau eich hun trwy clicio 'Ychwanegu Gwasanaeth' uchod</p>
    </div>
</c:if>

<c:if test="${not empty gwasanaethau}">

<c:if test="${not empty param.dinas}">
<div class="alert alert-info" role="alert">
    <p>Hidlo i ddangos gwasanaethau dinas <em>${param.dinas}</em> yn unig. <a href='<c:url value="/">
        <c:forEach var="prm" items="${param}">
            <c:if test="${prm.key != 'dinas'}">
                <c:param name="${prm.key}" value="${prm.value}"/>
            </c:if>
        </c:forEach>
    </c:url>'>Ailosod</a></p>
</div>
</c:if>

<c:if test="${not empty param.categori}">
<div class="alert alert-warning" role="alert">
    <p>Hidlo i ddangos gwasanaethau y categori <em>${param.categori}</em> yn unig. <a href='<c:url value="/">
        <c:forEach var="prm" items="${param}">
            <c:if test="${prm.key != 'categori'}">
                <c:param name="${prm.key}" value="${prm.value}"/>
            </c:if>
        </c:forEach>
    </c:url>'>Ailosod</a></p>
</div>
</c:if>

<div class="">
    <table class="table table-striped">
        <thead>
            <th>Enw</th>
            <th>Categori</th>
            <th>Dinas</th>
        </thead>
        <tbody>
            <c:forEach var="gwasanaeth" items="${gwasanaethau}">
            <tr>
                <td>
                    <a href='<c:url value="/id/${gwasanaeth.id}" />'>${gwasanaeth.enw}</a>
                </td>
                <td>
                    <a href='<c:url value="">
                        <c:param name="categori" value="${gwasanaeth.categori.categori}" />
                        <c:forEach var="prm" items="${param}">
                            <c:if test="${prm.key != 'categori'}">
                                <c:param name="${prm.key}" value="${prm.value}"/>
                            </c:if>
                        </c:forEach>
                        </c:url>'>
                        ${gwasanaeth.categori.categori}
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