<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 14.05.2017
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>

<div id="header">

    <div class="navbar navbar-inverse navbar-static-top">
        <div class="container">
            <a class="navbar-brand">Evolution
                <span class="glyphicon glyphicon-globe"></span>
            </a>
            <div class="callapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/user/id/${authUser.id}" methods="get">
                            <span class="glyphicon glyphicon-home"> Home</span>
                        </a>
                    </li>
                    <li>
                        <a href="#" onclick="showDiv('profile')" >
                            <span class="glyphicon glyphicon-edit"> Profile</span>
                        </a>
                    </li>
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
            </div>
        </div>
    </div>


    <div class="col-md-3 col-lg-2 col-sm-3 col-xs-3">
        <div class="col-sm-10 col-md-9 col-lg-12 col-xs-12 sidebar">
            <ul class="nav nav-sidebar">
                <div id="userPanel">
                    <hr/>
                    <li>
                        <a href="#" onclick="showDiv('search-box')">
                            <span class="glyphicon glyphicon-search"></span> Search
                        </a>
                    </li>
                    <br/>
                    <li>
                        <a href="/user/${authUser.id}/friend/start">
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

                <div id="showAdminPanel">
                    <li><a onclick="adminPanel()" href="#"><span class="glyphicon glyphicon-arrow-down"></span> Admin panel</a></li>
                </div>
                <br/>
                <div id="adminPanel" style="display:none;">
                    <li><a href="/admin/form-all/user/start"><span class="glyphicon glyphicon-pawn"></span> Show users</a></li>
                    <br/>
                    <li><a href="/admin/form-all/admin/start"><span class="glyphicon glyphicon-king"></span> Show admin</a></li>
                    <br/>
                    <li><a href="/admin/form-create-user"><span class="glyphicon glyphicon-check"></span> Registration</a></li>
                    <br/>
                    <li><a href="/admin/form-create-sqt" >Create secret question type</a></li>
                </div>
                <hr/>

            </ul>
        </div>
    </div>
</div>
