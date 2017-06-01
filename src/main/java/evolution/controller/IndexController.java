package evolution.controller;


import evolution.model.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Admin on 03.03.2017.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping (value = "/", method = RequestMethod.GET)
    public String index () {
        return "redirect:/welcome";
    }

    @RequestMapping (value = "/welcome", method = RequestMethod.GET)
    public String welcome (
            Authentication authentication,
            HttpServletRequest request, SessionStatus sessionStatus) {
        if (authentication != null)
            if (authentication.isAuthenticated()) {
                sessionStatus.setComplete();
                return "redirect:/user/id" + ((User)request.getSession().getAttribute("authUser")).getId();

            }
        return "index/login-page";
    }

    @RequestMapping (value = "/login", method = RequestMethod.GET)
    public String login (HttpServletRequest request) {
        if (request == null || request.getParameter("error") == null) {
            return "index/login-page";
        }
        return "index/login-page";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
            new SecurityContextLogoutHandler().logout(request, response, auth);
        return "redirect:/welcome";
    }
}
