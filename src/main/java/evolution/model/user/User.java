package evolution.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.common.UserRoleEnum;
import evolution.model.secretQuestionType.SecretQuestionType;
import lombok.*;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.DateFormat;

/**
 * Created by Admin on 09.03.2017.
 */
@Entity
@Table (name = "user_data")
@NoArgsConstructor @ToString(callSuper = true) @Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends UserDefaultData{

    public User(Long id){
        this.id = id;
    }

    public User(Long id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @JsonIgnore
    public void updateFields(User user) {
        this.id = user.id;
        this.login = user.login;
        this.password = user.password;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.secretQuestionType = user.secretQuestionType;
        this.secretQuestion = user.secretQuestion;
        this.roleId = user.roleId;
        this.registrationDate = user.registrationDate;
    }

    @JsonIgnore
    public String getRole() {
        if (roleId != null) {
            if (roleId == UserRoleEnum.USER.getId())
                return UserRoleEnum.USER.toString();
            if (roleId == UserRoleEnum.ADMIN.getId())
                return UserRoleEnum.ADMIN.toString();
        }
        return null;
    }

    @JsonIgnore
    public String getDateFormatRegistrationDate() {
        return DateFormat.getInstance().format(registrationDate);
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "secret_question_type_id")
    @JsonProperty
    private SecretQuestionType secretQuestionType;

    @Column(name = "secret_question")
    @JsonProperty
    private String secretQuestion;
}
