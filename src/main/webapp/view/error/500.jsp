<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.06.2017
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Http status 500</title>
    <script src="<c:url value="/resources/JQuery/jquery-3.2.1.min.js" />"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
</head>
<body>

<div class="text-center">
    <img src="/resources/img/500.jpg"/>
    <h1>
        <a style="color: #9400af" href="/user/id${authUser.id}">Back to home</a>
    </h1>
</div>

</body>
</html>
