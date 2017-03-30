<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 24.03.2017
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Search</title>

    <%--<spring:url value="/user/search" var="pageurl" />--%>
    <spring:url value="${page_url}" var="pageurl" />



</head>
<body>
<%@include file="../index/header.jsp" %>
<c:set var="pageListHolder" value="${productList}" scope="session"/>

<c:if test="${productList != null}">
    <div class="col-md-9">
        <div class="row">
            <div class="col-md-4">
                <ul class="pagination justify-content-end pull-left">
                    <li class="page-item">
                        <c:if test="${pageListHolder.isFirstPage()}">
                            <a class="page-link">First</a>
                        </c:if>
                        <c:if test="${!pageListHolder.isFirstPage()}">
                            <a class="page-link" href="${pageurl}/0">First</a>
                        </c:if>

                    </li>
                    <li class="page-item">
                        <c:if test="${pageListHolder.isFirstPage()}">
                            <a class="page-link">Prev</a>
                        </c:if>
                        <c:if test="${!pageListHolder.isFirstPage()}">
                            <%--<a class="page-link" href="${pageurl}/prev">Prev</a>--%>
                            <a class="page-link" href="${pageurl}/${productList.getPage() - 1}">Prev</a>
                        </c:if>
                    </li>
                    <li class="page-item">
                        <a class="page-link">${pageListHolder.getPage()}</a>
                    </li>
                    <li class="page-item">
                        <c:if test="${pageListHolder.isLastPage()}">
                            <a class="page-link">Next</a>
                        </c:if>
                        <c:if test="${!pageListHolder.isLastPage()}">
                            <%--<a class="page-link" href="${pageurl}/next">Next</a>--%>
                            <a class="page-link" href="${pageurl}/${productList.getPage() + 1}">Next</a>
                        </c:if>
                    </li>
                    <li class="page-item">
                        <c:if test="${pageListHolder.isLastPage()}">
                            <a class="page-link">Last</a>
                        </c:if>
                        <c:if test="${!pageListHolder.isLastPage()}">
                            <a class="page-link" href="${pageurl}/${pageListHolder.getPageCount() - 1}">Last</a>
                        </c:if>
                    </li>
                </ul>
            </div>
        </div>
        <%--<div class="row">--%>
            <%--<div class="col-md-12">--%>
                <%--<table class="table table-hover">--%>
                    <%--<thead>--%>
                    <%--<tr>--%>
                        <%--<td>Id: </td>--%>
                        <%--<td>Email: </td>--%>
                    <%--</tr>--%>
                    <%--</thead>--%>
                    <%--<tbody>--%>
                    <%--<c:forEach var="a" items="${pageListHolder.pageList}">--%>
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
        <div class="row">
            <div class="col-md-14">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <td>Id: </td>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <td>Email: </td>
                        </sec:authorize>
                        <td>First name: </td>
                        <td>Last name: </td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="a" items="${pageListHolder.pageList}">
                        <tr>
                            <td style="width: 2%">${a.getId()}</td>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <td style="width: 10%">${a.getLogin()}</td>
                            </sec:authorize>
                            <td style="width: 4%">${a.getFirstName()}</td>
                            <td style="width: 4%">${a.getLastName()}</td>
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
</c:if>


</body>
</html>
