<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<div class="row">
    <h3><tiles:getAsString name="stepName"/></h3>
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
        <c:if test="${not empty searchCriteria.categoryImg}">
          <img class="card-img-top" src="/images/${searchCriteria.categoryImg}">
        </c:if>
      </li>
      <li class="list-group-item"><em>Location</em> ${searchCriteria.city}</li>
    </ul>
    </div>

    <div class="col-lg-8">
        <tiles:insertAttribute name="body" />
    </div>
</div>