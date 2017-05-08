package evolution.controller;

import evolution.dao.SecretQuestionTypeDao;
import evolution.dao.UserDao;
import evolution.model.User;
import evolution.service.ResetPasswordService;
import evolution.service.builder.UserBuilderService;
import evolution.service.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Admin on 03.03.2017.
 */
@Controller
@RequestMapping("/")
@SessionAttributes(value = "sqt")
public class IndexController {

    @RequestMapping (value = "/", method = RequestMethod.GET)
    public String index () {
        return "redirect:/welcome";
    }

    @RequestMapping (value = "/welcome", method = RequestMethod.GET)
    public String welcome (
            Authentication authentication,
            HttpServletRequest request,
            SessionStatus sessionStatus) {

        if (authentication != null)
            if (authentication.isAuthenticated()) {
                return "redirect:/user/id/" + ((User)request.getSession().getAttribute("authUser")).getId();
            }
        sessionStatus.setComplete();
        return "index/login-page";
    }


    @RequestMapping (value = "/login", method = RequestMethod.GET)
    public String login (HttpServletRequest request) {
        if (request == null || request.getParameter("error") == null) {
            return "index/login-page";
        }

        return "index/login-page";
    }

    @RequestMapping (value = "/form-create-user", method = RequestMethod.GET)
    public String createUserForm (Model model) {
        model.addAttribute("sqt", sqtDao.findAll());
        return "user/form-create-user";
    }

    @RequestMapping (value = "/create-user", method = RequestMethod.POST)
    public String createUser(
            HttpServletRequest request,
            Model model, SessionStatus sessionStatus) {

        User result = null;
//        try {
//            result = userDao.findByLogin(request.getParameter("login"));
//        } catch (NoResultException e) {}
//        if (result != null) {
//            model.addAttribute("info", "User " + result.getLogin() + " is exist. Try again");
//            return "user/form-create-user";
//        }

        try {
            result = userDao.findByLogin(request.getParameter("login"));
            model.addAttribute("info", "User " + result.getLogin() + " is exist. Try again");
            return "user/form-create-user";
        } catch (NoResultException e) {

        }


        try {
            result = userBuilderService.build(null, request);
            if (!validator.userValidator(result)) {
                model.addAttribute("info", "Sorry, I can not create such a user");
                return "user/form-create-user";
            }
        } catch (Exception e){
            model.addAttribute("info", "Sorry, I can not create such a user");
            return "user/form-create-user";
        }

        userDao.save(result);
        sessionStatus.setComplete();
        return "redirect:/welcome";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
            new SecurityContextLogoutHandler().logout(request, response, auth);
        return "redirect:/welcome";
    }

    @RequestMapping (value = "/form-reset-password", method = RequestMethod.GET)
    public String formResetPassword (Model model) {
        model.addAttribute("sqt", sqtDao.findAll());
        return "user/form-reset";
    }

    @RequestMapping (value = "/reset-password", method = RequestMethod.POST)
    public String reset (Model model, HttpServletRequest request) {
        boolean reset = resetPasswordService.reset(request);
        if (!reset) {
            model.addAttribute("info", "Excuse me. I could not retrieve password");
            return "user/form-reset";
        }
        model.addAttribute("info", "Your password has been restored. You can log in");
        return "user/form-reset";
    }

    @Autowired
    private UserDao userDao;
    @Autowired
    private SecretQuestionTypeDao sqtDao;
    @Autowired
    private UserBuilderService userBuilderService;
    @Autowired
    private Validator validator;
    @Autowired
    private ResetPasswordService resetPasswordService;
}
