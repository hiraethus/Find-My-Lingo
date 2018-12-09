<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>


<div class="row">

    <div class="col-lg-6">
        <h1>${gwasanaeth.enw}</h1>
        <p>
          <em><spring:message code="${gwasanaeth.categori.categori}" /> | ${gwasanaeth.language.nativeLanguageName} <sec:authorize access="isAuthenticated()">| <a href="<c:url value='/edit/${gwasanaeth.id}' />"><spring:message code='service.edit' /></a></sec:authorize></em>
        </p>
    </div>
</div>

<div class="row mt-3 mb-3">
    <div id="service-images" class="d-flex flex-wrap"></div>
</div>
    <div class="row pb-3">

        <div class="col-lg-6 pb-3">
            <h2>Description</h2>
            <p>${gwasanaeth.disgrifiad}</p>

            <h2>Address</h2>
            <div class="vcard">
                <div class="adr">
                    <div class="street-address">${gwasanaeth.cyfeiriadLlinellGyntaf}</div>
                    <div class="extended-address">${gwasanaeth.cyfeiriadAilLinell}</div>
                    <div class="locality">${gwasanaeth.cyfeiriadDinas}</div>
                    <div class="region">${gwasanaeth.cyfeiriadSir}</div>
                    <div class="postal-code">${gwasanaeth.cyfeiriadCodPost}</div>
                </div>

             <h2>Contact</h2>
                <div class="tel">${gwasanaeth.rhifFfon}</div>
                <div class="email">${gwasanaeth.ebost}</div>
            </div>
       </div>
       <c:if test="${gwasanaeth.latitude != null}">
       <div class="col-lg-6">
        <div id="map"></div>
       </div>
       </c:if>
    </div>


<c:if test="${gwasanaeth.latitude != null}">
<script type="text/javascript">
    var map;
    var latLng = {lat: ${gwasanaeth.latitude}, lng: ${gwasanaeth.longitude}};
    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: latLng,
            zoom: 15
        });

        var marker = new google.maps.Marker({
            position: latLng,
            map: map,
            title: '${gwasanaeth.enw}'
            });

    }
</script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBb8tWZz5wUmn-81Cfx5-YNwLmYG2CYqEE&v=3.29&callback=initMap" type="text/javascript"></script>
</c:if>

<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>

<script type="text/javascript">
getServiceImgs = (serviceId) => {
    csrfToken = document.getElementsByName('_csrf')[0].getAttribute('content')
    csrfHeader = document.getElementsByName('_csrf_header')[0].getAttribute('content')

    request = new XMLHttpRequest()

    request.open("GET", "/getServiceImgs/"+serviceId)
    request.setRequestHeader(csrfHeader, csrfToken)
    request.onload = (e) => {
        console.log(request.responseText)
        arrayOfImgs = JSON.parse(request.responseText)

        imgsDiv = document.getElementById("service-images")
        while (imgsDiv.firstChild) {
            imgsDiv.removeChild(imgsDiv.firstChild);
        }

        arrayOfImgs.forEach((imgUrl) => {
            imgsDiv.appendChild(createImgCard(imgUrl))
        })
    }

    request.send()
}

createImgCard = (imgUrl) => {
    cardTemplate = document.querySelector("template")
    card = document.importNode(cardTemplate.content, true)
    card.querySelector("img").setAttribute("src", "/" + imgUrl)
    card.querySelector("a").setAttribute("href", "/" + imgUrl)

    return card
}

window.onload = () => {
    getServiceImgs('${gwasanaeth.id}')
}
</script>

<template>
    <div class="card img-card mx-2">
        <a href="...">
            <img class="card-img-top" src="...">
        </a>
    </div>
</template>
