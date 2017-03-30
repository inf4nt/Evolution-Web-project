package evolution.model.form;

import javax.validation.constraints.Size;

/**
 * Created by Admin on 11.03.2017.
 */
public class SecretQuestionTypeForm {

    public SecretQuestionTypeForm() {
    }

    public String getSqt() {
        return sqt;
    }

    public void setSqt(String sqt) {
        this.sqt = sqt;
    }

    @Size(min = 4, max = 32, message = "secret question type must be between 4-32 character")
    private String sqt;
}
