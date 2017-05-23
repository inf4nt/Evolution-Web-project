<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 08.05.2017
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>${authUser.getFirstName()} ${authUser.getLastName()}</title>
    <script src="<c:url value="/resources/JQuery/jquery-3.2.1.min.js" />"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
    <script src="/resources/my_js/my_js.js"/>
    <script>
        $(document).ready(function () {
            $('.dropdown-toggle').dropdown();
        });
    </script>
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/my_css/my_css.css" />" rel="stylesheet">


    <style>
        .parent {
            width: 100%;
            height: 100%;
            position: absolute;
            top: 0;
            left: 0;
            overflow: auto;
        }

        .block {
            width: 250px;
            height: 250px;
            position: absolute;
            top: 50%;
            left: 50%;
            margin: -125px 0 0 -125px;
        }
    </style>


</head>
<body>

<div id="header">
    <div class="navbar navbar-inverse navbar-static-top">
        <div class="container">
            <a class="navbar-brand">Evolution
                <span class="glyphicon glyphicon-globe"></span>
            </a>
            <div class="callapse navbar-collapse">

                <sec:authorize access="isAuthenticated()">

                    <ul class="nav navbar-nav">
                        <li>
                            <a href="/user/id/${authUser.getId()}" methods="get">
                                <span class="glyphicon glyphicon-home"> Home</span>
                            </a>
                        </li>
                            <li>
                                <a id="headerMyProfile" href="#">
                                    <span class="glyphicon glyphicon-edit"> Profile</span>
                                </a>
                            </li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    ${authUser.getLogin()}
                                <span class="glyphicon glyphicon-log-out"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="/logout", methods="get">Exit</a></li>
                            </ul>
                        </li>
                    </ul>

                    <form role="form" action="/user/search" class="navbar-form navbar-right">
                        <div class="input-group">
                            <input type="text" class="form-control input-xs" name="like">
                            <div class="input-group-btn">
                                <button type="submit" formaction="/user/search/start" class="btn btn-search btn-info">
                                    <span class="glyphicon glyphicon-search"></span>
                                    <span class="label-icon">Search</span>
                                </button>
                            </div>
                        </div>
                    </form>

                </sec:authorize>

                <sec:authorize access="!isAuthenticated()">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="/welcome">Log in
                                <span class="glyphicon glyphicon-log-in"></span>
                            </a>
                        </li>

                    </ul>
                </sec:authorize>
            </div>
        </div>
    </div>

    <sec:authorize access="isAuthenticated()">
        <div class="col-md-3 col-lg-2 col-sm-3 col-xs-3">
            <div class="col-sm-10 col-md-9 col-lg-12 col-xs-12 sidebar">
                <ul class="nav nav-sidebar">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <hr/>
                        <li><a href="/admin/form-all/user/start" methods="get"><span class="glyphicon glyphicon-pawn"></span> Show users</a></li>
                        <li><a href="/admin/form-all/admin/start" methods="get"><span class="glyphicon glyphicon-king"></span> Show admin</a></li>
                        <li><a href="/form-create-user" methods="get"><span class="glyphicon glyphicon-check"></span> Registration</a></li>
                        <li><a href="/admin/form-create-sqt" methods="get">Create secret question type</a></li>
                        <hr/>
                    </sec:authorize>
                    <li>
                        <a href="/user/${authUser.getId()}/friend/start" methods="get">
                            <span class="glyphicon glyphicon-user"></span> Friends
                        </a>
                    </li>
                    <li>
                        <a href="/im/" methods="get">
                            <span class="glyphicon glyphicon-envelope"></span> Message
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </sec:authorize>
</div>










<div style="display: none" id="loader" class="parent">
    <div class="block">
        <h1 class="text text-center">
            <img src="/resources/loader.gif"/>
        </h1>
    </div>
</div>










