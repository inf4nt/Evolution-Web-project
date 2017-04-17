<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 02.04.2017
  Time: 1:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <c:set var="pageListHolder" value="${productList}" scope="session"/>
    <c:choose>
        <c:when test="${pageListHolder.pageList.size() > 0 }">
            <c:set var="myFirstName" value="${pageListHolder.pageList.get(0).getUserFirstName()}" />
            <c:set var="myLastName" value="${pageListHolder.pageList.get(0).getUserLastName()}" />
        </c:when>
    </c:choose>
    <spring:url value="${page_url}" var="pageurl" />
    <title>${myFirstName} ${myLastName} ${friendStatus}</title>
</head>
<body>
<%@include file="../index/header.jsp" %>



<div class="col-md-9">

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

    <div class="col-md-3 pull-right">
        <br/>
        <a href="/user/id/${id}" >${myFirstName} ${myLastName}</a>
        <br/><br/>
        <span class="glyphicon glyphicon-user"></span><a href="/user/${id}/friend/start" methods="get"> Friends</a>
        <br/>
        <span class="glyphicon glyphicon-share-alt"></span><a href="/user/${id}/follower/start" methods="get"> Followers</a>
        <br/>
        <sec:authorize access="hasRole('ROLE_USER')">
            <c:if test="${authUser.getId() == id}">
                <span class="glyphicon glyphicon-question-sign"></span><a href="/user/${authUser.getId()}/request/start" methods="get"> Friend requests</a>
            </c:if>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <span class="glyphicon glyphicon-question-sign"></span><a href="/user/${id}/request/start" methods="get"> Friend requests</a>
        </sec:authorize>

    </div>

    <div class="col-md-8">
        <table class="table table-hover">
            <thead>
            <tr>
                <td></td>
                <td></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="a" items="${pageListHolder.pageList}">
                <tr>
                    <td style="width: 2%">
                        <img data-src="holder.js/140x140" class="img-circle center-block" style="width: 100px; height: 100px;"
                             src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true">
                    </td>
                    <td style="width: 10%">
                        <a href="/user/id/${a.getId()}">${a.getFirstName()} ${a.getLastName()}</a>
                    </td>
                    <td style="width: 2%">
                        <c:if test="${authUser.getId() == id}">
                            <ul class="menu">
                                <li class="menu-item">
                                    <a href="#">
                                        <span class="glyphicon glyphicon-option-horizontal"/>
                                    </a>
                                    <ul class="submenu">
                                        <li class="submenu-item big-submenu-item">
                                            <c:if test="${friendStatus == 'friend'}">
                                                <a href="/user/friend-action/delete-friend/${a.getId()}">
                                                    <span class="glyphicon glyphicon-remove text-danger"></span> Delete friend
                                                </a>
                                            </c:if>
                                            <c:if test="${friendStatus == 'request'}">
                                                <a href="/user/friend-action/delete-request/${a.getId()}">
                                                    <span class="glyphicon glyphicon-remove text-danger"></span> Delete request
                                                </a>
                                            </c:if>
                                            <c:if test="${friendStatus == 'follower'}">
                                                <a href="/user/friend-action/accept-friend/${a.getId()}">
                                                    <span class="glyphicon glyphicon-plus text-success"></span> Accept request
                                                </a>
                                            </c:if>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>