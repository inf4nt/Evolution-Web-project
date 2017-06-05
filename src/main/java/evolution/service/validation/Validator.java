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

        String emailPattern = "^[a-zA-Z0-9._-]{1,40}@[a-zA-Z0-9.-]{1,40}\\.[a-zA-Z]{2,12}";
        String onlyCharacter = "^[a-zA-Z0-9]{4,32}";
        String namePattern = "^[a-zA-Z]{4,32}";

        try {
            if (!user.getLogin().matches(emailPattern) ||
                    !user.getPassword().matches(onlyCharacter) ||
                    !user.getFirstName().matches(namePattern) ||
                    !user.getLastName().matches(namePattern) ||
                    user.getRegistrationDate() == null)
                return  false;
            if (user.getRoleId().equals(UserRoleEnum.ADMIN.getId()) || user.getRoleId().equals(UserRoleEnum.USER.getId()))
                return true;

            else
                return false;
        } catch (Exception e){
            return false;
        }
    }
}
