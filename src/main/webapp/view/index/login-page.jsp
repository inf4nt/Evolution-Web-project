<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Login</title>
  <script src="<c:url value="/resources/JQuery/jquery-3.2.1.min.js" />"></script>
  <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
  <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
  <script src="<c:url value="/resources/my_js/my_js.js" />"></script>
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
    <img src="/resources/3.gif"/>
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
                <input onblur="firstPasswordValid('div_password_formLogin')" type="password" value="77788877"  name="password" class="form-control text-center"/>
                <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
              </div>
              <div class="form-group text-center">
                <td><a id="aForgotPassword" style="color: #fbfff9" href="#">Forgot password</a></td>
              </div>
              <div class="form-group text-center">
                <td><a id="aRegistration" style="color: #fbfff9" href="/form-create-user">Registration</a></td>
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
      <%--<div style="display: none" id="loader" class="parent">--%>
        <%--<div class="block">--%>
          <%--<h1 class="text text-center">--%>
            <%--<img src="/resources/3.gif"/>--%>
          <%--</h1>--%>
        <%--</div>--%>
      <%--</div>--%>
      <form id="formForgotPassword">
        <div id="stepOne" class="step">
          <h3 class="text-center">Step one</h3>
          <hr/>
          <div id="div_email_formForgotPassword" class="form-group has-feedback">
            <label id="email_error" class="control-label" >Email</label>
            <input type="text" name="login" class="form-control text-center"/>
            <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
            <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
          </div>
          <div class="text-center">
            <a id="forgotPassword_step_one" style="color: white; display: none" href="#"><p><span class="glyphicon glyphicon-share-alt"></span> Next step</p></a>
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
            <input type="text" name="secretQuestion" class="form-control text-center"/>
            <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
            <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
          </div>

          <a id="forgotPassword_return_step_one" class="pull-left" href="#"><p><span class="glyphicon glyphicon-menu-left"></span>Return to step one</p></a>
          <a id="forgotPassword_step_final" style="display: none" class="pull-right" href="#"><p>Next step<span class="glyphicon glyphicon-menu-right"></span></p></a>

        </div>

        <div id="stepFinal" class="step" style="display: none">
          <h3 class="text-center">Final step</h3>
          <hr/>
          <div id="div_password_formForgotPassword"  class="form-group has-feedback">
            <label class="control-label" >Password</label>
            <input onblur="firstPasswordValid('div_password_formForgotPassword')" type="password"  name="password" class="form-control text-center"/>
            <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
            <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
          </div>


          <div id="div_password_confirm_formForgotPassword"  class="form-group has-feedback">
            <label class="control-label" >Confirm password</label>
            <input onblur="confirmPassword('div_password_formForgotPassword', 'div_password_confirm_formForgotPassword')" type="password" name="passwordConfirm" class="form-control text-center"/>
            <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
            <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
          </div>
          <a id ="hrefShowHidePassword"  href="#">Show password and confirm password</a>
          <a href="#" id="forgotPassword_return_step_two" >
            <p>
              <span class="glyphicon glyphicon-menu-left"></span>
              Return to step two
            </p>
          </a>

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
      <a id="returnToLogin" style="color: #fbfff9" href="#"><h1 class="text-center">Return to login</h1></a>
    </div>

  </div>

  <address style="color: white" class="down">
    <strong>EvolutionMVC</strong><br>
    Author: Maksim Lukaretskiy<br>
    Address: Ukraine, Odessa<br>
    <abbr title="Phone">Phone:</abbr> (+38) 093 53-68-034
    <br/><abbr title="Phone">Skype:</abbr> s4rgeist
  </address>
</div>


