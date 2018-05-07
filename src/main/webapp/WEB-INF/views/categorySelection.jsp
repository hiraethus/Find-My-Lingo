<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<div class="row">
    <h3>1. Choose a category</h3>
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

<form:form style="display: none;" action="${flowExecutionUrl}" method="post">
  <input type="text" name="category" id="category" />
  <input id="submit" type="submit" name="_eventId_selectCategory" />
</form:form>

<script type="text/javascript">
    var chooseCategory = (lang) => {
        $('#category').val(lang);
        $('#submit').click();
    };
</script>