<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 02.04.2017
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Search</title>
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
        <div class="row">
            <div class="col-md-14">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <td>Id: </td>
                        <td>First name: </td>
                        <td>Last name: </td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="a" items="${pageListHolder.pageList}">
                        <tr>
                            <td style="width: 2%">${a.getId()}</td>
                            <td style="width: 4%">${a.getFirstName()}</td>
                            <td style="width: 4%">${a.getLastName()}</td>
                            <td class="col-xs-1"><a href="/user/form-my-profile/${a.getId()}" class="btn btn-info pull-right">Profile</a></td>
                            <c:if test="${a.getFriendStatus() == 'NO_MATCHES'}">
                                <td style="width: 1%" class="col-xs-1"><a href="/user/friend-action/request-friend/${userid}/${a.getId()}" class="btn btn-info pull-right">Request</a></td>
                            </c:if>
                            <c:if test="${a.getFriendStatus() == 'PROGRESS'}">
                                <td style="width: 1%" class="col-xs-1">
                                    <a href="/user/friend-action/delete-friend/${userid}/${a.getId()}" class="btn btn-danger pull-right">Delete friend</a>
                                </td>
                            </c:if>
                            <c:if test="${a.getFriendStatus() == 'FOLLOWER'}">
                                <td style="width: 1%" class="col-xs-1">
                                    <a href="/user/friend-action/accept-friend/${userid}/${a.getId()}" class="btn btn-success pull-right">Accept friend</a>
                                </td>
                            </c:if>
                            <c:if test="${a.getFriendStatus() == 'REQUEST'}">
                                <td style="width: 1%" class="col-xs-1">
                                    <a href="/user/friend-action/delete-request/${userid}/${a.getId()}" class="btn btn-danger pull-right">Delete request</a>
                                </td>
                            </c:if>

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
