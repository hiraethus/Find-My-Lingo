<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>


<div class="jumbotron">
    <div class="row">
        <div class="col-md-4">
            <a href="#" class="thumbnail">
                <img src="https://placeholdit.imgix.net/~text?txtsize=33&txt=320%C3%97320&w=320&h=320" />
            </a>
        </div>

        <div class="col-md-8">
            <h2>Disgrifiad</h2>
            <em>${gwasanaeth.disgrifiad}</em>
            <div>Categori: <em>${gwasanaeth.categori.categori}</em></div>

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
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <h2>Sylwadau</h2>
    </div>
</div>

<div class="row">
    <!-- TODO pass gwasanaeth ID to form -->
    <form:form action="${pageContext.request.contextPath}/gwasanaethau/cyflwynoSylw/${gwasanaeth.id}" commandName="sylw" method="POST" role="form">
    <div class="panel panel-default">
      <div class="panel-heading">Ychwanegu sylw</div>
          <div class="panel-body">
          <div class="row">
            <div class="col-md-4">
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
            <div class="col-md-8">
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
            <div class="col-md-4">
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
            <div class="col-md-8">
                ${sylw.sylw}
            </div>
          </div>
      </div>
    </div>
</div>
</c:forEach>