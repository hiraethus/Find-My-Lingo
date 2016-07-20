<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
  <head>
    <title><tiles:getAsString name="title"/></title>
    <meta charset="UTF-8">

    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        html {
          position: relative;
          min-height: 100%;
        }
        body {
          /* Margin bottom by footer height */
          padding-top: 70px;
          margin-bottom: 60px;
        }
        .footer {
          position: absolute;
          bottom: 0;
          width: 100%;
          /* Set the fixed height of the footer here */
          height: 60px;
          background-color: #f5f5f5;
        }
    </style>
  </head>
  <body>


  <tiles:insertAttribute name="menu" />
  <div class="container">
        <div class="jumbotron">

            <div class="row">
                <div class="col-md-4">
                    <div class="thumbnail">
                        <img src="https://placeholdit.imgix.net/~text?txtsize=33&txt=480%C3%97480&w=480&h=480" />
                    </div>
                </div>
                <div class="col-md-8">
                            <h1>Gwasanaethau Cymraeg</h1>

                <p>
                    Prosiect yw Gwasanaethau Cymraeg i alluogi siaradwyr yr iaith Gymraeg i ddarganfod gwasanaethau
                    cyfrwng cymraeg yn eu hardal.
                    Os ydych chi yn perchen ar caffi, bwyti, yn gweithio i lywodraeth lleol neu yn darparu gwasanaeth preifat
                    trwy gyfrwng y Gymraeg, byswn ni yn eich croesawi i gofrestru eich hun gyda'r ffurflen uchod.
                </p>
                </div>
            </div>
        </div>

        <tiles:insertAttribute name="body" />
  </div>

  <footer class="footer">
        <tiles:insertAttribute name="footer" />
  </footer>


  </body>
</html>