<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<div class="row">
    <h3>1. Choose service language</h3>
</div>
<div class="row">

<div class="col-sm-2">
<ul class="list-group">
  <li class="list-group-item">
  <em>Language</em>
  <c:if test="${not empty searchCriteria.languageImg}">
    <img class="card-img-top" src="/images/${searchCriteria.languageImg}">
  </c:if>
  </li>
  <li class="list-group-item">
    <em>Category</em>
    <c:if test="${not empty searchCriteria.category}">
      <spring:message code="${searchCriteria.category}" />
    </c:if>
  </li>
  <li class="list-group-item"><em>Location</em> ${searchCriteria.city}</li>
</ul>
</div>

<c:forEach var="language" items="${languages}">
<div class="col-sm-2">
  <a href="#" onclick="chooseLang('${language.nativeLanguageName}', '${language.languageFlagImg}')">
      <div class="card" style="max-width: 10rem">
        <img class="card-img-top" src="/images/${language.languageFlagImg}">
      </div>
  </a>
</div>
</c:forEach>


</div>
</div>

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

<form:form style="display: none;" action="${flowExecutionUrl}" method="post" modelAttribute="searchCriteria">
  <form:input path="languageImg" id="languageImg" />
  <form:input path="language" id="language" />
  <input id="submit" type="submit" name="_eventId_selectLang" />
</form:form>

<script type="text/javascript">
    var chooseLang = (lang, langImg) => {
        $('#language').val(lang);
        $('#languageImg').val(langImg);
        $('#submit').click();
    };
</script>