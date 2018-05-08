<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<nav style="padding-top: 1em" aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li class="page-item">
      <a class="page-link" href="${flowExecutionUrl}&_eventId=previous" tabindex="-1">Previous</a>
    </li>
    <li class="page-item disabled">
      <a class="page-link" href="${flowExecutionUrl}&_eventId=next">Next</a>
    </li>
  </ul>
</nav>
<div class="row">
    <h3>3. Type your location</h3>
</div>
<div class="row">

<div class="text-centered">
<form:form class="form-inline" action="${flowExecutionUrl}" method="post">
  <div class="form-group">
    <label for="location">Location</label>
    <input type="text" id="location" class="form-control mx-sm-3" />
    <input class="btn btn-primary" type="submit" name="_eventId_selectLocation" value="Go!" />
  </div>
</form:form>
</div>
</div>