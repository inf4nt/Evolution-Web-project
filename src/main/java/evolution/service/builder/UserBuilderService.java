package evolution.service.builder;


import evolution.common.UserRoleEnum;
import evolution.dao.SecretQuestionTypeDao;
import evolution.model.secretQuestionType.SecretQuestionType;
import evolution.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.PanelUI;
import java.util.Date;

/**
 * Created by Admin on 11.03.2017.
 */
@Service
public class UserBuilderService {

    public User requestBuild(boolean newUser, Long role, User authUser, HttpServletRequest request) {
        User result = new User();

        if (newUser) {
            SecretQuestionType sqt = new SecretQuestionType();
            sqt.setId(Long.parseLong(request.getParameter("sqtId")));
            result.setSecretQuestionType(sqt);
            result.setSecretQuestion(request.getParameter("secretQuestion"));
            result.setRegistrationDate(new Date());

        } else {
            result.setId(authUser.getId());
            result.setRegistrationDate(authUser.getRegistrationDate());
            result.setSecretQuestionType(authUser.getSecretQuestionType());
            result.setSecretQuestion(authUser.getSecretQuestion());
        }

        result.setRoleId(role);

        result.setLogin(request.getParameter("login"));
        result.setPassword(request.getParameter("password"));
        result.setFirstName(request.getParameter("firstName"));
        result.setLastName(request.getParameter("lastName"));
        return result;
    }


    public User requestBuild(HttpServletRequest request) {
        User result = new User();
        SecretQuestionType sqt = new SecretQuestionType();
        sqt.setId(Long.parseLong(request.getParameter("sqtId")));
        result.setSecretQuestionType(sqt);
        result.setSecretQuestion(request.getParameter("secretQuestion"));
        result.setRegistrationDate(new Date());

        result.setRoleId(UserRoleEnum.USER.getId());
        result.setLogin(request.getParameter("login"));
        result.setPassword(request.getParameter("password"));
        result.setFirstName(request.getParameter("firstName"));
        result.setLastName(request.getParameter("lastName"));
        return result;
    }

    public User requestBuild(Long role, HttpServletRequest request) {
        User result = requestBuild(request);
        result.setRoleId(role);
        return result;
    }

    public User requestBuild(User authUser, HttpServletRequest request){
        User result = new User ();


        result.setId(authUser.getId());
        result.setRegistrationDate(authUser.getRegistrationDate());
        result.setSecretQuestionType(authUser.getSecretQuestionType());
        result.setSecretQuestion(authUser.getSecretQuestion());
        result.setRoleId(authUser.getRoleId());


        result.setLogin(request.getParameter("login"));
        result.setPassword(request.getParameter("password"));
        result.setFirstName(request.getParameter("firstName"));
        result.setLastName(request.getParameter("lastName"));
        return result;
    }




}
