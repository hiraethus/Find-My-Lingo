<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<c:if test="${param.error ne null}" >
<div class="row">
    <div class="col-lg-12">
        <div class="alert alert-danger" role="alert">
            <p>Enw defnyddiwr neu cyfrinair anghywir. Ceisiwch eto.</p>
        </div>
    </div>
</div>
</c:if>

<div class="row">
    <div class="col-lg-6">
        <div class="jumbotron">
            <form class="form-signin" action="<c:url value='/login' />" method="POST">
                <h2 class="form-signin-heading">Logiwch i mewn</h2>
                <label for="username" class="sr-only">Enw cyfrif</label>
                <input type="username" id="username" name="username" class="form-control" placeholder="Enw cyfrif" required autofocus />
                <label for="password" class="sr-only">Cyfrinair</label>
                <input type="password" id="password"  name="password" class="form-control" placeholder="Cyfrinair" required />
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Cofnodi mewn</button>
            </form>

        </div>
    </div>
    <div class="col-lg-6">
        <div class="jumbotron"><h2>Cofrestrwch</h2></div>
    </div>
</div>