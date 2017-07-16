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
    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
</head>
<body>
<%@include file="../index/header.jsp" %>
<%--<style >--%>
    <%--#footer {--%>
        <%--position: fixed;--%>
        <%--/*right: 0; bottom: 30px;*/--%>
        <%--left: 0; bottom:0;--%>
        <%--width: 100%;--%>
    <%--}--%>
<%--</style>--%>

<div id="content">
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
                    <p>
                        Country
                    </p>
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
                            <a href="/user/${user.id}/put/view" class="btn btn-md btn-primary">
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




        <div class="col-lg-7">
            <div class="col-lg-12 block-background">

                <div id="tweet-block">
                    <c:if test="${user.id == authUser.id}">
                        <form action="/feed/post/view" method="POST">
                            <div class="form-group">
                                <textarea id="input-tweet" placeholder="What's new ?" name="tweetContent" class="form-control" style="height: 100px " rows="5" ></textarea>
                            </div>
                            <div id="tweet-post">
                                <button type="submit" id="btn-tweet-post" style="width: 20%" class="btn btn-info pull-right">
                                    Post <span class="glyphicon glyphicon-ok"></span>
                                </button>
                            </div>
                        </form>
                        <br/><br/>
                        <hr/>
                    </c:if>
                </div>

                <%--<div id="btn-tweet-support">--%>
                <%--<button id="show-tweet-content" class="btn btn-link" style="color: white; width: 100%">Show posts</button>--%>
                <%--<br/>--%>
                <%--</div>--%>

                <div style="display: block;" id="tweet-content">
                    <table style="width: 100%">
                        <thead>
                        <tr>
                            <td></td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="a" items="${feeds}">
                            <tr id="tr-${a.id}">
                                <td>
                                    <div class="block-background div-white">
                                        <c:if test="${a.sender.id == authUser.id && a.reposted.id == null}">
                                            <form action="/feed/${a.id}/delete/view" method="GET">
                                                <button type="submit" class="btn btn-default btn-md pull-right">
                                                    <span class="glyphicon glyphicon-remove text-danger"></span>
                                                </button>
                                            </form>
                                        </c:if>
                                        <c:if test="${authUser.id == a.reposted.id}">
                                            <form action="/feed/${a.id}/repost/delete/view" method="GET">
                                                <button type="submit" class="btn btn-default btn-md pull-right">
                                                    <span class="glyphicon glyphicon-remove text-danger"></span>
                                                </button>
                                            </form>
                                        </c:if>





                                        <c:if test="${a.reposted.id != null}">
                                            <a href="/user/id${a.reposted.id}">
                                                    ${a.reposted.firstName} ${a.reposted.lastName}
                                            </a>
                                            <span style="color: white"> <span class="glyphicon glyphicon-share-alt"></span> reposted</span>
                                            <br/><br/>
                                        </c:if>

                                        <a href="/user/id${a.sender.id}">
                                                ${a.sender.firstName} ${a.sender.lastName}
                                        </a>

                                        <div class="feed-link">
                                            <br/>
                                            <c:if test="${a.feedData.content.length() > 1000}">
                                                <p onclick="ajaxTweetModal(this)" class="curs" id="${a.id}" href="#modal-id" data-toggle="modal">
                                                        ${fn:substring(a.feedData.content, 0, 1000)}...
                                                </p>
                                            </c:if>
                                            <c:if test="${a.feedData.content.length() <= 1000}">
                                                <p>
                                                        ${a.feedData.content}
                                                </p>
                                            </c:if>
                                        </div>
                                        <p>
                                            <c:forEach var="t" items="${a.feedData.listTags()}">
                                                <a class="tweet-tags" href="/feed/tag/${t}">
                                                    #${t}
                                                </a>
                                            </c:forEach>
                                        </p>
                                        <br/>
                                        <div class="btn-group">
                                            <button class="btn btn-default"><span class="glyphicon glyphicon-heart text-danger"></span> Like</button>
                                            <button name="${a.id}" type="submit" data-toggle="modal" data-target="#modal-id-repost" class="btn btn-default btn-repost-info"><span class="glyphicon glyphicon-retweet"></span>
                                                    ${a.countRepost}
                                                Repost
                                            </button>
                                        </div>
                                    </div>
                                    <br/>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
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
</div>




</body>
<script>

    $(document).ready(function () {


//        function autoHeight() {
//            $('#content').css('min-height', 0);
//            $('#content').css('min-height', (
//                $(document).height()
//                - $('#header').height()
//                - $('#footer').height()
//            ));
//        }
//
//        // onDocumentReady function bind
//        $(document).ready(function() {
//            autoHeight();
//        });
//
//        // onResize bind of the function
//        $(window).resize(function() {
//            autoHeight();
//        });





        $(".tweet-tags").css("color", "#84cbff");

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

//        $("#show-tweet-content").click(function () {
//            var disp =  document.getElementById("tweet-content").style.display;
//            if (disp === 'none') {
//                $("#show-tweet-content").html("Hide posts");
//                $("#tweet-content").slideDown("slow");
//            } else {
//                $("#show-tweet-content").html("Show posts");
//                $("#tweet-content").slideUp("slow");
//            }
//        })



    })

    function actionFriend(actionType) {
        $("#div-friend-panel").hide();
        $("#div-friend-panel-loader").fadeToggle("slow");

        setTimeout(function () {
            actionFriendAJAX(actionType, function (data) {
                if (data.info === true) {
                    var span;
                    if (data.message === 'ACCEPT_REQUEST') {
                        span = '<span class="glyphicon glyphicon-ok"></span> Accept request';
                    } else if (data.message === 'DELETE_FRIEND'){
                        span = '<span class="glyphicon glyphicon-remove "></span> Delete friend';
                    } else if (data.message === 'DELETE_REQUEST') {
                        span = '<span class="glyphicon glyphicon-trash"></span> Delete request';
                    } else if (data.message === 'ADD_FRIEND') {
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
//                alert('Sorry, server is not responded (((');
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