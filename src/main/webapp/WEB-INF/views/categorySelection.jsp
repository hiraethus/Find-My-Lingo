<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<nav style="padding-top: 1em" aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li class="page-item">
      <a class="page-link" href="${flowExecutionUrl}&_eventId=previous" tabindex="-1">Previous</a>
    </li>
    <li class="page-item">
      <a class="page-link" href="${flowExecutionUrl}&_eventId=next">Next</a>
    </li>
  </ul>
</nav>
<div class="row">
    <h3>2. Choose a category</h3>
</div>
<div class="row">
  <div class="col-sm-4">
  <a href="#" onclick="chooseCategory('restaurant')">
      <div class="card">
        <div class="card-body">
          <div class="card-text">Restaurant</div>
        </div>
      </div>
  </a>
  </div>
  <div class="col-sm-4">
  <a href="#" onclick="chooseCategory('coffeeshop')">
      <div class="card">
        <div class="card-body">
          <div class="card-text">Coffee shop</div>
        </div>
      </div>
  </a>
  </div>
  <div class="col-sm-4">
  <a href="#" onclick="chooseCategory('library')">
      <div class="card">
        <div class="card-body">
          <div class="card-text">Library</div>
        </div>
      </div>
  </a>
  </div>
</div>

<div class="row">
<ul class="list-group">
  <li class="list-group-item"><em>Language</em> ${searchCriteria.language}</li>
  <li class="list-group-item"><em>Category</em> ${searchCriteria.category}</li>
  <li class="list-group-item"><em>Location</em> ${searchCriteria.city}</li>
</ul>
</div>

<form:form style="display: none;" action="${flowExecutionUrl}" method="post" modelAttribute="searchCriteria">
  <form:input path="category" id="category" />
  <input id="submit" type="submit" name="_eventId_selectCategory" />
</form:form>

<script type="text/javascript">
    var chooseCategory = (lang) => {
        $('#category').val(lang);
        $('#submit').click();
    };
</script>