<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.04.2017
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${user.firstName} ${user.lastName}</title>
</head>
<body>
<%@include file="../index/header.jsp" %>

<div id="default-user-data" style="display: none">
    <div class="col-md-3 col-md-offset-2">
        <div class="profile-sidebar">
            <div class="profile-userpic">
                <img data-src="holder.js/140x140" class="img-circle center-block" style="width: 200px; height: 250px;"
                     src="https://avatars1.githubusercontent.com/u/15056371?v=3&s=400" data-holder-rendered="true">
            </div>
            <div class="profile-usertitle">
                <div class="profile-usertitle-name">
                    <a href="/user/id${user.id}">
                        ${user.firstName} ${user.lastName}
                    </a>
                </div>
            </div>
            <div class="profile-usertitle-job text-center">
                Country
            </div>
            <c:if test="${authUser.id != user.id}">
                <div class="profile-userbuttons">
                    <c:if test="${status == 0}">
                        <button class="btn btn-success" onclick="actionFriend('ADD_FRIEND')">
                            <span class="glyphicon glyphicon-plus"></span> Add friend
                        </button>
                    </c:if>
                    <c:if test="${status == 2}">
                        <button class="btn btn-success" onclick="actionFriend('ACCEPT_REQUEST')" >
                            <span class="glyphicon glyphicon-ok "></span> Accept request
                        </button>
                    </c:if>

                    <c:if test="${status == 1}">
                        <button class="btn btn-danger" onclick="actionFriend('DELETE_FRIEND')" >
                            <span class="glyphicon glyphicon-remove"></span> Delete friend
                        </button>
                    </c:if>

                    <c:if test="${status == 3}">
                        <button class="btn btn-muted" onclick="actionFriend('DELETE_REQUEST')" >
                            <span class="glyphicon glyphicon-trash"></span> Delete request
                        </button>
                    </c:if>
                </div>
            </c:if>
            <div class="profile-usermenu">
                <ul class="nav">
                    <li>
                        <c:if test="${authUser.id == user.id}">
                            <a href="/im/">
                                <i class="glyphicon glyphicon-envelope"></i>
                                Messages
                            </a>
                        </c:if>
                        <c:if test="${authUser.id != user.id}">
                            <a href="/im/${user.id}">
                                <i class="glyphicon glyphicon-envelope"></i>
                                Messages
                            </a>
                        </c:if>
                    </li>
                    <li>
                        <a href="/friend/${user.id}" >
                            <i class="glyphicon glyphicon-user"></i>
                            Friends
                        </a>
                    </li>
                    <li>
                        <a href="#/">
                            <i class="glyphicon glyphicon-flag"></i>
                            Help
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>


<div id="user-data">
    <div class="col-lg-3 col-lg-offset-2 block-background">
        <div id="user-avatar">
            <img data-src="holder.js/140x140" class="img-circle center-block" style="width: 200px; height: 250px;"
                 src="https://avatars1.githubusercontent.com/u/15056371?v=3&s=400" data-holder-rendered="true">
        </div>

        <div id="user-data">
            <hr/>
            <h4 class="text-center">
                <a style="color: white" href="/user/id${user.id}">
                    ${user.firstName} ${user.lastName}
                </a>
                <br/><br/>
                <p>Country</p>
            </h4>
            <hr/>
        </div>


        <div class="btn-group" style="width: 100%">
            <p class="text-center">
                <a href="/friend/${user.id}" class="btn btn-md btn-success">
                    <span class="glyphicon glyphicon-user"></span>
                    Friends
                </a>
                <c:if test="${user == authUser}">
                    <a href="/im/" class="btn btn-md btn-info">
                        <span class="glyphicon glyphicon-envelope"></span>
                        Message
                    </a>
                </c:if>
                <c:if test="${user != authUser}">
                    <a href="/im/${user.id}" class="btn btn-md btn-info">
                        <span class="glyphicon glyphicon-envelope"></span>
                        Message
                    </a>
                </c:if>
            </p>
        </div>

        <hr/>
        <c:if test="${authUser != user}">
            <div id="div-friend-panel-loader" style="display: none">
                <h1 class="text-center">
                    <img style="height: 34px; width: 34px" src="/resources/preloader.gif"/>
                </h1>
            </div>
            <div id="div-friend-panel">
                <h4 class="text-center">
                    <c:if test="${status == null}">
                        <button class="btn btn-md btn-info" onclick="actionFriend('ADD_FRIEND')">
                            <span class="glyphicon glyphicon-plus"></span> Add friend
                        </button>
                    </c:if>
                    <c:if test="${status == 2}">
                        <button class="btn btn-md btn-info" onclick="actionFriend('ACCEPT_REQUEST')" >
                            <span class="glyphicon glyphicon-ok "></span> Accept request
                        </button>
                    </c:if>

                    <c:if test="${status == 1}">
                        <button class="btn btn-md btn-info" onclick="actionFriend('DELETE_FRIEND')">
                            <span class="glyphicon glyphicon-remove"></span> Delete friend
                        </button>
                    </c:if>

                    <c:if test="${status == 3}">
                        <button class="btn btn-md btn-info" onclick="actionFriend('DELETE_REQUEST')">
                            <span class="glyphicon glyphicon-trash"></span> Delete request
                        </button>
                    </c:if>
                </h4>
            </div>
        </c:if>


        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <c:if test="${authUser != user}">
                <hr/>
                <div class="btn-group" style="width: 100%">
                    <p class="text-center">
                        <a href="/user/profile/${user.id}" class="btn btn-md btn-primary">
                            <span class="glyphicon glyphicon-edit"></span>
                            Profile
                        </a>
                        <button data-toggle="modal" data-target="#modal-id" class="btn btn-md btn-danger">
                            <span class="glyphicon glyphicon-remove"></span>
                            Delete
                        </button>
                    </p>
                </div>
                <hr/>
            </c:if>
        </sec:authorize>
    </div>
