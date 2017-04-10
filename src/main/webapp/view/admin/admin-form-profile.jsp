<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.04.2017
  Time: 1:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<%@include file="../index/header.jsp" %>

<div class="col-md-9">
    <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
            <div class="text-primary">

                <h4><strong><ins>Id</ins></strong></h4><h4>${user.getId()}</h4>
                <h4><strong><ins>Email</ins></strong></h4><h4>${user.getLogin()}</h4>
                <h4><strong><ins>Password</ins></strong></h4><h4>${user.getPassword()}</h4>
                <h4><strong><ins>First name</ins></strong></h4><h4>${user.getFirstName()}</h4>
                <h4><strong><ins>Last name</ins></strong></h4><h4>${user.getLastName()}</h4>
                <c:if test="${user.getRoleId() == 1}">
                    <h4><strong><ins>Role</ins></strong></h4><h4>User</h4>
                </c:if>
                <c:if test="${user.getRoleId() == 2}">
                    <h4><strong><ins>Role</ins></strong></h4><h4>Admin</h4>
                </c:if>
                <h4><strong><ins>Registration date</ins></strong></h4><h4>${user.getRegistrationDate()}</h4>
                <h4><strong><ins>Secret question type</ins></strong></h4><h4>${user.getSecretQuestionType().getName()}</h4>
                <h4><strong><ins>Secret question</ins></strong></h4><h4>${user.getSecretQuestion()}</h4>
            </div>
        </div>

        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">


            <form method="post" id="form" action="/user/edit/${user.getId()}" class="text text-center">

                <p id="email_error" class="text-danger"></p>
                <div id="div_email"  class="form-group has-feedback">
                    <input id="login" type="text" onblur="loginValid()" value="${user.getLogin()}" name="login" class="form-control text-center"/>
                    <span id="span_email_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                    <span id="span_email_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                </div>

                <p id="password_error" class="text text-danger"></p>
                <div id="div_password"  class="form-group has-feedback">
                    <input onblur="passwordValid()" id="password" type="password" value="${user.getPassword()}" name="password" class="form-control text-center"/>
                    <span id="span_password_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                    <span id="span_password_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                </div>

                <p id="password_confirm_error" class="text text-danger"></p>
                <div id="div_password_confirm"  class="form-group has-feedback">
                    <input onblur="passwordValid()" id="confirmPassword" type="password" value="${user.getPassword()}" name="passwordConfirm" class="form-control text-center"/>
                    <span id="span_password_confirm_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                    <span id="span_password_confirm_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                </div>

                <p id="first_name_error" class="text text-danger"></p>
                <div id="div_first_name"  class="form-group has-feedback">
                    <input onblur="firstNameValid()" id="firstName" type="text" value="${user.getFirstName()}" name="firstName" class="form-control text-center"/>
                    <span id="span_first_name_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                    <span id="span_first_name_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                </div>

                <p id="last_name_error" class="text text-danger"></p>
                <div id="div_last_name"  class="form-group has-feedback">
                    <input onblur="lastNameValid()" id="lastName" type="text" value="${user.getLastName()}" name="lastName" class="form-control text-center"/>
                    <span id="span_last_name_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                    <span id="span_last_name_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                </div>

                <div class="form-group">
                    <h4 class="text-center">Role</h4>
                    <select name="role" class="form-control">
                        <option value="ADMIN">Admin</option>
                        <option value="USER">User</option>
                    </select>
                </div>

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
                    <input onblur="sqtValid()" id="secretQuestion" type="text" value="${user.getSecretQuestion()}" name="secretQuestion" class="form-control text-center"/>
                    <span id="span_secret_question_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                    <span id="span_secret_question_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                </div>
                <br/>
                <div class="text-center">
                    <input type="submit" name="submit" value="Edit" class="btn btn-success" onclick="return validRegistration()" style="width: 100%">
                </div>

            </form>
        </div>
    </div>
</div>
</body>

<script>
    function loginValid() {
        var email = document.getElementById("login").value;
        var result = false;
        var emailPattern = /^[a-zA-Z0-9._-]{1,40}@[a-zA-Z0-9.-]{1,40}\.[a-zA-Z]{2,6}$/;
        if (emailPattern.test(email) == false) {
            document.getElementById("email_error").innerHTML = "email error pattern";
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
        var pattern = /^[a-zA-Z0-9]{1,32}$/;
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

</html>