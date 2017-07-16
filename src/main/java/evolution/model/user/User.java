package evolution.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.common.UserRoleEnum;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.WhereJoinTable;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 09.03.2017.
 */
@Entity
@Table (name = "user_data")
@NoArgsConstructor @ToString(callSuper = true) @Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends UserDefaultData{

    @Column
    private String country;

    @Column
    private String state;

    public User(Long id){
        this.id = id;
    }

    public User(Long id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User (String login, String password, String firstName, String lastName) {
        this.login = login;
        this.password = password;
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
        this.roleId = user.roleId;
        this.registrationDate = user.registrationDate;
    }

    @JsonIgnore
    public String getRole() {
        StringBuilder s = new StringBuilder();
        Arrays.asList(UserRoleEnum.values()).forEach(userRoleEnum -> {
            if (userRoleEnum.id == roleId) {
                s.append(userRoleEnum.name());
            }
        });
        return s.toString();
    }

    @JsonIgnore
    public String getDateFormatRegistrationDate() {
        return DateFormat.getInstance().format(registrationDate);
    }
}
