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


<div id="content" class="col-lg-10 col-lg-offset-2">

    <div id="info-block" class="col-lg-4 ">
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
        <sec:authorize access="hasRole('ROLE_ADMIN')">\
            <c:if test="${authUser.id != user.id}">
                <hr/>
                <h4 class="text-center">
                    <span class="glyphicon glyphicon-edit text-muted"></span>
                    <a href="/user/profile/${user.id}">
                        Profile
                    </a>
                    <span class="glyphicon glyphicon-remove text-danger"></span>
                    <a id="delete-user" href="#/">
                        Delete
                    </a>
                </h4>

                <hr/>
            </c:if>
        </sec:authorize>
    </div>

    <div id="other-block" class="col-lg-8">

        <div id="name-block" class="text-center">

            <h3>
                <c:if test="${user.id == authUser.id}">
                    <ins><span>Hello!</span></ins>
                </c:if>
                <a href="/user/id${user.id}">${user.firstName} ${user.lastName}</a>
            </h3>
            <hr/>

        </div>


        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div id="tweet-block" style="display: none" class="col-lg-12">


                <c:if test="${authUser.id == user.id}">
                    <div id="tweet-text" class="col-lg-12">

                        <form action="/feed/" method="POST" id="formMessage">
                            <div class="form-group">
                        <textarea id="inputMessage" placeholder="write post............"
                                  name="tweet" class="form-control" style="height: 80px " rows="5">

                        </textarea>
                            </div>

                            <div class="col-lg-5 col-lg-offset-7">
                                <button style="width: 100%" class="btn btn-success">
                                    Send <span class="glyphicon glyphicon-pushpin"></span>
                                </button>
                            </div>
                        </form>

                    </div>
                </c:if>




                <div class="col-lg-12">
                    <br/>
                </div>


                <div id="tweet-content" class="col-lg-12 form-group block-tweet">

                    <div class="tweets" id="tweet-content-post">

                        <table class="table">
                            <thead>
                            <tr>
                                <td></td>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="a" items="${news}">

                                <tr>
                                    <td>
                                        <div class="btn-group pull-right">


                                            <c:choose>
                                                <c:when test="${authUser.id == a.sender.id}">
                                                    <form>
                                                        <button onclick="alert('remove my post')" class="btn btn-danger">
                                                            <span class="glyphicon glyphicon-remove"></span>
                                                        </button>
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <form>
                                                        <button onclick="alert('remove repost')" class="btn btn-danger">
                                                            <span class="glyphicon glyphicon-remove"></span>
                                                        </button>
                                                    </form>
                                                </c:otherwise>
                                            </c:choose>


                                            <button onclick="alert('post info')" class="btn btn-info">
                                                <span class="glyphicon glyphicon-info-sign"></span>
                                            </button>
                                        </div>
                                        <h4>

                                            <c:if test="${a.sender.id != user.id}">
                                                <a href="/user/id${user.id}">
                                                    @${user.firstName} ${user.lastName}
                                                </a>
                                                pinned post
                                                <br/><br/>
                                            </c:if>

                                            <a href="/user/id${a.sender.id}">
                                                @${a.sender.firstName} ${a.sender.lastName}
                                            </a>

                                        </h4>

                                        <h4 id="tweet">
                                            <div class="col-lg-12">
                                                    ${a.feedContent}
                                                <br/><br/>
                                            </div>

                                            <div id="tweet-img" class="text-center">
                                                <img style="width: 70%; height: 50%" src="http://www.team-bhp.com/forum/attachments/international-automotive-scene/314225d1269364819-new-bwm-vision-image001.jpg"/>
                                            </div>
                                        </h4>


                                        <br/>

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

                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>


                    </div>

                </div>

            </div>
        </sec:authorize>


    </div>

</div>


<footer class="footer text-center">
    <p>Evolution</p>
    <a href="/user/id226"><span class="glyphicon glyphicon-eye-open"></span> Maksim Lukaretskiy</a>
</footer>



</body>
<script>

    $(document).ready(function () {
        $("#delete-user").click(function () {

            $.ajax({
                url:"/user/${user.id}",
                type:"DELETE",
                success:function () {
                    $("#content").html("");
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
