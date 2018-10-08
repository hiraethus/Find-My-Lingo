<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<style>
input[type="radio"] { display: none; }
input[type="radio"]+label{ opacity: 0.5; }
input[type="radio"]:checked+label{ opacity: 1.0; }

.name-input {
    width: 50em;
    border-bottom: 1px solid rgba(180,180,180,.15);
    margin-bottom: 2em;
    border-top: none;
    border-left: none;
    border-right: none;
}
</style>

<form:form action="${flowExecutionUrl}" method="post" modelAttribute="aService">
  <form:input path="enw" id="enw" class="name-input" placeholder="Type Service Name Here"/>

  <div class="row">
    <h5>Language</h5>
  </div>
  <div class="row">
      <c:forEach var="language" items="${languages}">
        <div class="form-check form-check-inline">
          <form:radiobutton class="form-check-input" path="language" id="${language.nativeLanguageName}" value="${language.nativeLanguageName}" />
          <form:label path="language" class="form-check-label" for="${language.nativeLanguageName}">
          <div class="card" style="max-width: 10rem">
              <img id="${language.nativeLanguageName}" class="lang-img card-img-top inactive" src="/images/${language.languageFlagImg}">
                <div class="card-body">
                    <div class="card-text">
                        ${language.nativeLanguageName}
                    </div>
                </div>
          </div>
          </form:label>
        </div>
      </c:forEach>
  </div>

  <div class="row">
    <h5>Category</h5>
  </div>
  <div class="row">
      <c:forEach var="category" items="${categories}">
        <div class="form-check form-check-inline">
          <form:radiobutton class="form-check-input" path="categori" id="${category.categori}" value="${category.categori}" />
          <form:label path="categori" class="form-check-label" for="${category.categori}">
             <div class="card" style="max-width: 10rem">
                 <img class="card-img-top" src="/images/${category.categoriImg}">
                 <div class="card-body">
                     <div class="card-text">
                         <spring:message code="${category.categori}" />
                     </div>
                 </div>
             </div>
          </form:label>
        </div>
      </c:forEach>
  </div>

  <input style="display: none" id="submit" type="submit" name="_eventId_setServiceNameLangAndCategory" />
</form:form>

<nav style="padding-top: 1em" aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li class="page-item disabled">
      <a class="page-link" href="${flowExecutionUrl}&_eventId=previous">Previous</a>
    </li>
    <li class="page-item">
      <a class="page-link" href="#" onclick="next()">Next</a>
    </li>
  </ul>
</nav>

<script type="text/javascript">
    var next = () => {
        $('#submit').click();
    };
</script>