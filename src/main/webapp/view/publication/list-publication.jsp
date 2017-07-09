<%--
  Created by IntelliJ IDEA.
  User: Infant
  Date: 09.07.2017
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Publication list</title>
</head>
<body>
<%@include file="../index/header.jsp" %>
<c:set var="count" value="0"/>


<div class="col-md-9 col-lg-offset-2">

    <p class="pull-left">
        Publication
        <a href="/user/id${list.get(0).getSender().getId()}">
            ${list.get(0).getSender().getFirstName()} ${list.get(0).getSender().getLastName()}
        </a>
    </p>
    <br/>
    <table id="table-friend" class="table">
        <thead>
        <tr>
            <td style="width: 40%">
                <p class="text-center">
                    Subject
                </p>
            </td>
            <td style="width: 25%">
                Sender
            </td>
            <td>
                Date
            </td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="a" items="${list}">
            <tr>
                <td>
                    <a href="/publication/${a.id}/get/view">
                        ${a.subject}
                    </a>
                </td>
                <td>
                    <a href="/user/id${a.sender.id}">${a.sender.firstName} ${a.sender.lastName}</a>
                </td>
                <td>
                    ${a.date}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <hr/>

</div>

</body>
<script>
    $(document).ready(function () {
        $(".pagination-btn").click(function () {
            $(".pagination li").removeClass("active");
            var id = this.name;
            var ul = $("#pagination-content ul[data-page=" + id + "]");
            $(".pagination-content").addClass("hidden");
            ul.removeClass("hidden");
            $(".pagination li[name=" + id + "]").addClass("active");
        })
    })
</script>
</html>
