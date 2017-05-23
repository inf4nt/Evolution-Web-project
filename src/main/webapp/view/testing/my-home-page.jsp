<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 14.05.2017
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <title>${authUser.firstName} ${authUser.lastName}</title>
    <script src="<c:url value="/resources/JQuery/jquery-3.2.1.min.js" />"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
    <script>
        $(document).ready(function () {
            $('.dropdown-toggle').dropdown();
        });
    </script>

    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/my_css/my_css.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/pageJS.js" />"></script>
    <script src="<c:url value="/resources/js/validator.js" />"></script>
    <c:set value="${authUser}" var="user"/>
</head>
<body>


<%@include file="new-header.jsp" %>


<div id="dynamic">

    <div class="dynamic" id="my-start-page">
        <div class="col-lg-10">
            <div class="col-lg-4">
                <h1 class="text-center">PHOTO</h1>
                <img data-src="holder.js/140x140" class="img-circle center-block" style="width: 250px; height: 300px;"
                     src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true">
                <h4 class="text-center">
                    <a href="/user/${authUser.id}/friend/start" methods="get">
                        <span class="glyphicon glyphicon-user"></span> Friends
                    </a>
                    <a href="/user/${authUser.id}/follower/start" methods="get">
                        <span class="glyphicon glyphicon-share-alt"></span> Followers
                    </a>
                    <a href="/user/${authUser.id}/request/start" methods="get">
                        <span class="glyphicon glyphicon-question-sign"></span> Requests
                    </a>
                </h4>

            </div>

            <div class="col-lg-8 pull-right">
                <div class="text-center text-info">
                    <h3>
                        <ins><span>Hello!</span></ins>
                        <a href="/user/id/${authUser.id}">${authUser.firstName} ${authUser.lastName}</a>
                    </h3>
                    <hr/>
                </div>
            </div>
        </div>
    </div>

    <div class="dynamic" style="display: none" id="profile">
        <div class="col-md-9">
            <div class="row">
                <div class="col-lg-8 col-md-6 col-sm-6 col-xs-6 col-lg-offset-2">
                    <form id="profilePageForm" class="text text-center">

                        <fieldset disabled>
                            <div class="form-group">
                                <label for="disabledTextInputId">Id</label>
                                <input type="text" id="disabledTextInputId" class="form-control" placeholder="${user.id}">
                            </div>
                        </fieldset>

                        <div id="div_email_registrationPageForm"  class="form-group has-feedback">
                            <label class="control-label ">Email</label>
                            <input value="${user.login}" autocomplete="off" type="text" onblur="loginValid('div_email_registrationPageForm')" name="login" class="form-control text-center"/>
                            <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                            <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
                        </div>

                        <div id="div_first_name_registrationPageForm"  class="form-group has-feedback">
                            <label class="control-label" for="firstName">First name</label>
                            <input value="${user.firstName}" autocomplete="off"  onblur="firstNameValid('div_first_name_registrationPageForm')" id="firstName" type="text"  name="firstName" class="form-control text-center"/>
                            <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                            <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
                        </div>

                        <div id="div_last_name_registrationPageForm"  class="form-group has-feedback">
                            <label class="control-label">Last name</label>
                            <input value="${user.lastName}" autocomplete="off"  onblur="lastNameValid('div_last_name_registrationPageForm')" type="text" name="lastName" class="form-control text-center"/>
                            <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                            <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
                        </div>

                        <div id="div_password_registrationPageForm"  class="form-group has-feedback">
                            <label class="control-label" >Password</label>
                            <input value="${user.password}" autocomplete="off" onblur="firstPasswordValid('div_password_registrationPageForm')" type="password"  name="password" class="form-control text-center"/>
                            <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                            <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
                        </div>

                        <div id="div_password_confirm_registrationPageForm"  class="form-group has-feedback">
                            <label class="control-label" >Confirm password</label>
                            <input value="${user.password}" autocomplete="off" onblur="confirmPassword('div_password_registrationPageForm', 'div_password_confirm_registrationPageForm')"  type="password" name="passwordConfirm" class="form-control text-center"/>
                            <span class="glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                            <span class="glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
                        </div>

                        <a id ="hrefShowHidePassword_profilePageForm"
                           onclick="showHidePassword('hrefShowHidePassword_profilePageForm',
            'div_password_registrationPageForm',
            'div_password_confirm_registrationPageForm')"
                           href="#">Show password and confirm password</a>

                        <br/> <br/>

                        <fieldset disabled>
                            <div class="form-group">
                                <label for="disabledTextInputId">Secret question type</label>
                                <input name="secretQuestionType" type="text" class="form-control" placeholder="${user.secretQuestionType.name}">
                            </div>
                        </fieldset>


                        <fieldset disabled>
                            <div class="form-group">
                                <label for="disabledTextInputId">Secret question</label>
                                <input name="secretQuestion" type="text" class="form-control" placeholder="${user.secretQuestion}">
                            </div>
                        </fieldset>

                        <hr/>
                        <div id="submit_profilePageForm" class="text-center">
                            <button class="btn btn-success"
                                    style="width: 100%">
                                Save <span class="glyphicon glyphicon-check"/>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="dynamic" style="display: none" id="search-box">
        <div id="searchBox" class="input-group col-lg-8 col-lg-offset-3">
            <input maxlength="32" autocomplete="off" type="text" class="form-control input-xs" name="like">
            <div class="input-group-btn">
                <button id="button_searchBox" class="btn btn-search btn-info">
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
                        <table class="table table-hover">
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
    </div>

</div>






<script>

    $(document).ready(function () {
        $("label").addClass("pull-left");
        $("#my-start-page").fadeIn("slow");
    })

    // PROFILE
    $(document).ready(function () {
        $('#profilePageForm').on('submit', function () {
            if (validProfileForm('div_email_registrationPageForm',
                    'div_password_registrationPageForm',
                    'div_password_confirm_registrationPageForm',
                    'div_first_name_registrationPageForm',
                    'div_last_name_registrationPageForm') == false)
                return false;

            $("#submit_profilePageForm").hide();
            setTimeout(function () {
                $.ajax({
                    url: "/user/edit",
                    type: "POST",
                    data: $("#profilePageForm").serialize(),
                    success: function () {
                        $("#submit_profilePageForm").slideDown(1000);
                    },
                    error:function () {
                        $("#profilePageForm div input").val("");
                        $("#profilePageForm div span").hide();
                    }
                });
            }, 2000);
            return false;
        });

    })

    // SEARCH
    $(document).ready(function () {

        $("#button_searchBox").click(function () {
            var like = $("#searchBox input").val();
            if (like.length > 2) {
                $("#headSearchResult").show();
                getResultAjax(like);
            } else {
                $("#headSearchResult").hide();

            }
        });

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
                    '<a href="/user/id/' + user.userId +'">' + user.firstName + ' ' + user.lastName + '</a>'+
                    '</td>' +
                    '</tr>';
                result = result + imageTable + userTable
            }
            $("#headSearchResult #tbodySearchResult")
                .html(result);
        }
    }


    function showDiv(divId) {
        $(".dynamic").hide();
        $("#"+divId).fadeIn("slow");
    }

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
