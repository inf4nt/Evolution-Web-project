<html>
<head>
    <title>${user.firstName} ${user.lastName}</title>
</head>
<body>
<%@include file="../index/header.jsp" %>

<div id="content">

    <div class="col-lg-10 col-lg-offset-2">

        <div class="col-lg-4">

            <div class="col-lg-12 block-background">

                <div class="profile-userpic">
                    <img data-src="holder.js/140x140" class="img-circle center-block" style="width: 250px; height: 300px;"
                         src="https://avatars1.githubusercontent.com/u/15056371?v=3&s=400" data-holder-rendered="true">
                </div>

                <br/>

                <div>
                    <c:if test="${user == authUser}">
                        <a href="/im/" class="btn btn-success" style="width: 100%">
                            Message <span class="glyphicon glyphicon-envelope"></span>
                        </a>
                    </c:if>
                    <c:if test="${user != authUser}">
                        <a href="/im/${user.id}" class="btn btn-success" style="width: 100%">
                            Message <span class="glyphicon glyphicon-envelope"></span>
                        </a>
                    </c:if>
                </div>

                <br/>

                <div>
                    <c:if test="${user != authUser}">
                        <div id="div-friend-panel-loader" style="display: none">
                            <p class="text-center">
                                <img style="height: 25px; width: 150px" src="/resources/page-preloader.gif"/>
                            </p>
                        </div>
                        <div id="div-friend-panel">
                            <p class="text-center">
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
                            </p>
                        </div>
                    </c:if>
                </div>

                <div id="admin-panel">
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



            </div>

            <div class="col-lg-12">
                <br/>
            </div>

            <div class="col-lg-12 block-background">
                <a href="/friend/${user.id}" class="white-and-visited">
                    Friends ${countFriends}
                </a>
                <br/><br/>
                <c:forEach var="a" items="${randomFriends}">
                    <div class="col-lg-4">
                        <a href="/user/id${a.user.id}" class="block-link" style="color: white">
                            <img data-src="holder.js/140x140" class="img-circle center-block" style="width: 100%;"
                                 src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true">
                            <p class="text-center" style="width: 100%">${a.user.firstName}</p>
                        </a>
                    </div>
                </c:forEach>

            </div>


        </div>

        <div class="col-lg-8">

            <div class="col-lg-12 block-background">
                <div>
                    <a class="user-data-link" href="/user/id${user.id}">
                        ${user.firstName} ${user.lastName}
                    </a>
                </div>

                <hr/>

                <div id="friends-block">

                    <a href="/friend/${user.id}" class="block-link white-and-visited">
                        <div class="col-lg-4  text-center">
                            Friends
                        </div>
                        <div class="col-lg-4 text-center">
                            Followers
                        </div>
                        <div class="col-lg-4 text-center">
                            Requests
                        </div>

                        <br/><br/>
                        <div class="col-lg-4 text-center">
                            ${countFriends}
                        </div>
                        <div class="col-lg-4 text-center">
                            ${countFollowers}
                        </div>
                        <div class="col-lg-4 text-center">
                            ${countRequests}
                        </div>
                    </a>
                </div>

                <br/>
                <hr/>
            </div>

            <div class="col-lg-12 block-background" style="top:15px">
                <form action="/feed/post/view" method="POST">
                    <div class="form-group">
                        <textarea id="input-tweet" placeholder="What's new ?" name="tweet-content" class="form-control" style="height: 100px " rows="5" ></textarea>
                    </div>
                    <div class="col-lg-12 " id="tweet-post">
                        <button type="submit" id="btn-tweet-post" style="width: 20%" class="btn btn-info pull-right">
                            Post <span class="glyphicon glyphicon-ok"></span>
                        </button>
                    </div>
                </form>
            </div>

            <c:if test="${!empty tweets}">
                <div id="content-tweet" class="col-lg-12 block-background" style="top:35px">
                    <table style="width: 100%">
                        <thead>
                        <tr>
                            <td></td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="a" items="${tweets}">
                            <tr id="tr-${a.id}">
                                <td>
                                    <div class="block-background div-white">
                                        <c:if test="${a.sender.id == authUser.id}">
                                            <form action="/feed/${a.id}/delete/view" method="GET">
                                                <button type="submit" class="btn btn-default btn-md pull-right">
                                                    <span class="glyphicon glyphicon-remove text-danger"></span>
                                                </button>
                                            </form>
                                        </c:if>
                                        <c:if test="${a.toUser.id == authUser.id}">
                                            <form action="/feed/feed-message/${a.id}/delete/view" method="GET">
                                                <button type="submit" class="btn btn-default btn-md pull-right">
                                                    <span class="glyphicon glyphicon-remove text-danger"></span>
                                                </button>
                                            </form>
                                        </c:if>
                                        <a href="/user/id${a.sender.id}">
                                                ${a.sender.firstName} ${a.sender.lastName}
                                        </a>
                                        <span class="date-tweet">
                                                ${a.dateFormat()}
                                        </span>
                                        <div class="feed-link">
                                            <br/>
                                            <p>
                                                    ${a.content}
                                            </p>
                                        </div>
                                        <p>
                                            <c:forEach var="t" items="${a.listTags()}">
                                                <a class="tweet-tags" href="/feed/tag/${t}">
                                                    #${t}
                                                </a>
                                            </c:forEach>
                                        </p>
                                        <br/>
                                    </div>
                                    <br/>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>


        </div>

    </div>

</div>

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
</div>

</body>

<script>

    $(document).ready(function () {

        $(".white-and-visited").css("color", "white");

        $(".user-data-link").css("font-size", "x-large").css("color", "white");

        $(".tweet-tags").css("color", "#84cbff");

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
            $("#div-friend-panel-loader").hide();
        })



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



