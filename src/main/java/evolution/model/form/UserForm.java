package evolution.model.form;



import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Admin on 10.03.2017.
 */
public class UserForm {

    public UserForm() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //    @Size(min = 4, max = 32, message = "login must be between 4-32 character")
    @Size (max = 32, message ="email must be < 32 character")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Template is not met. XXX@XXX.XXX")
    private String login;
    @Size (min = 4, max = 32, message = "password must be between 4-32 character")
    private String password;
    @Size (min = 1, max = 32, message = "secret question must be between 1-32 character")
    private String secretQuestion;
    @Size(min = 3, max = 32, message = "first name must be between 3-32 character")
    private String firstName;
    @Size(min = 3, max = 32, message = "last name must be between 3-32 character")
    private String lastName;
}