<div id="dynamic">

    <div id="profile" class="dynamic" style="display: none">
        <div class="col-md-9">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 col-lg-offset-3">

                    <form id="formUserProfile">
                        <label id="email_error" class="control-label" for="login">Email</label>
                        <div id="div_email"  class="form-group has-feedback">
                            <input id="login" type="text" onblur="loginValid('login')" name="login" class="form-control text-center"/>
                            <span id="span_email_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                            <span id="span_email_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                        </div>

                        <label id="password_error" class="control-label" for="password">Password</label>
                        <div id="div_password"  class="form-group has-feedback">
                            <input onblur="firstPasswordValid('password')" id="password" type="password"  name="password" class="form-control text-center"/>
                            <span id="span_password_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                            <span id="span_password_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                        </div>

                        <label id="password_confirm_error" class="control-label" for="confirmPassword">Confirm password</label>
                        <div id="div_password_confirm"  class="form-group has-feedback">
                            <input onblur="passwordValid('password', 'confirmPassword')" id="confirmPassword" type="password"  name="passwordConfirm" class="form-control text-center"/>
                            <span id="span_password_confirm_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                            <span id="span_password_confirm_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                            <a id ="hrefShowHidePassword" onclick="showHidePassword()" href="#">Show password and confirm password</a>
                        </div>

                        <label id="first_name_error" class="control-label" for="firstName">First name</label>
                        <div id="div_first_name"  class="form-group has-feedback">
                            <input onblur="firstNameValid('firstName')" id="firstName" type="text"  name="firstName" class="form-control text-center"/>
                            <span id="span_first_name_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                            <span id="span_first_name_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                        </div>

                        <label id="last_name_error" class="control-label" for="lastName">Last name</label>
                        <div id="div_last_name"  class="form-group has-feedback">
                            <input onblur="lastNameValid('lastName')" id="lastName" type="text"  name="lastName" class="form-control text-center"/>
                            <span id="span_last_name_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
                            <span id="span_last_name_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
                        </div>

                        <fieldset disabled>
                            <div class="form-group">
                                <label for="secretQuestion">Secret question</label>
                                <input type="text" id="secretQuestion" class="form-control" >
                            </div>
                        </fieldset>

                        <fieldset hidden>
                            <input id="role" value="USER" name="role" type="hidden"/>
                            <input id="sqtId" name="sqtId" type="hidden"/>
                            <input id="secretQuestionData" name="secretQuestion" type="hidden"/>
                            <input id="registrationDateTime" name="registrationDate" type="hidden"/>
                        </fieldset>
                        <br/>
                        <div class="text-center">
                            <button form="formUserProfile" class="btn btn-success" style="width: 100%">
                                Edit <span class="glyphicon glyphicon-check"/>
                            </button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>

</div>









<h1 id="info"></h1>
<script>

    // получаю профиль
    $(document).ready(
        function () {
            $("#headerMyProfile").click(
                function () {
                    $.ajax({
                        url: "/user/profile",
                        type: "GET",
                        beforeSend: function () {
                            $("#profile").hide();
                            pageLoad(true);
                        },
                        success: function (data) {
                            pageLoad(false);
                            generateDivProfile(data);
                            $("#profile").show();
                        }
                    })
                }
            )
        }
    )

    function generateDivProfile(data) {
        var jsonData = JSON.parse(data);
        $("#login").attr("value", jsonData.login);
        $("#password").attr("value", jsonData.password);
        $("#confirmPassword").attr("value", jsonData.password);
        $("#firstName").attr("value", jsonData.firstName);
        $("#lastName").attr("value", jsonData.lastName);
        $("#registrationDateTime").attr("value", jsonData.registrationDate);
        $("#secretQuestion").attr("value", jsonData.secretQuestion);
        $("#secretQuestionData").attr("value", jsonData.secretQuestion);
        $("#sqtId").attr("value", jsonData.secretQuestionType.id);
    }

    // отправляю на сервер данные с профиля на редактирвание
    $(document).ready(function () {
        $("#formUserProfile").submit(function () {
            $.ajax({
                url: "/user/update",
                type: "POST",
                data: $("#formUserProfile").serialize(),
                success: function (data) {
                    $("#info").html(data);
                }
            });
            return false;
        });
    });



























    function pageLoad(run) {
        if (run == true) {
            $("#dynamic .dynamic").hide();
            $("#loader").show();
        }

        else {
            $("#loader").hide();
            $("#dynamic .dynamic").show();
        }
    }
</script>




</body>
</html>
