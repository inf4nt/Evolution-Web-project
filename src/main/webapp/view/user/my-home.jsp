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

<div class="col-lg-10">
    <div class="col-lg-4">
        <h1 class="text-center">PHOTO</h1>
        <img data-src="holder.js/140x140" class="img-circle center-block" style="width: 250px; height: 300px;"
             src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true">

        <hr/>
        <h4 class="text-center">
            <span class="glyphicon glyphicon-user"></span>
            <a href="/friend/${user.id}">
                Friends
            </a>
            <c:if test="${authUser.id != user.id}">
                <hr/>
                <span class="glyphicon glyphicon-envelope"></span>
                <a href="/im/${user.id}">Message</a>
            </c:if>
        </h4>
        <hr/>
        <c:if test="${authUser.id != user.id}">
        <div id="div-friend-panel-loader" style="display: none">
            <h1 class="text-center">
                <img style="height: 40px; width: 40px" src="/resources/3.gif"/>
            </h1>
        </div>
        <div id="div-friend-panel">
                <h4 class="text-center">
                    <c:if test="${status == null}">
                        <a onclick="actionFriend('ADD_FRIEND')" href="#/">
                            <span class="glyphicon glyphicon-plus text-success"></span> Add friend
                        </a>
                    </c:if>
                    <c:if test="${status == 2}">
                        <a onclick="actionFriend('ACCEPT_REQUEST')" href="#/" >
                            <span class="glyphicon glyphicon-ok text-success"></span> Accept request
                        </a>
                    </c:if>

                    <c:if test="${status == 1}">
                        <a onclick="actionFriend('DELETE_FRIEND')" href="#/">
                            <span class="glyphicon glyphicon-remove text-danger"></span> Delete friend
                        </a>
                    </c:if>

                    <c:if test="${status == 3}">
                        <a onclick="actionFriend('DELETE_REQUEST')" href="#/">
                            <span class="glyphicon glyphicon-trash text-muted"></span> Delete request
                        </a>
                    </c:if>
                </h4>
            </div>
        </c:if>
    </div>
    <div class="col-lg-8 pull-right">
        <div class="text-center text-info">
            <h3>
                <c:if test="${user.id == authUser.id}">
                    <ins><span>Hello!</span></ins>
                </c:if>
                <a href="/user/id${user.id}">${user.firstName} ${user.lastName}</a>
            </h3>
            <hr/>
        </div>
    </div>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <c:if test="${authUser.id != user.id}">
            <div class="col-lg-8 pull-right">
                <div class="text-center text-info">
                    <h3 ><span class="glyphicon glyphicon-wrench"></span> Admin panel</h3>
                    <hr/>
                    <div id="divShowHideAdminPanel">
                        <h4 class="text-center">
                            <br/>
                            <a href="/user/profile/${user.id}" >
                                <span class="glyphicon glyphicon-edit text-muted"></span> Edit
                            </a>
                            <%--<a href="/admin/remove-user/${user.id}" >--%>
                            <a href="#" onclick="alert('coming soon')" >
                                <span class="glyphicon glyphicon-remove text-danger"></span> Remove
                            </a>
                        </h4>
                    </div>
                    <hr/>
                </div>
            </div>
        </c:if>
    </sec:authorize>
</div>
<footer class="footer text-center">
    <p>Evolution</p>
    <a href="/user/id226"><span class="glyphicon glyphicon-eye-open"></span> Maksim Lukaretskiy</a>
</footer>
</body>
<script>

    var friendId = ${user.id};

    function actionFriend(actionType) {
        $("#div-friend-panel").hide();
        $("#div-friend-panel-loader").fadeToggle("slow");

        setTimeout(function () {
            actionFriendAJAX(actionType, function (data) {
                if (data.info == true) {
                    var span;
                    if (data.message == 'ACCEPT_REQUEST') {
                        span = '<span class="glyphicon glyphicon-ok text-success"></span> Accept request';
                    } else if (data.message == 'DELETE_FRIEND'){
                        span = '<span class="glyphicon glyphicon-remove text-danger"></span> Delete friend';
                    } else if (data.message == 'DELETE_REQUEST') {
                        span = '<span class="glyphicon glyphicon-trash text-muted"></span> Delete request';
                    } else if (data.message == 'ADD_FRIEND') {
                        span = '<span class="glyphicon glyphicon-plus text-success"></span> Add friend';
                    }

                    var html = '<h4 class="text-center"><a href="#/" style="color: white" onclick="actionFriend(\''+ data.message + '\')"> ' +
                        span +
                        '</a></h4>';
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
        var json = JSON.stringify({"info":friendId, "message":actionType});
        var url = "/friend/";
        $.ajax({
            url: url,
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
