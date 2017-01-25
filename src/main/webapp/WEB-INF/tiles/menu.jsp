<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

        <li><a href='<c:url value="/" />'>Adref</a></li>
        <sec:authorize access="hasRole('ROLE_CREATE_GWASANAETH')">
          <li><a href='<c:url value="/ychwanegu" />'>Ychwanegu Gwasanaeth</a></li>
        </sec:authorize>
        <li><a href="cysylltwch">Cysylltwch</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
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
    </div><!--/.nav-collapse -->
  </div>
</nav>