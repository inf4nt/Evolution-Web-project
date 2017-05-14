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


}
