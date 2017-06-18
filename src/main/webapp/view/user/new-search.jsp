<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.05.2017
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Search</title>
</head>
<body>
<%@include file="../index/header.jsp" %>

<div id="searchBox" class="input-group col-lg-7 col-lg-offset-3">
    <input maxlength="32" autocomplete="off" type="text" class="form-control input-xs" name="like">
    <div class="input-group-btn">
        <button id="button_searchBox" class="btn btn-search btn-muted">
            <span class="glyphicon glyphicon-search"></span>
            <span class="label-icon">Search</span>
        </button>
    </div>
</div>

<div id="headSearchResult" style="display: block">
    <div class="col-md-6 col-lg-offset-3">
        <%--<h1 id="result"></h1>--%>
        <div class="row">
            <div class="col-md-14">
                <table class="table">
                    <thead>
                    <tr>
                        <td></td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody id="tbodySearchResult">
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
                <button name="more-users" class="btn btn-default">
                    <span class="glyphicon glyphicon-download"></span> more
                </button>
                <button style="display: none" name="more-users-like" class="btn btn-default">
                    <span class="glyphicon glyphicon-download"></span> more
                </button>
                <span style="display: none" id="span-more-users-loader" class="glyphicon glyphicon-download">
                         <img style="width: 35px; height: 35px;" src="/resources/3.gif"/>
                </span>
                <br/><br/><br/>
            </div>
        </div>
    </div>
</div>



<script>

    var count = 0;
    var countLike;
    var limit = ${limit};
    var likes;


    $(document).ready(function () {
        var idbutton = $("#more-users button[name=more-users]");
        var idSpanLoader = $("#span-more-users-loader");
        $(idbutton).click(function () {
            count = count + 1;
            var offset = count * limit;

            idbutton.hide();
            idSpanLoader.fadeToggle("slow");

            $.ajax({
                url:"/user/?limit=" + limit + "&offset=" + offset,
                type:"GET",
                success:function (data) {
                    moreUser(data);
                    idSpanLoader.hide();
                    if (data.length >= ${limit}){
                        idbutton.fadeToggle("slow");
                    }
                },
                error:function () {
                    alert('Sorry, server is not responded');
                },
                timeout:15000
            })
        })


        var idButtonLikeUser = $("#more-users button[name=more-users-like]");

        idButtonLikeUser.click(function () {
            countLike = countLike + 1;
            var offset = countLike * limit;


            idButtonLikeUser.hide();
            idSpanLoader.fadeToggle("slow");

            $.ajax({
                url:"/user/search-result/?limit=" + limit + "&offset=" + offset + "&like=" + likes,
                success:function (data) {
                    idSpanLoader.hide();
                    if (data.length >= ${limit}){
                        idButtonLikeUser.fadeToggle("slow");
                    }
                    else {
                        idButtonLikeUser.hide();
                    }
                    moreUser(data);
                },
                error:function () {

                },
                timeout:15000
            })
        })
    })



    function moreUser(data) {
        if (data) {
            var result;
            for (var i = 0; i < data.length; i++) {
                var user = data[i];
                result = result + templateUsersTable(user);
            }
            $("#tbodySearchResult")
                .append(result);
            $("a").css("color", "white");
        }
    }

    $(document).ready(function () {
        $("#button_searchBox").click(function () {
            var like = $("#searchBox input").val();
            if (like.length > 2) {
                $("#headSearchResult").show();
                getResultAjax(like);
            } else {
                $("#headSearchResult").hide();

            }
            countLike = 0;
            likes = like;
        })
        $("#searchBox input").keyup(function () {
            setTimeout(function () {
                var like = $("#searchBox input").val();
                if (like.length > 2) {
                    $("#headSearchResult").show();
                    getResultAjax(like);
                } else {
                    $("#headSearchResult").hide();
                }
                likes = like;
            }, 1000);
            $("#more-users button[name=more-users]").hide();
            countLike = 0;
        });
    })

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

    function getResultAjax(like) {
        $.ajax({
            url:"/user/search-result?limit=" + limit + "&offset=" + count + "&like=" + like,
            type:"GET",
            contentType:"application/json; charset=UTF-8",
            dataType: "json",
            success:function (data) {
                if (data.length == 0)
                    $("#tbodySearchResult").html("");

                if (data.length >= ${limit}) {
                    $("#more-users button[name=more-users-like]").show();
                }

                generateTable(data);
            },
            error:function () {

            },
            timeout:15000
        })
    }

    function generateTable(data) {
        if (data) {
            var jsonData = data;
            var result;
            $("#result").html(jsonData.length +' matches')
            for (var i = jsonData.length - 1; i >= 0; i--) {
                var user = jsonData[i];
                result = result + templateUsersTable(user);
            }
            $("#tbodySearchResult")
                .html(result);
            $("a").css("color", "white");
        }
    }

</script>


</body>
</html>
