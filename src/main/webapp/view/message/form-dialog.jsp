<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.04.2017
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dialog</title>
    <style>

        li {
            list-style-type: none; /* Убираем маркеры */
        }
    </style>
</head>
<body>
<%@include file="../index/header.jsp" %>


<div id="div-message-dialog" class="col-lg-6 col-lg-offset-1 col-md-6 col-md-offset-1">

    <h4 class="text-primary">
        <span class="glyphicon glyphicon-th-list"></span>
        Dialog
        <a href="/user/id${authUser.getId()}">${authUser.getFirstName()} ${authUser.getLastName()}</a>
    </h4>

    <div class="col-md-12 col-sm-10 block-sms">

        <table class="table table-hover">
            <thead>
            <tr>
                <td></td>
                <td></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="a" items="${list}">
                <tr>
                    <td style="width: 2%">
                        <img data-src="holder.js/140x140" class="img-circle center-block" style="width: 100px; height: 100px;"
                             src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true">
                    </td>
                    <td style="width: 10%">
                        <li>
                            <a href="/im/${a.dialog.second.id}">
                                <p> ${a.dialog.second.firstName} ${a.dialog.second.lastName}</p>
                                <hr/>
                                <p class="text-muted"><span class="glyphicon glyphicon-pencil"></span>
                                        ${a.sender.firstName} ${a.sender.lastName}:
                                    <br/><br/> <span class="glyphicon glyphicon-envelope"></span> ${a.message} </p>
                                <hr/>
                            </a>
                        </li>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>



</body>
</html>
