<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link href="<c:url value="/resources/css/my-css.css" />" rel="stylesheet">



    <style>
        li {
            list-style-type: none;
        }
        .block-sms {
            height: 500px;
            width: 600px;
            overflow: scroll;
            word-break:break-all;
            overflow-x:hidden;
        }
         .parent {
             width: 100%;
             height: 100%;
             position: absolute;
             top: 0;
             left: 0;
             overflow: auto;
         }

        .block {
            width: 250px;
            height: 250px;
            position: absolute;
            top: 50%;
            left: 50%;
            margin: -125px 0 0 -125px;
        }
    </style>

</head>
<body>

<div id="header">
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
                        <a href="#" methods="get" onclick="dialog()">
                            <span class="glyphicon glyphicon-envelope"></span> Message
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </sec:authorize>
</div>



<div style="display: none" id="loader" class="parent">
    <div class="block">
        <h1 class="text text-center">
            <img src="/resources/loader.gif"/>
        </h1>
    </div>
</div>

<div id="dynamic">

    <div id="dialog" style="display:none;" class="col-lg-6 col-lg-offset-1 col-md-6 col-md-offset-1 dynamic">
        <h4 class="text-primary">
            <span class="glyphicon glyphicon-th-list"></span>
            Dialog <a href="/user/id/${authUser.getId()}">${authUser.getFirstName()} ${authUser.getLastName()}</a>
        </h4>

        <div class="col-md-12 col-sm-10">
            <table class="table table-hover">
                <thead>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
                </thead>
                <tbody id="tbodyDialog">

                </tbody>
            </table>
        </div>
    </div>

    <div id="message" style="display:none;" class="col-lg-6 col-lg-offset-2 dynamic">
        <a id="messageUser" href="">
            <h1 class="text-center text-primary"></h1>
        </a>
        <hr/>
        <div id="scroll" class="form-group block-sms scroll-down" >
            <table class="table table-hover">
                <thead>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
                </thead>
                <tbody id="tbodyMessage">

                </tbody>
            </table>
        </div>
        <form id="formMessage">
            <div class="form-group">
                <textarea id="" placeholder="write message............" name="message" class="form-control" style="height: 100px " rows="5" ></textarea>
            </div>

            <div class="col-lg-12 " >
                <button id="btn-message" form="formMessage" style="width: 100%" class="btn btn-info">
                    Send <span class="glyphicon glyphicon-ok"/>
                </button>
            </div>
        </form>
    </div>





</div>










<script>



   $(document).ready(
       function () {
           $("#formMessage").submit(function () {
               $.ajax({
                   url: "/im/write",
                   type: "post",
                   data: $("#formMessage").serialize(),
                   beforeSend:function () {
                       loader(true);
                   },
                   success: function () {
                       loader(false),
                       operationDiv('message');
                   }
               });
               return false;
           })
       }
   )







    function dialog() {
        $.ajax({
                url: '/im/',
                beforeSend: function () {
                    loader(true)
                },
                success:
                    function (data) {
                        loader(false),
                            createTableDialog(data),
                            operationDiv('dialog')
                    }
            }
        );
    }

    function createTableDialog(data) {
        if (data) {
            var jsonData = JSON.parse(data);
            var result;
            for (var i = 0; i < jsonData.length; i++) {
                var user = jsonData[i].dialogPK.second;
                var onclickText = 'message(' + user.userId + ',' + text(user.firstName) + ',' + text(user.lastName) + ')';
                var table = '<tr> ' +
                    '<td style="width: 2%"> ' +
                    '<img data-src="holder.js/140x140" class="img-circle center-block" style="width: 100px; height: 100px;"src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true"></td><td style="width: 10%">' +
                    '<li class="link">'
                    + '<a href="#" onclick="'+onclickText+'">'
                    + '<p class="text text-primary"> '
                    + '<hr/>'
                    + user.firstName + ' ' + user.lastName + '<hr/></p></a></li></td></tr>';
                result = result + table;
            }
            $("#dialog #tbodyDialog")
                .html(result);
        }
    }

    function message(sel, firstName, lastName) {
        $.ajax({
                url: '/im/dialog?&sel='+sel,
            beforeSend: function () {
                loader(true)
            },
            success:
                function (data) {
                    createTableMessage(data, sel, firstName, lastName),
                        loader(false),
                        operationDiv('message')
                        scrollDown();
                }
        }
        );
    }

    function createTableMessage(data, sel, firstName, lastName) {
        if (data) {
            var jsonData = JSON.parse(data);
            var result;
            for (var i = jsonData.length - 1; i >= 0; i--) {
                var element = jsonData[i];
                var date = new Date(element.dateDispatch);
                var fullDate = date.getUTCDate() + '/' + date.getUTCMonth() + '/' + date.getFullYear() + '  '+ date.getUTCHours() + ':' + date.getUTCMinutes();
                var table = ' <tr><td><p><a href="/user/id/' + element.sender.userId + '">'
                    + element.sender.firstName +
                    ' ' + element.sender.lastName +
                    '</a><span>' + '  ' + fullDate +'</span></p> ' +
                    '<p>' + element.message + '</p> ' +
                    '</td> </tr>';
                result = result + table;
            }
            $("#message #tbodyMessage")
                .html(result);
            $("#messageUser").attr("href", '/user/id/' + sel);
            $("#messageUser h1").html(firstName + ' ' + lastName);
            $("#messageUser h1").attr("name", sel);
        }
    }













    function text(text) {
        return "'" + text + "'";
    }

    function operationDiv(id) {
        $(".dynamic").hide();
        document.getElementById(id).style.display = 'block';
    }

    function scrollDown() {
        var div = $(".scroll-down");
        div.scrollTop(div.prop('scrollHeight'));
    }

    function loader(run) {
        if (run == true) {
            $(".dynamic").hide();
            $("#loader").show();
        }
        else {
            $("#loader").hide();
        }
    }
</script>






</body>
</html>
