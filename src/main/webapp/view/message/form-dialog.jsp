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
        .link {
            width: 450px; /* Ширина слоя */
            height: 100px; /* Высота слоя */
            /*background: #fc0; !* Цвет фона *!*/
        }
        .link a {
            display: block; /* Ссылка как блочный элемент */
            text-align: center; /* Выравнивание по центру */
            height: 100%; /* Высота на весь слой */
            /*color: #666; !* Цвет ссылки *!*/
        }
        li {
            list-style-type: none; /* Убираем маркеры */
        }
    </style>
</head>
<body>
<%@include file="../index/header.jsp" %>


<div id="div" class="col-lg-6 col-lg-offset-1 col-md-6 col-md-offset-1">

    <div class="col-md-12 col-sm-10">
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
                        <li class="link">
                            <a href="/im/dialog?sel=${a.getDialogPK().getSecond().getId()}">
                                <p class="text text-primary">
                                <hr/>
                                    ${a.getDialogPK().getSecond().getFirstName()} ${a.getDialogPK().getSecond().getLastName()}
                                <hr/>
                                </p>
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
