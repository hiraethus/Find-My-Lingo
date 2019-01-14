<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<div id="service-images" class="d-flex flex-wrap"></div>
<form:form id="form1" action="/image/upload" method="POST" enctype="multipart/form-data">
    <input name="serviceId" style="display: none" value="${aService.id}" type="text" />
    <table>
        <tr>
            <td><input type="file" name="file" onchange="uploadImg('form1')" /></td>
        </tr>
    </table>
</form:form>

<form:form action="${flowExecutionUrl}" modelAttribute="aService" method='POST'>
    <div class="form-group">
        <form:label path="disgrifiad"><spring:message code="form.description" /></form:label>
        <form:errors path="disgrifiad" element="div" cssClass="alert alert-warning"/>
        <form:textarea path="disgrifiad" class="form-control"/>
    </div>
    <input style="display: none" id="submit_next" type="submit" name="_eventId_next" />
    <input style="display: none" id="submit_prev" type="submit" name="_eventId_previous" />
</form:form>

<nav style="padding-top: 1em" aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li class="page-item">
      <a id="prev_pg" class="page-link" href="#">Previous</a>
    </li>
    <li class="page-item">
      <a id="next_pg" class="page-link" href="#">Finish</a>
    </li>
  </ul>
</nav>

<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>

<script type="text/javascript">
uploadImg = (formId) => {
    var formElement = document.getElementById("form1")
    var request = new XMLHttpRequest()
    request.open("POST", "/uploadServiceImg")
    request.onload = (e) => {
        serviceId = document.getElementsByName("serviceId")[0].value
        getServiceImgs(serviceId)
    }
    request.send(new FormData(formElement))
}

getServiceImgs = (serviceId) => {
    csrfToken = document.getElementsByName('_csrf')[0].getAttribute('content')
    csrfHeader = document.getElementsByName('_csrf_header')[0].getAttribute('content')

    request = new XMLHttpRequest()

    request.open("GET", "/getServiceImgs/"+serviceId)
    request.setRequestHeader(csrfHeader, csrfToken)
    request.onload = (e) => {
        console.log(request.responseText)
        arrayOfImgs = JSON.parse(request.responseText)

        imgsDiv = document.getElementById("service-images")
        while (imgsDiv.firstChild) {
            imgsDiv.removeChild(imgsDiv.firstChild);
        }

        arrayOfImgs.forEach((imgUrl) => {
            imgsDiv.appendChild(createImgCard(imgUrl))
        })
    }

    request.send()
}

createImgCard = (imgUrl) => {
    cardTemplate = document.querySelector("template")
    card = document.importNode(cardTemplate.content, true)
    card.querySelector("img").setAttribute("src", "/" + imgUrl)
    card.querySelector('a').onclick = () => deleteImg("/" + imgUrl)

    return card
}

deleteImg = (imgUrl) => {
    console.log('Deleting img at ' + imgUrl)
    csrfHeader = document.getElementsByName('_csrf_header')[0].getAttribute('content')


    request = new XMLHttpRequest()
    request.open("POST", "/removeServiceImg")
    request.setRequestHeader(csrfHeader, '${_csrf.token}')
    request.onload = (e) => {
        serviceId = document.getElementsByName("serviceId")[0].value
        getServiceImgs(serviceId)
    }

    formData = new FormData()
    formData.append('imgUrl', imgUrl)
    formData.append('_csrf', '${_csrf.token}')

    request.send(formData)
}

window.onload = () => {
    serviceId = document.getElementsByName("serviceId")[0].value
    getServiceImgs(serviceId)

    document.getElementById("next_pg").onclick = () => {
        document.getElementById("submit_next").click()
    }

    document.getElementById("prev_pg").onclick = () => {
        document.getElementById("submit_prev").click()
    }
}
</script>

<template>
    <div class="card img-card">
      <img class="card-img-top" src="...">
      <a href="#" class="btn btn-danger card-delete-btn">Delete</a>
    </div>
</template>
