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
        <h1>Welcome to <span class><em>Find My Lingo</em></span><sup>alpha</sup></h1>
    </div>
    <div class="row">
        <div class="col-lg-5" style="overflow: hidden">
            <img style="transform: skewY(1deg);" src="https://cdn.pixabay.com/photo/2015/01/15/16/17/hands-600497_960_720.jpg" class="rounded img-thumbnail">
        </div>
        <div class="col-lg-7" style="overflow: hidden">
            <p>
                Find My Lingo<sup>alpha</sup> is a new website for expats around the World that want to find and share
                services in their native language.
            </p>
            <p>
                If you've recently moved to a new country or are missing speaking in your own language or if you want to
                find a restaurant or coffee shop where you can practise your language of choice, then use Find My Lingo.
            </p>
            <p>
                If you own a service, feel free to add your service to the website to find new customers who will
                appreciate your language and cultural skills.
            </p>
            <p>To get started, please <a href="/find">find a service</a> and <a href="#">create an account</a>. Thanks!</p>
        </div>

    </div>
</div>

