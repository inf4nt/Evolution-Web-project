

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Login</title>
  <script src="<c:url value="/resources/JQuery/jquery-3.2.1.min.js" />"></script>
  <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
  <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
  <script src="<c:url value="/resources/js/validators.js" />"></script>
  <script src="<c:url value="/resources/js/pageJS.js" />"></script>
  <style>
    html { height: 100%; }
    body {
      margin: 0;
      height: 100%;
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
    <img src="/resources/preloader.gif"/>
  </h1>
</div>


<div id="firstPage">

  <div id="loginPage" style="display: none;">
    <div class="container">
      <div class="row">
        <div class="col-lg-12 text-center" style="margin-top: 15%">
          <h1 style="color: #fbfff9">Login</h1>
          <c:if test="${param.error == 'true'}">
            <h3 style="color: #fbfff9">Login Failed!
            </h3>
          </c:if>
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
                <input onblur="loginValid('div_email_formLogin')" type="text" name="username" value="com.infant@gmail.com" class="form-control text-center"/>
                <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
              </div>
              <div id="div_password_formLogin"  class="form-group has-feedback">
                <label class="control-label" style="color: #ffffff">Password</label>
                <input autocomplete="off" onblur="firstPasswordValid('div_password_formLogin')" type="password" value="77788877"  name="password" class="form-control text-center"/>
                <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
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

  <div id="forgotPasswordPage" style="display: none;">
    <div style="height: 150px"
         class="col-lg-5 col-lg-offset-2 col-md-5 col-md-offset-1 col-sm-4 col-sm-offset-1 col-xs-5 col-xs-offset-2">
    </div>
    <div class="col-lg-6 col-lg-offset-3 col-md-5 col-md-offset-4 col-sm-6 col-sm-offset-4 col-xs-5 col-xs-offset-2">

      <form id="formForgotPassword">
        <div id="stepOne" class="step">
          <h3 class="text-center">Step one</h3>
          <hr/>
          <div id="div_email_formForgotPassword" class="form-group has-feedback">
            <label id="email_error" class="control-label" >Email</label>
            <input autocomplete="off"  type="text" name="login" class="form-control text-center"/>
            <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
            <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
          </div>
          <div class="text-center">
            <a class="okey" style="color: white; display: none" href="#"><p><span class="glyphicon glyphicon-ok"></span> Confirm value  </p></a>
            <a class="step" id="forgotPassword_step_one" style="color: white; display: none" href="#"><p><span class="glyphicon glyphicon-share-alt"></span> Next step</p></a>
          </div>
        </div>

        <div id="stepTwo" class="step" style="display: none">
          <h3 class="text-center">Step two</h3>
          <hr/>
          <div class="form-group">
            <label class="control-label">Secret question type</label>
            <select id="sqtId" name="sqtId" class="form-control">
              <option value ="1"> Your house number</option>
            </select>
          </div>

          <div id="div_secret_question_formForgotPassword"  class="form-group has-feedback">
            <label id="secret_question_error" class="control-label">Secret question</label>
            <input autocomplete="off" type="text" name="secretQuestion" class="form-control text-center"/>
            <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
            <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
          </div>

          <a id="forgotPassword_return_step_one" class="pull-left" href="#"><p><span class="glyphicon glyphicon-menu-left"></span>Return to step one</p></a>
          <a id="forgotPassword_step_final" style="display: none" class="step pull-right" href="#"><p>  Next step<span class="glyphicon glyphicon-menu-right"></span></p></a>
          <a style="display: none" class="okey text-center" href="#"><p><span class="glyphicon glyphicon-ok"></span> Confirm value  </p></a>
        </div>

        <div id="stepFinal" class="step" style="display: none">
          <h3 class="text-center">Final step</h3>
          <hr/>
          <div id="div_password_formForgotPassword"  class="form-group has-feedback">
            <label class="control-label" >Password</label>
            <input autocomplete="off" onblur="firstPasswordValid('div_password_formForgotPassword')" type="password"  name="password" class="form-control text-center"/>
            <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
            <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
          </div>

          <div id="div_password_confirm_formForgotPassword"  class="form-group has-feedback">
            <label class="control-label" >Confirm password</label>
            <input autocomplete="off" onblur="confirmPassword('div_password_formForgotPassword', 'div_password_confirm_formForgotPassword')" type="password" name="passwordConfirm" class="form-control text-center"/>
            <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
            <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
          </div>

          <a id="hrefShowHidePassword"
             onclick="showHidePassword('hrefShowHidePassword', 'div_password_formForgotPassword', 'div_password_confirm_formForgotPassword')"
             href="#">Show password and confirm password</a>
          <br/>
          <br/>
          <a class="pull-left" href="#" id="forgotPassword_return_step_two" ><p><span class="glyphicon glyphicon-menu-left"></span>Return to step two</p></a>
          <br/>


          <hr/>
          <div id="div_button_formForgotPassword" >
            <button form="formForgotPassword" class="btn btn-info center-block">
              Confirm <span class="glyphicon glyphicon-ok"></span>
            </button>
          </div>
        </div>

      </form>
    </div>

    <div style="height: 150px"
         class="col-lg-4 col-lg-offset-4 col-md-5 col-md-offset-1 col-sm-4 col-sm-offset-1 col-xs-5 col-xs-offset-2">
      <a id="returnToLogin" style="color: #fbfff9" href="#"><h1 class="text-center"><span class="glyphicon glyphicon-share"></span> Return to login</h1></a>
    </div>

  </div>

  <div id="registrationPage" style="display: none">
    <div class="col-lg-10 col-lg-offset-1 wordwrap">

      <div class="col-lg-6 col-lg-offset-3">
        <br/>
        <hr/>
        <h1 class="text-center">Registration</h1>
        <hr/>
        <form id="registrationPageForm" class="text text-center">

          <div id="div_email_registrationPageForm"  class="form-group has-feedback">
            <label class="control-label ">Email</label>
            <input autocomplete="off"  type="text" onblur="loginValid('div_email_registrationPageForm')" name="login" class="form-control text-center"/>
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
          <br/>
          <br/>

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

          <div id="div_secret_question_registrationPageForm"  class="form-group has-feedback">
            <label class="control-label" >Secret question</label>
            <input autocomplete="off"  onblur="sqValid('div_secret_question_registrationPageForm')" name="secretQuestion" type="text" class="form-control text-center"/>
            <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
            <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
          </div>

          <br/>
          <div class="text-center">
            <button class="btn btn-success"
                    style="width: 100%">
              Registration <span class="glyphicon glyphicon-check"/>
            </button>
          </div>
        </form>

        <a onclick="returnToLogin_registrationPageForm()" href="#">
          <h1 class="text-center"><span class="glyphicon glyphicon-share"></span> Return to login</h1>
        </a>

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

    function returnToLogin_registrationPageForm() {
        $('#registrationPage').slideUp(1000);
        $("#loginPage").slideDown(2000);
        $("#registrationPageForm div input").val("");
        $("#registrationPageForm div span").hide();
    }

    function getRegistrationPage() {
        $('#registrationPage').slideDown(2000);
        $("#loginPage").slideUp(1000);
    }

    function getForgotPage() {
      $("#loginPage").slideUp(1000);
      $("#forgotPasswordPage, #stepOne").slideDown(2000);
      $("#stepFinal, #stepTwo").hide();
    }


    $(document).ready(function () {

        $("#forgotPassword_return_step_one").click(function () {
            $("#stepTwo").slideUp(1000);
            $("#stepOne").slideDown(2000);
        });

        $("#forgotPassword_return_step_two").click(function () {
            $("#stepFinal").slideUp(1000);
            $("#stepTwo").slideDown(2000);
        });

        $("#forgotPassword_step_one").click(function () {
            $("#stepOne").slideUp(1000);
            $("#stepTwo").slideDown(2000);
        });

        $("#forgotPassword_step_final").click(function () {
            $("#stepTwo").slideUp(1000);
            $("#stepFinal").slideDown(2000);
        });

        $("#returnToLogin").click(function () {
            $("#forgotPasswordPage").slideUp(1000);
            $("#loginPage").slideDown(2000);
        });

        $("#formForgotPassword div div input").focus(function () {
            $(".okey").show();
        });

        $("#formForgotPassword div div input").blur(function () {
            $(".okey").hide();
        });

        $("#loginPage").slideDown(1000);

        $("label, a, h3, h1").css("color", "#fbfff9");

        // forgot step one
        $("#div_email_formForgotPassword input").blur(function () {
            if (loginValid('div_email_formForgotPassword') == false) {
                $("#div_email_formForgotPassword label").html('Email: Pattern error');
                $("#forgotPassword_step_one").hide();
            } else {
                $.ajax({
                    url: "/service/forgot-password-step-one",
                    type: "GET",
                    data: "login=" + $("#div_email_formForgotPassword input").val(),
                    beforeSend: function () {
                        $("#stepOne").hide();
                        $("#loader").show();
                    },
                    success: function (data) {
                        $("#stepOne").show();
                        $("#loader").hide();
                        if (data == true) {
                            $("#div_email_formForgotPassword label").html('Email: Success');
                            $("#forgotPassword_step_one").show();
                        } else {
                            $("#div_email_formForgotPassword label").html('Email: This email not found');
                            $("#forgotPassword_step_one").hide();
                        }
                    }
                });
            }
        });

        // FORGOT STEP TWO
        $("#div_secret_question_formForgotPassword input").blur(function () {
            if (sqValid('div_secret_question_formForgotPassword') == false) {
                $("#div_secret_question_formForgotPassword label").html('Secret question: Pattern error');
                $("#forgotPassword_step_final").hide();
            } else {
                var login = $("#div_email_formForgotPassword input").val();
                var sq = $("#div_secret_question_formForgotPassword input").val();
                var sqtId = $("#sqtId option:selected").val();
                $.ajax({
                    url: "/service/forgot-password-step-two?login=" + login + "&sq=" + sq + "&sqtId=" + sqtId,
                    type: "GET",
                    beforeSend: function () {
                        $("#stepTwo").hide();
                        $("#loader").show();
                    },
                    success: function (data) {
                        $("#stepTwo").show();
                        $("#loader").hide();
                        if (data == true) {
                            $("#div_secret_question_formForgotPassword label").html('Secret question: Success');
                            $("#forgotPassword_step_final").show();
                        } else {
                            $("#div_secret_question_formForgotPassword label").html('Secret question: Sorry, the parameters do not match');
                            $("#forgotPassword_step_final").hide();
                        }
                    }
                });
            }
        });

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

        //FINAL STEP
        $("#formForgotPassword").on("submit", function () {
            var p = firstPasswordValid('div_password_formForgotPassword');
            var c = confirmPassword('div_password_formForgotPassword', 'div_password_confirm_formForgotPassword');
            if (c == true && p == true) {
                $("#div_password_formForgotPassword label").html("Password:");
                $("#div_password_confirm_formForgotPassword label").html("Confirm password:");

                $("#forgotPasswordPage").slideUp(2000);
                setTimeout(function () {
                    $("#loader").slideDown(2000);
                }, 500)

                setTimeout(function () {
                    var login = $("#div_email_formForgotPassword input").val();
                    var password = $("#div_password_formForgotPassword input").val();
                    var sq = $("#div_secret_question_formForgotPassword input").val();
                    var sqtId = $("#sqtId option:selected").val();
                    $.ajax({
                        url: "/service/forgot-password-step-final?login=" + login + "&sq=" + sq + "&sqtId=" + sqtId + "&password=" + password,
                        type: "GET",
                        success: function (data) {
                            if (data == true) {
                                alert('Success');
                                $("#loader").slideUp(1000);
                                $("#loginPage").slideDown(2000);
                                $("#forgotPasswordPage, #stepFinal, #stepTwo").slideUp();
                                $("#formForgotPassword div div input").val("");
                                $("#formForgotPassword div div span, .step").hide();

                            } else {
                                alert('Try again');
                            }
                        },
                        error: function () {
                            alert('error');
                            return false;
                        }
                    });
                }, 2000)
            }
            else {
                if (p == false)
                    $("#div_password_formForgotPassword label").html("Password: Pattern error");
                else
                    $("#div_password_formForgotPassword label").html("Password");
                if (c == false)
                    $("#div_password_confirm_formForgotPassword label").html("Confirm password: Password and confirm password not equals");
                else
                    $("#div_password_confirm_formForgotPassword label").html("Confirm password");
            }
            return false;
        })

        // REGISTRATION
        $('#registrationPageForm').on('submit', function () {
            var check = validRegistrationForm('div_email_registrationPageForm',
                'div_password_registrationPageForm',
                'div_password_confirm_registrationPageForm',
                'div_first_name_registrationPageForm',
                'div_last_name_registrationPageForm', 'div_secret_question_registrationPageForm');
            if (check == false)
                return false;
            $("#registrationPage").slideUp(1000);
            setTimeout(function () {
                $("#loader").slideDown(1000);
            }, 500)
            setTimeout(function () {
                $.ajax({
                    url: "/create-user",
                    type: "POST",
                    data: $("#registrationPageForm").serialize(),
                    success: function (data) {
                        alert(data);
                        if (data == 'Success' || data == 'error' || data == 'validator') {
                            $("#loader").slideUp(1000);
                            $("#loginPage").slideDown(1000);
                            $("#registrationPage div input").val("");
                            $("#registrationPage div span").hide();
                        }
                        else {
                            $("#loader").slideUp(1000);
                            setTimeout(function () {
                                $("#registrationPage").slideDown(1000);
                            }, 500)
                            $("#div_email_registrationPageForm input").val("");
                            $("#div_email_registrationPageForm span").hide();
                        }
                    }
                });
            }, 2000);
            return false;
        });
    })

</script>
</body>
</html>
