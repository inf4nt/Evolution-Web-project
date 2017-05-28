<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 25.05.2017
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
    <title>Registration</title>
    <script src="<c:url value="/resources/JQuery/jquery-3.2.1.min.js" />"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/js/country.js" />"></script>
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">

    <style>
        .centerLayer {
            position: absolute; /* Абсолютное позиционирование */
            width: 400px; /* Ширина слоя в пикселах */
            height: 300px; /* Высота слоя в пикселах */
            left: 50%; /* Положение слоя от левого края */
            top: 50%; /* Положение слоя от верхнего края */
            margin-left: -190px; /* Отступ слева */
            margin-top: -190px;	/* Отступ сверху */
            padding: 10px; /* Поля вокруг текста */
            overflow: auto; /* Добавление полосы прокрутки */
        }
        li {
            list-style-type: none; /* Убираем маркеры */
        }
    </style>

</head>
<body>

<%@include file="../index/header.jsp" %>


<h1 class="text-center">Registration</h1>


<div id="loader" style="display: none" class="centerLayer">
    <h1 class="text text-center">
        <img src="/resources/loader.gif"/>
    </h1>
</div>


<div style="display: block">

    <form id="form-user-data">

        <h4 id="info" class="text-center"></h4>

        <div id="div-user-data" class="col-lg-6 col-lg-offset-3">

            <div id="div-first-user-data">
                <div id="div_email_form-user-data"  class="form-group has-feedback ">
                    <label class="control-label">Email:</label>
                    <label id="email-result" class="control-label"></label>
                    <input type="text" onblur="loginValid('div_email_form-user-data')" name="login" class="form-control text-center"/>
                    <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                    <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
                </div>

                <div class="text-center">
                    <button name="submit-first-user-data" class="btn btn-success"
                            style="width: 100%">
                        Next step <span class="glyphicon glyphicon-check"/>
                    </button>
                </div>
            </div>

            <div id="div-second-user-data" style="display: none;">

                <div id="div_password_form-user-data"  class="form-group has-feedback">
                    <label class="control-label" >Password</label>
                    <input onblur="firstPasswordValid('div_password_form-user-data')" type="password"  name="password" class="form-control text-center"/>
                    <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                    <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
                </div>


                <div id="div_password_confirm_form-user-data"  class="form-group has-feedback">
                    <label class="control-label" >Confirm password</label>
                    <input onblur="confirmPassword('div_password_form-user-data', 'div_password_confirm_form-user-data')"  type="password" name="passwordConfirm" class="form-control text-center"/>
                    <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                    <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>

                </div>
                <a id ="hrefShowHidePassword_form-user-data"
                   onclick="showHidePassword('hrefShowHidePassword_form-user-data',
                        'div_password_form-user-data',
                        'div_password_confirm_form-user-data')"
                   href="#/">Show password and confirm password</a>

                <br/><br/>

                <div id="div_first_name_form-user-data"  class="form-group has-feedback">
                    <label class="control-label" for="firstName">First name</label>
                    <input onblur="firstNameValid('div_first_name_form-user-data')" id="firstName" type="text"  name="firstName" class="form-control text-center"/>
                    <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                    <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
                </div>

                <div id="div_last_name_form-user-data"  class="form-group has-feedback">
                    <label class="control-label">Last name</label>
                    <input onblur="lastNameValid('div_last_name_form-user-data')" type="text"  name="lastName" class="form-control text-center"/>
                    <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                    <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
                </div>

                <div id="country-city">
                    <label class="control-label">Country</label>
                    <select class="form-control" id="country" name="country"></select>
                    <br/>
                    <label class="control-label">State</label>
                    <select class="form-control" name="state" id="state"></select>
                </div>

                <br/><br/>
                <div class="text-center">
                    <button name="submit-user-email" class="btn btn-success"
                            style="width: 100%">
                        Registration <span class="glyphicon glyphicon-check"/>
                    </button>
                </div>

            </div>

        </div>

    </form>

</div>







