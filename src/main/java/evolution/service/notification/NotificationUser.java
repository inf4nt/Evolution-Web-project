package evolution.service.notification;

import evolution.model.user.User;
import evolution.model.userToken.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 26.05.2017.
 */
@Service
public class NotificationUser {

    public void sendTokenUserRegistration(UserToken userToken) {
//        String tokenRegistrationUrl = "http://localhost:8585/service/user-registration/" + userToken.getToken();
        String tokenRegistrationUrl = "https://evolution-web.herokuapp.com/service/user-registration/" + userToken.getToken();
        subject = "Registration on the site evolution-web.herokuapp.com";
        message = "<html>" +
                "\n<img src=\"" + pathLogo + "\"/>" +
                "\n<h3>Hello ! You are trying to register</h3>" +
                "\n<br/><p>To continue the registration, follow on the link</p><br/>" +
                "\n<b>" + tokenRegistrationUrl + "</b>" +
                "\n<hr/>" +
                "\n<p>If you did not, ignore the message</p>" +
                "\n</html>";
        mailService.send(userToken.getUser().getLogin(), subject, message);
    }

    public void successUserRegistration(UserToken userToken) {

        subject = "Registration on the site evolution-web.herokuapp.com";
        message = "<html>" +
                "\n<img src=\"" + pathLogo + "\"/>" +
                "\n<h3>Hello " + userToken.getUser().getFirstName() + " " + userToken.getUser().getLastName() +  " ! Registration success</h3>" +
                "\n<br/><p>Your data. Visit the site and enjoy</p><br/>" +
                "\n<b>Login: " + userToken.getUser().getLogin() +"\n<br/>Password: " + userToken.getUser().getPassword() + "</b>" +
                "\n</html>";
        mailService.send(userToken.getUser().getLogin(), subject, message);
    }


    public void forgotPassword(UserToken userToken) {
//        String tokenForgotUrl = "http://localhost:8585/service/user-forgot/" + userToken.getToken();
        String tokenForgotUrl = "https://evolution-web.herokuapp.com/service/user-forgot/" + userToken.getToken();
        subject = "Forgot password on the site evolution-web.herokuapp.com";
        message = "<html>" +
                "\n<img src=\"" + pathLogo + "\"/>" +
                "\n<h3>Hello " + userToken.getUser().getFirstName() + " " + userToken.getUser().getLastName() +  " !</h3>" +
                "\n<br/><p>You are trying to restore password</p><br/>" +
                "\n<br/><p>To continue the restore, follow on the link</p><br/>" +
                "\n<b>" + tokenForgotUrl + "</b>" +
                "\n<p>If you did not, ignore the message</p>" +
                "\n</html>";
        mailService.send(userToken.getUser().getLogin(), subject, message);
    }



    public void successUserForgot(UserToken userToken) {
        subject = "Forgot password on the site evolution-web.herokuapp.com";
        message = "<html>" +
                "\n<img src=\"" + pathLogo + "\"/>" +
                "\n<h3>Hello " + userToken.getUser().getFirstName() + " " + userToken.getUser().getLastName() +  " ! Password recovery successful</h3>" +
                "\n<br/><p>Your new data. Visit the site and enjoy</p><br/>" +
                " <hr/>" +
                "\n<b>Login: " + userToken.getUser().getLogin() +"\n<br/><br/>Password: " + userToken.getUser().getPassword() + "</b>" +
                " <hr/>" +
                "\n</html>";
        mailService.send(userToken.getUser().getLogin(), subject, message);
    }






    public void setUserName(User userNew, User userOld){
        subject = "Change email on the site evolution-web.herokuapp.com";
        message = "<html>" +
                "\n<img src=\"" + pathLogo + "\"/>" +
                "\n<h3>Hello " + userOld.getFirstName() + " " + userOld.getLastName() +  " ! Your email address has been changed</h3>" +
                "\n<br/><p>Your data. Visit the site and enjoy</p><br/>" +
                "\n<b>Your new email address: " + userNew.getLogin() + "</b>" +
                "\n</html>";
        mailService.send(userOld.getLogin(), subject, message);

    }


    private String subject;
    private String message;
    private String pathLogo = "https://s8.hostingkartinok.com/uploads/images/2017/05/4d404fbf78d4a6c8c9d09417a2f4ae4b.png";
    @Autowired
    private MailService mailService;
}
