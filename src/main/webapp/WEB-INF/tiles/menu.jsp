<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>

<div class="header clearfix">
  <nav>
      <ul class="nav nav-pills pull-right">
        <li><a href='<c:url value="/" />'>Adref</a></li>
        <li><a href='<c:url value="/adio" />'>Adio Gwasanaeth</a></li>
        <li><a href="amdanom">Amdanom</a></li>
        <c:if test="${not empty pageContext.request.remoteUser}">
        <li><a href='<c:url value="/defnyddiwr/proffil" />'><span class="glyphicon glyphicon glyphicon-user" aria-hidden="true"></span> Fy Ngwasanaethau</a></li>
        <li>
          <c:url var="logoutUrl" value="/logout"/>
          <form class="form-inline" action="${logoutUrl}" method="post">
            <input class="btn btn-primary" type="submit" value="Allgofnodi"  style="margin-top: .5em" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          </form>
        </li>
        </c:if>
        <c:if test="${empty pageContext.request.remoteUser}">
          <li><a href='<c:url value="/mewngofnodi" />'>Mewngofnodi/Cofrestru</a></li>
        </c:if>
      </ul>
    <h3 class="text-muted">Gwasanaethau Cymru</h3>
  </nav>
</div>