<script>

    // форма 1 проверка и открытие след формы
    $(document).ready(function () {
        $("#div-first-user-data button[name=submit-first-user-data]").click(function () {
            if (loginValid('div_email_form-user-data') == false)
                return false;

            $("#div-first-user-data").hide();
            $("#loader").fadeToggle("slow");
            $("#info").html("");

////             ТУТ АЯКСОМ ПРОВЕРИМ СВОБОДЕН ЛИ
            setTimeout(function () {
                var login = $("#form-user-data input[name=login]").val();
                var json = JSON.stringify({"login":login});

                $.ajax({
                    url: "/service/user-registration-action/check",
                    type:"POST",
                    data:json,
                    contentType: "application/json; charset=UTF-8",
                    success:function (dataJson) {

                        if (dataJson.info == true) {
                            $("#loader").hide();
                            $("#div-first-user-data").fadeToggle("slow");
                            $("#info").html("This user " + login + " is exist").delay(3000).fadeToggle("slow");
                            $("#form-user-data input[name=login]").val("");
                            $("#div_email_form-user-data span").hide();
                            return false;
                        }
                        if (dataJson.info == false) {
                            // ТУТ ОТКРЫВАЕМ СЛЕД ФОРМУ
                            $("#loader").hide();
                            $("#div-second-user-data").fadeToggle("slow");
                        }
                    },
                    error:function () {
                        alert('Sorry, server is not responded');
                        $("#loader").hide();
                        $("#div-second-user-data").hide();
                        $("#div-first-user-data").fadeToggle("slow");
                    },
                    timeout: 10000
                })

            },1500)

            return false;
        })
    })

    $(document).ready(function () {

        $("#form-user-data button[name=submit-user-email]").click(function () {
            var valid = validRegistrationForm('div_password_form-user-data',
                'div_password_confirm_form-user-data',
                'div_first_name_form-user-data',
                'div_last_name_form-user-data')
            if (valid == false)
                return false;


            var login = $("#form-user-data input[name=login]").val();
            var password = $("#form-user-data input[name=password]").val();
            var firstName = $("#form-user-data input[name=firstName]").val();
            var lastName = $("#form-user-data input[name=lastName]").val();


            // ТУТ СОБЕРУ ГДЕ ОН ЖИВЕТ
            var countryCheck = $("#country").val();
            var stateCheck = $("#state").val();
            var country; var state;
            if (stateCheck != null && stateCheck.length > 0)
                state = stateCheck;
            if (countryCheck != -1)
                country = countryCheck;

            var json = JSON.stringify({"login":login, "password":password,
                "firstName":firstName, "lastName":lastName,
                "country":country, "state":state});



            $("#div-second-user-data").hide();
            $("#loader").fadeToggle("slow");
            $("#info").html("");

            setTimeout(function () {
               $.ajax({
                   url: "/service/user-registration-action/createToken",
                   type: "POST",
                   data: json,
                   contentType: "application/json; charset=UTF-8",
                   success:function (data) {
                       $("#info").html(data.message).delay(5000).fadeToggle("slow");
                       $("#form-user-data :input").val("");
                       $("#loader, #div-second-user-data, #div_email_form-user-data span, #div-second-user-data div span").hide();
                       $("#div-first-user-data").fadeToggle("slow");
                   },
                   error:function () {
                       alert('Sorry, server is not responded');
                       $("#loader, #div-first-user-data").hide();
                       $("#div-second-user-data").fadeToggle("slow");
                   },
                   timeout: 10000
               })
            }, 1000);
            return false;


        })


    })



    function validRegistrationForm(divPassword, divConfirmPassword, divFirstName, divLastName) {
        if (firstPasswordValid(divPassword) == false ||
            confirmPassword(divPassword, divConfirmPassword) == false ||
            firstNameValid(divFirstName) == false ||
            lastNameValid(divLastName) == false )
            return false;
        else
            return true;
    }

    function error(error, divId) {
        if (error == true) {
            $("#" + divId + " span:last-child").show();
            $("#" + divId + " input").next().hide();
        } else {
            $("#" + divId + " input").next().show();
            $("#" + divId + " span:last-child").hide();
        }
    }

    function loginValid(divEmail) {
        var emailPattern = /^[a-zA-Z0-9._-]{1,40}@[a-zA-Z0-9.-]{1,40}\.[a-zA-Z]{2,6}$/;
        return testPattern(divEmail, emailPattern);
    }

    function firstPasswordValid(divPassword) {
        var onlyCharacter = /^[a-zA-Z0-9]{4,32}$/;
        return testPattern(divPassword, onlyCharacter);
    }

    function confirmPassword(divPassword, divConfirmPassword) {
        if (firstPasswordValid(divConfirmPassword) == false) {
            $("#"+divConfirmPassword+" label").html("Confirm password: Pattern error");
            return false;
        }
        var confirmPassword =  $("#"+divConfirmPassword+" input").val();
        var password =  $("#"+divPassword+" input").val();
        if (confirmPassword != password) {
            error(true, divConfirmPassword);
            $("#"+divConfirmPassword+" label").html("Confirm password: Password and confirm password not equals");
            $("#"+divPassword+" label").html("Password");
            return false;
        }
        error(false, divConfirmPassword);
        error(false, divPassword);
        $("#"+divConfirmPassword+" label").html("Confirm password");
        $("#"+divPassword+" label").html("Password");
        return true;
    }

    function testPattern(divId, pattern) {
        var val = $("#"+divId +" input").val();
        if (pattern.test(val) == false) {
            error(true, divId);
            return false;
        } else {
            error(false, divId);
            return true;
        }
    }

    function firstNameValid(divFirstName) {
        var pattern = /^[a-zA-Z]{4,32}$/;
        return testPattern(divFirstName, pattern);
    }

    function lastNameValid(divLastName) {
        return firstNameValid(divLastName);
    }


    function showHidePassword(href, divPass, divConfirm) {
        var type = $("#"+divPass+" input").attr("type");
        if (type == 'password') {
            $("#"+href).html('Hide password and confirm password');
            $("#"+divPass+" input, #"+divConfirm+" input").attr("type", "text");
        } else {
            $("#"+href).html('Show password and confirm password');
            $("#"+divPass+" input, #"+divConfirm+" input").attr("type", "password");
        }
    }

    $(document).ready(function () {
        populateCountries("country", "state");
        populateCountries("country2");
    })

</script>


</body>
</html>
