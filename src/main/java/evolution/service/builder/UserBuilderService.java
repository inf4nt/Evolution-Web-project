package evolution.service.builder;


import evolution.common.UserRoleEnum;
import evolution.dao.SecretQuestionTypeDao;
import evolution.model.SecretQuestionType;
import evolution.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Admin on 11.03.2017.
 */
@Service
public class UserBuilderService {

    public User build (Long id, HttpServletRequest request) {
        User user = new User();
        SecretQuestionType sqt = sqtDao.findById(Long.parseLong(request.getParameter("sqtId")));
        long roleId = UserRoleEnum.valueOf(request.getParameter("role")).getId();
        if (id != null) {
            user.setId(id);
            user.setRegistrationDate(new Date(Long.parseLong(request.getParameter("registrationDate"))));
        } else {
            user.setRegistrationDate(new Date());
        }
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setRoleId(roleId);
        user.setSecretQuestionType(sqt);
        user.setSecretQuestion(request.getParameter("secretQuestion"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        return user;
    }

    public User getDefaultUser() {
        User user = new User();
        user.setRoleId(UserRoleEnum.USER.getId());
        user.setLogin("defaultUser@evolution.com");
        user.setPassword("defaultUser");
        user.setId(0l);
        return user;
    }

    public User getDefaultAdmin() {
        User user = new User();
        user.setRoleId(UserRoleEnum.ADMIN.getId());
        user.setLogin("defaultAdmin@evolution.com");
        user.setPassword("defaultAdmin");
        user.setId(0l);
        return user;
    }

    @Autowired
    private SecretQuestionTypeDao sqtDao;
}
