<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.04.2017
  Time: 1:07
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile</title>
    <script src="<c:url value="/resources/js/pageJS.js" />"></script>
    <script src="<c:url value="/resources/js/validators.js" />"></script>
</head>
<body>
<%@include file="../index/header.jsp" %>

<div class="col-md-9">
    <div class="row">
        <div class="col-lg-8 col-md-6 col-sm-6 col-xs-6 col-lg-offset-2">

            <form id="formAdminProfile">

                <a href="/user/id${user.id}">
                    <h4 class="text-center">${user.firstName} ${user.lastName}</h4>
                </a>

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

                <%--<div id="div_email_formAdminProfile" class="form-group has-feedback">--%>
                    <%--<label class="control-label" >Email</label>--%>
                    <%--<input type="text" onblur="loginValid('div_email_formAdminProfile')" value="${user.login}" name="login" class="form-control text-center"/>--%>
                    <%--<span class="span-validator-success" aria-hidden="true" style="display: none"></span>--%>
                    <%--<span class="span-validator-error" aria-hidden="true" style="display: none"></span>--%>
                <%--</div>--%>

                <div id="div_password_formAdminProfile" class="form-group has-feedback">
                    <label class="control-label">Password</label>
                    <input onblur="firstPasswordValid('div_password_formAdminProfile')" type="password" value="${user.password}" name="password" class="form-control text-center"/>
                    <span class="span-validator-success" aria-hidden="true" style="display: none"></span>
                    <span class="span-validator-error" aria-hidden="true" style="display: none"></span>
                </div>


                <div id="div_password_confirm_formAdminProfile"  class="form-group has-feedback">
                    <label class="control-label" >Confirm password</label>
                    <input onblur="confirmPassword('div_password_formAdminProfile', 'div_password_confirm_formAdminProfile')" type="password" value="${user.getPassword()}" name="passwordConfirm" class="form-control text-center"/>
                    <span  class="span-validator-success" aria-hidden="true" style="display: none"></span>
                    <span class="span-validator-error" aria-hidden="true" style="display: none"></span>
                </div>

                <a id ="hrefShowHidePassword_formAdminProfile"
                   onclick="showHidePassword('hrefShowHidePassword_formAdminProfile',
                   'div_password_formAdminProfile',
                   'div_password_confirm_formAdminProfile')"
                   href="#/">Show password and confirm password</a>

                <br/> <br/>


                <div id="div_first_name_formAdminProfile"  class="form-group has-feedback">
                    <label class="control-label">First name</label>
                    <input onblur="firstNameValid('div_first_name_formAdminProfile')" type="text" value="${user.firstName}" name="firstName" class="form-control text-center"/>
                    <span class="span-validator-success" aria-hidden="true" style="display: none"></span>
                    <span class="span-validator-error" aria-hidden="true" style="display: none"></span>
                </div>


                <div id="div_last_name_formAdminProfile" class="form-group has-feedback">
                    <label class="control-label">Last name</label>
                    <input onblur="lastNameValid('div_last_name_formAdminProfile')" type="text" value="${user.lastName}" name="lastName" class="form-control text-center"/>
                    <span class="span-validator-success" aria-hidden="true" style="display: none"></span>
                    <span class="span-validator-error" aria-hidden="true" style="display: none"></span>
                </div>

                <div id="role" class="form-group">
                    <label class="control-label">Role</label>
                    <select name="role" class="form-control">
                        <c:choose>
                            <c:when test="${user.getRole() == 'USER'}">
                                <option value="1">User</option>
                                <option value="2">Admin</option>
                            </c:when>
                            <c:otherwise>
                                <option value="2">Admin</option>
                                <option value="1">User</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </div>

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

                <fieldset disabled>
                    <div class="form-group">
                        <label for="disabledTextInputRD">Registration date</label>
                        <input type="text" id="disabledTextInputRD" class="form-control" placeholder="${user.getDateFormatRegistrationDate()}">
                    </div>
                </fieldset>

                <br/>
                <div id="submit_formAdminProfile" class="text-center">
                    <button form="formAdminProfile" type="submit" class="btn btn-success" style="width: 100%">
                        Edit <span class="glyphicon glyphicon-check"/>
                    </button>
                </div>
            </form>

        </div>
    </div>
</div>
</body>


<script>

    $(document).ready(function () {
        $("label").addClass("pull-left");

        $(".span-validator-success").addClass("glyphicon glyphicon-ok form-control-feedback text-success");

        $(".span-validator-error").addClass("glyphicon glyphicon-remove form-control-feedback text-danger");

        $('#formAdminProfile').on('submit', function () {
            if (validProfileForm('div_password_formAdminProfile',
                    'div_password_confirm_formAdminProfile',
                    'div_first_name_formAdminProfile',
                    'div_last_name_formAdminProfile') == false)
                return false;


            var login = $("#formAdminProfile input[name=login]").val();
            var password = $("#formAdminProfile input[name=password]").val();
            var firstName = $("#formAdminProfile input[name=firstName]").val();
            var lastName = $("#formAdminProfile input[name=lastName]").val();
            var role = $("#formAdminProfile select[name=role]").val();

            $("#submit_formAdminProfile").hide();
            setTimeout(function () {
                $.ajax({
                    url: "/user/${user.id}",
                    type: "PUT",
                    data:JSON.stringify({"login":login, "password":password, "firstName":firstName, "lastName":lastName, "roleId": role}),
                    success: function () {
                        $("#submit_formAdminProfile").slideDown(1000);
                    },
                    error: function () {
                        $("#formAdminProfile div input").val("");
                        $("#formAdminProfile div span").hide();
                        $("#submit_formAdminProfile").slideDown(1000);
                    }
                });
            }, 2000);
            return false;
        });


    })

</script>



</html>
