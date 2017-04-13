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


<div class="col-lg-10">
    <div class="col-lg-4">
        <h1 class="text-center">PHOTO</h1>
        <img data-src="holder.js/140x140" class="img-circle center-block" style="width: 250px; height: 300px;"
             src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true">
        <br/>
        <span class="glyphicon glyphicon-user"></span><a href="/user/${user.getId()}/friend/start" methods="get"> Friends</a>
        <span class="glyphicon glyphicon-share-alt"></span><a href="/user/${user.getId()}/follower/start" methods="get"> Followers</a>
        <sec:authorize access="hasRole('ROLE_USER')">
            <c:if test="${authUser.getId() == user.getId()}">
                <span class="glyphicon glyphicon-question-sign"></span><a href="/user/${authUser.getId()}/request/start" methods="get"> Friend requests</a>
            </c:if>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <span class="glyphicon glyphicon-question-sign"></span><a href="/user/${user.getId()}/request/start" methods="get"> Friend requests</a>
        </sec:authorize>
    </div>

    <div class="col-lg-8 pull-right">
        <div class="text-center text-info">
            <h3>
                <c:if test="${user.getId() == authUser.getId()}">
                    <ins><span>Hello!</span></ins>
                </c:if>
                ${user.getFirstName()} ${user.getLastName()}
            </h3>
            <hr/>
            <ul class="nav">
                <li><a  id="showHideUserInfo" onclick="showHideAllInfo('divAllUserInfo', 'showHideUserInfo', 'Hide full information', 'Show fullinformation')" href="#" >Show full information</a></li>
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
                    <li><a  id="showHideAdminPanel" onclick="showHideAllInfo('divShowHideAdminPanel', 'showHideAdminPanel', 'Show panel', 'Hide panel')" href="#" >Show panel</a></li>
                </ul>
                <div id="divShowHideAdminPanel" style="display: none">
                    <br/>
                    <a href="/user/form-my-profile/${user.getId()}" >
                        <span class="glyphicon glyphicon-edit text-muted"></span> Edit
                    </a>
                    <a href="/admin/remove-user/${user.getId()}" >
                        <span class="glyphicon glyphicon-remove text-danger"></span> Remove
                    </a>
                </div>
            </div>
        </div>
    </sec:authorize>
</div>

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
