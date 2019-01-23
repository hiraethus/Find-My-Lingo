<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<div class="row">
    <div class="col-sm-2">
        <h5><tiles:getAsString name="stepName"/></h5>
    </div>

    <div class="col-lg-8">
        <tiles:insertAttribute name="body" />
    </div>
</div>