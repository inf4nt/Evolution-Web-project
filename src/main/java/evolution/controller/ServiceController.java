package evolution.controller;

import evolution.dao.UserDao;
import evolution.model.user.User;
import evolution.service.MyJacksonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Admin on 10.05.2017.
 */
@Controller
@RequestMapping(value = "/service")
public class ServiceController {

    @ResponseBody
    @RequestMapping(value = "/forgot-password/{step}", method = RequestMethod.GET)
    public Object forgotGet(@RequestParam String json, @PathVariable String step) throws IOException {

        User user = (User) jacksonService.jsonToObject(json, User.class);

        if (step.equals("one")) {
            try {userDao.findByLogin(user.getLogin());return true;
            } catch (NoResultException nre){return false;}

        } else if (step.equals("two")) {
            try {
                userDao.findBySecretQuestionAndSecretQuestionType(
                        user.getLogin(),
                        user.getSecretQuestion(),
                        user.getSecretQuestionType().getId());
                return true;
            } catch (NoResultException nre){return false;}

        }
        throw new NoResultException();
    }

    @ResponseBody
    @RequestMapping(value = "/forgot-password", method = RequestMethod.PUT)
    public Object forgotPut(@RequestBody String json) throws IOException {
        User user = (User) jacksonService.jsonToObject(json, User.class);
        try {
            userDao.findBySecretQuestionAndSecretQuestionType(user.getLogin(), user.getSecretQuestion(), user.getSecretQuestionType().getId());
            userDao.updateForgotPassword(user.getLogin(), user.getSecretQuestion(), user.getSecretQuestionType().getId(), user.getPassword());
            return true;
        } catch (NoResultException nre){return false;}
    }

    @Autowired
    private UserDao userDao;
    @Autowired
    private MyJacksonService jacksonService;
}
