package evolution.web.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Admin on 03.03.2017.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping (value = "/", method = RequestMethod.GET)
    public String index () {
        return "redirect:/welcome";
    }

    @RequestMapping (value = "/welcome", method = RequestMethod.GET)
    public String welcome () {

        LOGGER.info("Okey. Return login page");
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
