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
    <title>All users</title>

    <script>
        function maxPage() {
            var max = ${maxPage};
            if (max%10 == 0 ){
                max = max/10 - 1;
            } else
                max = max/10;

           return max;
        }
    </script>


</head>
<body>
<%@include file="../index/header.jsp" %>


<div class="col-md-9">
    <div class="row">
        <div class="col-md-4">
            <ul class="pagination justify-content-end pull-left">
                <c:if test="${maxPage >= 10}">
                    <li class="page-item">
                        <c:if test="${pageNumber > 0}">
                            <a class="page-link" href="/user/form-all/${role}/${pageNumber - 1}">Prev</a>
                        </c:if>
                        <c:if test="${pageNumber <= 0}">
                            <a class="page-link">Prev</a>
                        </c:if>
                    </li>

                    <li class="page-item">
                        <a class="page-link">${pageNumber}</a>
                    </li>

                    <li class="page-item ">

                        <c:if test="${pageNumber >= maxPage}">
                            <a class="page-link">Next</a>
                        </c:if>
                        <c:if test="${pageNumber < maxPage}">
                            <a class="page-link" href="/user/form-all/${role}/${pageNumber + 1}">Next</a>
                        </c:if>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <thead>
                <tr>
                    <%--<td style="color: #fbfff9">Id: </td>--%>
                    <%--<td style="color: #fbfff9">Email: </td>--%>
                        <td>Id: </td>
                        <td>Email: </td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="a" items="${list}">
                    <tr>
                        <%--<td style="color: #fbfff9; width: 2%">${a.getId()}</td>--%>
                        <%--<td style="color: #fbfff9; width: 2%">${a.getLogin()}</td>--%>
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
</div>
















<%--<div class="container">--%>
    <%--<div class="row">--%>

        <%--<ul class="pagination justify-content-end pull-left">--%>

            <%--<li class="page-item">--%>
                <%--<c:if test="${pageNumber > 0}">--%>
                    <%--<a class="page-link" href="/user/form-all/${role}/${pageNumber - 1}">Prev</a>--%>
                <%--</c:if>--%>
                <%--<c:if test="${pageNumber <= 0}">--%>
                    <%--<a class="page-link">Prev</a>--%>
                <%--</c:if>--%>
            <%--</li>--%>

            <%--<li class="page-item">--%>
                <%--<a class="page-link">${pageNumber}</a>--%>
            <%--</li>--%>

            <%--<li class="page-item ">--%>
                <%--<c:if test="${pageNumber >= maxPage}">--%>
                    <%--<a class="page-link">Next</a>--%>
                <%--</c:if>--%>
                <%--<c:if test="${pageNumber < maxPage}">--%>
                    <%--<a class="page-link" href="/user/form-all/${role}/${pageNumber + 1}">Next</a>--%>
                <%--</c:if>--%>
            <%--</li>--%>

        <%--</ul>--%>


        <%--<table class="table table-hover">--%>
            <%--<thead>--%>
            <%--<tr>--%>
                <%--<td>Id: </td>--%>
                <%--<td>Email: </td>--%>
            <%--</tr>--%>
            <%--</thead>--%>
            <%--<tbody>--%>
            <%--<c:forEach var="a" items="${list}">--%>
                <%--<tr>--%>
                    <%--<td style="width: 2%">${a.getId()}</td>--%>
                    <%--<td style="width: 2%">${a.getLogin()}</td>--%>
                    <%--<td class="col-xs-1"><a href="/user/form-my-profile/${a.getId()}" class="btn btn-info pull-right">Profile</a></td>--%>
                    <%--<sec:authorize access="hasRole('ROLE_ADMIN')">--%>
                        <%--<td style="width: 1%"><a href="/admin/remove-user/${a.getId()}" class="btn btn-danger pull-right">Remove</a></td>--%>
                    <%--</sec:authorize>--%>
                <%--</tr>--%>
            <%--</c:forEach>--%>
            <%--</tbody>--%>
        <%--</table>--%>

    <%--</div>--%>
<%--</div>--%>



</body>
</html>
