/**
 * Created by Admin on 13.05.2017.
 */

function validRegistrationForm(divLogin, divPassword, divConfirmPassword, divFirstName, divLastName,divSq) {
    if (loginValid(divLogin) == false ||
        firstPasswordValid(divPassword) == false ||
        confirmPassword(divPassword, divConfirmPassword) == false ||
        firstNameValid(divFirstName) == false ||
        lastNameValid(divLastName) == false ||
        sqValid(divSq) == false)
        return false;
    else
        return true;
}


function validLoginPage(divEmail, divPassword) {
    if (loginValid(divEmail) === false ||
        firstPasswordValid(divPassword) == false)
        return false;
    return true;
}

function sqValid(divSq) {
    var pattern = /^[a-zA-Z0-9-/]{1,32}$/;
    return testPattern(divSq, pattern);
}

function error(error, divId) {
    if (error == true) {
        $("#" + divId + " span:last-child").show();
        $("#" + divId + " input").next().hide();
    } else {
        $("#" + divId + " input").next().show();
        $("#" + divId + " span:last-child").hide();
    }
}

function loginValid(divEmail) {
    var emailPattern = /^[a-zA-Z0-9._-]{1,40}@[a-zA-Z0-9.-]{1,40}\.[a-zA-Z]{2,6}$/;
    return testPattern(divEmail, emailPattern);
}

function firstPasswordValid(divPassword) {
    var onlyCharacter = /^[a-zA-Z0-9]{4,32}$/;
    return testPattern(divPassword, onlyCharacter);
}

function confirmPassword(divPassword, divConfirmPassword) {
    if (firstPasswordValid(divConfirmPassword) == false) {
        $("#"+divConfirmPassword+" label").html("Confirm password: Pattern error");
        return false;
    }
    var confirmPassword =  $("#"+divConfirmPassword+" input").val();
    var password =  $("#"+divPassword+" input").val();
    if (confirmPassword != password) {
        error(true, divConfirmPassword);
        $("#"+divConfirmPassword+" label").html("Confirm password: Password and confirm password not equals");
        $("#"+divPassword+" label").html("Password");
        return false;
    }
    error(false, divConfirmPassword);
    error(false, divPassword);
    $("#"+divConfirmPassword+" label").html("Confirm password");
    $("#"+divPassword+" label").html("Password");
    return true;
}

function testPattern(divId, pattern) {
    var val = $("#"+divId +" input").val();
    if (pattern.test(val) == false) {
        error(true, divId);
        return false;
    } else {
        error(false, divId);
        return true;
    }
}

function firstNameValid(divFirstName) {
    var pattern = /^[a-zA-Z]{4,32}$/;
    return testPattern(divFirstName, pattern);
}

function lastNameValid(divLastName) {
    return firstNameValid(divLastName);
}

function validProfileForm(divLogin, divPassword, divConfirmPassword, divFirstName, divLastName) {
    if (loginValid(divLogin) == false ||
        firstPasswordValid(divPassword) == false ||
        confirmPassword(divPassword, divConfirmPassword) == false ||
        firstNameValid(divFirstName) == false ||
        lastNameValid(divLastName) == false)
        return false;
    else
        return true;
}