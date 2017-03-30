<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17.03.2017
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<%@include file="../index/header.jsp" %>


<sec:authorize access="hasRole('ROLE_ADMIN')">
    <div class="col-md-9">
        <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                <div class="text-primary">
                    <br/>
                    <h4><strong><ins>Id</ins></strong></h4><h4>${user.getId()}</h4>
                    <h4><strong><ins>Email</ins></strong></h4><h4>${user.getLogin()}</h4>
                    <h4><strong><ins>Password</ins></strong></h4><h4>${user.getPassword()}</h4>
                    <h4><strong><ins>First name</ins></strong></h4><h4>${user.getFirstName()}</h4>
                    <h4><strong><ins>Last name</ins></strong></h4><h4>${user.getLastName()}</h4>
                    <%--<h4><strong><ins>Role</ins></strong></h4><h4>${user.getRole().getName()}</h4>--%>
                    <h4><strong><ins>Role</ins></strong></h4><h4>${user.getRoleId()}</h4>
                    <h4><strong><ins>Registration date</ins></strong></h4><h4>${user.getRegistrationDate()}</h4>
                    <h4><strong><ins>Secret question type</ins></strong></h4><h4>${user.getSecretQuestionType().getName()}</h4>
                    <h4><strong><ins>Secret question</ins></strong></h4><h4>${user.getSecretQuestion()}</h4>
                </div>
            </div>

            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                <form:form method="POST" action="/user/edit/${user.getId()}" commandName="form" cssClass="text-center">
                    <div class="form-group">
                        <br/> Email:
                        <input type="text" value="${user.getLogin()}" name="login" class="form-control text-center"/>
                        <form:errors path="login" cssStyle="color: #ff0000;" />
                    </div>
                    <br/> Password:
                    <input type="text" value="${user.getPassword()}" name="password" class="form-control"/>
                    <form:errors path="password" cssStyle="color: #ff0000;" />
                    <br/> First name:
                    <input type="text" value="${user.getFirstName()}" name="firstName" class="form-control"/>
                    <form:errors path="firstName" cssStyle="color: #ff0000;" />
                    <br/> Last name:
                    <input type="text" value="${user.getLastName()}" name="lastName" class="form-control"/>
                    <form:errors path="lastName" cssStyle="color: #ff0000;" />
                    <div class="form-group">
                        <br/> Role:
                        <select name="role" class="form-control">
                            <option value="USER">User</option>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <option value="ADMIN">Admin</option>
                            </sec:authorize>
                        </select>
                    </div>
                    <div class="form-group">
                        <br/> Secret question type:
                        <select name="sqtId" class="form-control">
                            <c:forEach var = "a" items = "${sqt}">
                                <option value = ${a.getId()}>${a.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <br/> Secret question:
                    <input type="text" value="${user.getSecretQuestion()}" name="secretQuestion" class="form-control"/>
                    <form:errors path="secretQuestion" cssStyle="color: #ff0000;" />
                    <br/>
                    <div class="text-center">
                        <input type="submit" name="submit" value="save" class="btn btn-success">
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_USER')">
    <div class="col-md-9">
        <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                <div class="text-primary">
                    <br/>
                    <h4><strong><ins>Id</ins></strong></h4><h4>${user.getId()}</h4>
                    <h4><strong><ins>First name</ins></strong></h4><h4>${user.getFirstName()}</h4>
                    <h4><strong><ins>Last name</ins></strong></h4><h4>${user.getLastName()}</h4>
                    <c:if test="${userid == user.getId()}">
                        <h4><strong><ins>Email</ins></strong></h4><h4>${user.getLogin()}</h4>
                        <h4><strong><ins>Password</ins></strong></h4><h4>${user.getPassword()}</h4>
                        <h4><strong><ins>Secret question type</ins></strong></h4><h4>${user.getSecretQuestionType().getName()}</h4>
                        <h4><strong><ins>Secret question</ins></strong></h4><h4>${user.getSecretQuestion()}</h4>
                    </c:if>
                    <%--<h4><strong><ins>Role</ins></strong></h4><h4>${user.getRole().getName()}</h4>--%>
                    <h4><strong><ins>Role</ins></strong></h4><h4>${user.getRoleId()}</h4>
                    <h4><strong><ins>Registration date</ins></strong></h4><h4>${user.getRegistrationDate()}</h4>
                </div>
            </div>

            <c:if test="${userid == user.getId()}">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">

                    <form:form method="POST" action="/user/edit/${user.getId()}" commandName="form" cssClass="text-center">
                        <div class="form-group">
                            <br/> Email:
                            <input type="text" value="${user.getLogin()}" name="login" class="form-control text-center"/>
                            <form:errors path="login" cssStyle="color: #ff0000;" />
                        </div>

                        <br/> Password:
                        <input type="text" value="${user.getPassword()}" name="password" class="form-control"/>
                        <form:errors path="password" cssStyle="color: #ff0000;" />
                        <br/> First name:
                        <input type="text" value="${user.getFirstName()}" name="firstName" class="form-control"/>
                        <form:errors path="firstName" cssStyle="color: #ff0000;" />
                        <br/> Last name:
                        <input type="text" value="${user.getLastName()}" name="lastName" class="form-control"/>
                        <form:errors path="lastName" cssStyle="color: #ff0000;" />
                        <div class="form-group">
                            <br/> Role:
                            <select name="role" class="form-control">
                                <option value="USER">User</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <br/> Secret question type:
                            <select name="sqtId" class="form-control">
                                <c:forEach var = "a" items = "${sqt}">
                                    <option value = ${a.getId()}>${a.getName()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <br/> Secret question:
                        <input type="text" value="${user.getSecretQuestion()}" name="secretQuestion" class="form-control"/>
                        <form:errors path="secretQuestion" cssStyle="color: #ff0000;" />
                        <br/>
                        <div class="text-center">
                            <input type="submit" name="submit" value="save" class="btn btn-success">
                        </div>
                    </form:form>
                </div>
            </c:if>
        </div>
    </div>
</sec:authorize>

</body>
</html>
