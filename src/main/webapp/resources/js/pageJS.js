/**
 * Created by Admin on 13.05.2017.
 */
function showHidePassword(href, divPass, divConfirm) {
    var type = $("#"+divPass+" input").attr("type");
    if (type == 'password') {
        $("#"+href).html('Hide password and confirm password');
        $("#"+divPass+" input, #"+divConfirm+" input").attr("type", "text");
    } else {
        $("#"+href).html('Show password and confirm password');
        $("#"+divPass+" input, #"+divConfirm+" input").attr("type", "password");
    }
}
