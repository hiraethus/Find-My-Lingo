<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
           <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>

<style type="text/css">
    input[type='radio'] {
        background: none;
    }

    input[type='radio'] + label {
        font-size: 1.5em;
    }

    input[type='radio'] + label.DA:after {
        content: "\1F44C";
    }

    input[type='radio'] + label.GWEDDOL:after {
        content: "\1F44D";
    }

    input[type='radio'] + label.GWAEL:after {
        content: "\1F44E";
    }

     input[type='radio']:checked + label:after {
            box-shadow: 0 4px 2px -2px gray;
     }
</style>

<div class="jumbotron">
    <div class="row">

        <div class="col-lg-6">
            <h2><spring:message code='service.description' /></h2>
            <div>Service Language: ${gwasanaeth.language.nativeLanguageName}</div>
            <em>${gwasanaeth.disgrifiad}</em>
            <div><spring:message code='service.category' />: <span class="${gwasanaeth.categori.categori}"><spring:message code="${gwasanaeth.categori.categori}" /></span></div>
            <div><spring:message code='service.maintained.by' arguments='${gwasanaeth.owner}' /></div>

            <h2><spring:message code='service.contact' /></h2>
            <div class="vcard">
                <div class="adr">
                    <div class="street-address">${gwasanaeth.cyfeiriadLlinellGyntaf}</div>
                    <div class="extended-address">${gwasanaeth.cyfeiriadAilLinell}</div>
                    <div class="locality">${gwasanaeth.cyfeiriadDinas}</div>
                    <div class="region">${gwasanaeth.cyfeiriadSir}</div>
                    <div class="postal-code">${gwasanaeth.cyfeiriadCodPost}</div>
                </div>
                <br />
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
    <sec:authorize access="isAuthenticated()">
    <div class="row">
        <a href="<c:url value='/adolygu/${gwasanaeth.id}' />">
        <div style="float: right; display: block;">
            <spring:message code='service.edit' />
        </div>
        </a>
    </div>
    </sec:authorize>
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
<style>
    #map {
        height: 30em;
        width: 30em;
    }
</style>
</c:if>