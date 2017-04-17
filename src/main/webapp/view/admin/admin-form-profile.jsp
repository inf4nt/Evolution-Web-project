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
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 col-lg-offset-3">
            <form method="post" id="form" action="/user/edit/${user.getId()}">
                <fieldset disabled>
                    <div class="form-group">
                        <label for="disabledTextInputId">Id</label>
                        <input type="text" id="disabledTextInputId" class="form-control" placeholder="${user.getId()}">
                    </div>
                </fieldset>

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

                <div class="form-group">
                    <label class="control-label">Role</label>
                    <select name="role" class="form-control">
                        <c:choose>
                            <c:when test="${user.getRole() == 'USER'}">
                                <option value="USER">User</option>
                                <option value="ADMIN">Admin</option>
                            </c:when>
                            <c:otherwise>
                                <option value="ADMIN">Admin</option>
                                <option value="USER">User</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </div>

                <fieldset disabled>
                    <div class="form-group">
                        <label for="disabledTextInputRD">Registration date</label>
                        <input type="text" id="disabledTextInputRD" class="form-control" placeholder="${user.getDateFormatRegistrationDate()}">
                    </div>
                </fieldset>

                <fieldset disabled>
                    <div class="form-group">
                        <label for="disabledTextInputR">Role</label>
                        <c:if test="${user.getRole() == 'USER'}">
                            <input type="text" id="disabledTextInputR" class="form-control" placeholder="User">
                        </c:if>
                        <c:if test="${user.getRole() == 'ADMIN'}">
                            <input type="text" id="disabledTextInputR" class="form-control" placeholder="Admin">
                        </c:if>
                    </div>
                </fieldset>

                <fieldset hidden>
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
</body>
</html>
