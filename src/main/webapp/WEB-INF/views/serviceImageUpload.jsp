<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>


<!-- image1 -->
<form:form id="form1" action="/image/upload" method="POST" enctype="multipart/form-data">
    <img id="img1" src="#"></div>
    <input name="serviceId" style="display: none" value="42" type="number" />
    <table>
        <tr>
            <td><input type="file" name="file" /></td>
            <td><a href="#" onclick="uploadImg('form1')">Upload Image</a></td>
        </tr>
    </table>
</form:form>

<nav style="padding-top: 1em" aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li class="page-item">
      <a class="page-link" href="${flowExecutionUrl}&_eventId=previous">Previous</a>
    </li>
    <li class="page-item">
      <a class="page-link" href="#" onclick="next()">Next</a>
    </li>
  </ul>
</nav>

<script type="text/javascript">
uploadImg = (formId) => {
    var formElement = document.querySelector("form")
    var request = new XMLHttpRequest()
    request.open("POST", "/uploadImg")
    request.send(new FormData(formElement))
}
</script>