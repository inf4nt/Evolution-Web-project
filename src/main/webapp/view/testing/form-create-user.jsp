

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Registration</title>
    <script src="<c:url value="/resources/js/validators.js" />"></script>
    <script src="<c:url value="/resources/js/pageJS.js" />"></script>
</head>
<body>

<%@include file="../index/header.jsp" %>

<div class="col-md-9">
    <div class="row">
        <div class="col-lg-12">
            <div class="col-lg-8 col-lg-offset-2">
                <h1 class="text-center">Registration</h1>


                <form id="registrationPageForm" class="text text-center">

                    <div id="div_email_registrationPageForm"  class="form-group has-feedback">
                        <label class="control-label ">Email</label>
                        <input autocomplete="off" type="text" onblur="loginValid('div_email_registrationPageForm')" name="login" class="form-control text-center"/>
                        <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                        <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
                    </div>

                    <div id="div_password_registrationPageForm"  class="form-group has-feedback">
                        <label class="control-label" >Password</label>
                        <input autocomplete="off"  onblur="firstPasswordValid('div_password_registrationPageForm')" type="password"  name="password" class="form-control text-center"/>
                        <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                        <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
                    </div>

                    <div id="div_password_confirm_registrationPageForm"  class="form-group has-feedback">
                        <label class="control-label" >Confirm password</label>
                        <input autocomplete="off"  onblur="confirmPassword('div_password_registrationPageForm', 'div_password_confirm_registrationPageForm')"  type="password" name="passwordConfirm" class="form-control text-center"/>
                        <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                        <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>

                    </div>
                    <a id ="hrefShowHidePassword_registrationPageForm"
                       onclick="showHidePassword('hrefShowHidePassword_registrationPageForm',
                        'div_password_registrationPageForm',
                        'div_password_confirm_registrationPageForm')"
                       href="#">Show password and confirm password</a>

                    <br/><br/>

                    <div id="div_first_name_registrationPageForm"  class="form-group has-feedback">
                        <label class="control-label" for="firstName">First name</label>
                        <input autocomplete="off"  onblur="firstNameValid('div_first_name_registrationPageForm')" id="firstName" type="text"  name="firstName" class="form-control text-center"/>
                        <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                        <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
                    </div>

                    <div id="div_last_name_registrationPageForm"  class="form-group has-feedback">
                        <label class="control-label">Last name</label>
                        <input autocomplete="off"  onblur="lastNameValid('div_last_name_registrationPageForm')" type="text" name="lastName" class="form-control text-center"/>
                        <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                        <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
                    </div>

                    <div class="form-group">
                        <label class="control-label">Secret question type</label>
                        <select name="sqtId" class="form-control">
                            <option value ="1"> Your house number</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="control-label">Role</label>
                        <select name="role" class="form-control">
                            <option value="ADMIN">Admin</option>
                            <option value="USER">User</option>
                        </select>
                    </div>

                    <div id="div_secret_question_registrationPageForm"  class="form-group has-feedback">
                        <label class="control-label" >Secret question</label>
                        <input autocomplete="off"  onblur="sqValid('div_secret_question_registrationPageForm')" name="secretQuestion" type="text" class="form-control text-center"/>
                        <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                        <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
                    </div>

                    <br/>
                    <div id="submit_registrationPageForm" class="text-center">
                        <button class="btn btn-success"
                                style="width: 100%">
                            Registration <span class="glyphicon glyphicon-check"/>
                        </button>
                    </div>
                </form>


            </div>
        </div>
    </div>
</div>
</body>

<script>

    $(document).ready(function () {
        // REGISTRATION
        $('#registrationPageForm').on('submit', function () {
            var check = validRegistrationForm('div_email_registrationPageForm',
                'div_password_registrationPageForm',
                'div_password_confirm_registrationPageForm',
                'div_first_name_registrationPageForm',
                'div_last_name_registrationPageForm', 'div_secret_question_registrationPageForm');
            if (check == false)
                return false;

            var sqtId = $("#registrationPageForm select[name=sqtId]").val();
            var sq = $("#registrationPageForm input[name=secretQuestion]").val();

            var login = $("#registrationPageForm input[name=login]").val();
            var password = $("#registrationPageForm input[name=password]").val();
            var firstName = $("#registrationPageForm input[name=firstName]").val();
            var lastName = $("#registrationPageForm input[name=lastName]").val();
            var role = $("#registrationPageForm select[name=role]").val();

            var json = JSON.stringify({"secretQuestionType":{"id":sqtId},
                "secretQuestion":sq,
                "login":login, "password":password,
                "lastName":lastName, "firstName":firstName});

            $("#submit_registrationPageForm").hide();
            setTimeout(function () {
                $.ajax({
                    url:"/user/?role="+role,
                    type: "POST",
                    data: json,
                    contentType: "application/json; charset=UTF-8",
                    success: function (data) {
                        if (data == 'Success'){
                            $("#registrationPageForm div input").val("");
                            alert(data);
                        } else {
                            $("#div_email_registrationPageForm input").val("");
                            alert(data);
                        }
                        $("#registrationPageForm div span").hide();
                        $("#submit_registrationPageForm").slideDown(1000);
                    },
                    error: function () {
                        alert("Error")
                        $("#div_email_registrationPageForm input").val("");
                        $("#div_email_registrationPageForm span").hide();
                        $("#submit_registrationPageForm").slideDown(1000);
                    }
                });
            }, 2000);
            return false;
        });
    })

