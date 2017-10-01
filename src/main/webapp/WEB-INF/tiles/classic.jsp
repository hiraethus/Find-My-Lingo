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
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="http://cdn.intercoolerjs.org/intercooler-1.1.2.min.js"></script>
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