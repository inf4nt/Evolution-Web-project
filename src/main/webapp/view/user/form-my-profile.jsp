<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Profile</title>
</head>
<body>

<%@include file="../index/header.jsp" %>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <jsp:forward page="../admin/admin-form-profile.jsp"/>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_USER')">

    <c:if test="${user.getId() != authUser.getId()}">
        <c:redirect url="/user/id/${user.getId()}" />
    </c:if>
    <div class="col-md-9">
        <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 col-lg-offset-3">
                <form method="post" id="form" action="/user/edit/${user.getId()}">
                    <label id="email_error" class="control-label" for="login">Email</label>
                    <div id="div_email"  class="form-group has-feedback">
                        <input id="login" type="text" onblur="loginValid('login')" value="${user.getLogin()}" name="login" class="form-control text-center"/>
                        <span id="span_email_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                        <span id="span_email_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                    </div>

                    <label id="password_error" class="control-label" for="password">Password</label>
                    <div id="div_password"  class="form-group has-feedback">
                        <input onblur="firstPasswordValid('password')" id="password" type="password" value="${user.getPassword()}" name="password" class="form-control text-center"/>
                        <span id="span_password_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                        <span id="span_password_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                    </div>

                    <label id="password_confirm_error" class="control-label" for="confirmPassword">Confirm password</label>
                    <div id="div_password_confirm"  class="form-group has-feedback">
                        <input onblur="passwordValid('password', 'confirmPassword')" id="confirmPassword" type="password" value="${user.getPassword()}" name="passwordConfirm" class="form-control text-center"/>
                        <span id="span_password_confirm_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                        <span id="span_password_confirm_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                        <a id ="hrefShowHidePassword" onclick="showHidePassword()" href="#">Show password and confirm password</a>
                    </div>

                    <label id="first_name_error" class="control-label" for="firstName">First name</label>
                    <div id="div_first_name"  class="form-group has-feedback">
                        <input onblur="firstNameValid('firstName')" id="firstName" type="text" value="${user.getFirstName()}" name="firstName" class="form-control text-center"/>
                        <span id="span_first_name_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                        <span id="span_first_name_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                    </div>

                    <label id="last_name_error" class="control-label" for="lastName">Last name</label>
                    <div id="div_last_name"  class="form-group has-feedback">
                        <input onblur="lastNameValid('lastName')" id="lastName" type="text" value="${user.getLastName()}" name="lastName" class="form-control text-center"/>
                        <span id="span_last_name_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                        <span id="span_last_name_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                    </div>

                    <div class="form-group">
                        <label class="control-label">Secret question type</label>
                        <select name="sqtId" class="form-control">
                            <c:forEach var = "a" items = "${sqt}">
                                <option value = ${a.getId()}> ${a.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <label id="secret_question_error" class="control-label" for="secretQuestion">Secret question</label>
                    <div id="div_secret_question"  class="form-group has-feedback">
                        <input onblur="sqtValid('secretQuestion')" id="secretQuestion" type="text" value="${user.getSecretQuestion()}" name="secretQuestion" class="form-control text-center"/>
                        <span id="span_secret_question_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                        <span id="span_secret_question_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                    </div>

                    <fieldset disabled>
                        <div class="form-group">
                            <label for="disabledTextInput">Registration date</label>
                            <input type="text" id="disabledTextInput" class="form-control" placeholder="${user.getDateFormatRegistrationDate()}">
                        </div>
                    </fieldset>

                    <fieldset hidden>
                        <input value="USER" name="role" type="hidden"/>
                        <input value="${user.getRegistrationDate().getTime()}" name="registrationDate" type="hidden"/>
                    </fieldset>
                    <br/>
                    <div class="text-center">
                        <button form="form" type="submit" class="btn btn-success"
                                onclick="return validRegistration('login', 'password', 'confirmPassword', 'firstName', 'lastName', 'secretQuestion')"
                                style="width: 100%">
                            Edit <span class="glyphicon glyphicon-check"/>
                        </button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</sec:authorize>


</body>
</html>
