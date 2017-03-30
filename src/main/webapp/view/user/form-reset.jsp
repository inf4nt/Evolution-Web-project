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
<h1 class="text-center text-danger">This service does not work. Try later</h1>


<%--<div>--%>
    <%--<sec:authorize access="isAuthenticated()">--%>
        <%--<p>Sorry ${username}. You is authenticated</p>--%>
        <%--<p>If you want reset password, press logout</p>--%>
        <%--<form action="/logout">--%>
            <%--<input value="logout" type="submit"/>--%>
        <%--</form>--%>
    <%--</sec:authorize>--%>
<%--</div>--%>

<%--<c:if test="${step == 'one' || step == 'three'}">--%>
    <%--<div>--%>
        <%--<hr/>--%>
        <%--<h1>Reset password. Step one</h1>--%>
        <%--<hr/>--%>
    <%--</div>--%>

    <%--<div>--%>
        <%--<sec:authorize access="isAuthenticated() == false">--%>

            <%--<c:if test="${step == 'three'}">--%>
                <%--<h1 style="color: #58ae00">Successful</h1>--%>
                <%--<form action="/">--%>
                    <%--<input type="submit" value="login">--%>
                <%--</form>--%>
            <%--</c:if>--%>

            <%--<c:forEach var="e" items="${error}">--%>
                <%--<p style="color: #dc0500">${e}</p>--%>
            <%--</c:forEach>--%>

            <%--<form action="/user/reset-password" method="get">--%>
                <%--<br/>Login<input type="text" name="username">--%>
                <%--</select>--%>
                <%--<br/> Secret question type:<select name="sqt">--%>
                <%--<option value="PetName">Pet's name</option>--%>
                <%--<option value="MotherMaidenName">Mother's maiden name</option>--%>
            <%--</select>--%>
                <%--<br/> Secret question <input type="text" name="sq">--%>
                <%--<br/><input type="submit" value="reset">--%>
            <%--</form>--%>
        <%--</sec:authorize>--%>
    <%--</div>--%>
<%--</c:if>--%>


<%--<c:if test="${step == 'two'}">--%>
    <%--<div>--%>
        <%--<hr/>--%>
        <%--<h1>Reset password. Step two</h1>--%>
        <%--<hr/>--%>
    <%--</div>--%>
    <%--<c:forEach var="e" items="${error}">--%>
        <%--<p style="color: #dc0500">${e}</p>--%>
    <%--</c:forEach>--%>
    <%--<h3>Okey ${username} i'm found you</h3>--%>
    <%--<form action="/user/reset-password">--%>
        <%--<br/> New password: <input type="password" name="newPassword"/>--%>
        <%--<br/> Confirm password: <input type="password" name="confirmPassword"/>--%>
        <%--<br/><input type="submit" value="reset password">--%>
    <%--</form>--%>
<%--</c:if>--%>



</body>
</html>
