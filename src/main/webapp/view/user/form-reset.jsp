<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 06.03.2017
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Reset password</title>
</head>
<body>
<%@include file="../index/header.jsp" %>
<h3 class="text-center text-info">${info}</h3>

<div style="height: 150px"
     class="col-lg-5 col-lg-offset-2 col-md-5 col-md-offset-1 col-sm-4 col-sm-offset-1 col-xs-5 col-xs-offset-2">
</div>

<div class="col-lg-6 col-lg-offset-3 col-md-5 col-md-offset-4 col-sm-6 col-sm-offset-4 col-xs-5 col-xs-offset-2">

    <form id="form" method="post" action="/reset-password">
        <div id="stepOne">
            <h3 class="text-center text-muted">Step one</h3>
            <hr/>
            <label id="email_error" class="control-label" for="login">Email</label>
            <div id="div_email"  class="form-group has-feedback">
                <input id="login" type="text" onblur="loginValid('login')" name="login" class="form-control text-center"/>
                <span id="span_email_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                <span id="span_email_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
            </div>
            <div class="text-center">
                <span class="glyphicon glyphicon-share-alt"></span>
                <a href="#" onclick="step('stepOne', 'stepTwo', 'stepFinal', 'none', 'block', 'none')">Next step</a>
            </div>
        </div>

        <div id="stepTwo" style="display: none">
            <h3 class="text-center text-muted">Step two</h3>
            <hr/>
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
                <input onblur="sqtValid('secretQuestion')" id="secretQuestion" type="text" name="secretQuestion" class="form-control text-center"/>
                <span id="span_secret_question_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                <span id="span_secret_question_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
            </div>

            <a href="#" onclick="step('stepOne', 'stepTwo', 'stepFinal', 'block', 'none', 'none')">
                <span class="glyphicon glyphicon-menu-left"></span>
                Return to step one
            </a>

            <a class="pull-right" href="#" onclick="step('stepOne', 'stepTwo', 'stepFinal', 'none', 'none', 'block')">
                Next step
                <span class="glyphicon glyphicon-menu-right"></span>
            </a>

        </div>

        <div id="stepFinal" style="display: none">
            <h3 class="text-center text-muted">Final step</h3>
            <hr/>
            <label id="password_error" class="control-label" for="password">Password</label>
            <div id="div_password"  class="form-group has-feedback">
                <input onblur="firstPasswordValid('password')" id="password" type="password"  name="password" class="form-control text-center"/>
                <span id="span_password_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                <span id="span_password_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
            </div>

            <label id="password_confirm_error" class="control-label" for="confirmPassword">Confirm password</label>
            <div id="div_password_confirm"  class="form-group has-feedback">
                <input onblur="passwordValid('password', 'confirmPassword')" id="confirmPassword" type="password" name="passwordConfirm" class="form-control text-center"/>
                <span id="span_password_confirm_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                <span id="span_password_confirm_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                <a id ="hrefShowHidePassword" onclick="showHidePassword()" href="#">Show password and confirm password</a>
            </div>
            <a href="#" onclick="step('stepOne', 'stepTwo', 'stepFinal', 'none', 'block', 'none')">
                <span class="glyphicon glyphicon-menu-left"></span>
                Return to step two
            </a>

            <hr/>
            <button type="submit" form="form" class="btn btn-info center-block">
                Confirm <span class="glyphicon glyphicon-ok"></span>
            </button>

        </div>
    </form>



</div>


</body>

</html>
