<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<nav style="margin-bottom: 1.5em" class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
  <a class="navbar-brand" href='<c:url value="/" />'><img style="transform: skewY(1deg); width: 2em" src="/images/hands-600497_960_720.jpg" class="rounded">
Find My Lingo <sup>alpha</sup></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href='<c:url value="/find" />'>Find Service</a></li>
      </li>
      <li class="nav-item">
        <a class="nav-link" href='<c:url value="/add" />'>Add Service</a></li>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/about">About</a>
      </li>
    </ul>

    <ul class="navbar-nav ml-auto">
    <c:choose>
      <c:when test="${empty pageContext.request.remoteUser}">
        <span class="navbar-text">
          <a href='<c:url value="/login" />'><spring:message code="menu.login.or.register" /></a>
        </span>
      </c:when>
      <c:otherwise>
      <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
               <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                 <spring:message code="menu.my.services" />
               </a>
               <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                 <a class="dropdown-item" href="<c:url value="/defnyddiwr/proffil" />"><spring:message code="menu.my.services" /></a>
                 <div class="dropdown-divider"></div>
                 <a onclick="$('#logoutButton').click();" class="dropdown-item" href="#"><spring:message code='menu.logout' /></a>
               </div>
             </li>
        </ul>
        <c:url var="logoutUrl" value="/logout"/>
        <form style="display: none" action="${logoutUrl}" method="post">
            <input id="logoutButton" type="submit" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
      </c:otherwise>
    </c:choose>
  </div>
  </div>

</nav>

