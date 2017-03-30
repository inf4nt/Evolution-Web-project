<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.03.2017
  Time: 1:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Create secret question type</title>
</head>
<body>
<%@include file="../index/header.jsp" %>

<br/>
<div class="col-md-9">
    <div class="row">
        <div class="col-lg-4 col-lg-offset-4">
            <form:form method="POST" action="/admin/create-sqt" commandName="form" cssClass="text-center">
                <input type="text" placeholder="secret question type" name="sqt" class="form-control"/>
                <form:errors path="sqt" cssStyle="color: #ff0000;"/>
                <br/>
                <div class="text-center">
                    <input type="submit" value="save" class="btn btn-success">
                </div>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>
