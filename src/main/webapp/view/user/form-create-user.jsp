<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Create user form</title>
</head>
<body>

<%@include file="../index/header.jsp" %>

<div class="col-md-9">
    <div class="row">
        <sec:authorize access="isAuthenticated()">
            <div class="col-lg-6 col-lg-offset-3">
        </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
            <div class="col-lg-6 col-lg-offset-5">
                </sec:authorize>
                <h1 class="text-center">Registration</h1>
                <p class="text-center text-danger">${info}</p>

                    <form method="post" id="form" action="/create-user" class="text text-center">

                        <p id="email_error" class="text-danger"></p>
                        <div id="div_email"  class="form-group has-feedback">
                            <input id="login" type="text" onblur="loginValid()" placeholder="email" name="login" class="form-control text-center"/>
                            <span id="span_email_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                            <span id="span_email_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                        </div>

                        <p id="password_error" class="text text-danger"></p>
                        <div id="div_password"  class="form-group has-feedback">
                            <input onblur="passwordValid()" id="password" type="password" placeholder="password" name="password" class="form-control text-center"/>
                            <span id="span_password_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                            <span id="span_password_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                        </div>


                        <p id="password_confirm_error" class="text text-danger"></p>
                        <div id="div_password_confirm"  class="form-group has-feedback">
                            <input onblur="passwordValid()" id="confirmPassword" type="password" placeholder="confirm password" name="passwordConfirm" class="form-control text-center"/>
                            <span id="span_password_confirm_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                            <span id="span_password_confirm_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                        </div>


                        <p id="first_name_error" class="text text-danger"></p>
                        <div id="div_first_name"  class="form-group has-feedback">
                            <input onblur="firstNameValid()" id="firstName" type="text" placeholder="first name" name="firstName" class="form-control text-center"/>
                            <span id="span_first_name_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                            <span id="span_first_name_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                        </div>

                        <p id="last_name_error" class="text text-danger"></p>
                        <div id="div_last_name"  class="form-group has-feedback">
                            <input onblur="lastNameValid()" id="lastName" type="text" placeholder="last name" name="lastName" class="form-control text-center"/>
                            <span id="span_last_name_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                            <span id="span_last_name_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                        </div>

                        <sec:authorize access="isAuthenticated()">
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <div class="form-group">
                                    <h4 class="text-center">Role</h4>
                                    <select name="role" class="form-control">
                                        <option value="ADMIN">Admin</option>
                                        <option value="USER">User</option>
                                    </select>
                                </div>
                            </sec:authorize>
                        </sec:authorize>
                        <sec:authorize access="!isAuthenticated()">
                            <input name="role" value="USER" type="hidden">
                        </sec:authorize>
                        <div class="form-group">
                            <h4 class="text-center">Secret question type</h4>
                            <select name="sqtId" class="form-control">
                                <c:forEach var = "a" items = "${sqt}">
                                    <option value = ${a.getId()}> ${a.getName()}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <p id="secret_question_error" class="text text-danger"></p>
                        <div id="div_secret_question"  class="form-group has-feedback">
                            <input onblur="sqtValid()" id="secretQuestion" type="text" placeholder="secret question" name="secretQuestion" class="form-control text-center"/>
                            <span id="span_secret_question_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                            <span id="span_secret_question_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                        </div>
                        <br/>
                        <div class="text-center">
                            <input type="submit" name="submit" value="Registration" class="btn btn-success" onclick="return validRegistration()" style="width: 100%">
                        </div>
                    </form>

            </div>
        </div>
    </div>
</div>

