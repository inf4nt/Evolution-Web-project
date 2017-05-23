package evolution.service.validation;

import evolution.common.UserRoleEnum;
import evolution.model.message.Message;
import evolution.model.user.User;
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
            if (user.getRole().equals(UserRoleEnum.ADMIN.toString()) || user.getRole().equals(UserRoleEnum.USER.toString()))
                return true;
            else
                return false;
        } catch (Exception e){
            return false;
        }
    }

    public boolean messageValidator(Message message) {
        try {
            if (
                    message.getDialog().getId().toString().matches("^[0-9]+")
                    || message.getSender().getId().toString().matches("^[0-9]+")
                    || message.getSender().getFirstName().matches("^[a-zA-Z]{4,32}")
                    || message.getSender().getLastName().matches("^[a-zA-Z]{4,32}")
                    || message.getMessage().matches("[a-zA-Z0-9-]")
            )
            return false;
        } catch (Exception e){
            return false;
        }
       return true;
    }
}
