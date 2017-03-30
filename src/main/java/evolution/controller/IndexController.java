package evolution.controller;

import evolution.dao.SecretQuestionTypeDao;
import evolution.dao.UserDao;
import evolution.model.User;
import evolution.model.form.UserForm;
import evolution.service.UserBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


/**
 * Created by Admin on 03.03.2017.
 */
@Controller
@RequestMapping("/")
@SessionAttributes(value = "sqt")
public class IndexController {

    @RequestMapping (value = "/", method = RequestMethod.GET)
    public String index () {
//        userBuilderService.createDefault();
        return "redirect:/welcome";
    }

    @RequestMapping (value = "/welcome", method = RequestMethod.GET)
    public String welcome (Authentication authentication, HttpServletRequest request, SessionStatus sessionStatus) {
        if (authentication != null)
            if (authentication.isAuthenticated()) {
                return "redirect:/user/home";
            }
        sessionStatus.setComplete();
        request.getSession().invalidate();
        return "index/index";
    }


    @RequestMapping (value = "/login", method = RequestMethod.GET)
    public String login (HttpServletRequest request, Model model) {
        if (request == null || request.getParameter("error") == null) {
            return "index/index";
        }

        return "index/index";
    }

    @RequestMapping (value = "/form-create-user", method = RequestMethod.GET)
    public String createUserForm (Model model, HttpServletRequest request, SessionStatus sessionStatus) {
        model.addAttribute("sqt", sqtDao.findAll());
        model.addAttribute("form", new UserForm());
        return "user/form-create-user";
    }

    @RequestMapping (value = "/create-user", method = RequestMethod.POST)
    public String createUser(
            @Valid @ModelAttribute("form") UserForm form,
            BindingResult bindingResult, HttpServletRequest request,
            Model model, SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            return "user/form-create-user";
        }

        User result = null;
        try {
            result = userDao.findByLogin(request.getParameter("login"));
        } catch (NoResultException e) {}
        if (result != null) {
            model.addAttribute("info", "User " + result.getLogin() + " is exist. Try again");
            return "user/form-create-user";
        }
        User user = userBuilderService.build(null, request);
        userDao.save(user);
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

    @Autowired
    private UserDao userDao;
    @Autowired
    private SecretQuestionTypeDao sqtDao;
    @Autowired
    private UserBuilderService userBuilderService;
}
