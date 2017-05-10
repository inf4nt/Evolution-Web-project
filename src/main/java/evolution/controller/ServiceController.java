package evolution.controller;

import evolution.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.NoResultException;

/**
 * Created by Admin on 10.05.2017.
 */
@Controller
@RequestMapping(value = "/service")
public class ServiceController {

    @ResponseBody
    @RequestMapping(value = "/forgot-password-step-one", method = RequestMethod.GET)
    public boolean forgotStepOne(@RequestParam String login) {
        try {userDao.findByLogin(login);return true;
        } catch (NoResultException nre){return false;}
    }

    @ResponseBody @RequestMapping(value = "/forgot-password-step-two", method = RequestMethod.GET)
    public boolean forgotStepTwo(@RequestParam String sq,
                                 @RequestParam Long sqtId,
                                 @RequestParam String login) {
        try {
            userDao.findBySecretQuestionAndSecretQuestionType(login, sq, sqtId);
            return true;
        } catch (NoResultException nre){return false;}
    }

    @ResponseBody @RequestMapping(value = "/forgot-password-step-final", method = RequestMethod.GET)
    public boolean forgotStepFinal(@RequestParam String sq,
                                   @RequestParam Long sqtId,
                                   @RequestParam String login,
                                   @RequestParam String password) {
        try {
            userDao.findBySecretQuestionAndSecretQuestionType(login, sq, sqtId);
            userDao.update(login, sq, sqtId, password);
            return true;
        } catch (NoResultException nre){return false;}
    }

    @Autowired
    private UserDao userDao;
}
