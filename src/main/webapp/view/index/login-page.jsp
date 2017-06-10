

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Login</title>
  <script src="<c:url value="/resources/JQuery/jquery-3.2.1.min.js" />"></script>
  <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
  <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
  <script src="<c:url value="/resources/js/validators.js" />"></script>
  <script src="<c:url value="/resources/js/other-js.js" />"></script>
  <script src="<c:url value="/resources/js/country.js" />"></script>
  <style>
    html { height: 100%; }
    body {
      margin: 0;
      height: 95%;
      background: url(http://daleki-zori.com.ua/wp-content/uploads/2016/05/maxresdefault.jpg);
      background-size: cover;
    }
    .down {
      position: absolute;
      bottom: 0;
      right: 0;
    }
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
  </style>
</head>


<body>




<div id="loader" style="display: none" class="centerLayer">
  <h1 class="text text-center">
    <img src="/resources/3.gif"/>
  </h1>
</div>


<div id="firstPage">

  <div class="form-action" id="loginPage" style="display: none;">
    <div class="container">
      <div class="row">
        <div class="col-lg-12 text-center" style="margin-top: 15%">
          <h1 style="color: #fbfff9">Login</h1>
          <h3>${param.info}</h3>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-4 col-md-4 col-sm-3 col-xs-2"></div>
        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-8">
          <div class="col-lg-2"></div>
          <div class="col-lg-8">
            <form id ="formLogin" method="POST" action="${pageContext.request.contextPath}/j_spring_security_check">
              <div id="div_email_formLogin"  class="form-group has-feedback">
                <label class="control-label" style="color: #ffffff">Email</label>
                <input onblur="loginValid('div_email_formLogin')" type="text" name="username" class="form-control text-center"/>
                <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
              </div>
              <div id="div_password_formLogin"  class="form-group has-feedback">
                <label class="control-label" style="color: #ffffff">Password</label>
                <input autocomplete="off" onblur="firstPasswordValid('div_password_formLogin')" type="password" name="password" class="form-control text-center"/>
                <span class="span-validator glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                <span class="span-validator glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
              </div>
              <div class="form-group text-center">
                <td><a onclick="getForgotPage()" style="color: #fbfff9" href="#">Forgot password</a></td>
              </div>
              <div class="form-group text-center">
                <td><a onclick="getRegistrationPage()" style="color: #fbfff9" href="#">Registration</a></td>
              </div>
              <div id="div_button_formLogin" class="text-center">
                <button class="btn btn-info" form="formLogin"
                        style="width: 100%">Log in
                  <span class="glyphicon glyphicon-log-in"></span>
                </button>
              </div>
            </form>
          </div>
          <div class="col-lg-2"></div>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-3 col-xs-2"></div>
      </div>
    </div>
  </div>

  <div class="form-action" id="forgotPasswordPage" style="display: none;">
    <div style="height: 100px"
         class="col-lg-5 col-lg-offset-2 col-md-5 col-md-offset-1 col-sm-4 col-sm-offset-1 col-xs-5 col-xs-offset-2">
    </div>

    <div class="col-lg-6 col-lg-offset-3 col-md-5 col-md-offset-4 col-sm-6 col-sm-offset-4 col-xs-5 col-xs-offset-2">

      <br/>
      <hr/>
      <h1 class="text-center">Forgot password</h1>
      <hr/>

      <form id="form-forgot">
        <h4 id="info-forgot" class="text-center"></h4>
        <div id="div-user-forgot">
          <div id="div_email_form-user-forgot"  class="form-group has-feedback ">
            <label class="control-label">Email:</label>
            <input type="text" onblur="loginValid('div_email_form-user-forgot')" name="login" class="form-control text-center"/>
            <span class="span-validator glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
            <span class="span-validator glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
          </div>

          <div class="text-center">
            <button id="submit-form-forgot" name="submit-form-forgot" class="btn btn-success"
                    style="width: 100%">
              Next step <span class="glyphicon glyphicon-check"/>
            </button>
          </div>
        </div>
      </form>

    </div>


    <div id="div-returnToLogin" style="height: 150px"
         class="col-lg-4 col-lg-offset-4 col-md-5 col-md-offset-1 col-sm-4 col-sm-offset-1 col-xs-5 col-xs-offset-2">
      <a name="return-login" style="color: #fbfff9;" href="#/">
        <h1 class="text-center">
          <div id="div-return-login-br" style="display: none">
            <br/><br/><br/><br/>
          </div>
          <span class="glyphicon glyphicon-share"></span>
          Return to login
        </h1>
      </a>
    </div>

  </div>

  <div class="form-action" id="registrationPage" style="display: none">
    <div class="col-lg-6 col-lg-offset-3 col-md-5 col-md-offset-4 col-sm-6 col-sm-offset-4 col-xs-5 col-xs-offset-2">

        <br/>
        <hr/>
        <h1 class="text-center">Registration</h1>
        <hr/>

        <form id="form-user-data">

          <h4 id="info" class="text-center"></h4>

          <div id="div-first-user-data">
            <div id="div_email_form-user-data"  class="form-group has-feedback ">
              <label class="control-label">Email:</label>
              <input type="text" onblur="loginValid('div_email_form-user-data')" name="login" class="form-control text-center"/>
              <span class="span-validator glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
              <span class="span-validator glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
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
              <span class="span-validator glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
              <span class="span-validator glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
            </div>


            <div id="div_password_confirm_form-user-data"  class="form-group has-feedback">
              <label class="control-label" >Confirm password</label>
              <input onblur="confirmPassword('div_password_form-user-data', 'div_password_confirm_form-user-data')"  type="password" name="passwordConfirm" class="form-control text-center"/>
              <span class="span-validator glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
              <span class="span-validator glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>

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
              <span class="span-validator glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
              <span class="span-validator glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
            </div>

            <div id="div_last_name_form-user-data"  class="form-group has-feedback">
              <label class="control-label">Last name</label>
              <input onblur="lastNameValid('div_last_name_form-user-data')" type="text"  name="lastName" class="form-control text-center"/>
              <span class="span-validator glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
              <span class="span-validator glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
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

        </form>

      <div>
        <a name="return-login" href="#/">
          <h1 class="text-center"><span class="glyphicon glyphicon-share"></span> Return to login</h1>
        </a>
      </div>


      </div>
    </div>


</div>

  <address style="color: white" class="down">
    <strong>Evolution</strong><br>
    Author: Maksim Lukaretskiy<br>
    Address: Ukraine, Odessa<br>
    <abbr title="Phone">Phone:</abbr> (+38) 093 53-68-034
    <br/><abbr title="Phone">Skype:</abbr> s4rgeist
  </address>
</div>


<script>

    $(document).ready(function () {
        $("#form-forgot button[name=submit-form-forgot]").click(function () {
            if (loginValid('div_email_form-user-forgot') == false)
                return false;
            var login = $("#form-forgot input[name=login]").val();
            var json = JSON.stringify({"login":login});

            $("#form-forgot").hide();
            $("#loader, #div-return-login-br").fadeToggle("slow");


            setTimeout(function () {
                $.ajax({
                    url: "/service/user/forgot/CREATE_FORGOT_TOKEN",
                    type: "POST",
                    data: json,
                    contentType: "application/json; charset=UTF-8",
                    success: function (response) {
                        $("#info-forgot").html(response.message);
                        complete();
                    },
                    error: function () {
                        $("#info-forgot").html("Sorry, server is not responded").delay(3000).fadeToggle("slow");
                        complete();
                    },
                    timeout: 15000
                })
            }, 1500)

            function complete() {
                $("#loader, #div-return-login-br, .span-validator").hide();
                $("#form-forgot").fadeToggle("slow");
                $("#form-forgot input[name=login]").val("");
            }

            return false;
        })
    })

    $(document).ready(function () {
        $("#firstPage a[name=return-login]").click(function () {
            $(".form-action, #div-return-login-br, #loader, .span-validator, #div-second-user-data").hide();
            $(".form-action :input").val("");
            $("#loginPage").fadeToggle("slow");
        })
    })


    function getRegistrationPage() {
        $(".form :input").val("");
        $('#registrationPage, #div-first-user-data').slideDown(2000);
        $("#loginPage").slideUp(1000);
        $("#div-second-user-data, .span-validator").hide();
    }

    function getForgotPage() {
        $("#form-forgot").show();
        $("#div-return-login-br, .span-validator").hide();
        $("#form-forgot :input").val();
        $("#loginPage").slideUp(1000);
        $("#forgotPasswordPage").slideDown(2000);
    }


    $(document).ready(function () {

        $("#returnToLogin").click(function () {
            $("#forgotPasswordPage").slideUp(1000);
            $("#loginPage").slideDown(2000);
        });

        $("#loginPage").slideDown(1000);

        $("label, a, h3, h1, h4").css("color", "#fbfff9");

        // LOGIN
        $('#formLogin').on('submit', function () {
            if (validLoginPage('div_email_formLogin', 'div_password_formLogin') == false)
                return false;
            var form = this;
            $("#div_button_formLogin").hide();
            $("#loginPage").slideUp(2000);
            $("#loader").slideDown(2000);
            setTimeout(function () {
                form.submit();
            }, 2000);
            return false;
        });



        $("#div-first-user-data button[name=submit-first-user-data]").click(function () {
            if (loginValid('div_email_form-user-data') == false)
                return false;

            $("#div-first-user-data, #return-login-registrationPage").hide();
            $("#loader").fadeToggle("slow");
            $("#info").html("");


            setTimeout(function () {
                var login = $("#form-user-data input[name=login]").val();

                var json = JSON.stringify({"login":login});

                $.ajax({
                    url: "/service/user/registration/CHECK_EXIST_USER",
                    type:"POST",
                    data:json,
                    contentType: "application/json; charset=UTF-8",
                    success:function (dataJson) {
                        if (dataJson.info == true) {
                            $("#loader").hide();
                            $("#div-first-user-data, #return-login-registrationPage").fadeToggle("slow");
                            $("#info").html("This user " + login + " is exist");
                            $("#form-user-data input[name=login]").val("");
                            $("#div_email_form-user-data span").hide();
                            return false;
                        }
                        if (dataJson.info == false) {
                            // ТУТ ОТКРЫВАЕМ СЛЕД ФОРМУ
                            $("#loader").hide();
                            $("#div-second-user-data, #return-login-registrationPage").fadeToggle("slow");
                        }
                    },
                    error:function () {
                        alert('Sorry, server is not responded');
                        $("#loader").hide();
                        $("#div-second-user-data").hide();
                        $("#div-first-user-data, #return-login-registrationPage").fadeToggle("slow");
                    },
                    timeout: 15000
                })

            },1500)

            return false;
        })

        $("#form-user-data button[name=submit-user-email]").click(function () {
            var valid = validProfileForm('div_password_form-user-data',
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

            $("#div-second-user-data, #return-login-registrationPage").hide();
            $("#loader").fadeToggle("slow");
            $("#info").html("");


            setTimeout(function () {
                $.ajax({
                    url: "/service/user/registration/CREATE_REGISTRATION_TOKEN",
                    type: "POST",
                    data:json,
                    contentType: "application/json; charset=UTF-8",
                    success:function (data) {
                        $("#info").html(data.message).delay(5000).fadeToggle("slow");
                        $("#form-user-data :input").val("");
                        $("#loader, #div-second-user-data, #div_email_form-user-data span, #div-second-user-data div span").hide();
                        $("#div-first-user-data, #return-login-registrationPage").fadeToggle("slow");
                    },
                    error:function () {
                        alert('Sorry, server is not responded');
                        $("#loader, #div-first-user-data").hide();
                        $("#div-second-user-data, #return-login-registrationPage").fadeToggle("slow");
                    },
                    timeout: 15000
                })
            }, 1000);

            return false;

        })

        populateCountries("country", "state");
        populateCountries("country2");
    })




    $(document).ready(function () {

    })

</script>



</body>
</html>
