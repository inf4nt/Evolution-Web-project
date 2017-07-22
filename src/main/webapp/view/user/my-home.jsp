<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.04.2017
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>${user.firstName} ${user.lastName}</title>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <style>

        .user-data-link {
            font-size: x-large;

        }
        .user-data-link:visited {
            color: white;
        }


        .white-and-visited {
            color: white;
        }
        .white-and-visited:visited {
            color: white;
        }


    </style>
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
                        <a href="/user/id${a.friend.id}" class="block-link" style="color: white">
                            <img data-src="holder.js/140x140" class="img-circle center-block" style="width: 100%;"
                                 src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true">
                            <p class="text-center" style="width: 100%">${a.friend.firstName}</p>
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

        </div>

    </div>

</div>

<footer class="footer">
    <div class="container">
		<span class="text-muted">
			<p class="text-center text-muted">
				Evolution <span class="glyphicon glyphicon-globe"></span> <br/>
				<a href="/user/id226" style="color: white">
					Maksim Lukaretskiy
				</a>
			</p>
		</span>
    </div>
</footer>


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


//        setTimeout(function () {
//            actionFriendAJAX(actionType, function (data) {
//                if (data.info === true) {
//                    var span;
//                    if (data.message === 'ACCEPT_REQUEST') {
//                        span = '<span class="glyphicon glyphicon-ok"></span> Accept request';
//                    } else if (data.message === 'DELETE_FRIEND'){
//                        span = '<span class="glyphicon glyphicon-remove "></span> Delete friend';
//                    } else if (data.message === 'DELETE_REQUEST') {
//                        span = '<span class="glyphicon glyphicon-trash"></span> Delete request';
//                    } else if (data.message === 'ADD_FRIEND') {
//                        span = '<span class="glyphicon glyphicon-plus"></span> Add friend';
//                    }
//
//                    var html = '<h4 class="text-center"><button class="btn btn-md btn-info" onclick="actionFriend(\''+ data.message + '\')"> ' +
//                        span +
//                        '</button></h4>';
//                    $("#div-friend-panel").html(html);
//                    $("#div-friend-panel-loader").hide();
//                    $("#div-friend-panel").fadeToggle("slow");
//                }
//
//            }, function (data) {
//                $("#div-friend-panel-loader").hide();
//            })
//        }, 1000)




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



