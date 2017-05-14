package evolution.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.common.FriendStatusEnum;
import evolution.common.UserRoleEnum;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;


import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 09.03.2017.
 */
@Entity
@Table (name = "user_data")
@NoArgsConstructor @ToString @Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable{

    public User(Long id){
        this.id = id;
    }

    public User (User user, Long status) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.registrationDate = user.getRegistrationDate();
        this.roleId = user.getRoleId();
        this.secretQuestion = user.getSecretQuestion();
        this.secretQuestionType = user.getSecretQuestionType();
        setFriendStatus(status);
    }

    public User (Long id, String firstName, String lastName, Long roleId, Date registrationDate, Long friendStatus){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleId = roleId;
        this.registrationDate = registrationDate;
        setFriendStatus(friendStatus);
    }

    public User (Long friendId, String firstName, String lastName, Long userId, String userFirstName, String userLastName, Long status){
        this.id = friendId;
        this.firstName = firstName;
        this.lastName = lastName;
        setFriendStatus(status);
        this.userId = userId;
        this.userLastName = userLastName;
        this.userFirstName = userFirstName;
    }

    public User (User user, SecretQuestionType secretQuestionType, Long friendStatus){
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.registrationDate = user.getRegistrationDate();
        this.roleId = user.getRoleId();
        this.secretQuestion = user.getSecretQuestion();
        this.secretQuestionType = secretQuestionType;
        setFriendStatus(friendStatus);
    }

    public User(Long id, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public User(Long id, String firstName, String lastName, Long status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        setFriendStatus(status);
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(Long id, String login, String firstName, String lastName, SecretQuestionType secretQuestionType, String sq) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.login = login;
        this.secretQuestion = sq;
        this.secretQuestionType = secretQuestionType;
    }

    public User(Long id, String login, String firstName, String lastName) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public User(Long id, Long roleId, String login, String password) {
        this.id = id;
        this.roleId = roleId;
        this.login = login;
        this.password = password;
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
    public void setFriendStatus(Long friendStatus) {
        if (friendStatus != null) {
            if (friendStatus == FriendStatusEnum.FOLLOWER.getId())
                this.friendStatus = FriendStatusEnum.FOLLOWER.toString();
            if (friendStatus == FriendStatusEnum.PROGRESS.getId())
                this.friendStatus = FriendStatusEnum.PROGRESS.toString();
            if (friendStatus == FriendStatusEnum.REQUEST.getId())
                this.friendStatus = FriendStatusEnum.REQUEST.toString();
        }
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        User user = (User) object;

        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_user")
    @SequenceGenerator(name = "seq_user", sequenceName = "seq_user_data_id", allocationSize = 1)
    @JsonProperty(value = "userId")
    private Long id;
    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty
    private Date registrationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "secret_question_type_id")
    @JsonProperty
    private SecretQuestionType secretQuestionType;

    @Column(name = "role_id")
    @JsonProperty
    private Long roleId;
    @Column(unique = true, nullable = false)
    @JsonProperty
    private String login;
    @Column (nullable = false)
    @JsonProperty
    private String password;
    @Column(name = "secret_question")
    @JsonProperty
    private String secretQuestion;
    @Column(name = "first_name")
    @JsonProperty
    private String firstName;
    @Column(name = "last_name")
    @JsonProperty
    private String lastName;















    @Transient
    @JsonIgnore
    private String friendStatus;
    @Transient
    @JsonIgnore
    private String userFirstName;
    @Transient
    @JsonIgnore
    private String userLastName;
    @Transient
    @JsonIgnore
    private Long userId;
}
