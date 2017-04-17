package evolution.service.validation;

import evolution.common.UserRoleEnum;
import evolution.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 25.03.2017.
 */
@Service
public class Validator {

    public boolean userValidator(User user){
        String emailPattern = "^[a-zA-Z0-9._-]{1,40}@[a-zA-Z0-9.-]{1,40}\\.[a-zA-Z]{2,6}";
        String onlyCharacter = "^[a-zA-Z0-9]{4,32}";
        String namePattern = "^[a-zA-Z]{4,32}";
        String sqtPattern = "^[a-zA-Z0-9-/]{1,32}";

        try {
            if (!user.getLogin().matches(emailPattern) ||
                    !user.getPassword().matches(onlyCharacter) ||
                    !user.getSecretQuestion().matches(sqtPattern) ||
                    user.getSecretQuestionType() == null ||
                    !user.getFirstName().matches(namePattern) ||
                    !user.getLastName().matches(namePattern) ||
                    user.getRegistrationDate() == null)
                return  false;
//            if (user.getRoleId()  == UserRoleEnum.ADMIN.getId() || user.getRoleId() == UserRoleEnum.USER.getId())
//                return true;
            if (user.getRole().equals(UserRoleEnum.ADMIN.toString()) || user.getRole().equals(UserRoleEnum.USER.toString()))
                return true;
            else
                return false;
        } catch (Exception e){
            return false;
        }
    }
}
