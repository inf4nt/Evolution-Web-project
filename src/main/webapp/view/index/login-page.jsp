<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Login</title>
  <script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.js" />"></script>
  <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
  <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
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
  </style>
</head>
<body>

<div class="container">
  <div class="row">
    <div class="col-lg-12 text-center" style="margin-top: 15%">
      <h1 style="color: #fbfff9">Login</h1>
      <c:if test="${param.error == 'true'}">
        <h3 style="color: #fbfff9">Login Failed!
        </h3>
      </c:if>
      <div>
        <h1 style="color: #14ae00">${info}</h1>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-2"></div>
    <div class="col-lg-4 col-md-4 col-sm-6 col-xs-8">
      <div class="col-lg-2"></div>
      <div class="col-lg-8">
        <form id ="form" action="${pageContext.request.contextPath}/j_spring_security_check"  method='POST'>

          <p id="username_error" style="color: #ffffff"></p>
          <div id="div_username"  class="form-group has-feedback">
            <input onblur="loginValid()" id="username" type="text" placeholder="email" value="com.infant@gmail.com" name="username" class="form-control"/>
            <span id="span_username_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
            <span id="span_username_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
          </div>

          <p id="password_error" style="color: #ffffff"></p>
          <div id="div_password"  class="form-group has-feedback">
            <input onblur="passwordValid()" id="password" type="password" placeholder="password" value="77788877" name="password" class="form-control">
            <span id="span_password_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
            <span id="span_password_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
          </div>

          <div class="form-group text-center">
            <td><a style="color: #fbfff9" href="/user/form-reset-password">Forgot password</a></td>
          </div>
          <div class="form-group text-center">
            <td><a style="color: #fbfff9" href="/form-create-user">Registration</a></td>
          </div>
          <div class="text-center">
              <button type="submit" class="btn-info" form="form" onclick="return validLogin(document.getElementById('form'))" style="width: 100%">Log in
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


<address style="color: #ffffff" class="down">
  <strong>EvolutionMVC</strong><br>
  Author: Maksim Lukaretskiy<br>
  Address: Ukraine, Odessa<br>
  <abbr title="Phone">Phone:</abbr> (+38) 093 53-68-034
  <br/><abbr title="Phone">Skype:</abbr> s4rgeist
</address>

<script>
    function loginValid() {
        var result = false;
        var username = document.getElementById("username").value;
        var emailPattern = /^[a-zA-Z0-9._-]{1,40}@[a-zA-Z0-9.-]{1,40}\.[a-zA-Z]{2,6}$/;
        if (emailPattern.test(username) == false || username.length > 48) {
            document.getElementById("username_error").innerHTML = "email error pattern";
            document.getElementById("div_username").classList.add("has-error");
            document.getElementById("span_username_error").style.display = "block";
            document.getElementById("span_username_ok").style.display = "none";
            return result;
        }
        else {
            document.getElementById("username_error").innerHTML = "";
            document.getElementById("div_username").classList.add("has-success");
            document.getElementById("div_username").classList.remove("has-error");
            document.getElementById("span_username_error").style.display = "none";
            document.getElementById("span_username_ok").style.display = "block";
        }
    }


    function passwordValid() {
        var password = document.getElementById("password").value;
        var onlyCharacter = /^[a-zA-Z0-9]{4,32}$/;
        var result = false;
        if (password.length < 4 || password.length > 32 || !onlyCharacter.test(password)) {
            document.getElementById("password_error").innerHTML = "password must be between 4-32";
            document.getElementById("div_password").classList.add("has-error");
            document.getElementById("span_password_error").style.display = "block";
            document.getElementById("span_password_ok").style.display = "none";
            return result;
        }
        else {
            document.getElementById("password_error").innerHTML = "";
            document.getElementById("div_password").classList.add("has-success");
            document.getElementById("div_password").classList.remove("has-error");
            document.getElementById("span_password_error").style.display = "none";
            document.getElementById("span_password_ok").style.display = "block";
        }
    }

    function validLogin () {
        var username = loginValid();
        var password = passwordValid();
        if (username == false || password == false)
            return false;
        return true;
    }
</script>

</body>
</html>
