<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Login</title>

  <%--<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">--%>
  <%--<script src="<c:url value="/resources/js/bootstrap.js" />"></script>--%>

  <script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.js" />"></script>
  <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
  <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">

  <%--<style>--%>
  <%--body {--%>
  <%--background: linear-gradient(to bottom, #ced1c4, #d5cccc);--%>
  <%--}--%>
  <%--</style>--%>
  <style>
    html { height: 100%; }
    body {
      margin: 0;
      height: 100%;
      background: url(http://daleki-zori.com.ua/wp-content/uploads/2016/05/maxresdefault.jpg);
      background-size: cover;
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
                <%--${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}--%>
            </h3>
          </c:if>
          <div>
            <h1 style="color: #14ae00">${info}</h1>
          </div>
        </div>
      </div>
      <!-- Login error -->
      <div class="row">
        <div class="col-lg-4 col-md-4 col-sm-3 col-xs-2"></div>
        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-8">
          <div class="col-lg-2"></div>
          <div class="col-lg-8">
            <form action="${pageContext.request.contextPath}/j_spring_security_check" method='POST'>
              <div class="form-group text-center">
                <%--<legend>Email</legend>--%>
                <input type="text" placeholder="email" name="username" class="form-control" value="com.infant@gmail.com">
              </div>
              <div class="form-group text-center">
                <%--<legend>Password</legend>--%>
                <input type="password" placeholder="password" name="password" class="form-control" value="77788877">
              </div>
              <%--<div class="form-group text-center">--%>
              <%--<label>Remember Me:</label> <input type="checkbox" name="remember-me"/>--%>
              <%--</div>--%>

              <div class="form-group text-center">
                <td><a href="/user/form-reset-password">Forgot password</a></td>
              </div>
              <div class="form-group text-center">
                <td><a href="/form-create-user">Registration</a></td>
              </div>
              <div class="text-center">
                <input class="btn btn-info" name="submit" type="submit" value="Log in" style="width: 100%">
              </div>
            </form>
          </div>
          <div class="col-lg-2"></div>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-3 col-xs-2"></div>
      </div>
    </div>



</body>
</html>






