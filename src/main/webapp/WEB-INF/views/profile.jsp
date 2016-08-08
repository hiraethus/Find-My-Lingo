<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page isELIgnored="false" %>


<h1>Proffil <c:out value="${pageContext.request.remoteUser}"/></h1>

<h2>Fy Ngwasanaethau</h2>
<tiles:insertAttribute name="rhestrGwasanaethau" />