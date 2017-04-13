function getById(id) {
    return document.getElementById(id);
}

function notValid(label_error, div_error, span_error, span_success, errorMessage) {
    getById(label_error).innerHTML = errorMessage;
    getById(label_error).classList.add("text-danger");
    getById(div_error).classList.add("has-error");
    getById(span_error).style.display = "block";
    getById(span_success).style.display = "none";
}

function valid(label_error, div_error, span_error, span_success, successMessage) {
    getById(label_error).innerHTML = successMessage;
    getById(label_error).classList.remove("text-danger");
    getById(div_error).classList.add("has-success");
    getById(div_error).classList.remove("has-error");
    getById(span_error).style.display = "none";
    getById(span_success).style.display = "block";
}

function showHidePassword() {
    var type = document.getElementById("password").getAttribute("type");
    if (type == "password") {
        getById("hrefShowHidePassword").innerHTML = "Hide password and confirm password";
        getById("password").setAttribute("type", "text");
        getById("confirmPassword").setAttribute("type", "text");
    } else {
        getById("hrefShowHidePassword").innerHTML = "Show password and confirm password";
        getById("password").setAttribute("type", "password");
        getById("confirmPassword").setAttribute("type", "password");
    }
}

function loginValid(loginId) {
    var email = getById(loginId).value;
    var result = false;
    var emailPattern = /^[a-zA-Z0-9._-]{1,40}@[a-zA-Z0-9.-]{1,40}\.[a-zA-Z]{2,6}$/;
    if (emailPattern.test(email) == false) {
        notValid("email_error", "div_email", "span_email_error", "span_email_ok", "email pattern error");
        return result;
    }
    else
        valid("email_error", "div_email", "span_email_error", "span_email_ok", "Email");
}

function passwordValid(passwordId, confirmPassword) {
    var password = getById(passwordId).value;
    var confirmPassword = document.getElementById(confirmPassword).value;
    var onlyCharacter = /^[a-zA-Z0-9]{4,32}$/;
    var result = false;
    if (onlyCharacter.test(password) == false || onlyCharacter.test(confirmPassword) == false || password != confirmPassword) {
        notValid("password_error", "div_password", "span_password_error", "span_password_ok", "password must be between 4-32 character and can not contain a space");
        notValid("password_confirm_error", "div_password_confirm", "span_password_confirm_error", "span_password_confirm_ok", "password and confirm password not equals");
        return result;
    }
    else {
        valid("password_error", "div_password", "span_password_error", "span_password_ok", "Password");
        valid("password_confirm_error", "div_password_confirm", "span_password_confirm_error", "span_password_confirm_ok", "Confirm password");
    }
}

function firstPasswordValid(passwordId) {
    var password = getById(passwordId).value;
    var onlyCharacter = /^[a-zA-Z0-9]{4,32}$/;
    if (onlyCharacter.test(password) == false) {
        notValid("password_error", "div_password", "span_password_error", "span_password_ok", "password must be between 4-32 character and can not contain a space");
        return false;
    }
    else
        valid("password_error", "div_password", "span_password_error", "span_password_ok", "Password");
}

function firstNameValid(firstNameId) {
    var pattern = /^[a-zA-Z]{4,32}$/;
    var result = false;
    var firstName = getById(firstNameId).value;
    if (pattern.test(firstName) == false) {
        notValid("first_name_error", "div_first_name", "span_first_name_error", "span_first_name_ok", "first name must be between 2-32 character and can not contain a space");
        return result;
    }
    else
        valid("first_name_error", "div_first_name", "span_first_name_error", "span_first_name_ok", "First name");
}

function lastNameValid(lastNameId) {
    var pattern = /^[a-zA-Z]{4,32}$/;
    var result = false;
    var lastName = getById(lastNameId).value;
    if (pattern.test(lastName) == false) {
        notValid("last_name_error", "div_last_name", "span_last_name_error", "span_last_name_ok", "last name must be between 2-32 character and can not contain a space");
        return result;
    }
    else
        valid("last_name_error", "div_last_name", "span_last_name_error", "span_last_name_ok", "Last name");
}

function sqtValid(sqtId) {
    var pattern = /^[a-zA-Z0-9-/]{1,32}$/;
    var result = false;
    var sqt = getById(sqtId).value;
    if (pattern.test(sqt) == false) {
        notValid("secret_question_error", "div_secret_question", "span_secret_question_error", "span_secret_question_ok", "secret question must be between 1-32 character and can not contain a space");
        return result;
    }
    else
        valid("secret_question_error", "div_secret_question", "span_secret_question_error", "span_secret_question_ok", "Secret question");
}

function validRegistration(loginId, passwordId, confirmPasswordId, firstNameId, lastNameId, sqtId){
    var login = loginValid(loginId);
    var password = passwordValid(passwordId, confirmPasswordId);
    var firstName = firstNameValid(firstNameId);
    var lastName = lastNameValid(lastNameId);
    var sqt = sqtValid(sqtId);
    if (login == false || password == false || firstName == false || lastName == false || sqt == false)
        return false;
    return true;
}

function validLoginPage (loginId, passwordId) {
    var username = loginValid(loginId);
    var password = firstPasswordValid(passwordId);
    if (username == false || password == false)
        return false;
    return true;
}

function step(elementIdStepOne, elementIdStepTwo, elementIdFinalStep, typeStepOne, typeStepTwo, typeFinalStep) {
    getById(elementIdStepOne).style.display = typeStepOne;
    getById(elementIdStepTwo).style.display = typeStepTwo;
    getById(elementIdFinalStep).style.display = typeFinalStep;
}