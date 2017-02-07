<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>


<h1><spring:message code="users.profile" arguments="${pageContext.request.remoteUser}" /></h1>

<h2><spring:message code="menu.my.services" /></h2>
<tiles:insertAttribute name="rhestrGwasanaethau" />