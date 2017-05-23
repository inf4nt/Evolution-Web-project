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
                                <a href="/user/id${a.getId()}">${a.getFirstName()} ${a.getLastName()}</a>
                            </td>
                            <td style="width: 2%">
                                <ul class="menu">
                                    <li class="menu-item">
                                        <a href="#">
                                            <span class="glyphicon glyphicon-option-horizontal"/>
                                        </a>
                                        <ul class="submenu">
                                            <li class="submenu-item">
                                                <a href="/user/profile/${a.id}" >
                                                    <span class="glyphicon glyphicon-edit text-muted"></span> Edit
                                                </a>
                                                <%--<a href="/admin/remove-user/${a.id}" >--%>
                                                <a href="#" onclick="alert('coming soon')">
                                                    <span class="glyphicon glyphicon-remove text-danger"></span> Remove
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </td>
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
