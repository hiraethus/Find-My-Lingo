<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <div class="navbar-brand">Gwasanaethau Cymraeg</div>
    </div>
    <div id="navbar" class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li><a href='<c:url value="/gwasanaethau/" />'>Adref</a></li>
        <li><a href="#amdanom">Amdanom</a></li>
        <li><a href="#cysylltwch">Cysylltwch</a></li>
      </ul>
    </div><!--/.nav-collapse -->
  </div>
</nav>