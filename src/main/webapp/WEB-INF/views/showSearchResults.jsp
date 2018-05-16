<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<div class="container">

    <div class="row">
        <div class="col-md-9" style="overflow: hidden">
            <h1>Results</h1>
            <tiles:insertAttribute name="serviceList" />
        </div>
    </div>
</div>

