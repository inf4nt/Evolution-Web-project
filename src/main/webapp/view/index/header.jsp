<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<script src="<c:url value="/resources/JQuery/jquery-3.2.1.min.js" />"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/validators.js" />"></script>
<script src="<c:url value="/resources/js/js.js" />"></script>
<script src="<c:url value="/resources/js/country.js" />"></script>
<link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/other-css.css" />" rel="stylesheet">

<script>
    $(document).ready(function () {
        $(".div-white a, .div-white p, .div-white h4").css("color", "white");
    })
</script>

<style>
	.block-background {
		background: rgba(255, 253, 244, 0.5); /* Цвет фона */
		color: #fff; /* Цвет текста */
		padding: 5px; /* Поля вокруг текста */
		border-radius: 10px;
	}
</style>



<div id="header">
	<div id="head-navbar" style="display: none"  class="navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="div-white">
				<a id="brand" href="#/" class="navbar-brand">Evolution
					<span class="glyphicon glyphicon-globe"></span>
				</a>
			</div>
			<div class="callapse navbar-collapse div-white">
				<ul class="nav navbar-nav">
					<li>
						<a href="/user/id${authUser.id}">
							<span class="glyphicon glyphicon-home"> Home</span>
						</a>
					</li>
					<li>
						<a href="/user/profile/${authUser.id}">
							<span class="glyphicon glyphicon-edit"> Profile</span>
						</a>
					</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<%--${authUser.login}--%>
							${fn:substring(authUser.login,0 , 20)}
							<span class="glyphicon glyphicon-log-out"></span>
						</a>
						<ul class="dropdown-menu">
							<li><a href="/logout" class="black">Exit</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<br/><br/>  <br/>
	<div style="display: none" id="side" class="sidebar-nav-fixed affix col-md-3 col-lg-2 col-sm-3 col-xs-3">
		<div class="col-sm-10 col-md-9 col-lg-10 col-xs-12 sidebar block-background">
			<ul class="nav nav-sidebar">
				<div id="userPanel" class="div-white">
					<hr/>
					<li>
						<a href="/user/search">
							<span class="glyphicon glyphicon-search"></span> Search
						</a>
					</li>
					<hr/>
					<li>
						<a href="/friend/${authUser.id}"/>
						<span class="glyphicon glyphicon-user"></span> Friends
						</a>
					</li>
					<hr/>
					<li>
						<a href="/im/">
							<span class="glyphicon glyphicon-envelope"></span> Message
						</a>
					</li>
					<hr/>
				</div>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<div id="showAdminPanel" class="div-white">
						<li><a onclick="adminPanel()" href="#"><span class="glyphicon glyphicon-arrow-down"></span> Admin panel</a></li>
					</div>
					<div id="adminPanel" style="display:none;" class="div-white">
						<hr/>
						<li>
							<a href="/admin/form-all/user/start">
								<span class="glyphicon glyphicon-pawn"></span>
								Show users
							</a>
						</li>
						<hr/>
						<li>
							<a href="/admin/form-all/admin/start"><span class="glyphicon glyphicon-king"></span>
								Show admin
							</a>
						</li>
					</div>
					<hr/>
				</sec:authorize>
			</ul>
		</div>
	</div>
</div>


