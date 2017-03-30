<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.03.2017
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>

    <%--<script>--%>
        <%--$(document).ready(function () {--%>
            <%--$('.dropdown-toggle').dropdown();--%>
        <%--});--%>
    <%--</script>--%>

    <%--<script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.js" />"></script>--%>
    <%--<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>--%>
    <%--<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">--%>

    <spring:url value="/user/form-all-user2" var="pageurl" />
    <title>All users</title>
</head>
<body>
<%@include file="../index/header.jsp" %>
<h1>${time}</h1>

<div class="container">

    <ul class="pagination justify-content-end pull-left">
        <li class="page-item">
            <a class="page-link" href="${pageurl}/prev">Prev</a>
        </li>

        <li class="page-item">
            <a class="page-link">${pageListHolder.getPage()}</a>
        </li>

        <li class="page-item">
            <a class="page-link" href="${pageurl}/next">Next</a>
        </li>
    </ul>

    <div>
        <c:set var="pageListHolder" value="${productList}" scope="session" />
        <table class="table table-hover">
            <thead>
            <tr>
                <td>Id: </td>
                <td>Email: </td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="a" items="${pageListHolder.pageList}">
                <tr>
                    <td style="width: 2%">${a.getId()}</td>
                    <td style="width: 2%">${a.getLogin()}</td>
                    <td class="col-xs-1"><a href="/user/form-my-profile/${a.getId()}" class="btn btn-info pull-right">Profile</a></td>
                    <td style="width: 1%"><a href="/admin/remove-user/${a.getId()}" class="btn btn-danger pull-right">Remove</a></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>



</body>
</html>