</div>

<%--<a class="btn btn-primary" data-toggle="modal" href="#modal-id">Trigger modal</a>--%>
<div class="modal fade" id="modal-id">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <div class="text-muted">Are you sure ?</div>
            </div>
            <div class="modal-footer">
                <button id="delete-user" type="button" class="btn btn-danger" data-dismiss="modal">Delete</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


</body>
<script>

    $(document).ready(function () {

        $("#delete-user").click(function () {
            $.ajax({
                url:"/user/${user.id}",
                type:"DELETE",
                success:function () {
                    $("#user-data").html("");
                },
                error:function () {
                    alert('Sorry ( server is not responded')
                },
                timeout:15000
            })
        })
    })

    function actionFriend(actionType) {
        $("#div-friend-panel").hide();
        $("#div-friend-panel-loader").fadeToggle("slow");

        setTimeout(function () {
            actionFriendAJAX(actionType, function (data) {
                if (data.info == true) {
                    var span;
                    if (data.message == 'ACCEPT_REQUEST') {
                        span = '<span class="glyphicon glyphicon-ok"></span> Accept request';
                    } else if (data.message == 'DELETE_FRIEND'){
                        span = '<span class="glyphicon glyphicon-remove "></span> Delete friend';
                    } else if (data.message == 'DELETE_REQUEST') {
                        span = '<span class="glyphicon glyphicon-trash"></span> Delete request';
                    } else if (data.message == 'ADD_FRIEND') {
                        span = '<span class="glyphicon glyphicon-plus"></span> Add friend';
                    }

                    var html = '<h4 class="text-center"><button class="btn btn-md btn-info" onclick="actionFriend(\''+ data.message + '\')"> ' +
                        span +
                        '</button></h4>';
                    $("#div-friend-panel").html(html);
                    $("#div-friend-panel-loader").hide();
                    $("#div-friend-panel").fadeToggle("slow");
                }

            }, function (data) {
                alert('Sorry, server is not responded (((');
                $("#div-friend-panel-loader").hide();
            })
        }, 1000)

        return false;
    }

    function actionFriendAJAX(actionType, callBackSuccess, callBackError) {
        var json = JSON.stringify({"info":${user.id}, "message":actionType});
        $.ajax({
            url: "/friend/",
            type: "PUT",
            data: json,
            contentType:"application/json; charset=UTF-8",
            success: function (data) {
                callBackSuccess(data);
            },
            error: function (data) {
                callBackError(data);
            },
            timeout: 15000
        })
    }


</script>



</html>



<%--<footer class="footer text-center">--%>
<%--<p>Evolution</p>--%>
<%--<a href="/user/id226"><span class="glyphicon glyphicon-eye-open"></span> Maksim Lukaretskiy</a>--%>
<%--</footer>--%>



<%--<h4>--%>
<%--<a name="a-like" href="#/">--%>
<%--<span class="glyphicon glyphicon-heart text-danger"></span> Like--%>
<%--<span> 321312312321</span>--%>
<%--</a>--%>
<%--<a name="a-repost"  href="#/" >--%>
<%--<span class="glyphicon glyphicon-retweet text-muted"></span> Repost--%>
<%--<span> 321312312321</span>--%>
<%--</a>--%>

<%--</h4>--%>




<%--<c:if test="${user.id == authUser.id}">--%>
    <%--<c:if test="${a.sender.id == authUser.id && a.user.id == authUser.id}">--%>
        <%--<form class="form-group" action="/feed/${a.id}" method="GET">--%>
        <%--<button onclick="alert('мой пост на моей стене')" class="btn btn-danger">--%>
            <%--<span class="glyphicon glyphicon-remove"></span>--%>
        <%--</button>--%>
    <%--</c:if>--%>
    <%--<c:if test="${a.sender.id != authUser.id && a.repostFeed.userReposted.id == authUser.id}">--%>
        <%--<form class="form-group" action="/feed/repost/${a.id}">--%>
        <%--<button onclick="alert('удаляю свой репост')" class="btn btn-danger">--%>
            <%--<span class="glyphicon glyphicon-remove"></span>--%>
        <%--</button>--%>
    <%--</c:if>--%>
    <%--<c:if test="${a.sender.id != authUser.id && a.user.id == authUser.id}">--%>
        <%--<form action="/feed/delete-feed-on-my-board/${a.id}" method="GET">--%>
        <%--<button onclick="alert('пост который мне написали')" class="btn btn-danger">--%>
            <%--<span class="glyphicon glyphicon-remove"></span>--%>
        <%--</button>--%>
    <%--</c:if>--%>
<%--</c:if>--%>
<%--<c:if test="${user.id != authUser.id}">--%>
    <%--<c:if test="${a.sender.id == authUser.id && a.user.id == user.id}">--%>
        <%--<form action="/feed/${a.id}" method="GET">--%>
        <%--<button onclick="alert('пост который я написал')" class="btn btn-danger">--%>
            <%--<span class="glyphicon glyphicon-remove"></span>--%>
        <%--</button>--%>
    <%--</c:if>--%>
<%--</c:if>--%>