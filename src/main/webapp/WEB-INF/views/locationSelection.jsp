<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<div class="row">
    <form class="form">
        <div class="form-group">
            <div id="map"></div>
        </div>
        <div class="form-group">
            <input class="form-control" type="text" id="address" placeholder="Address" />
        </div>
        <div class="form-group">
            <input class="btn" type="button" id="findAddress" value="Find" />
            <input class="invisible" type="button" id="go" value="Go" style="padding-left: 1em" />
        </div>
    </form>
</div>

<div class="row">

<div class="text-centered">
<form:form class="form-inline" action="${flowExecutionUrl}" method="post" modelAttribute="aService">
  <div class="form-group">
    <form:input path="cyfeiriadDinas" id="city" class="invisible" />
    <form:input id="longitude" path="longitude" class="invisible" />
    <form:input id="latitude" path="latitude" class="invisible" />
    <input id="submitForm" class="invisible" type="submit" name="_eventId_selectLocation" />
  </div>
</form:form>
</div>
</div>

<nav style="padding-top: 1em" aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li class="page-item">
      <a class="page-link" href="${flowExecutionUrl}&_eventId=previous">Previous</a>
    </li>
    <li class="page-item disabled">
      <a class="page-link" href="${flowExecutionUrl}&_eventId=next">Next</a>
    </li>
  </ul>
</nav>

<script type="text/javascript">
    var map
    var geocoder
    var initGmaps = () => {
        geocoder = new google.maps.Geocoder()
        let latLng = new google.maps.LatLng(39.305, -76.617)
        map = new google.maps.Map(document.getElementById('map'), {
            center: latLng,
            zoom: 1
        })

        let retrieveGeocode = () => {
            let addr = document.getElementById('address')
            geocoder.geocode( { 'address': addr.value}, (results, status) => {
              if (status == 'OK') {
                addr.value = results[0].formatted_address
                let location = results[0].geometry.location
                document.getElementById('longitude').value = location.lng()
                document.getElementById('latitude').value = location.lat()
                map.setCenter(location)
                map.setZoom(16)
                var marker = new google.maps.Marker({
                    map: map,
                    position: location
                })

                // make go button invisible
                document.getElementById('go').className = "btn btn-primary"
              } else {
                alert('Geocode was not successful for the following reason: ' + status);
              }
            })
        }

        // listen for findAddress click
        document.getElementById('findAddress').addEventListener('click', retrieveGeocode)

        // submit form
        document.getElementById('go').addEventListener('click', () => {
            document.getElementById('submitForm').click()
        })
    }
</script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBb8tWZz5wUmn-81Cfx5-YNwLmYG2CYqEE&v=3.31&callback=initGmaps" type="text/javascript"></script>
<style>
    #map {
        height: 30em;
        width: 30em;
    }
</style>