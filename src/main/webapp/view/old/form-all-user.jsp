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

    <title>All users</title>
</head>
<body>
<%@include file="../index/header.jsp" %>



<div class="container">
    <div class="row">

        <ul class="pagination justify-content-end pull-left">

            <li class="page-item">
                <a class="page-link" href="/user/form-all/${role}/prev">Prev</a>
            </li>

            <li class="page-item">
                <a class="page-link">${pageNumber}</a>
            </li>

            <li class="page-item">
                <a class="page-link" href="/user/form-all/${role}/next">Next</a>
            </li>


            <form role="form" action="/user/form-all/${role}" class="col-lg-2 col-md-3 col-sm-4 col-xs-4 navbar-center">
                <div class="input-group">
                    <input type="text" placeholder="page..." class="form-control input-xs" name="numberPage">
                    <div class="input-group-btn">
                        <button type="submit" formaction="/user/form-all/${role}" class="btn btn-search btn-info">
                            <span class="glyphicon glyphicon-search"></span>
                            <span class="label-icon">Go</span>
                        </button>
                    </div>
                </div>
            </form>


        </ul>

        <table class="table table-hover">
            <thead>
            <tr>
                <td>Id: </td>
                <td>Email: </td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="a" items="${list}">
                <tr>
                    <td style="width: 2%">${a.getId()}</td>
                    <td style="width: 2%">${a.getLogin()}</td>
                    <td class="col-xs-1"><a href="/user/form-my-profile/${a.getId()}" class="btn btn-info pull-right">Profile</a></td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td style="width: 1%"><a href="/admin/remove-user/${a.getId()}" class="btn btn-danger pull-right">Remove</a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
</div>



</body>
</html>
