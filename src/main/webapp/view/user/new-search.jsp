<%--<html>--%>
<%--<head>--%>
    <%--<title>Search</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<%@include file="../index/header.jsp" %>--%>



<%--<div class="col-lg-10 col-lg-offset-2 div-white">--%>
    <%--<div class="col-lg-9">--%>

        <%--<div id="search-box" style="display: block" >--%>
            <%--<div id="searchBox" class="input-group">--%>
                <%--<input id="search-input" onkeyup="keyupSearch()" maxlength="32" autocomplete="off" type="text" class="form-control input-xs" name="like">--%>
                <%--<div class="input-group-btn">--%>
                    <%--<button id="button-search" class="btn btn-search btn-muted">--%>
                        <%--<span class="glyphicon glyphicon-search"></span>--%>
                        <%--<span class="label-icon">Search</span>--%>
                    <%--</button>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<br/>--%>
            <%--<div id="search-result">--%>
                <%--<table class="table">--%>
                    <%--<thead>--%>
                    <%--<tr>--%>
                        <%--<td></td>--%>
                        <%--<td></td>--%>
                    <%--</tr>--%>
                    <%--</thead>--%>
                    <%--<tbody id="tbody-search-result" class="block-background">--%>
                    <%--</tbody>--%>
                <%--</table>--%>
            <%--</div>--%>
        <%--</div>--%>

    <%--</div>--%>

<%--</div>--%>





<%--<script>--%>

    <%--function keyupSearch() {--%>
        <%--var like = $("#search-input").val();--%>

        <%--var likePattern = /^[a-zA-Z0-9]+\s?[a-zA-Z0-9]*$/;--%>

        <%--console.log(likePattern.test(like));--%>

        <%--if (likePattern.test(like) === false)--%>
            <%--return false;--%>

        <%--$.ajax({--%>
            <%--url:"/user/search-result?like=" + like + "&size=" + 50 + "&page=" + 0,--%>
            <%--type:"GET",--%>
            <%--success:function (data) {--%>
                <%--$("#tbody-search-result").html(generateTable(data));--%>
                <%--$("a").css("color", "white");--%>
            <%--},--%>
            <%--error:function () {--%>

            <%--},--%>
            <%--timeout:30000--%>
        <%--})--%>
    <%--}--%>

    <%--function templateUsersTable(user) {--%>
        <%--var template =--%>
            <%--'<tr><td style="width: 2%"><img data-src="holder.js/140x140" class="img-circle center-block" style="width: 100px; height: 100px;"  src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true">' +--%>
            <%--'</td>' +--%>
            <%--'<td style="width: 10%">' +--%>
            <%--'<a href="/user/id' + user.userId +'">' + user.firstName + ' ' + user.lastName + '</a>'+--%>
            <%--'</td>' +--%>
            <%--'</tr>';--%>
        <%--return template;--%>
    <%--}--%>

    <%--function generateTable(json) {--%>
        <%--var result = '';--%>
        <%--for (var i = 0; i < json.length; i++)--%>
            <%--result = result + templateUsersTable(json[i]);--%>
        <%--return result;--%>
    <%--}--%>


<%--</script>--%>
<%--</body>--%>
<%--</html>--%>



<html>
<head>
    <title>Search</title>
</head>
<body>
<%@include file="../index/header.jsp" %>



