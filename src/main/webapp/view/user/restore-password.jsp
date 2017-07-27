<%--
  Created by IntelliJ IDEA.
  User: Infant
  Date: 23.07.2017
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Restore password</title>
</head>
<body>
<%@include file="../index/header.jsp" %>

<div id="content">

    <div class="col-lg-12" style="top:60px">

        <div id="restore-password-block" class="col-lg-6 col-lg-offset-1 block-background">

            <p id="info" class="x-large text-center"></p>

            <div id="div-user-forgot">
                <div id="div_email_form-user-forgot"  class="form-group has-feedback ">
                    <label class="control-label">Email:</label>
                    <input type="text" onblur="loginValid('div_email_form-user-forgot')" name="login" class="form-element form-control text-center"/>
                    <span class="span-validator glyphicon glyphicon-ok form-control-feedback text-success" aria-hidden="true" style="display: none"></span>
                    <span class="span-validator glyphicon glyphicon-remove form-control-feedback text-danger" aria-hidden="true" style="display: none"></span>
                </div>

                <div class="text-center">
                    <button id="submit-form-forgot" name="submit-form-forgot" class="btn btn-success"
                            style="width: 100%">
                        Next step <span class="glyphicon glyphicon-check"></span>
                    </button>
                </div>
            </div>
        </div>

        <div id="restore-password-support" class="col-lg-5">
            <div class="support-block" id="support-login" style="display: block">
                <p class="xx-large text-center support-text">
                    Please enter your email. Then press "next step" button.
                    You must have access to this mail, because it will receive a confirmation letter.
                </p>
            </div>
        </div>

    </div>
</div>


<script>

    $(document).ready(function () {
        $("#submit-form-forgot").click(function () {
            if (loginValid('div_email_form-user-forgot') == false)
                return false;
            var login = $("#restore-password-block input[name=login]").val();
            var json = JSON.stringify({"login":login});

            console.log(json);

            var info = $("#info");

            info.html("Loading...");
            actionBtn("submit-form-forgot", true);

            $.ajax({
                url: "/service/user/forgot/CREATE_FORGOT_TOKEN",
                type: "POST",
                data: json,
                contentType: "application/json; charset=UTF-8",
                success: function (response) {
                    info.html(response.message);
                    complete();
                },
                error: function () {
                    info.html("Sorry, server is not responded");
                    complete();
                },
                timeout: 30000
            })

            function complete() {
                actionBtn("submit-form-forgot", false);
                $(".span-validator").hide();
                $(".form-element").val("");
            }

            return false;
        })
    })


</script>





</body>
</html>
