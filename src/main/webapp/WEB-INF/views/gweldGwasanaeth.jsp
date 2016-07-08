<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>


<div class="jumbotron">
    <div class="row">
        <div class="col-md-12">
        </div>
    </div>

    <div class="row">
        <div class="col-md-4">
            <a href="#" class="thumbnail">
                <img src="https://placeholdit.imgix.net/~text?txtsize=33&txt=320%C3%97320&w=320&h=320" />
            </a>
        </div>

        <div class="col-md-4">
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
        <h2>Disgrifiad</h2>
        <pre>${gwasanaeth.disgrifiad}</pre>
    </div>
</div>
