<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<style>

img.active {
    opacity: 1.0;
}

img.inactive {
    opacity: 0.2;
}

.name-input {
    width: 50em;
    border-bottom: 1px solid rgba(180,180,180,.15);
    margin-bottom: 2em;
    border-top: none;
    border-left: none;
    border-right: none;
}
</style>

<div class="row">
    <input id="name-input" class="name-input" type="text" placeholder="Type Service Name Here" />
</div>

<div class="row">
<c:forEach var="language" items="${languages}">
<div class="col-sm-3">
  <a href="#" onclick="chooseLang('${language.nativeLanguageName}')">
      <div class="card" style="max-width: 10rem">
        <img id="${language.nativeLanguageName}" class="lang-img card-img-top inactive" src="/images/${language.languageFlagImg}">
          <div class="card-body">
              <div class="card-text">
                  ${language.nativeLanguageName}
              </div>
          </div>
      </div>

  </a>
</div>
</c:forEach>
</div>

<div class="row">
<c:forEach var="category" items="${categories}">
<div class="col-sm-3">
    <a href="#" onclick="chooseCategory('${category.categori}')">
        <div class="card">
            <img class="card-img-top" src="/images/${category.categoriImg}">
            <div class="card-body">
                <div class="card-text">
                    <spring:message code="${category.categori}" />
                </div>
            </div>
        </div>
    </a>
</div>
</c:forEach>
</div>

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

<form:form style="display: none;" action="${flowExecutionUrl}" method="post" modelAttribute="aService">
  <form:input path="enw" id="enw" />
  <form:input path="language" id="language" />
  <form:input path="categori" id="categori" />
  <input id="submit" type="submit" name="_eventId_setServiceNameLangAndCategory" />
</form:form>

<script type="text/javascript">
    $('#name-input').change(() => {
        $('#enw').val($('#name-input').val())
    });

    var chooseLang = (lang) => {
        $('#language').val(lang)

        // make all images opaque
        $('.lang-img').each((idx) => {
            $( this ).removeClass('active')
            $( this ).addClass('inactive')
        })

        // make all images opaque
        $('#' + lang).removeClass('inactive')
        $('#' + lang).addClass('active')
    };

    var chooseCategory = (cat) => {
        $('#categori').val(cat)
    };

    var next = () => {
        $('#submit').click();
    };
</script>