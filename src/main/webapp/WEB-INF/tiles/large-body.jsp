<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
  <head>
    <title><tiles:getAsString name="title"/></title>
    <meta charset="UTF-8">

    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/logo.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/sticky-footer.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/categories.css" rel="stylesheet" />
  </head>
  <body>


  <div class="container">
        <tiles:insertAttribute name="menu" />
        <tiles:insertAttribute name="body" />
  </div>

  <footer class="footer">
        <tiles:insertAttribute name="footer" />
  </footer>


  </body>
</html>