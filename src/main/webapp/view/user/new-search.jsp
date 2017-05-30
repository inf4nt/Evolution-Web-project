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
    <title>Title</title>
</head>
<body>
<%@include file="../index/header.jsp" %>

<br/>
<br/>


<div id="searchBox" class="input-group col-lg-7 col-lg-offset-3">
    <input maxlength="32" autocomplete="off" type="text" class="form-control input-xs" name="like">
    <div class="input-group-btn">
        <button id="button_searchBox" class="btn btn-search btn-muted">
            <span class="glyphicon glyphicon-search"></span>
            <span class="label-icon">Search</span>
        </button>
    </div>
</div>
<h1 id="result"></h1>
<div id="headSearchResult" style="display: none">
    <div class="col-md-9">
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

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>



<script>


    $(document).ready(function () {
        $("#button_searchBox").click(function () {
            var like = $("#searchBox input").val();
            if (like.length > 2) {
                $("#headSearchResult").show();
                getResultAjax(like);
            } else {
                $("#headSearchResult").hide();

            }
        })
    })


    $(document).ready(function () {
        $("#searchBox input").keyup(function () {
            setTimeout(function () {
                var like = $("#searchBox input").val();

                if (like.length > 2) {
                    $("#headSearchResult").show();
                    getResultAjax(like);
                } else {
                    $("#headSearchResult").hide();
                }
            }, 1000);
        });
    })


    function getResultAjax(like) {
        $.ajax({
            url:"/user/search-result",
            type:"GET",
            data:"like=" + like,
            contentType:"application/json; charset=UTF-8",
            dataType: "json",
            success:function (data) {
                generateTable(data);
            }
        })
    }


    function generateTable(data) {
        if (data) {
            var jsonData = data;
            var result;
            $("#result").html(jsonData.length +' matches')
            for (var i = jsonData.length - 1; i >= 0; i--) {
                var user = jsonData[i];
                var imageTable = '<tr><td style="width: 2%"><img data-src="holder.js/140x140" class="img-circle center-block" style="width: 100px; height: 100px;"  src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true">' +
                    '</td>';
                var userTable = '<td style="width: 10%">' +
                    '<a href="/user/id' + user.userId +'">' + user.firstName + ' ' + user.lastName + '</a>'+
                    '</td>' +
                    '</tr>';
                result = result + imageTable + userTable
            }
            $("#headSearchResult #tbodySearchResult")
                .html(result);
            $("a").css("color", "white");
        }
    }



</script>


</body>
</html>
