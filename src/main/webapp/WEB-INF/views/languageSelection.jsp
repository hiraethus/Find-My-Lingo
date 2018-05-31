<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<nav style="padding-top: 1em" aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li class="page-item disabled">
      <a class="page-link" href="${flowExecutionUrl}&_eventId=previous" tabindex="-1">Previous</a>
    </li>
    <li class="page-item">
      <a class="page-link" href="${flowExecutionUrl}&_eventId=next">Next</a>
    </li>
  </ul>
</nav>
<div class="row">
    <h3>1. Choose service language</h3>
</div>
<div class="row">
<c:forEach var="language" items="${languages}">
  <div class="col-sm-4">
  <a href="#" onclick="chooseLang('${language.nativeLanguageName}')">
      <div class="card">
        <div class="card-body">
          <div class="card-text">${language.nativeLanguageName}</div>
        </div>
      </div>
  </a>
  </div>
</c:forEach>
</div>

<div class="row">
<ul class="list-group">
  <li class="list-group-item"><em>Language</em> ${searchCriteria.language}</li>
  <li class="list-group-item">
    <em>Category</em>
    <c:if test="${not empty searchCriteria.category}">
      <spring:message code="${searchCriteria.category}" />
    </c:if>
  </li>
  <li class="list-group-item"><em>Location</em> ${searchCriteria.city}</li>
</ul>
</div>

<form:form style="display: none;" action="${flowExecutionUrl}" method="post" modelAttribute="searchCriteria">
  <form:input path="language" id="language" />
  <input id="submit" type="submit" name="_eventId_selectLang" />
</form:form>

<script type="text/javascript">
    var chooseLang = (lang) => {
        $('#language').val(lang);
        $('#submit').click();
    };
</script>