</script>



</html>


















<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>--%>

<%--<html>--%>
<%--<head>--%>
    <%--<title>Registration</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--<%@include file="../index/header.jsp" %>--%>

<%--<div class="col-md-9">--%>
    <%--<div class="row">--%>
        <%--<div class="col-lg-6 col-lg-offset-3">--%>
            <%--<div class="col-lg-6 col-lg-offset-5">--%>
                <%--<h1 class="text-center">Registration</h1>--%>
                <%--<p class="text-center text-danger">${info}</p>--%>

                    <%--<form method="post" id="form" action="/create-user" class="text text-center">--%>

                        <%--<label id="email_error" class="control-label" for="login">Email</label>--%>
                        <%--<div id="div_email"  class="form-group has-feedback">--%>
                            <%--<input id="login" type="text" onblur="loginValid('login')" name="login" class="form-control text-center"/>--%>
                            <%--<span id="span_email_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                            <%--<span id="span_email_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                        <%--</div>--%>

                        <%--<label id="password_error" class="control-label" for="password">Password</label>--%>
                        <%--<div id="div_password"  class="form-group has-feedback">--%>
                            <%--<input onblur="firstPasswordValid('password')" id="password" type="password" name="password" class="form-control text-center"/>--%>
                            <%--<span id="span_password_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                            <%--<span id="span_password_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                        <%--</div>--%>

                        <%--<label id="password_confirm_error" class="control-label" for="confirmPassword">Confirm password</label>--%>
                        <%--<div id="div_password_confirm"  class="form-group has-feedback">--%>
                            <%--<input onblur="passwordValid('password', 'confirmPassword')" id="confirmPassword" type="password" name="passwordConfirm" class="form-control text-center"/>--%>
                            <%--<span id="span_password_confirm_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                            <%--<span id="span_password_confirm_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                            <%--<a id ="hrefShowHidePassword" onclick="showHidePassword()" href="#">Show password and confirm password</a>--%>
                        <%--</div>--%>

                        <%--<label id="first_name_error" class="control-label" for="firstName">First name</label>--%>
                        <%--<div id="div_first_name"  class="form-group has-feedback">--%>
                            <%--<input onblur="firstNameValid('firstName')" id="firstName" type="text" name="firstName" class="form-control text-center"/>--%>
                            <%--<span id="span_first_name_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                            <%--<span id="span_first_name_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                        <%--</div>--%>

                        <%--<label id="last_name_error" class="control-label" for="lastName">Last name</label>--%>
                        <%--<div id="div_last_name"  class="form-group has-feedback">--%>
                            <%--<input onblur="lastNameValid('lastName')" id="lastName" type="text"  name="lastName" class="form-control text-center"/>--%>
                            <%--<span id="span_last_name_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                            <%--<span id="span_last_name_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                        <%--</div>--%>


                        <%--<div class="form-group">--%>
                            <%--<label class="control-label">Role</label>--%>
                            <%--<select name="role" class="form-control">--%>
                                <%--<option value="ADMIN">Admin</option>--%>
                                <%--<option value="USER">User</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>

                        <%--<div class="form-group">--%>
                            <%--<label class="control-label">Secret question type</label>--%>
                            <%--<select name="sqtId" class="form-control">--%>
                                <%--<option value = ${a.getId()}> ${a.getName()}</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>

                        <%--<label id="secret_question_error" class="control-label" for="secretQuestion">Secret question</label>--%>
                        <%--<div id="div_secret_question"  class="form-group has-feedback">--%>
                            <%--<input onblur="sqtValid('secretQuestion')" id="secretQuestion" type="text"  name="secretQuestion" class="form-control text-center"/>--%>
                            <%--<span id="span_secret_question_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                            <%--<span id="span_secret_question_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>--%>
                        <%--</div>--%>

                        <%--<br/>--%>
                        <%--<div class="text-center">--%>
                            <%--<button form="form" type="submit" class="btn btn-success"--%>
                                    <%--onclick="return validRegistration('login', 'password', 'confirmPassword', 'firstName', 'lastName', 'secretQuestion')"--%>
                                    <%--style="width: 100%">--%>
                                <%--Registration <span class="glyphicon glyphicon-check"/>--%>
                            <%--</button>--%>
                        <%--</div>--%>

                    <%--</form>--%>

            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--</body>--%>

<%--</html>--%>
