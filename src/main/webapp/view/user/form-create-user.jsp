<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Create user form</title>
</head>
<body>

<%@include file="../index/header.jsp" %>


<sec:authorize access="isAuthenticated()">
    <br/>
    <div class="col-md-9">
        <div class="row">
            <div class="col-lg-4 col-lg-offset-4">

                <form:form method="POST" action="/create-user" commandName="form" cssClass="text-center">
                    <div class="form-group">
                        <input type="text" placeholder="email" name="login" class="form-control text-center"/>
                        <form:errors path="login" cssStyle="color: #ff0000;" />
                    </div>
                    <div class="form-group">
                        <input type="password" placeholder="password" name="password" class="form-control text-center"/>
                        <form:errors path="password" cssStyle="color: #ff0000;" />
                    </div>
                    <div class="form-group">
                        <input type="text" placeholder="first name" name="firstName" class="form-control text-center"/>
                        <form:errors path="firstName" cssStyle="color: #ff0000;" />
                    </div>
                    <div class="form-group">
                        <input type="text" placeholder="last name" name="lastName" class="form-control text-center"/>
                        <form:errors path="lastName" cssStyle="color: #ff0000;" />
                    </div>
                    <div class="form-group">
                        <h4 class="text-center">Role</h4>
                        <select name="role" class="form-control">
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <option value="ADMIN">Admin</option>
                            </sec:authorize>
                            <option value="USER">User</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <h4 class="text-center">Secret question type</h4>
                        <select name="sqtId" class="form-control">
                            <c:forEach var = "a" items = "${sqt}">
                                <option value = ${a.getId()}> ${a.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <input type="text" placeholder="secret question" name="secretQuestion" class="form-control"/>
                    <form:errors path="secretQuestion" cssStyle="color: #ff0000;" />

                    <br/>
                    <div class="text-center">
                        <input type="submit" name="submit" value="registration" class="btn btn-success">
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
    <h1 class="text-center">Registration</h1>
    <div class="row">
        <div class="col-lg-4 col-lg-offset-4">

            <form:form method="POST" action="/create-user" commandName="form" cssClass="text-center">
                <div class="form-group">
                    <input type="text" placeholder="email" name="login" class="form-control text-center"/>
                    <form:errors path="login" cssStyle="color: #ff0000;" />
                </div>
                <div class="form-group">
                    <input type="password" placeholder="password" name="password" class="form-control text-center"/>
                    <form:errors path="password" cssStyle="color: #ff0000;" />
                </div>
                <div class="form-group">
                    <input type="text" placeholder="first name" name="firstName" class="form-control text-center"/>
                    <form:errors path="firstName" cssStyle="color: #ff0000;" />
                </div>
                <div class="form-group">
                    <input type="text" placeholder="last name" name="lastName" class="form-control text-center"/>
                    <form:errors path="lastName" cssStyle="color: #ff0000;" />
                </div>
                <div class="form-group">
                    <h4 class="text-center">Role</h4>
                    <select name="role" class="form-control">
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <option value="ADMIN">Admin</option>
                        </sec:authorize>
                        <option value="USER">User</option>
                    </select>
                </div>
                <div class="form-group">
                    <h4 class="text-center">Secret question type</h4>
                    <select name="sqtId" class="form-control">
                        <c:forEach var = "a" items = "${sqt}">
                            <option value = ${a.getId()}> ${a.getName()}</option>
                        </c:forEach>
                    </select>
                </div>
                <input type="text" placeholder="secret question" name="secretQuestion" class="form-control"/>
                <form:errors path="secretQuestion" cssStyle="color: #ff0000;" />

                <br/>
                <div class="text-center">
                    <input type="submit" name="submit" value="registration" class="btn btn-success">
                </div>
            </form:form>
        </div>
    </div>
</sec:authorize>


</body>
</html>
