<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<c:if test="${param.error ne null}" >
<div class="row">
    <div class="col-lg-12">
        <div class="alert alert-danger" role="alert">
            <p>Enw defnyddiwr neu cyfrinair anghywir. Ceisiwch eto.</p>
        </div>
    </div>
</div>
</c:if>

<div class="row">
    <div class="col-lg-6">
        <div class="jumbotron">
            <form class="form-signin" action="<c:url value='/login' />" method="POST">
                <h2 class="form-signin-heading">Logiwch i mewn</h2>
                <fieldset class="form-group">
                    <label for="username">Enw Defnyddiwr</label>
                    <input type="username" id="username" name="username" class="form-control" required autofocus />
                </fieldset>
                <fieldset class="form-group">
                    <label for="password">Cyfrinair</label>
                   <input type="password" id="password"  name="password" class="form-control" required />
                </fieldset>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <fieldset class="form-group">
                    <input class="btn btn-primary" type="submit" value="Cofnodi mewn" />
                </fieldset>
            </form>

        </div>
    </div>
    <div class="col-lg-6">
        <div class="jumbotron">
            <form:form action="${pageContext.request.contextPath}/mewngofnodi/cofrestru" commandName="registrationDetails" method="POST">
                <h2>Cofrestrwch</h2>
                <c:if test="${not empty registrationError}">
                    <c:choose>
                        <c:when test="${registrationError == 'USER_ALREADY_EXISTS'}">
                        <div class="alert alert-danger" role="alert">
                            Cofrestrwyd yr enw defnyddiwr yma yn barod. Ceisiwch eto.
                        </div>
                        </c:when>
                        <c:when test="${registrationError == 'UNMATCHED_PASSWORDS'}">
                        <div class="alert alert-danger" role="alert">
                            Nid yw'r cyfrineiriau yn cyfateb. Ceisiwch eto.
                        </div>
                        </c:when>
                        <c:when test="${registrationError == 'USER_TOO_LONG'}">
                        <div class="alert alert-danger" role="alert">
                            Mae'r enw defnyddwir yn rhy hir. Ceisiwch eto.
                        </div>
                        </c:when>
                        <c:when test="${registrationError == 'USER_TOO_SHORT'}">
                        <div class="alert alert-danger" role="alert">
                            Mae'r enw defnyddwir yn rhy fyr. Ceisiwch eto.
                        </div>
                        </c:when>
                        <c:when test="${registrationError == 'PASS_TOO_SHORT'}">
                        <div class="alert alert-danger" role="alert">
                            Mae'r cyfrinair yn rhy fyr. Ceisiwch eto.
                        </div>
                        </c:when>
                    </c:choose>
                </c:if>
                <fieldset class="form-group">
                    <form:label path="username">Enw Defnyddiwr</form:label>
                    <form:input path="username" class="form-control" />
                    <form:errors path="username" cssStyle="color: red;"/>
                </fieldset>
                <fieldset class="form-group">
                    <form:label path="password">Cyfrinair</form:label>
                    <form:input path="password" type="password" class="form-control" />
                    <form:errors path="password" cssStyle="color: red;"/>
                </fieldset>
                <fieldset class="form-group">
                    <form:label path="passwordSecondTimeEntered">Cyfrinair Ail-waith</form:label>
                    <form:input path="passwordSecondTimeEntered" type="password" class="form-control" />
                    <form:errors path="passwordSecondTimeEntered" cssStyle="color: red;"/>
                </fieldset>

                <fieldset class="form-group">
                    <input type="submit" value="Cyflwyno" class="btn btn-primary" />
                </fieldset>
            </form:form>
        </div>
    </div>
</div>