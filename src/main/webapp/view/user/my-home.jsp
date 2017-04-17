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
    <title>${user.getFirstName()} ${user.getLastName()}</title>
</head>
<body>
<%@include file="../index/header.jsp" %>
<h3 class="text text-center text-primary">${info}</h3>

<c:if test="${info == null}">
    <div class="col-lg-10">
        <div class="col-lg-4">
            <h1 class="text-center">PHOTO</h1>
            <img data-src="holder.js/140x140" class="img-circle center-block" style="width: 250px; height: 300px;"
                 src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true">


            <c:if test="${authUser.getId() != user.getId()}">
                <ul class="nav">
                    <li>
                        <a class="text-center " id="showHideFriendPanel"
                           onclick="showHideAllInfo('divFriendPanel', 'showHideFriendPanel', 'Hide friend panel', 'Show friend panel')" href="#" >
                            Show friend panel
                        </a>
                    </li>
                </ul>

                <div id="divFriendPanel" style="display: none">
                    <br/>
                    <h4 class="text-center">
                        <c:if test="${user.getFriendStatus() == null}">
                            <a href="/user/friend-action/request-friend/${user.getId()}">
                                <span class="glyphicon glyphicon-plus text-success"></span> Add friend
                            </a>
                        </c:if>
                        <c:if test="${user.getFriendStatus() == 'FOLLOWER'}">
                            <a href="/user/friend-action/accept-friend/${user.getId()}">
                                <span class="glyphicon glyphicon-ok text-success"></span> Accept request
                            </a>
                        </c:if>

                        <c:if test="${user.getFriendStatus() == 'PROGRESS'}">
                            <a href="/user/friend-action/delete-friend/${user.getId()}">
                                <span class="glyphicon glyphicon-remove text-danger"></span> Delete friend
                            </a>
                        </c:if>

                        <c:if test="${user.getFriendStatus() == 'REQUEST'}">
                            <a href="/user/friend-action/delete-request/${user.getId()}">
                                <span class="glyphicon glyphicon-trash text-muted"></span> Delete request
                            </a>
                        </c:if>
                    </h4>
                </div>
                <br/>
            </c:if>

            <h4 class="text-center">
                <span class="glyphicon glyphicon-user"></span>
                <a href="/user/${user.getId()}/friend/start" methods="get"> Friends</a>
                <span class="glyphicon glyphicon-share-alt"></span>
                <a href="/user/${user.getId()}/follower/start" methods="get"> Followers</a>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <span class="glyphicon glyphicon-question-sign"></span>
                    <a href="/user/${user.getId()}/request/start" methods="get"> Requests</a>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <c:if test="${authUser.getId() == user.getId()}">
                        <span class="glyphicon glyphicon-question-sign"></span>
                        <a href="/user/${authUser.getId()}/request/start" methods="get"> Requests</a>
                    </c:if>
                </sec:authorize>
            </h4>

        </div>

        <div class="col-lg-8 pull-right">
            <div class="text-center text-info">
                <h3>
                    <c:if test="${user.getId() == authUser.getId()}">
                        <ins><span>Hello!</span></ins>
                    </c:if>
                    <a href="/user/id/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a>
                </h3>
                <hr/>
                <ul class="nav">
                    <li><a  id="showHideUserInfo" onclick="showHideAllInfo('divAllUserInfo', 'showHideUserInfo', 'Hide full information', 'Show full information')" href="#" >Show full information</a></li>
                </ul>
                <div id="divAllUserInfo" style="display: none">
                    <h1>FRIEND COUNT</h1>
                    <h1>FOLLOWER COUNT</h1>
                    <h1>Offer to be friends</h1>
                </div>
            </div>
        </div>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="col-lg-8 pull-right">
                <div class="text-center text-info">
                    <h3 class="text-danger"><span class="glyphicon glyphicon-wrench"></span> Admin panel</h3>
                    <hr/>
                    <ul class="nav">
                        <li><a  id="showHideAdminPanel" onclick="showHideAllInfo('divShowHideAdminPanel', 'showHideAdminPanel', 'Hide panel', 'Show panel')" href="#" >Show panel</a></li>
                    </ul>
                    <div id="divShowHideAdminPanel" style="display: none">
                        <h4 class="text-center">
                            <br/>
                            <a href="/user/form-my-profile/${user.getId()}" >
                                <span class="glyphicon glyphicon-edit text-muted"></span> Edit
                            </a>
                            <a href="/admin/remove-user/${user.getId()}" >
                                <span class="glyphicon glyphicon-remove text-danger"></span> Remove
                            </a>
                        </h4>
                    </div>
                </div>
            </div>
        </sec:authorize>
    </div>
</c:if>


<script>
    function showHideAllInfo(divElementId, elementId,messageHide, messageShow) {
        var cl = getById(divElementId).style.display;
        if (cl == "none"){
            getById(divElementId).style.display = "block";
            getById(elementId).innerHTML = messageHide;
        }
        if (cl == "block"){
            getById(divElementId).style.display = "none";
            getById(elementId).innerHTML = messageShow;
        }
    }
</script>


</body>
</html>
