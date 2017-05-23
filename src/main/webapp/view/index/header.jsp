
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
	<script src="<c:url value="/resources/JQuery/jquery-3.2.1.min.js" />"></script>
	<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
	<script>
        $(document).ready(function () {
            $('.dropdown-toggle').dropdown();
        });
	</script>

	<link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/my_css/my_css.css" />" rel="stylesheet">

	<style>
		li {
			list-style-type: none; /* Убираем маркеры */
		}
	</style>

</head>
<body>

<div class="navbar navbar-inverse navbar-static-top">
	<div class="container">
		<a class="navbar-brand">Evolution
			<span class="glyphicon glyphicon-globe"></span>
		</a>
		<div class="callapse navbar-collapse">

			<sec:authorize access="isAuthenticated()">

				<ul class="nav navbar-nav">
					<li>
						<a href="/user/id${authUser.id}">
							<span class="glyphicon glyphicon-home"> Home</span>
						</a>
					</li>
					<c:if test="${authUser.id > 0}">
						<li>
								<a href="/user/profile/${authUser.id}">
								<span class="glyphicon glyphicon-edit"> Profile</span>
							</a>
						</li>
					</c:if>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								${authUser.login}
							<span class="glyphicon glyphicon-log-out"></span>
						</a>
						<ul class="dropdown-menu">
							<li><a href="/logout", methods="get">Exit</a></li>
						</ul>
					</li>
				</ul>
			</sec:authorize>

			<sec:authorize access="!isAuthenticated()">
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a href="/welcome">Log in
							<span class="glyphicon glyphicon-log-in"></span>
						</a>
					</li>

				</ul>
			</sec:authorize>
		</div>
	</div>
</div>

<sec:authorize access="isAuthenticated()">
	<div class="col-md-3 col-lg-2 col-sm-3 col-xs-3">
		<div class="col-sm-10 col-md-9 col-lg-12 col-xs-12 sidebar">
			<ul class="nav nav-sidebar">
				<div id="userPanel">
					<hr/>
					<li>
						<a href="/user/search">
							<span class="glyphicon glyphicon-search"></span> Search
						</a>
					</li>
					<br/>
					<li>
						<a href="/friend/${authUser.id}"/>
						<span class="glyphicon glyphicon-user"></span> Friends
						</a>
					</li>
					<br/>
					<li>
						<a href="/im/">
							<span class="glyphicon glyphicon-envelope"></span> Message
						</a>
					</li>
					<hr/>
				</div>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<div id="showAdminPanel">
						<li><a onclick="adminPanel()" href="#"><span class="glyphicon glyphicon-arrow-down"></span> Admin panel</a></li>
					</div>
					<br/>
					<div id="adminPanel" style="display:none;">
						<li><a href="/admin/form-all/user/start"><span class="glyphicon glyphicon-pawn"></span> Show users</a></li>
						<br/>
						<li><a href="/admin/form-all/admin/start"><span class="glyphicon glyphicon-king"></span> Show admin</a></li>
						<br/>
						<li><a href="/user/registration"><span class="glyphicon glyphicon-check"></span> Registration</a></li>
						<br/>
						<li><a href="/admin/form-create-sqt" >Create secret question type</a></li>
					</div>
					<hr/>
				</sec:authorize>
			</ul>
		</div>
	</div>
</sec:authorize>
<script>
    function adminPanel() {
        var check = $("#adminPanel").css("display");
        if (check == 'none'){
            $("#adminPanel").slideDown(500);
        } else {
            $("#adminPanel").slideUp(500);
        }
    }
</script>
</body>
</html>
