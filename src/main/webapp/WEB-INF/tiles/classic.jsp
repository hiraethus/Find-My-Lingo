<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
  <head>
    <title><tiles:getAsString name="title"/></title>

    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding-top: 70px;
        }

        h3 {
            padding-bottom: 30px;
        }
    </style>
  </head>
  <body>


  <tiles:insertAttribute name="menu" />
  <div class="container">
        <div class="page-header">
            <h1><c:out value="${heading}" /></h1>
        </div>
        <tiles:insertAttribute name="body" />
  </div>


  </body>
</html>