<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<div><a href="?locale=en_US">English</a> | <a href="?locale=cy">Cymraeg</a></div>
<div class="header clearfix">
  <nav>
      <ul class="nav nav-pills pull-right">
        <li><a href='<c:url value="/" />'><spring:message code="menu.home" /></a></li>
        <li><a href='<c:url value="/adio" />'><spring:message code="service.add" /></a></li>
        <li><a href="amdanom"><spring:message code="menu.aboutus" /></a></li>
        <c:if test="${not empty pageContext.request.remoteUser}">
        <li>
            <a href='<c:url value="/defnyddiwr/proffil" />'>
                <span class="glyphicon glyphicon glyphicon-user" aria-hidden="true">
                </span> <spring:message code="menu.my.services" />
            </a>
        </li>
        <li>
          <c:url var="logoutUrl" value="/logout"/>
          <form class="form-inline" action="${logoutUrl}" method="post">
            <input class="btn btn-primary" type="submit" value="<spring:message code='menu.logout' />"  style="margin-top: .5em" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          </form>
        </li>
        </c:if>
        <c:if test="${empty pageContext.request.remoteUser}">
          <li><a href='<c:url value="/mewngofnodi" />'><spring:message code="menu.login.or.register" /></a></li>
        </c:if>
      </ul>
      <div class="mini-logo-square"></div>
    <h3 class="text-muted"><spring:message code="service.wales" /></h3>
  </nav>
</div>