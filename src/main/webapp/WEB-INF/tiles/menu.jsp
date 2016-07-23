<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<nav class="navbar navbar-fixed-top" style="background-color: #fff">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <div class="navbar-brand">Gwasanaethau Cymraeg</div>
    </div>
    <div id="navbar" class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li><a href='<c:url value="/gwasanaethau/" />'>Adref</a></li>
        <li><a href="#amdanom">Amdanom</a></li>
        <li><a href="#cysylltwch">Cysylltwch</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <c:if test="${not empty pageContext.request.remoteUser}">
        <li><a><span class="glyphicon glyphicon glyphicon-user" aria-hidden="true"></span> Shwmae, <c:out value="${pageContext.request.remoteUser}"/></a></li>
        <li>
            <c:url var="logoutUrl" value="/logout"/>
            <form class="form-inline" action="${logoutUrl}" method="post">
              <input class="btn btn-primary" type="submit" value="Allgofnodi"  style="margin-top: .5em" />
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </li>
        </c:if>
        <c:if test="${empty pageContext.request.remoteUser}">
            <li><a href='<c:url value="/mewngofnodi" />'>Mewngofnodi</a></li>
        </c:if>
      </ul>
    </div><!--/.nav-collapse -->
  </div>
</nav>