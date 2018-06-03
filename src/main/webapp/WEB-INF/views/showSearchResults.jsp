<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<div class="container">

    <div class="row">
        <div class="col-md-9" style="overflow: hidden">
            <h1>Results</h1>
            <tiles:insertAttribute name="serviceList" />
        </div>
                    <div class="col-md-3">
                        <div id="map"></div>
                    </div>
    </div>

</div>
<c:if test="${gwasanaethau != null}">
<script type="text/javascript">
    var map;
    var latLng = [
        <c:forEach var="g" items="${gwasanaethau}">
        <c:if test="${not empty g.latitude}">
        {lat: ${g.latitude}, lng: ${g.longitude}},
        </c:if>
        </c:forEach>
        ];
    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: latLng[1],
            zoom: 8
        });

         var marker, i;
         for (i = 0; i < latLng.length; i++) {
              marker = new google.maps.Marker({
                position: latLng[i],
                map: map
              });
         }
    }
</script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBb8tWZz5wUmn-81Cfx5-YNwLmYG2CYqEE&v=3.29&callback=initMap" type="text/javascript"></script>
<style>
    #map {
        height: 20em;
        width: 20em;
    }
</style>
</c:if>