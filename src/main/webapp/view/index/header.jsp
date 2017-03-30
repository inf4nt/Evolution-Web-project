<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>



	<script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script>
        $(document).ready(function () {
            $('.dropdown-toggle').dropdown();
        });
	</script>
	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
	<%--<style>--%>
		<%--html { height: 100%; }--%>
		<%--body {--%>
			<%--margin: 0;--%>
			<%--height: 100%;--%>
			<%--background: url(http://sebulfin.com/wp-content/uploads/2014/04/Saturn-2.jpg);--%>
			<%--background-size: cover;--%>
		<%--}--%>
	<%--</style>--%>
</head>
<body>

<div class="navbar navbar-inverse navbar-static-top">
	<div class="container">
		<a class="navbar-brand">EvolutionMVC</a>
			<div class="callapse navbar-collapse">

				<sec:authorize access="isAuthenticated()">

					<ul class="nav navbar-nav">
						<li><a href="/user/home", methods="get">Home</a></li>
						<li><a href="/user/form-my-profile/${userid}" methods="get">Profile</a></li>
					</ul>

					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<%--<a href="#" class="dropdown-toggle" data-toggle="dropdown">${username}<b class="caret"></b></a>--%>
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">${username}<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="/logout", methods="get">Exit</a></li>
							</ul>
						</li>
					</ul>

					<form role="form" action="/user/search" class="navbar-form navbar-right">
						<div class="input-group">
							<input type="text" class="form-control input-xs" name="like">
							<div class="input-group-btn">
								<%--<button type="submit" formaction="/user/search" class="btn btn-search btn-info">--%>
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
						<li><a href="/welcome">Log in</a></li>
					</ul>
				</sec:authorize>
			</div>
	</div>
</div>

<sec:authorize access="isAuthenticated()">
<div class="col-md-2 col-lg-2">
	<div class="col-sm-3 col-md-12 col-lg-12 sidebar">
			<ul class="nav nav-sidebar">
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="/user/form-all/user/start" methods="get">Show users</a></li>
				<li><a href="/user/form-all/admin/start" methods="get">Show admin</a></li>
				<li><a href="/form-create-user" methods="get">Registration</a></li>
				<li><a href="/admin/form-create-sqt" methods="get">Create secret question type</a></li>
				</sec:authorize>
				<li><a href="/user/friend/Friend/start" methods="get">Friends</a></li>
				<li><a href="/user/friend/Follower/start" methods="get">Follower</a></li>
				<%--<li><a href="/user/friend/Friend Request/start" methods="get">Friend Request</a></li>--%>
			</ul>
	</div>
</div>
</sec:authorize>
</body>
</html>


