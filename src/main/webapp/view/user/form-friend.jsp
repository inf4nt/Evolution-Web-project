
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20.05.2017
  Time: 0:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <c:set value="${limit}" var="limit"/>
    <title>Friends ${user.firstName} ${user.lastName}</title>
</head>
<body>
<%@include file="../index/header.jsp" %>

<div class="col-md-10 col-lg-offset-2 div-white">

    <div class="col-md-3 pull-right">
        <div class="block-background" style="width: 70%">
            <br/>
            <p class="text-center">
                <a href="/user/id${user.id}" >${user.firstName} ${user.lastName}</a>
                <br/><br/>
                <span class="glyphicon glyphicon-user"></span><a id="open-friend" href="#" > Friends</a>
                <br/>
                <span class="glyphicon glyphicon-share-alt"></span><a id="open-follower" href="#" > Followers</a>
                <br/>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <c:if test="${authUser.id == user.id}">
                        <span class="glyphicon glyphicon-question-sign"></span><a id="open-request" href="#" > Friend request</a>
                    </c:if>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <span class="glyphicon glyphicon-question-sign"></span><a id="open-request" href="#" > Friend request</a>
                </sec:authorize>
            </p>
        </div>
    </div>
    <div class="friend" id="div-friend" style="display: none">
        <c:if test="${progress.size() > 0}">
            <div class="col-md-8 block-background">
                <p class="pull-left">Friends</p>
                <br/>
                <table id="table-friend" class="table">
                    <thead>
                    <tr>
                        <td></td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="a" items="${progress}">
                        <tr>
                            <td style="width: 2%">
                                <img data-src="holder.js/140x140" class="img-circle center-block" style="width: 100px; height: 100px;"
                                     src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true">
                            </td>
                            <td style="width: 10%">
                                <a href="/user/id${a.friend.id}">${a.friend.firstName} ${a.friend.lastName}</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <hr/>
                <c:if test="${progress.size() >= limit}">
                    <div id="more-friend">
                        <a href="#">
                            <p class="text-center" ><span class="glyphicon glyphicon-download"></span> more</p>
                        </a>
                    </div>
                </c:if>
            </div>
        </c:if>
    </div>

    <div class="friend" id="div-follower" style="display: none">
        <c:if test="${follower.size() > 0}">
            <div class="col-md-8 block-background">
                <p class="pull-left">Followers</p>
                <br/>
                <table id="table-follower" class="table">
                    <thead>
                    <tr>
                        <td></td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="a" items="${follower}">
                        <tr>
                            <td style="width: 2%">
                                <img data-src="holder.js/140x140" class="img-circle center-block" style="width: 100px; height: 100px;"
                                     src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true">
                            </td>
                            <td style="width: 10%">
                                <a href="/user/id${a.friend.id}">${a.friend.firstName} ${a.friend.lastName}</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <hr/>
                <c:if test="${follower.size() >= limit}">
                    <div id="more-follower">
                        <a href="#">
                            <p class="text-center" ><span class="glyphicon glyphicon-download"></span> more</p>
                        </a>
                    </div>
                </c:if>
            </div>
        </c:if>
    </div>

    <div class="friend" id="div-request" style="display: none">
        <c:if test="${request.size() > 0}">
            <div class="col-md-8 block-background">
                <p class="pull-left">Request</p>
                <br/>
                <table id="table-request" class="table">
                    <thead>
                    <tr>
                        <td></td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="a" items="${request}">
                        <tr>
                            <td style="width: 2%">
                                <img data-src="holder.js/140x140" class="img-circle center-block" style="width: 100px; height: 100px;"
                                     src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true">
                            </td>
                            <td style="width: 10%">
                                <a href="/user/id${a.friend.id}">${a.friend.firstName} ${a.friend.lastName}</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <hr/>
                <c:if test="${request.size() >= limit}">
                    <div id="more-request">
                        <a href="#">
                            <p class="text-center" ><span class="glyphicon glyphicon-download"></span> more</p>
                        </a>
                    </div>
                </c:if>
            </div>
        </c:if>
    </div>

</div>





<script>

    var countFriend = 0;
    var countFollower = 0;
    var countRequest = 0;
    var limit = ${limit};

    $(document).ready(function () {

        $("span").css("color", "white");

        $("#div-friend").fadeToggle(1000);

        $("#more-friend a").click(function () {
            countFriend = countFriend + 1;
            var offset = countFriend * limit;
            ajaxFriend('more-friend', 'progress' ,'table-friend', limit, offset);
        })

        $("#more-follower a").click(function () {
            countFollower = countFollower + 1;
            var offset = countFollower * limit;
            ajaxFriend('more-follower', 'follower' ,'table-follower', limit, offset);
        })

        $("#more-request a").click(function () {
            countRequest = countRequest + 1;
            var offset = countRequest * limit;
            ajaxFriend('more-request', 'request' ,'table-request', limit, offset);
        })

        $("#open-follower").click(function () {
            $(".friend").hide();
            $("#div-follower").fadeToggle("slow");
        })

        $("#open-request").click(function () {
            $(".friend").hide();
            $("#div-request").fadeToggle("slow");
        })

        $("#open-friend").click(function () {
            $(".friend").hide();
            $("#div-friend").fadeToggle("slow");
        })

    })

    function ajaxFriend(moreHref, status, table, limit, offset) {
        $("#"+moreHref).hide();
        $.ajax({
            url:"/friend/" + status + "/${user.id}?limit="+limit+"&offset="+offset,
            type:"GET",
            contentType:"application/json; charset=UTF-8",
            dataType: "json",
            success:function (data) {
                if (data.length != 0)
                    generateTable(data, table);
                if (data.length < limit)
                    $("#"+moreHref).hide();
                else
                    $("#"+moreHref).slideDown(500);
            },
            error:function () {
                alert('error');
            }

        })
    }

    function generateTable(data, tableId) {
        if (data) {
            var jsonData = data;
            var result;
            for (var i = 0; i < jsonData.length; i++) {
                var element = jsonData[i];
                var user = element.user;
                var table = '<tr>' +
                    '<td style="width: 2%"><img data-src="holder.js/140x140" class="img-circle center-block" style="width: 100px; height: 100px;" src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true"></td>' +
                    ' <td style="width: 10%">' +
                    '<a href="/user/id'+ user.userId +'">' + user.firstName + ' ' + user.lastName + '</a>' +
                    '</td> ';
                result = result + table;
            }
            $("#"+tableId +" > tbody:last")
                .append(result);
            $("a").css("color", "white");
        }
    }

</script>

</body>
</html>
