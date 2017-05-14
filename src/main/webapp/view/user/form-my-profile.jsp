
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Profile</title>
    <script src="<c:url value="/resources/js/pageJS.js" />"></script>
    <script src="<c:url value="/resources/js/validator.js" />"></script>
</head>
<body>

<%@include file="../index/header.jsp" %>

<div class="col-md-9">
<div class="row">
<div class="col-lg-8 col-md-6 col-sm-6 col-xs-6 col-lg-offset-2">
    <form id="profilePageForm" class="text text-center">

        <fieldset disabled>
            <div class="form-group">
                <label for="disabledTextInputId">Id</label>
                <input type="text" id="disabledTextInputId" class="form-control" placeholder="${user.id}">
            </div>
        </fieldset>

        <div id="div_email_registrationPageForm"  class="form-group has-feedback">
            <label class="control-label ">Email</label>
            <input value="${user.login}" autocomplete="off" type="text" onblur="loginValid('div_email_registrationPageForm')" name="login" class="form-control text-center"/>
            <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
            <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
        </div>

        <div id="div_first_name_registrationPageForm"  class="form-group has-feedback">
            <label class="control-label" for="firstName">First name</label>
            <input value="${user.firstName}" autocomplete="off"  onblur="firstNameValid('div_first_name_registrationPageForm')" id="firstName" type="text"  name="firstName" class="form-control text-center"/>
            <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
            <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
        </div>

        <div id="div_last_name_registrationPageForm"  class="form-group has-feedback">
            <label class="control-label">Last name</label>
            <input value="${user.lastName}" autocomplete="off"  onblur="lastNameValid('div_last_name_registrationPageForm')" type="text" name="lastName" class="form-control text-center"/>
            <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
            <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
        </div>

        <div id="div_password_registrationPageForm"  class="form-group has-feedback">
            <label class="control-label" >Password</label>
            <input value="${user.password}" autocomplete="off" onblur="firstPasswordValid('div_password_registrationPageForm')" type="password"  name="password" class="form-control text-center"/>
            <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
            <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
        </div>

        <div id="div_password_confirm_registrationPageForm"  class="form-group has-feedback">
            <label class="control-label" >Confirm password</label>
            <input value="${user.password}" autocomplete="off" onblur="confirmPassword('div_password_registrationPageForm', 'div_password_confirm_registrationPageForm')"  type="password" name="passwordConfirm" class="form-control text-center"/>
            <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
            <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
        </div>

        <a id ="hrefShowHidePassword_profilePageForm"
           onclick="showHidePassword('hrefShowHidePassword_profilePageForm',
            'div_password_registrationPageForm',
            'div_password_confirm_registrationPageForm')"
           href="#">Show password and confirm password</a>

        <br/> <br/>

        <fieldset disabled>
            <div class="form-group">
                <label for="disabledTextInputId">Secret question type</label>
                <input name="secretQuestionType" type="text" class="form-control" placeholder="${user.secretQuestionType.name}">
            </div>
        </fieldset>


        <fieldset disabled>
            <div class="form-group">
                <label for="disabledTextInputId">Secret question</label>
                <input name="secretQuestion" type="text" class="form-control" placeholder="${user.secretQuestion}">
            </div>
        </fieldset>

        <hr/>
        <div id="submit_profilePageForm" class="text-center">
            <button class="btn btn-success"
                    style="width: 100%">
                Save <span class="glyphicon glyphicon-check"/>
            </button>
        </div>
    </form>
</div>
</div>
</div>
</body>

<script>

    $(document).ready(function () {
        $("label").addClass("pull-left");

        $('#profilePageForm').on('submit', function () {
            if (validProfileForm('div_email_registrationPageForm',
                    'div_password_registrationPageForm',
                    'div_password_confirm_registrationPageForm',
                    'div_first_name_registrationPageForm',
                    'div_last_name_registrationPageForm') == false)
                return false;

            $("#submit_profilePageForm").hide();
            setTimeout(function () {
                $.ajax({
                    url: "/user/edit",
                    type: "POST",
                    data: $("#profilePageForm").serialize(),
                    success: function () {
                        $("#submit_profilePageForm").slideDown(1000);
                    },
                    error:function () {
                        $("#profilePageForm div input").val("");
                        $("#profilePageForm div span").hide();
                    }
                });
            }, 2000);
            return false;
        });

    })

</script>


</html>
























<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Profile</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--<%@include file="../index/header.jsp" %>--%>

<%--<sec:authorize access="hasRole('ROLE_ADMIN')">--%>
    <%--<jsp:forward page="../admin/admin-form-profile.jsp"/>--%>
<%--</sec:authorize>--%>