<script>

    function getId(id) {
        return document.getElementById(id);
    }

    function loginValid() {
        var email = document.getElementById("login").value;
        var result = false;
        var emailPattern = /^[a-zA-Z0-9._-]{1,40}@[a-zA-Z0-9.-]{1,40}\.[a-zA-Z]{2,6}$/;
        if (emailPattern.test(email) == false) {
            getId("email_error").innerHTML = "email error pattern";
            document.getElementById("div_email").classList.add("has-error");
            document.getElementById("span_email_error").style.display = "block";
            document.getElementById("span_email_ok").style.display = "none";
            return result;
        }
        else {
            document.getElementById("email_error").innerHTML = "";
            document.getElementById("div_email").classList.add("has-success");
            document.getElementById("div_email").classList.remove("has-error");
            document.getElementById("span_email_error").style.display = "none";
            document.getElementById("span_email_ok").style.display = "block";
        }
    }

    function passwordValid() {
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirmPassword").value;
        var onlyCharacter = /^[a-zA-Z0-9]{4,32}$/;
        var result = false;
        if (onlyCharacter.test(password) == false || onlyCharacter.test(confirmPassword) == false || password != confirmPassword) {
            document.getElementById("password_error").innerHTML = "password must be between 4-32 character";
            document.getElementById("div_password").classList.add("has-error");
            document.getElementById("div_password").classList.add("has-feedback");
            document.getElementById("span_password_error").style.display = "block";
            document.getElementById("span_password_ok").style.display = "none";

            document.getElementById("password_confirm_error").innerHTML = "password and confirm password not equals";
            document.getElementById("div_password_confirm").classList.add("has-error");
            document.getElementById("span_password_confirm_error").style.display = "block";
            document.getElementById("span_password_confirm_ok").style.display = "none";
            return result;
        }
        else {
            document.getElementById("password_error").innerHTML = "";
            document.getElementById("div_password").classList.add("has-success");
            document.getElementById("div_password").classList.remove("has-error");
            document.getElementById("span_password_error").style.display = "none";
            document.getElementById("span_password_ok").style.display = "block";

            document.getElementById("password_confirm_error").innerHTML = "";
            document.getElementById("div_password_confirm").classList.add("has-success");
            document.getElementById("div_password_confirm").classList.remove("has-error");
            document.getElementById("span_password_confirm_error").style.display = "none";
            document.getElementById("span_password_confirm_ok").style.display = "block";
        }
    }

    function firstNameValid() {
        var pattern = /^[a-zA-Z]{4,32}$/;
        var result = false;
        var firstName = document.getElementById("firstName").value;
        if (pattern.test(firstName) == false) {
            document.getElementById("first_name_error").innerHTML = "first name must be between 2-32 character";
            document.getElementById("div_first_name").classList.add("has-error");
            document.getElementById("span_first_name_error").style.display = "block";
            document.getElementById("span_first_name_ok").style.display = "none";
            return result;
        }
        else{
            document.getElementById("first_name_error").innerHTML = "";
            document.getElementById("div_first_name").classList.add("has-success");
            document.getElementById("div_first_name").classList.remove("has-error");
            document.getElementById("span_first_name_ok").style.display = "block";
            document.getElementById("span_first_name_error").style.display = "none";
        }
    }

    function lastNameValid() {
        var pattern = /^[a-zA-Z]{4,32}$/;
        var result = false;
        var lastName = document.getElementById("lastName").value;
        if (pattern.test(lastName) == false) {
            document.getElementById("last_name_error").innerHTML = "last name must be between 2-32 character";
            document.getElementById("div_last_name").classList.add("has-error");
            document.getElementById("span_last_name_error").style.display = "block";
            document.getElementById("span_last_name_ok").style.display = "none";
            return result;
        }
        else{
            document.getElementById("last_name_error").innerHTML = "";
            document.getElementById("div_last_name").classList.add("has-success");
            document.getElementById("div_last_name").classList.remove("has-error");
            document.getElementById("span_last_name_ok").style.display = "block";
            document.getElementById("span_last_name_error").style.display = "none";
        }
    }

    function sqtValid() {
        var pattern = /^[a-zA-Z0-9-/]{1,32}$/;
        var result = false;
        var sqt = document.getElementById("secretQuestion").value;
        if (pattern.test(sqt) == false)  {
            document.getElementById("secret_question_error").innerHTML = "secret question must be between 1-32 character";
            document.getElementById("div_secret_question").classList.add("has-error");
            document.getElementById("span_secret_question_error").style.display = "block";
            document.getElementById("span_secret_question_ok").style.display = "none";
            return result;
        }
        else{
            document.getElementById("secret_question_error").innerHTML = "";
            document.getElementById("div_secret_question").classList.add("has-success");
            document.getElementById("div_secret_question").classList.remove("has-error");
            document.getElementById("span_secret_question_ok").style.display = "block";
            document.getElementById("span_secret_question_error").style.display = "none";
        }
    }

    function validRegistration(){
        var login = loginValid();
        var password = passwordValid();
        var firstName = firstNameValid();
        var lastName = lastNameValid();
        var sqt = sqtValid();
        if (login == false || password == false || firstName == false || lastName == false || sqt == false)
            return false;
        return true;
    }
</script>

</body>
</html>
