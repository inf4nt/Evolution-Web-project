package evolution.service;


import evolution.common.UserRoleEnum;
import evolution.dao.SecretQuestionTypeDao;
import evolution.dao.UserDao;
import evolution.model.SecretQuestionType;
import evolution.model.User;
import evolution.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        UserRole role = new UserRole();
        role.setName(request.getParameter("role"));
        role.setId(roleId);
        if (id != null)
            user.setId(id);
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setRole(role);
        user.setSecretQuestionType(sqt);
        user.setSecretQuestion(request.getParameter("secretQuestion"));
        user.setRegistrationDate(new Date(new java.util.Date().getTime()));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        return user;
    }

    public void createDefault() {
        try {
            userDao.findByLogin("default_user");
        } catch (NoResultException e) {
            User defaultUser = new User();
            UserRole role = new UserRole();
            role.setId(UserRoleEnum.valueOf("USER").getId());
            role.setName("USER");
            defaultUser.setLogin("default_user");
            defaultUser.setPassword("default_user");
            defaultUser.setRole(role);
            userDao.save(defaultUser);
        }

        try {
            userDao.findByLogin("default_admin");
        } catch (NoResultException e) {
            User defaultAdmin = new User();
            UserRole role = new UserRole();
            role.setId(UserRoleEnum.valueOf("ADMIN").getId());
            role.setName("ADMIN");
            defaultAdmin.setLogin("default_admin");
            defaultAdmin.setPassword("default_admin");
            defaultAdmin.setRole(role);
            userDao.save(defaultAdmin);
        }
    }

    @Autowired
    private SecretQuestionTypeDao sqtDao;
    @Autowired
    private UserDao userDao;
}