<%--<sec:authorize access="hasRole('ROLE_USER')">--%>

    <%--<c:if test="${user.getId() != authUser.getId()}">--%>
        <%--<c:redirect url="/user/id/${user.getId()}" />--%>
    <%--</c:if>--%>
    <%--<div class="col-md-9">--%>
        <%--<div class="row">--%>
            <%--<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 col-lg-offset-3">--%>
                <%--<form method="post" id="form" action="/user/edit/${user.getId()}">--%>
                    <%--<label id="email_error" class="control-label" for="login">Email</label>--%>
                    <%--<div id="div_email"  class="form-group has-feedback">--%>
                        <%--<input id="login" type="text" onblur="loginValid('login')" value="${user.getLogin()}" name="login" class="form-control text-center"/>--%>
                        <%--<span id="span_email_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                        <%--<span id="span_email_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                    <%--</div>--%>

                    <%--<label id="password_error" class="control-label" for="password">Password</label>--%>
                    <%--<div id="div_password"  class="form-group has-feedback">--%>
                        <%--<input onblur="firstPasswordValid('password')" id="password" type="password" value="${user.getPassword()}" name="password" class="form-control text-center"/>--%>
                        <%--<span id="span_password_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                        <%--<span id="span_password_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                    <%--</div>--%>

                    <%--<label id="password_confirm_error" class="control-label" for="confirmPassword">Confirm password</label>--%>
                    <%--<div id="div_password_confirm"  class="form-group has-feedback">--%>
                        <%--<input onblur="passwordValid('password', 'confirmPassword')" id="confirmPassword" type="password" value="${user.getPassword()}" name="passwordConfirm" class="form-control text-center"/>--%>
                        <%--<span id="span_password_confirm_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                        <%--<span id="span_password_confirm_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                        <%--<a id ="hrefShowHidePassword" onclick="showHidePassword()" href="#">Show password and confirm password</a>--%>
                    <%--</div>--%>

                    <%--<label id="first_name_error" class="control-label" for="firstName">First name</label>--%>
                    <%--<div id="div_first_name"  class="form-group has-feedback">--%>
                        <%--<input onblur="firstNameValid('firstName')" id="firstName" type="text" value="${user.getFirstName()}" name="firstName" class="form-control text-center"/>--%>
                        <%--<span id="span_first_name_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                        <%--<span id="span_first_name_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                    <%--</div>--%>

                    <%--<label id="last_name_error" class="control-label" for="lastName">Last name</label>--%>
                    <%--<div id="div_last_name"  class="form-group has-feedback">--%>
                        <%--<input onblur="lastNameValid('lastName')" id="lastName" type="text" value="${user.getLastName()}" name="lastName" class="form-control text-center"/>--%>
                        <%--<span id="span_last_name_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                        <%--<span id="span_last_name_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label">Secret question type</label>--%>
                        <%--<select name="sqtId" class="form-control">--%>
                            <%--<c:forEach var = "a" items = "${sqt}">--%>
                                <%--<option value = ${a.getId()}> ${a.getName()}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                    <%--</div>--%>

                    <%--<label id="secret_question_error" class="control-label" for="secretQuestion">Secret question</label>--%>
                    <%--<div id="div_secret_question"  class="form-group has-feedback">--%>
                        <%--<input onblur="sqtValid('secretQuestion')" id="secretQuestion" type="text" value="${user.getSecretQuestion()}" name="secretQuestion" class="form-control text-center"/>--%>
                        <%--<span id="span_secret_question_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                        <%--<span id="span_secret_question_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                    <%--</div>--%>

                    <%--<fieldset disabled>--%>
                        <%--<div class="form-group">--%>
                            <%--<label for="disabledTextInput">Registration date</label>--%>
                            <%--<input type="text" id="disabledTextInput" class="form-control" placeholder="${user.getDateFormatRegistrationDate()}">--%>
                        <%--</div>--%>
                    <%--</fieldset>--%>

                    <%--<fieldset hidden>--%>
                        <%--<input value="USER" name="role" type="hidden"/>--%>
                        <%--<input value="${user.getRegistrationDate().getTime()}" name="registrationDate" type="hidden"/>--%>
                    <%--</fieldset>--%>
                    <%--<br/>--%>
                    <%--<div class="text-center">--%>
                        <%--<button form="form" type="submit" class="btn btn-success"--%>
                                <%--onclick="return validRegistration('login', 'password', 'confirmPassword', 'firstName', 'lastName', 'secretQuestion')"--%>
                                <%--style="width: 100%">--%>
                            <%--Edit <span class="glyphicon glyphicon-check"/>--%>
                        <%--</button>--%>
                    <%--</div>--%>
                <%--</form>--%>
            <%--</div>--%>

        <%--</div>--%>
    <%--</div>--%>
<%--</sec:authorize>--%>


<%--</body>--%>
<%--</html>--%>
