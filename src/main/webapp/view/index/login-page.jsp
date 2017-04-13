<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Login</title>
  <script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.js" />"></script>
  <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
  <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
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

            <label id="email_error" class="control-label" style="color: #ffffff" for="login">Email</label>
            <div id="div_email"  class="form-group has-feedback">
              <input id="login" type="text" onblur="loginValid('login')" name="username" class="form-control text-center"/>
              <span id="span_email_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
              <span id="span_email_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
            </div>

            <label id="password_error" class="control-label" style="color: #ffffff" for="password">Password</label>
            <div id="div_password"  class="form-group has-feedback">
              <input onblur="firstPasswordValid('password')" id="password" type="password"  name="password" class="form-control text-center"/>
              <span id="span_password_ok" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="display: none"></span>
              <span id="span_password_error" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="display: none"></span>
            </div>


          <div class="form-group text-center">
            <td><a style="color: #fbfff9" href="/form-reset-password">Forgot password</a></td>
          </div>
          <div class="form-group text-center">
            <td><a style="color: #fbfff9" href="/form-create-user">Registration</a></td>
          </div>
          <div class="text-center">
              <button type="submit" class="btn btn-info" form="form" onclick="return validLoginPage('login', 'password')" style="width: 100%">Log in
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

</body>
</html>
