package evolution.controller;

import evolution.common.Constant;
import evolution.common.UserRoleEnum;
import evolution.dao.UserDao;
import evolution.model.user.User;
import evolution.model.userToken.UserToken;
import evolution.service.MyJacksonService;
import evolution.service.builder.JsonInformationBuilder;
import evolution.service.notification.NotificationUser;
import evolution.service.validation.Validator;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Admin on 10.05.2017.
 */
@Controller
@RequestMapping(value = "/service")
@SessionAttributes(value = {"userRegistrationToken", "userForgotPasswordToken"})
public class ServiceController {

    @Autowired
    private UserController userController;
    @Autowired
    private JsonInformationBuilder jsonInformationBuilder;
    @Autowired
    private NotificationUser notificationUser;
    @Autowired
    private Validator validator;
    @Autowired
    private UserDao userDao;
    @Autowired
    private MyJacksonService jacksonService;
    private Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @ResponseBody @RequestMapping(value = "/user-forgot/{action}", method = RequestMethod.POST,
            produces={"application/json; charset=UTF-8"})
    public String forgotUser(@PathVariable String action,
                             @RequestBody String json,
                             Model model) throws IOException {
        logger.info("Run " + action);
        User user = (User) jacksonService.jsonToObject(json, User.class);

        if (Constant.CREATE_FORGOT_TOKEN.toString().equalsIgnoreCase(action)) {
            User u;
            try {
                u = userDao.findByLogin(user.getLogin());
            } catch (NoResultException e) {
                logger.info("User by username:= " + user.getLogin()+", is not exist\n" + e.toString());
                return jsonInformationBuilder.buildJson(HttpStatus.OK.toString(), null, false);
            }

            UserToken userForgotPasswordToken = new UserToken(UUID.randomUUID().toString(), u);
            model.addAttribute("userForgotPasswordToken", userForgotPasswordToken);
            notificationUser.forgotPassword(userForgotPasswordToken);
            return jsonInformationBuilder.buildJson(HttpStatus.OK.toString(),
                    "Your e-mail has been sent further instructions",
                    true);
        }
        return null;
    }

    @ResponseBody @RequestMapping(value = "/user-registration/{action}", method = RequestMethod.POST,
            produces={"application/json; charset=UTF-8"})
    public String registrationUser(@RequestBody String json,
                                   @PathVariable String action,
                                   Model model) throws IOException {

        User user = (User) jacksonService.jsonToObject(json, User.class);


        logger.info("Run " + action);
        if (Constant.CHECK_EXIST_USER.toString().equalsIgnoreCase(action)){
            try {
                userDao.findByLogin(user.getLogin());
                return jsonInformationBuilder.buildJson(HttpStatus.OK.toString(), null, true);
            } catch (NoResultException e) {
                logger.info("User is not exist. Next step registration.\n" + e.toString());
                return jsonInformationBuilder.buildJson(HttpStatus.OK.toString(), null, false);
            }
        } else if (Constant.CREATE_REGISTRATION_TOKEN.toString().equalsIgnoreCase(action)) {
            UserToken userToken = new UserToken(UUID.randomUUID().toString(), user);
            model.addAttribute("userRegistrationToken", userToken);
            logger.info("Added to session attribute \n" + userToken.toString() + "\n");
            notificationUser.sendTokenUserRegistration(userToken);
            return jsonInformationBuilder.buildJson(HttpStatus.OK.toString(),
                    "Your e-mail has been sent further instructions",
                    true);
        }

        logger.info("Action not found.");
        throw new NoResultException();
    }

