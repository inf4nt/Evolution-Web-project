package evolution.service;

import evolution.dao.MyQuery;
import evolution.dao.UserDao;
import evolution.model.SecretQuestionType;
import evolution.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Admin on 13.04.2017.
 */
@Service
public class ResetPasswordService {

    public boolean stepOne (String username, HttpServletRequest request, SessionStatus sessionStatus) {
//        try {
//            User user = userDao.findByLogin(username);
//            request.setAttribute("forgotUser", user);
//            return true;
//        } catch (NoResultException e) {
//            sessionStatus.setComplete();
//            return false;
//        }
        return false;
    }

    public boolean stepTwo (String secretQuestion, SecretQuestionType secretQuestionType, HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("forgotUser");
//        User result = userDao.findBySecretQuestionAndSecretQuestionType(secretQuestion, secretQuestionType);
//        if (result == null || !user.equals(result))
//            return false;
        return true;
    }

    public void stepFinal (String password, HttpServletRequest request, SessionStatus sessionStatus) {
//        User user = (User) request.getSession().getAttribute("forgotUser");
//        user.setPassword(password);
//        userDao.update(user);
//        sessionStatus.setComplete();
    }

    public boolean reset (HttpServletRequest request) {
        String username = request.getParameter("login");
        String password = request.getParameter("password");
        String secretQuestion = request.getParameter("secretQuestion");
        Long sqtId = Long.parseLong(request.getParameter("sqtId"));

        User user;
        try {
            user = userDao.findByLogin(username);
        } catch (NoResultException e) {
            return false;
        }

        try {
            User result = userDao.findBySecretQuestionAndSecretQuestionType(user.getId(), secretQuestion, sqtId);
            result.setPassword(password);
            userDao.update(result);
        } catch (NoResultException e) {
            return false;
        }

        return true;
    }


    @Autowired
    private UserDao userDao;


}
