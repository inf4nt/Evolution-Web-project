/**
 * Created by Admin on 09.05.2017.
 */
function error(error, divId) {
    if (error == true) {
        $("#"+divId+" span:last-child").show();
        $("#"+divId+" input").next().hide();
    } else {
        $("#"+divId+" input").next().show();
        $("#"+divId+" span:last-child").hide();
    }
}

function loginValid(divEmail) {
    var email = $("#"+divEmail+" input").val();
    var emailPattern = /^[a-zA-Z0-9._-]{1,40}@[a-zA-Z0-9.-]{1,40}\.[a-zA-Z]{2,6}$/;
    if (emailPattern.test(email) == false)
        error(true, divEmail);
    else
        error(false, divEmail);
}


function firstPasswordValid(divPassword) {
    var password = $("#"+divPassword+" input").val();
    var onlyCharacter = /^[a-zA-Z0-9]{4,32}$/;
    if (onlyCharacter.test(password) == false)
        error(true, divPassword);
    else
        error(false, divPassword);
}