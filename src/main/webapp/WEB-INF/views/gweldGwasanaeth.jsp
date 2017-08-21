<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>


<div class="jumbotron">
    <div class="row">

        <div class="col-lg-6">
            <h2>Disgrifiad</h2>
            <em>${gwasanaeth.disgrifiad}</em>
            <div>Categori: <span class="${gwasanaeth.categori.categori}"><spring:message code="${gwasanaeth.categori.categori}" /></span></div>
            <div>Cynhelir y proffil yma gan: <em>${gwasanaeth.owner}</em></div>

            <h2>Cyswllt</h2>
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
       <c:if test="${geolocation != null}">
       <div class="col-lg-6">
        <div id="map"></div>
       </div>
       </c:if>
    </div>
    <sec:authorize access="isAuthenticated()">
    <div class="row">
        <a href="<c:url value='/adolygu/${gwasanaeth.id}' />">
        <div style="float: right; display: block;">
            Adolygu
        </div>
        </a>
    </div>
    </sec:authorize>
</div>

<div class="row">
    <div class="col-md-12">
        <h2>Sylwadau</h2>
    </div>
</div>

<div class="row">
    <!-- TODO pass gwasanaeth ID to form -->
    <form:form action="${pageContext.request.contextPath}/cyflwynoSylw/${gwasanaeth.id}" commandName="sylw" method="POST" role="form">
    <div class="panel panel-default">
      <div class="panel-heading">Adio sylw</div>
          <div class="panel-body">
          <div class="row">
            <div class="col-xs-6">
                <table class="table table-invisible">
                    <tr>
                        <th><form:label path="safonIaith">Safon Iaith</form:label></th>
                        <c:forEach var="safon" items="${safonnau}">
                             <td>
                                 <label class="radio-inline">
                                    <form:radiobutton path="safonIaith" value="${safon}" />${safon}
                                </label>
                             </td>
                        </c:forEach>
                    </tr
                    <tr>
                        <th><form:label path="safonArwyddiaeth">Safon Arwyddiaeth</form:label></th>
                        <c:forEach var="safon" items="${safonnau}">
                             <td>
                                 <label class="radio-inline">
                                    <form:radiobutton path="safonArwyddiaeth" value="${safon}" />${safon}
                                </label>
                             </td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <th><form:label path="safonGwasanaeth">Safon Gwasanaeth</form:label></th>
                        <c:forEach var="safon" items="${safonnau}">
                             <td>
                                <form:radiobutton path="safonGwasanaeth" value="${safon}" />${safon}
                             </td>
                        </c:forEach>
                    </tr>
                </table>

            </div>
            <div class="col-xs-6">
                <form:textarea path="sylw" rows="8" cols="30" class="form-control" placeholder="Sylwadau..." />
            </div>
          </div>
          <div class="row">
              <div class="col-md-8">

                <fieldset class="form-group">
                    <input type="submit" value="Cyflwyno" class="btn btn-primary" />
                </fieldset>
              </div>
          </div>
      </div>
    </div>
    </form:form>
</div>

<c:forEach var="sylw" items="${gwasanaeth.sylwadau}">
<div class="row">
    <!-- TODO pass gwasanaeth ID to form -->
    <div class="panel panel-default">
      <div class="panel-heading">Dyddiad: ${sylw.amserSylw}</div>
          <div class="panel-body">
          <div class="row">
            <div class="col-xs-8">
                <table class="table table-invisible">
                    <tr>
                        <th>Safon Iaith</th>
                        <c:forEach var="safon" items="${safonnau}">
                             <td>
                                  <label class="radio-inline">
                                    <input type="radio" value="${safon}" <c:if test="${ safon == sylw.safonIaith }">checked="checked"</c:if> disabled/>${safon}
                                  </label>
                             </td>
                        </c:forEach>
                    </tr
                    <tr>
                        <th>Safon Arwyddiaeth</th>
                        <c:forEach var="safon" items="${safonnau}">
                             <td>
                                  <label class="radio-inline">
                                    <input type="radio" value="${safon}" <c:if test="${ safon == sylw.safonArwyddiaeth }">checked="checked"</c:if> disabled/>${safon}
                                  </label>
                             </td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <th>Safon Gwasanaeth</th>
                        <c:forEach var="safon" items="${safonnau}">
                             <td>
                                  <label class="radio-inline">
                                    <input type="radio" value="${safon}" <c:if test="${ safon == sylw.safonGwasanaeth }">checked="checked"</c:if> disabled/>${safon}
                                  </label>
                             </td>
                        </c:forEach>
                    </tr>
                </table>

            </div>
            <div class="col-xs-6">
                ${sylw.sylw}
            </div>
          </div>
      </div>
    </div>
</div>
</c:forEach>
<c:if test="${geolocation != null}">
<script type="text/javascript">
    var map;
    var latLng = {lat: ${geolocation.latitude}, lng: ${geolocation.longitude}};
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