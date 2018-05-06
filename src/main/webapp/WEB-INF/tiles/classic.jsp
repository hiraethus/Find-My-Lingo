<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
  <head>
    <title><tiles:getAsString name="title"/></title>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="/images/favicon.ico" />
    <link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script src="webjars/intercooler-js/intercooler.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/logo.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/sticky-footer.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/categories.css" rel="stylesheet" />
  </head>
  <body>
  <tilesx:useAttribute name="heading" ignore="true" />

  <div class="container">
        <tiles:insertAttribute name="menu" />
        <div class="page-header">
            <h1>${heading}</h1>
        </div>
        <tiles:insertAttribute name="body" />
  </div>

  <footer class="footer">
        <tiles:insertAttribute name="footer" />
  </footer>


  </body>
</html>