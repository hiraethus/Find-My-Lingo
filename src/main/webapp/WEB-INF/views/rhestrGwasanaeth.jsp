<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<c:if test="${param.allgofnodi ne null}" >
<div class="row">
    <div class="col-lg-12">
        <div class="alert alert-success" role="alert">
            <p>Allgofnodwyd yn llwyddianus</p>
        </div>
    </div>
</div>
</c:if>
<c:if test="${not empty registrationSuccessful}" >
<div class="row">
    <div class="col-lg-12">
        <div class="alert alert-success" role="alert">
            ${registrationSuccessful}
        </div>
    </div>
</div>
</c:if>

<div class="jumbotron">

    <div class="row">
        <div class="col-md-4">
            <div class="thumbnail">
                <img src="https://placeholdit.imgix.net/~text?txtsize=33&txt=480%C3%97480&w=480&h=480" />
            </div>
        </div>
        <div class="col-md-8">
                    <h1>Gwasanaethau Cymraeg</h1>

        <p>
            Prosiect yw Gwasanaethau Cymraeg i alluogi siaradwyr yr iaith Gymraeg i ddarganfod gwasanaethau
            cyfrwng cymraeg yn eu hardal.
            Os ydych chi yn perchen ar caffi, bwyti, yn gweithio i lywodraeth lleol neu yn darparu gwasanaeth preifat
            trwy gyfrwng y Gymraeg, byswn ni yn eich croesawi i gofrestru eich hun gyda'r ffurflen uchod.
        </p>
        </div>
    </div>
</div>

<h2>Rhestr Gwasanaethau</h2>
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

<c:if test="${not empty param.categori}">
<div class="alert alert-warning" role="alert">
    <p>Hidlo i ddangos gwasanaethau y categori <em>${param.categori}</em> yn unig. <a href='<c:url value="/gwasanaethau/">
        <c:forEach var="prm" items="${param}">
            <c:if test="${prm.key != 'categori'}">
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
            <th>Categori</th>
            <th>Dinas</th>
        </thead>
        <tbody>
            <c:forEach var="gwasanaeth" items="${gwasanaethau}">
            <tr>
                <td>
                    <a href='<c:url value="/gwasanaethau/id/${gwasanaeth.id}" />'>${gwasanaeth.enw}</a>
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