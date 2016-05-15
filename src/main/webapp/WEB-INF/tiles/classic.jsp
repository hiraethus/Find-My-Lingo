<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
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
        <h3><tiles:getAsString name="heading"/></h3>
        <tiles:insertAttribute name="body" />
  </div>


  </body>
</html>