package evolution.service.validation;

import evolution.model.SecretQuestionType;
import evolution.model.User;

/**
 * Created by Admin on 25.03.2017.
 */
public class ValidatorImpl {


    public boolean isValid (Object object) {
        if (object instanceof User)
            return isValidUser((User) object);
        if (object instanceof SecretQuestionType)
            return isValidSqt((SecretQuestionType) object);
        return false;
    }

    private boolean isValidSqt(SecretQuestionType secretQuestionType){
        try {
            if (secretQuestionType.getName().length() < 0 || secretQuestionType.getName().length()> 32)
                return false;
        } catch (Exception e){
            return false;
        }
        return true;
    }

    private boolean isValidUser(User user){
        try {
            if (user.getLogin().length() < 4
                    || user.getLogin().length() > 32
                    || !user.getLogin()
                    .matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
                    )
                return false;
            if (user.getPassword().length() < 4 || user.getPassword().length() > 32)
                return false;
            if (user.getRegistrationDate() == null)
                return false;
            if (user.getSecretQuestion().length() < 0 || user.getSecretQuestion().length() > 32)
                return false;
            if (user.getSecretQuestionType().getName() == null)
                return false;
            String role = user.getRole().getName();
            if (!role.equals("USER") && !role.equals("ADMIN"))
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
