package evolution.service;


import evolution.common.UserRoleEnum;
import evolution.dao.SecretQuestionTypeDao;
import evolution.dao.UserDao;
import evolution.model.SecretQuestionType;
import evolution.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

/**
 * Created by Admin on 11.03.2017.
 */
@Service
public class UserBuilderService {

    public User build (Long id, HttpServletRequest request) {
        User user = new User();
        SecretQuestionType sqt = sqtDao.findById(Long.parseLong(request.getParameter("sqtId")));
        long roleId = UserRoleEnum.valueOf(request.getParameter("role")).getId();
        if (id != null)
            user.setId(id);
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setRoleId(roleId);
        user.setSecretQuestionType(sqt);
        user.setSecretQuestion(request.getParameter("secretQuestion"));
        user.setRegistrationDate(new Date(new java.util.Date().getTime()));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        return user;
    }

    public User getDefaultUser() {
        User user = new User();
        user.setRoleId(UserRoleEnum.USER.getId());
        user.setLogin("default_user");
        user.setPassword("default_user");
        user.setId(0l);
        return user;
    }

    public User getDefaultAdmin() {
        User user = new User();
        user.setRoleId(UserRoleEnum.ADMIN.getId());
        user.setLogin("default_admin");
        user.setPassword("default_admin");
        user.setId(0l);
        return user;
    }

    @Autowired
    private SecretQuestionTypeDao sqtDao;
}