<div class="col-lg-10 col-lg-offset-2 div-white">
    <div class="col-lg-9">

        <div id="users" style="display: none">
            <div class="col-md-14 block-background">
                <table class="table">
                    <thead>
                    <tr>
                        <td></td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody id="tbody-users">
                    <c:forEach var="a" items="${list}">
                        <tr>
                            <td style="width: 2%">
                                <img data-src="holder.js/140x140" class="img-circle center-block" style="width: 100px; height: 100px;"
                                     src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true">
                            </td>
                            <td style="width: 10%">
                                <a href="/user/id${a.id}">${a.firstName} ${a.lastName}</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div id="more-users" class="text-center">
                <br/>
                <button onclick="ajaxMoreUser()" name="more-users" class="btn btn-default">
                    <span class="glyphicon glyphicon-download"></span> more
                </button>
                <span style="display: none" id="span-more-users-loader" class="glyphicon glyphicon-download">
                 <img style="width: 35px; height: 35px;" src="/resources/page-preloader.gif"/></span>
                <br/><br/><br/>
            </div>
        </div>

        <div id="search-box" style="display: block" >
            <div id="searchBox" class="input-group">
                <input id="search-input" onkeyup="keyupSearch()" maxlength="32" autocomplete="off" type="text" class="form-control input-xs" name="like">
                <div class="input-group-btn">
                    <button id="button-search" class="btn btn-search btn-muted">
                        <span class="glyphicon glyphicon-search"></span>
                        <span class="label-icon">Search</span>
                    </button>
                </div>
            </div>
            <br/>
            <div id="search-result">
                <table class="table">
                    <thead>
                    <tr>
                        <td></td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody id="tbody-search-result" class="block-background">
                    </tbody>
                </table>
            </div>
        </div>

    </div>

    <div class="col-lg-2  btn-group">
        <br/><br/>

        <ul>
            <li class="text-center">
                <a onclick="showUsers()" href="#/" style="width: 80%" >Users <span class="glyphicon glyphicon-user"></span></a>
            </li>
            <hr/>
            <li class="text-center">
                <a onclick="showSearch()" href="#/" style="width: 80%">Search <span class="glyphicon glyphicon-search"></span></a>
            </li>
        </ul>
    </div>

</div>





<script>

    var countMoreUser = 0;
    var limit = ${limit};

    function keyupSearch() {
        var like = $("#search-input").val();

        var likePattern = /^[a-zA-Z0-9]+\s?[a-zA-Z0-9]*$/;

        console.log(likePattern.test(like));

        if (likePattern.test(like) === false)
            return false;

        $.ajax({
            url:"/user/search-result?like=" + like + "&size=" + 50 + "&page=" + 0,
            type:"GET",
            success:function (data) {
                $("#tbody-search-result").html(generateTable(data));
                $("a").css("color", "white");
            },
            error:function () {

            },
            timeout:30000
        })
    }


    function showUsers() {
        $("#search-box, #users").hide();
        $("#users").fadeToggle("slow");
    }

    function showSearch() {
        $("#search-box, #users").hide();
        $("#search-box").fadeToggle("slow");
    }

    function ajaxMoreUser() {
//        var offset = countMoreUser * limit + 1;

        countMoreUser = countMoreUser + 1;
        var loader = $("#span-more-users-loader");
        var button = $("#more-users button[name=more-users]");

        button.hide();
        loader.fadeToggle("slow");

        $.ajax({
            url:"/user/?size=${limit}&page=" + countMoreUser,
            type:"GET",
            complete:function () {
                loader.hide();
            },
            success:function (data) {
                if (data.length === 0 || data.length < limit) {
                    button.hide();
                } else {
                    button.fadeToggle("slow");
                    $("#tbody-users").append(generateTable(data));
                    $("a").css("color", "white");
                }
            },
            error:function () {
                alert('Sorry, server is not responded');
            },
            timeout:30000
        })
    }

    function templateUsersTable(user) {
        var template =
            '<tr><td style="width: 2%"><img data-src="holder.js/140x140" class="img-circle center-block" style="width: 100px; height: 100px;"  src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true">' +
            '</td>' +
            '<td style="width: 10%">' +
            '<a href="/user/id' + user.userId +'">' + user.firstName + ' ' + user.lastName + '</a>'+
            '</td>' +
            '</tr>';
        return template;
    }

    function generateTable(json) {
        var result = '';
        for (var i = 0; i < json.length; i++)
            result = result + templateUsersTable(json[i]);
        return result;
    }


</script>
</body>
</html>

