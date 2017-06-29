
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Profile</title>
</head>
<body>

<%@include file="../index/header.jsp" %>

<div id="div-self-profile" class="col-lg-offset-2" style="display: none">
    <div class="col-md-12">
        <div class="row">
            <div class="col-lg-8 col-md-6 col-sm-6 col-xs-6 col-lg-offset-2 block-background div-white">
                <form id="profilePageForm" class="text text-center">

                    <fieldset disabled>
                        <div class="form-group">
                            <label for="disabledTextInputId">Id</label>
                            <input type="text" id="disabledTextInputId" class="form-control" placeholder="${user.id}">
                        </div>
                    </fieldset>

                    <fieldset disabled>
                        <div class="form-group">
                            <label for="disabledTextInputEmail">Email</label>
                            <input type="text" id="disabledTextInputEmail" class="form-control" placeholder="${user.login}">
                        </div>
                    </fieldset>


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
                       href="#/">Show password and confirm password</a>

                    <br/> <br/>

                    <%--ТУТ  МЕНЯТЬ ГОРОД И СТРАНУ, КНОПКА ОТКРОЕТ ВЫБОР--%>
                    <fieldset disabled>
                        <div class="form-group">
                            <label for="disabledTextInputCountry">Country</label>
                            <input type="text" id="disabledTextInputCountry" class="form-control" placeholder="${user.country}">
                        </div>
                    </fieldset>

                    <fieldset disabled>
                        <div class="form-group">
                            <label for="disabledTextInputState">State</label>
                            <input type="text" id="disabledTextInputState" class="form-control" placeholder="${user.state}">
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

<script src="<c:url value="/resources/js/js.js" />"></script>
<script src="<c:url value="/resources/js/validators.js" />"></script>
</body>

<script>

    $(document).ready(function () {
        $("label").addClass("pull-left");

        $("#div-self-profile").fadeToggle(1000);

        $('#profilePageForm').on('submit', function () {

            if (validProfileForm('div_password_registrationPageForm',
                    'div_password_confirm_registrationPageForm',
                    'div_first_name_registrationPageForm',
                    'div_last_name_registrationPageForm') == false)
                return false;


            var password = $("#profilePageForm input[name=password]").val();
            var firstName = $("#profilePageForm input[name=firstName]").val();
            var lastName = $("#profilePageForm input[name=lastName]").val();
            var json = JSON.stringify({"password":password, "firstName":firstName, "lastName":lastName});



            $("#submit_profilePageForm").hide();
            setTimeout(function () {
                $.ajax({
                    url: "/user/${user.id}",
                    type: "PUT",
                    data: json,
                    contentType: "application/json; charset=UTF-8",
                    success: function (data) {
                        if (data == true)
                            $("#submit_profilePageForm").slideDown(1000);
                        else {
                            alert('validator or id');
                            $("#submit_profilePageForm").slideDown(1000);
                        }

                    },
                    error:function () {
                        $("#profilePageForm div input").val("");
                        $("#profilePageForm div span").hide();
                    },
                    timeout: 15000
                });
            }, 2000);
            return false;
        });


    })

</script>


</html>

