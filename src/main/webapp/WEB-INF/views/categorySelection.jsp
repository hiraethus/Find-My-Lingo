<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<div class="row">
<c:forEach var="category" items="${categories}">
<div class="col-sm-3">
    <a href="#" onclick="chooseCategory('${category.id}', '${category.categori}', '${category.categoriImg}')">
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
    <li class="page-item">
      <a class="page-link" href="${flowExecutionUrl}&_eventId=previous">Previous</a>
    </li>
    <li class="page-item">
      <a class="page-link" href="${flowExecutionUrl}&_eventId=next">Next</a>
    </li>
  </ul>
</nav>

<form:form style="display: none;" action="${flowExecutionUrl}" method="post" modelAttribute="searchCriteria">
  <form:input path="categoryId" id="categoryId" />
  <form:input path="category" id="category" />
  <form:input path="categoryImg" id="categoryImg" />
  <input id="submit" type="submit" name="_eventId_selectCategory" />
</form:form>

<script type="text/javascript">
    var chooseCategory = (catId, cat, catImg) => {
        $('#categoryId').val(catId);
        $('#category').val(cat);
        $('#categoryImg').val(catImg);
        $('#submit').click();
    };
</script>