<script>


    $(document).ready(function () {
        $('#formLogin').on('submit', function () {
            var check = validLoginPage('div_email_formLogin', 'div_password_formLogin');
            if (check == false)
                return false;
            var form = this;
            $("#loginPage").slideUp(2000);
            $("#loader").slideDown(2000);
            setTimeout(function () {
                form.submit();
            }, 2000);
            return false;
        });
    })

    $(document).ready(function () {
        $("#loginPage").slideDown(1000);
    })




    // FORGOT STEP FINAL
    $(document).ready(function () {
        $("#formForgotPassword").submit(function () {
            $("#div_button_formForgotPassword").slideUp(500);
            if (firstPasswordValid('div_password_formForgotPassword') == false) {
                $("#div_password_formForgotPassword label").html("Password: Pattern error");
                $("#div_button_formForgotPassword").slideDown(500);
                return false;
            } else {
                $("#div_password_formForgotPassword label").html("Password");
            }
            if (confirmPassword('div_password_formForgotPassword', 'div_password_confirm_formForgotPassword') == true) {
                var password = $("#div_password_formForgotPassword input").val();
                var login = $("#div_email_formForgotPassword input").val();
                var sq = $("#div_secret_question_formForgotPassword input").val();
                var sqtId = $("#sqtId option:selected").val();
                $.ajax({
                        url:"/service/forgot-password-step-final?login="+login+"&sq="+sq+"&sqtId="+sqtId+"&password="+password,
                        type: "GET",
                        success: function (data) {
                            if (data == true) {
                                alert('Success');
                                $("#forgotPasswordPage").slideUp(1000);
                                $("#loginPage").slideDown(2000);
                            } else {
                                alert('Try again');
                            }
                        },
                        error:function () {
                            alert('error');
                            return false;
                        }
                });
            } else {
                $("#div_button_formForgotPassword").slideDown(500);
                return false;
            }
            clearInputInForm("formForgotPassword");
            $("#div_button_formForgotPassword").slideDown(4000);
            return false;
        });
    });

    // FORGOT STEP TWO
    $(document).ready(function () {
        $("#div_secret_question_formForgotPassword input").blur(function () {
            if (sqValid('div_secret_question_formForgotPassword') == false) {
                $("#div_secret_question_formForgotPassword label").html('Secret question: Pattern error');
                $("#forgotPassword_step_final").hide();
            } else {
                var login = $("#div_email_formForgotPassword input").val();
                var sq = $("#div_secret_question_formForgotPassword input").val();
                var sqtId = $("#sqtId option:selected").val();
                    $.ajax({
                        url: "/service/forgot-password-step-two?login="+login+"&sq="+sq+"&sqtId="+sqtId,
                        type: "GET",
                        beforeSend:function () {
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
        })
    })

    // FORGOT STEP ONE
    $(document).ready(function () {
        $("#div_email_formForgotPassword input").blur(function () {
            if (loginValid('div_email_formForgotPassword') == false) {
                $("#div_email_formForgotPassword label").html('Email: Pattern error');
                $("#forgotPassword_step_one").hide();
            } else {
                $.ajax({
                    url:"/service/forgot-password-step-one",
                    type: "GET",
                    data:"login=" + $("#div_email_formForgotPassword input").val(),
                    beforeSend:function () {
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
        })
    })

    // SHOW HIDE PASSWORD
    $(document).ready(function () {
        $("#hrefShowHidePassword").click(function () {
            var type = $("#div_password_formForgotPassword input").attr("type");
            if (type == 'password') {
                $("#hrefShowHidePassword").html('Hide password and confirm password');
                $("#div_password_formForgotPassword input, #div_password_confirm_formForgotPassword input").attr("type", "text");
            } else {
                $("#hrefShowHidePassword").html('Show password and confirm password');
                $("#div_password_formForgotPassword input, #div_password_confirm_formForgotPassword input").attr("type", "password");
            }
        })
    })

    $(document).ready(function () {
        $("#forgotPassword_return_step_one").click(function () {
            $("#stepTwo").slideUp(1000);
            $("#stepOne").slideDown(2000);
        })
    })

    $(document).ready(function () {
        $("#forgotPassword_return_step_two").click(function () {
            $("#stepFinal").slideUp(1000);
            $("#stepTwo").slideDown(2000);
        })
    })

    $(document).ready(function () {
        $("#forgotPassword_step_one").click(function () {
            $("#stepOne").slideUp(1000);
            $("#stepTwo").slideDown(2000);
        })
    })

    $(document).ready(function () {
        $("#forgotPassword_step_final").click(function () {
            $("#stepTwo").slideUp(1000);
            $("#stepFinal").slideDown(2000);
        })
    })

    $(document).ready(function () {
        $("#returnToLogin").click(function () {
            $("#forgotPasswordPage").slideUp(1000);
            $("#loginPage").slideDown(2000);
        })
    })

    $(document).ready(function () {
        $("#aForgotPassword").click(function () {
            $("#loginPage").slideUp(1000);
            $("#forgotPasswordPage").slideDown(2000);
        })
    })

    function validLoginPage(divEmail, divPassword) {
        var email = $("#"+divEmail+" input").val();
        var password = $("#"+divPassword+ " input").val();
        email = loginValid(divEmail);
        password = firstPasswordValid(divPassword);
        if (email == false || password == false)
            return false;
        return true;
    }

    function clearInputInForm(div) {
        $("#"+div).find("div div input").val("");
        $("#"+div).find("div div span").hide();
        $("#stepTwo, #stepFinal, #forgotPassword_step_one, #forgotPassword_step_final").slideUp();
        $("#stepOne").slideDown(2000);
    }

    function sqValid(divSq) {
        var sq = $("#"+divSq+" input").val();
        var pattern = /^[a-zA-Z0-9-/]{1,32}$/;
        if (pattern.test(sq) == false) {
            error(true, divSq);
            return false;
        } else {
            error(false, divSq);
            return true;
        }
    }

    function error(error, divId) {
        if (error == true) {
            $("#"+divId+" span:last-child").show();
            $("#"+divId+" input").next().hide();
        } else {
            $("#"+divId+" input").next().show();
            $("#"+divId+" span:last-child").hide();
        }
    }

    function loginValid(divEmail) {
        var email = $("#"+divEmail+" input").val();
        var emailPattern = /^[a-zA-Z0-9._-]{1,40}@[a-zA-Z0-9.-]{1,40}\.[a-zA-Z]{2,6}$/;
        if (emailPattern.test(email) == false) {
            error(true, divEmail);
            return false;
        } else {
            error(false, divEmail);
            return true;
        }
    }

    function firstPasswordValid(divPassword) {
        var password = $("#"+divPassword+" input").val();
        var onlyCharacter = /^[a-zA-Z0-9]{4,32}$/;
        if (onlyCharacter.test(password) == false) {
            error(true, divPassword);
            return false;
        } else {
            error(false, divPassword);
            return true;
        }
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

    $(document).ready(function () {
        $("label, a, h3").css("color", "#fbfff9");
    })
</script>
</body>
</html>
