<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<div><a href="?locale=en_US">English</a> | <a href="?locale=cy">Cymraeg</a></div>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">Find my Lingo</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href='<c:url value="/" />'><spring:message code="menu.home" /><span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href='<c:url value="/adio" />'><spring:message code="service.add" /></a></li>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/amdanom"><spring:message code="menu.aboutus" /></a>
      </li>

    <c:choose>
      <c:when test="${empty pageContext.request.remoteUser}">
        <span class="navbar-text">
          <a href='<c:url value="/mewngofnodi" />'><spring:message code="menu.login.or.register" /></a>
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
</nav>

