<%--
  Created by IntelliJ IDEA.
  User: Infant
  Date: 23.07.2017
  Time: 2:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <style>
        .support-block{
            display: none;
        }
    </style>
</head>
<body>
<%@include file="../index/header.jsp" %>


<div id="content">

    <div class="col-lg-12" style="top:60px">

        <div id="registration-block" class="col-lg-6 col-lg-offset-1 block-background">

            <p id="info" class="x-large text-center"></p>

            <div id="div-first-user-data">
                <div id="div_email_form-user-data"  class="form-group has-feedback ">
                    <label class="control-label">Email:</label>
                    <input type="text" onblur="loginValid('div_email_form-user-data')" name="login" class="form-element form-control text-center"/>
                    <span class="span-validator glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                    <span class="span-validator glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
                </div>

                <div class="text-center">
                    <button id="submit-first-user-data" name="submit-first-user-data" class="btn btn-success"
                            style="width: 100%">
                        Next step <span class="glyphicon glyphicon-check"></span>
                    </button>
                </div>
            </div>

            <div id="div-second-user-data" style="display: none;">

                <div id="div_password_form-user-data"  class="form-group has-feedback">
                    <label class="control-label" >Password</label>
                    <input onblur="firstPasswordValid('div_password_form-user-data')" type="password"  name="password" class="form-element form-control text-center"/>
                    <span class="span-validator glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                    <span class="span-validator glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
                </div>


                <div id="div_password_confirm_form-user-data"  class="form-group has-feedback">
                    <label class="control-label" >Confirm password</label>
                    <input onblur="confirmPassword('div_password_form-user-data', 'div_password_confirm_form-user-data')"  type="password" name="confirm-password" class="form-element form-control text-center"/>
                    <span class="span-validator glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                    <span class="span-validator glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>

                </div>
                <a id ="hrefShowHidePassword_form-user-data" style="color: white" class="curs"
                   onclick="showHidePassword('hrefShowHidePassword_form-user-data',
                          'div_password_form-user-data',
                          'div_password_confirm_form-user-data')">
                    Show password and confirm password
                </a>

                <br/><br/>

                <div id="div_first_name_form-user-data"  class="form-group has-feedback">
                    <label class="control-label" for="firstName">First name</label>
                    <input onblur="firstNameValid('div_first_name_form-user-data')" id="firstName" type="text"  name="first-name" class="form-element form-control text-center"/>
                    <span class="span-validator glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                    <span class="span-validator glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
                </div>

                <div id="div_last_name_form-user-data"  class="form-group has-feedback">
                    <label class="control-label">Last name</label>
                    <input onblur="lastNameValid('div_last_name_form-user-data')" type="text"  name="last-name" class="form-element form-control text-center"/>
                    <span class="span-validator glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                    <span class="span-validator glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
                </div>

                <div id="country-city">
                    <label class="control-label">Country</label>
                    <select class="form-control" id="country" name="country"></select>
                    <br/>
                    <label class="control-label">State</label>
                    <select class="form-control" id="state" name="state"></select>
                </div>

                <br/><br/>
                <div class="text-center">
                    <button id="submit-user-registration" name="submit-user-email" class="btn btn-success"
                            style="width: 100%">
                        Registration <span class="glyphicon glyphicon-check"></span>
                    </button>
                </div>

            </div>

        </div>

        <div id="support-block" class="col-lg-5 ">
            <div class="support-block" data-toggle="login" id="support-login" style="display: block">
                <p class="xx-large text-center support-text">
                    Please enter your email. Then press "next step" button.
                    You must have access to this mail, because it will receive a confirmation letter.
                </p>
            </div>
            <div class="support-block" data-toggle="password" id="support-password">
                <p class="xx-large text-center">
                    Enter your password. Password should include latin letters and numbers only. 4-32 symbols in length
                </p>
            </div>
            <div class="support-block" data-toggle="confirm-password" id="support-confirm-password">
                <p class="xx-large text-center support-text">
                    Confirm your password. Password must match
                    Password should include Latin letters and numbers only. 4-32 symbols in length
                </p>
            </div>
            <div class="support-block" data-toggle="first-name" id="support-first-name">
                <p class="xx-large text-center support-text">
                    Enter your first name. First name should include latin letters only. 4-32 symbols in length
                </p>
            </div>
            <div class="support-block" data-toggle="last-name" id="support-last-name">
                <p class="xx-large text-center support-text">
                    Enter your last name. Last name should include latin letters only. 4-32 symbols in length
                </p>
            </div>
            <br/>
            <div data-toggle="valid" class="block-background">
                <p class="text-center" style="font-size: larger">
                    Red Cross means an error, green checkmarks- your input is valid, you can continue
                </p>
            </div>
            <br/>
            <div data-toggle="success" class="block-background" style="display: none;">
                <p class="text-center" style="font-size: larger">
                    If you would like to accept, press "Registration". You will receive an email with further instructions
                </p>
            </div>
        </div>



    </div>

</div>


<script>

    $(document).ready(function () {

        $(".form-element").focus(function () {
            var name = this.name;
            $(".support-block").hide();
            $("#support-block div[data-toggle=" + name + "]").show("slow");
        })

        $("#submit-first-user-data").click(function () {
            if (loginValid('div_email_form-user-data') === false)
                return false;
            var login = $("#div_email_form-user-data input[name=login]").val();
            var info = $("#info");
            actionBtn("submit-first-user-data", true);
            info.html("Loading...");

            $.ajax({
                url: "/service/user/is-exist?username=" + login,
                type:"GET",
                success:function (dataJson) {
                    actionBtn("submit-first-user-data", false);
                    if (dataJson === true) {
                        info.html("This user " + login + " is exist. Try again");
                        $(".form-element").val("");
                        $(".span-validator").hide();
                        return false;
                    }
                    if (dataJson === false) {
                        // ТУТ ОТКРЫВАЕМ СЛЕД ФОРМУ
                        info.html("");
                        $("#div-first-user-data, .support-block").hide();
                        $("#div-second-user-data, #support-block div[data-toggle=success]").fadeToggle("slow");
                    }
                },
                error:function () {
                    alert('Sorry, server is not responded');
                    info.html("");
                },
                timeout: 30000
            })

            return false;
        })

        $("#submit-user-registration").click(function () {
            var valid = validProfileForm('div_password_form-user-data',
                'div_password_confirm_form-user-data',
                'div_first_name_form-user-data',
                'div_last_name_form-user-data')
            if (valid === false)
                return false;

            var info = $("#info");

            var login = $("#registration-block input[name=login]").val();
            var password = $("#registration-block input[name=password]").val();
            var firstName = $("#registration-block input[name=first-name]").val();
            var lastName = $("#registration-block input[name=last-name]").val();


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

            actionBtn("submit-user-registration", true);

            info.html("Loading...");

            $.ajax({
                url: "/service/user/registration/CREATE_REGISTRATION_TOKEN",
                type: "POST",
                data:json,
                contentType: "application/json; charset=UTF-8",
                success:function (data) {
                    info.html(data.message);
                    actionBtn("submit-user-registration", false);
                    $(".span-validator").hide();
                    $(".form-element").val("");
                },
                error:function () {
                    alert('Sorry, server is not responded');
                    info.html("");
                    $(".span-validator").hide();
                    actionBtn("submit-user-registration", false);
                    $(".form-element").val("");
                },
                timeout: 30000
            })

            return false;
        })

    })

    populateCountries("country", "state");
    populateCountries("country2");

</script>



</body>
</html>