    @RequestMapping(value = "/user-registration/{token}", method = RequestMethod.GET)
    public String userRegistrationToken (@PathVariable String token,
                                         @SessionAttribute(required = false) UserToken userRegistrationToken,
                                         SessionStatus sessionStatus) throws IOException {

        if (userRegistrationToken != null &&
                (token.equals(userRegistrationToken.getToken()))) {
            User user = userRegistrationToken.getUser();
            user.setRoleId(UserRoleEnum.USER.getId());
            user.setRegistrationDate(new Date());
            if (validator.userValidator(user)) {
                userController.userPost(jacksonService.objectToJson(user));
                notificationUser.successUserRegistration(userRegistrationToken);
                logger.info("User is valid, success registration");
                sessionStatus.setComplete();
            }
        }
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/user-forgot/{token}", method = RequestMethod.GET)
    public String userForgotPasswordToken(@PathVariable String token,
                                          @SessionAttribute(required = false) UserToken userForgotPasswordToken,
                                          SessionStatus sessionStatus) throws IOException {

        if (userForgotPasswordToken != null && token.equals(userForgotPasswordToken.getToken())) {
            String newPassword = RandomStringUtils.randomAlphanumeric(20);
            logger.info("Generate new password:= " + newPassword);
            logger.info("update user");
            userForgotPasswordToken.getUser().setPassword(newPassword);
            userController.userPut(jacksonService.objectToJson(userForgotPasswordToken.getUser()));
            logger.info("send notification");
            notificationUser.successUserForgot(userForgotPasswordToken);
            logger.info("restore password successful");
            sessionStatus.setComplete();
        } else {
            logger.info("token not found");
        }
        return "redirect:/welcome";
    }
















//    @ResponseBody @RequestMapping(value = "/user-action/{action}",
//            method = RequestMethod.POST,
//            produces={"application/json; charset=UTF-8"})
//    public String checkExistUser (@RequestBody String json, @PathVariable String action, Model model) throws IOException {
//        User user = (User) jacksonService.jsonToObject(json, User.class);
//        if (action.equals("check")) {
//            try {
//                userDao.findByLogin(user.getLogin());
//                return jsonInformationBuilder.buildJson(HttpStatus.OK.toString(), null, true);
//            } catch (NoResultException e) {
//                return jsonInformationBuilder.buildJson(HttpStatus.OK.toString(), null, false);
//            }
//        } else if (action.equals("createRegistrationToken")) {
//            UserToken userToken = new UserToken(UUID.randomUUID().toString(), user);
//            model.addAttribute("userRegistrationToken", userToken);
//            notificationUser.sendTokenUserRegistration(userToken);
//            return jsonInformationBuilder.buildJson(HttpStatus.OK.toString(),
//                    "Your e-mail has been sent further instructions",
//                    true);
//        } else if (action.equals("createForgotToken")) {
//            User u;
//            try {
//                u = userDao.findByLogin(user.getLogin());
//            } catch (NoResultException e) {
//                return jsonInformationBuilder.buildJson(HttpStatus.OK.toString(), null, false);
//            }
//
//            UserToken userForgotPasswordToken = new UserToken(UUID.randomUUID().toString(), u);
//            model.addAttribute("userForgotPasswordToken", userForgotPasswordToken);
//            // ТУТ ПИСЬМО ОТПРАВЛЮ
//            notificationUser.forgotPassword(userForgotPasswordToken);
//            return jsonInformationBuilder.buildJson(HttpStatus.OK.toString(),
//                    "Your e-mail has been sent further instructions",
//                    true);
//        }
//
//
//        return null;
//    }
//
//    @RequestMapping(value = "/user-registration/{token}", method = RequestMethod.GET)
//    public String userRegistrationToken (@PathVariable String token,
//                                 @SessionAttribute UserToken userRegistrationToken,
//                                 SessionStatus sessionStatus) throws IOException {
//
//        if (token.equals(userRegistrationToken.getToken())) {
//            User user = userRegistrationToken.getUser();
//            user.setRoleId(UserRoleEnum.USER.getId());
//            user.setRegistrationDate(new Date());
//
//            if (!validator.userValidator(user))
//                return "redirect:/login?error=true";
//
//
//            userController.userPost(jacksonService.objectToJson(user));
//
//            notificationUser.successUserRegistration(userRegistrationToken);
//            sessionStatus.setComplete();
//            return "redirect:/welcome";
//        } else {
//            return "redirect:/login?error=true";
//        }
//    }
//
//
//    @RequestMapping(value = "/user-forgot/{token}")
//    public String userForgotPasswordToken(@PathVariable String token,
//                                          @SessionAttribute UserToken userForgotPasswordToken) throws IOException {
//        if (token.equals(userForgotPasswordToken.getToken())) {
//            String newPassword = RandomStringUtils.randomAlphanumeric(20);
//            userForgotPasswordToken.getUser().setPassword(newPassword);
//
//            userController.userPut(jacksonService.objectToJson(userForgotPasswordToken.getUser()));
//
//            // ТУТ СООБЩЕНИЕ С НОВЫМИ ДАННЫМИ
//            notificationUser.successUserForgot(userForgotPasswordToken);
//            return "redirect:/welcome";
//        } else {
//            return "redirect:/login?error=true";
//        }
//    }



}
