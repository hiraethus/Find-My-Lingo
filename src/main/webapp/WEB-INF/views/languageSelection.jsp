<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<div class="row">
    <h3>1. Choose service language</h3>
</div>
<div class="row">
  <div class="col-sm-4">
  <a href="#" onclick="chooseLang('welsh')">
      <div class="card">
        <div class="card-body">
          <div class="card-text">Welsh</div>

        </div>
      </div>
  </a>
  </div>

    <div class="col-sm-4">
    <a href="#" onclick="chooseLang('russian')">
        <div class="card">
          <div class="card-body">
            <div class="card-text">Russian</div>

          </div>
        </div>
    </a>
    </div>

        <div class="col-sm-4">
        <a href="#" onclick="chooseLang('french')">
            <div class="card">
              <div class="card-body">
                <div class="card-text">French</div>

              </div>
            </div>
        </a>
        </div>
</div>

<form:form style="display: none;" action="${flowExecutionUrl}" method="post">
  <input type="text" name="language" id="language" />
  <input id="submit" type="submit" name="_eventId_selectLang" value="Welsh" />
</form:form>

<script type="text/javascript">
    var chooseLang = (lang) => {
        $('#language').val(lang);
        $('#submit').click();
    };
</script>