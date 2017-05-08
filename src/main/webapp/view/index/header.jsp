
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
	<script src="<c:url value="/resources/JQuery/jquery-3.2.1.min.js" />"></script>
	<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
	<script src="/resources/my_js/my_js.js"/>
	<script>
        $(document).ready(function () {
            $('.dropdown-toggle').dropdown();
        });
	</script>

	<link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/my_css/my_css.css" />" rel="stylesheet">
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
								<a href="/user/id/${authUser.getId()}" methods="get">
									<span class="glyphicon glyphicon-home"> Home</span>
								</a>
							</li>
						<c:if test="${authUser.getId() > 0}">
							<li>
								<a href="/user/form-my-profile/${authUser.getId()}" methods="get">
									<span class="glyphicon glyphicon-edit"> Profile</span>
								</a>
							</li>
						</c:if>
					</ul>

					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
											${authUser.getLogin()}
										<span class="glyphicon glyphicon-log-out"></span>
									</a>
							<ul class="dropdown-menu">
								<li><a href="/logout", methods="get">Exit</a></li>
							</ul>
						</li>
					</ul>

					<form role="form" action="/user/search" class="navbar-form navbar-right">
						<div class="input-group">
							<input type="text" class="form-control input-xs" name="like">
							<div class="input-group-btn">
									<button type="submit" formaction="/user/search/start" class="btn btn-search btn-info">
									<span class="glyphicon glyphicon-search"></span>
									<span class="label-icon">Search</span>
								</button>
							</div>
						</div>
					</form>

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
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<hr/>
					<li><a href="/admin/form-all/user/start" methods="get"><span class="glyphicon glyphicon-pawn"></span> Show users</a></li>
					<li><a href="/admin/form-all/admin/start" methods="get"><span class="glyphicon glyphicon-king"></span> Show admin</a></li>
					<li><a href="/form-create-user" methods="get"><span class="glyphicon glyphicon-check"></span> Registration</a></li>
					<li><a href="/admin/form-create-sqt" methods="get">Create secret question type</a></li>
					<hr/>
				</sec:authorize>
				<li>
					<a href="/user/${authUser.getId()}/friend/start" methods="get">
						<span class="glyphicon glyphicon-user"></span> Friends
					</a>
				</li>
				<li>
					<a href="/im/" methods="get">
						<span class="glyphicon glyphicon-envelope"></span> Message
					</a>
				</li>
			</ul>
	</div>
</div>
</sec:authorize>
</body>
</html>


