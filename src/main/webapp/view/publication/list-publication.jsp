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
    <title>Publication ${user.firstName} ${user.lastName}</title>
</head>
<body>
<%@include file="../index/header.jsp" %>
<c:set var="count" value="0"/>


<div class="col-md-9 col-lg-offset-2 block-background">
    <p style="font-size:large;" class="pull-left">
        Publication
        <a href="/user/id${user.id}" style="color: white">
            ${user.firstName} ${user.lastName}
        </a>
    </p>
    <br/>
    <br/>
    <br/>
    <c:choose>
        <c:when test="${!empty list}">
            <table id="table-friend" class="table">
                <thead>
                <tr>
                    <td style="width: 40%;color: white;">
                        <p class="text-center">
                            Subject
                        </p>
                    </td>
                    <td style="width: 25%; color: white;">
                        Sender
                    </td>
                    <td style="color: white;">
                        Category
                    </td>
                    <td style="color: white;">
                        Date
                    </td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="a" items="${list}">
                    <tr>
                        <td>
                            <a style="color: white;" href="/publication/${a.id}/get/view">
                                    ${fn:substring(a.subject, 0, 40)}
                            </a>
                        </td>
                        <td>
                            <a style="color: white;" href="/user/id${a.sender.id}">
                                    ${a.sender.firstName} ${a.sender.lastName}
                            </a>
                        </td>
                        <td style="color: white;">
                                ${a.categoryName}
                        </td>
                        <td style="color: white;">
                                ${a.date}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p style="font-size:large;">Publication not found</p>
        </c:otherwise>
    </c:choose>
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
