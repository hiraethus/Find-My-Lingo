<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
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
            <div class="gwasanaethau-logo">
                <div class="logo-square">
                    <div class="gwasanaethau-text">GWASANAETHAU</div>
                </div>
                <div class="cymraeg-text">
                    .CYMRU
                </div>
            </div>
        </div>
        <div class="col-md-8">
                    <h1>Gwasanaethau Cymru</h1>

        <p>
            Prosiect yw Gwasanaethau Cymru i alluogi siaradwyr yr iaith Gymraeg i ddarganfod gwasanaethau
            cyfrwng cymraeg yn eu hardal.
            Os ydych chi yn perchen ar caffi, bwyti, yn gweithio i lywodraeth lleol neu yn darparu gwasanaeth preifat
            trwy gyfrwng y Gymraeg, byswn ni yn eich croesawi i gofrestru eich hun gyda'r ffurflen uchod.
        </p>
        </div>
    </div>
</div>

<h2>Rhestr Gwasanaethau</h2>
<tiles:insertAttribute name="rhestrGwasanaethau" />
