<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<style>
.loader {
 border-top: 16px solid blue;
 border-bottom: 16px solid blue;
}

@media (max-width:961px)  {
    div.twitter {
        display: none;
    }
}
</style>
<c:if test="${param.allgofnodi ne null}" >
<div class="row">
    <div class="col-lg-12">
        <div class="alert alert-success" role="alert">

            <p><spring:message code="logout.successful" /></p>
        </div>
    </div>
</div>
</c:if>
<c:if test="${not empty registrationSuccessful}" >
<div class="row">
    <div class="col-lg-12">
        <div class="alert alert-success" role="alert">
            ${registrationSuccessful}
        </div>
    </div>
</div>
</c:if>

<div class="container">

    <div class="row">
        <div class="col-md-3 twitter">
            <a class="twitter-timeline" data-theme="light" data-link-color="#E81C4F" href="https://twitter.com/gwasanaethaucym?ref_src=twsrc%5Etfw">Tweets by gwasanaethaucym</a> <script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>
        </div>
        <div class="col-md-9" style="overflow: hidden">
            <h1><spring:message code="service.wales" /></h1>
            <p style="padding-top: 1em;"><spring:message code="welcome.add.business" /></p>
                <div class="form-inline" style="width: 100%">
                    <input name="searchTerm" class="form-control" type="text" style="width: calc(80% - 7em);"
                        placeholder="<spring:message code='search.name.or.city' />"
                        required ic-get-from="/search" ic-trigger-on="keyup changed"
                         ic-trigger-delay="500ms" ic-target="#result-table" />
                </div>
                <div id="result-table"></div>
        </div>
    </div>
</div>

