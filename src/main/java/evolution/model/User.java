package evolution.model;

import evolution.common.FriendStatusEnum;
import evolution.common.UserRoleEnum;
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
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "User")
public class User implements Serializable{

    public User() {
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDateFormatRegistrationDate() {
        return DateFormat.getInstance().format(registrationDate);
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public SecretQuestionType getSecretQuestionType() {
        return secretQuestionType;
    }

    public void setSecretQuestionType(SecretQuestionType secretQuestionType) {
        this.secretQuestionType = secretQuestionType;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getRole() {
        if (roleId != null) {
            if (roleId == UserRoleEnum.USER.getId())
                return UserRoleEnum.USER.toString();
            if (roleId == UserRoleEnum.ADMIN.getId())
                return UserRoleEnum.ADMIN.toString();
        }
        return null;
    }

    public Long getRoleId () {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
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

    public String getFriendStatus() {
        return friendStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        if (id != null)
            sb.append("id=").append(id);
        if (registrationDate != null)
            sb.append(", registrationDate=").append(registrationDate);
        if (secretQuestionType != null)
            sb.append(", secretQuestionType=").append(secretQuestionType);
        if (roleId != null)
            sb.append(", roleId=").append(roleId);
        if (login != null)
            sb.append(", login='").append(login).append('\'');
        if (password != null)
            sb.append(", password='").append(password).append('\'');
        if (secretQuestion != null)
            sb.append(", secretQuestion='").append(secretQuestion).append('\'');
        if (firstName != null)
            sb.append(", firstName='").append(firstName).append('\'');
        if (lastName != null)
            sb.append(", lastName='").append(lastName).append('\'');
        if (userFirstName != null)
            sb.append(", userFirstName='").append(userFirstName).append('\'');
        if (userLastName != null)
            sb.append(", userLastName='").append(userLastName).append('\'');
        if (userId != null)
            sb.append(", userId='").append(userId).append('\'');
//        if (friendStatus != null)
        sb.append(", status='").append(friendStatus).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_user")
    @SequenceGenerator(name = "seq_user", sequenceName = "seq_user_data_id", allocationSize = 1)
    private Long id;
    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
    @ManyToOne
    @JoinColumn(name = "secret_question_type_id")
    private SecretQuestionType secretQuestionType;
    @Column(name = "role_id")
    private Long roleId;
    @Column(unique = true, nullable = false)
    private String login;
    @Column (nullable = false)
    private String password;
    @Column(name = "secret_question")
    private String secretQuestion;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Transient
    private String friendStatus;
    @Transient
    private String userFirstName;
    @Transient
    private String userLastName;
    @Transient
    private Long userId;






    public User (UserBuilder userBuilder) {
        this.id = userBuilder.id;
        this.login = userBuilder.login;
        this.password = userBuilder.password;
        this.firstName = userBuilder.firstName;
        this.lastName = userBuilder.lastName;
        this.roleId = userBuilder.roleId;
        this.secretQuestion = userBuilder.secretQuestion;
        this.secretQuestionType = userBuilder.secretQuestionType;
        this.registrationDate= userBuilder.registrationDate;
        this.friendStatus = userBuilder.friendStatus;
        this.userFirstName = userBuilder.userFirstName;
        this.userLastName = userBuilder.userLastName;
        this.userId = userBuilder.userId;
    }


    public static class UserBuilder {

        public UserBuilder id(Long id){
            this.id = id;
            return this;
        }

        public UserBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder password(String password){
            this.password = password;
            return this;
        }

        public UserBuilder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public UserBuilder roleId(Long roleId){
            this.roleId = roleId;
            return this;
        }

        public UserBuilder registrationDate(Date registrationDate){
            this.registrationDate = registrationDate;
            return this;
        }

        public UserBuilder secretQuestionType(SecretQuestionType secretQuestionType) {
            this.secretQuestionType = secretQuestionType;
            return this;
        }

        public UserBuilder secretQuestion(String secretQuestion){
            this.secretQuestion = secretQuestion;
            return this;
        }

        public UserBuilder friendStatus(String friendStatus){
            this.friendStatus = friendStatus;
            return this;
        }

        public UserBuilder userFirstName(String userFirstName){
            this.userFirstName = userFirstName;
            return this;
        }

        public UserBuilder userLastName(String userLastName){
            this.userLastName = userLastName;
            return this;
        }

        public UserBuilder userId(Long userId){
            this.userId = userId;
            return this;
        }

        public User build () {
            return new User(this);
        }

        private Long id;
        private Date registrationDate;
        private SecretQuestionType secretQuestionType;
        private Long roleId;
        private String login;
        private String password;
        private String secretQuestion;
        private String firstName;
        private String lastName;
        private String friendStatus;
        private String userFirstName;
        private String userLastName;
        private Long userId;
    }
}













//@Entity
//@Table (name = "user_data")
////@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "User")
//public class User implements Serializable{
//
//    public User() {
//    }
//
//    public User (Long friendId, String firstName, String lastName, Long userId, String userFirstName, String userLastName, Long status){
//        this.id = friendId;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        setFriendStatus(status);
//        this.userId = userId;
//        this.userLastName = userLastName;
//        this.userFirstName = userFirstName;
//    }
//
//    public User(Long id){
//        this.id = id;
//    }
//
//    public User(Long id, String firstName, String lastName) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.id = id;
//    }
//
//    public User(Long id, String firstName, String lastName, Long status) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.id = id;
//        setFriendStatus(status);
//    }
//
//    public User(String firstName, String lastName) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }
//
//    public User(Long id, String login, String firstName, String lastName, SecretQuestionType secretQuestionType, String sq) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.id = id;
//        this.login = login;
//        this.secretQuestion = sq;
//        this.secretQuestionType = secretQuestionType;
//    }
//
//    public User(Long id, String login, String firstName, String lastName) {
//        this.login = login;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getDateFormatRegistrationDate() {
//        return DateFormat.getInstance().format(registrationDate);
//    }
//
//    public Date getRegistrationDate() {
//        return registrationDate;
//    }
//
//    public void setRegistrationDate(Date registrationDate) {
//        this.registrationDate = registrationDate;
//    }
//
//    public SecretQuestionType getSecretQuestionType() {
//        return secretQuestionType;
//    }
//
//    public void setSecretQuestionType(SecretQuestionType secretQuestionType) {
//        this.secretQuestionType = secretQuestionType;
//    }
//
//    public String getSecretQuestion() {
//        return secretQuestion;
//    }
//
//    public void setSecretQuestion(String secretQuestion) {
//        this.secretQuestion = secretQuestion;
//    }
//
//    public Long getRoleId() {
//        return roleId;
//    }
//
//    public void setRoleId(Long roleId) {
//        this.roleId = roleId;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getFriendStatus() {
//        return friendStatus;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public void setFriendStatus(Long friendStatus) {
//        if (friendStatus != null) {
//            if (friendStatus == FriendStatusEnum.FOLLOWER.getId())
//                this.friendStatus = FriendStatusEnum.FOLLOWER.toString();
//            if (friendStatus == FriendStatusEnum.PROGRESS.getId())
//                this.friendStatus = FriendStatusEnum.PROGRESS.toString();
//            if (friendStatus == FriendStatusEnum.REQUEST.getId())
//                this.friendStatus = FriendStatusEnum.REQUEST.toString();
//        }
//    }
//
//    public String getUserFirstName() {
//        return userFirstName;
//    }
//
//    public void setUserFirstName(String userFirstName) {
//        this.userFirstName = userFirstName;
//    }
//
//    public String getUserLastName() {
//        return userLastName;
//    }
//
//    public void setUserLastName(String userLastName) {
//        this.userLastName = userLastName;
//    }
//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("User{");
//        if (id != null)
//            sb.append("id=").append(id);
//        if (registrationDate != null)
//            sb.append(", registrationDate=").append(registrationDate);
//        if (secretQuestionType != null)
//            sb.append(", secretQuestionType=").append(secretQuestionType);
//        if (roleId != null)
//            sb.append(", roleId=").append(roleId);
//        if (login != null)
//            sb.append(", login='").append(login).append('\'');
//        if (password != null)
//            sb.append(", password='").append(password).append('\'');
//        if (secretQuestion != null)
//            sb.append(", secretQuestion='").append(secretQuestion).append('\'');
//        if (firstName != null)
//            sb.append(", firstName='").append(firstName).append('\'');
//        if (lastName != null)
//            sb.append(", lastName='").append(lastName).append('\'');
//        if (userFirstName != null)
//            sb.append(", userFirstName='").append(userFirstName).append('\'');
//        if (userLastName != null)
//            sb.append(", userLastName='").append(userLastName).append('\'');
//        if (userId != null)
//            sb.append(", userId='").append(userId).append('\'');
////        if (friendStatus != null)
//            sb.append(", status='").append(friendStatus).append('\'');
//        sb.append('}');
//        return sb.toString();
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        User user = (User) o;
//
//        return id != null ? id.equals(user.id) : user.id == null;
//    }
//
//    @Override
//    public int hashCode() {
//        return id != null ? id.hashCode() : 0;
//    }
//
//    @Id
//    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_user")
//    @SequenceGenerator(name = "seq_user", sequenceName = "seq_user_data_id", allocationSize = 1)
//    private Long id;
//    @Column(name = "registration_date")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date registrationDate;
//    @ManyToOne
//    @JoinColumn(name = "secret_question_type_id")
//    private SecretQuestionType secretQuestionType;
//    @Column(name = "role_id")
//    private Long roleId;
//    @Column(unique = true, nullable = false)
//    private String login;
//    @Column (nullable = false)
//    private String password;
//    @Column(name = "secret_question")
//    private String secretQuestion;
//    @Column(name = "first_name")
//    private String firstName;
//    @Column(name = "last_name")
//    private String lastName;
//    @Transient
//    private String friendStatus;
//    @Transient
//    private String userFirstName;
//    @Transient
//    private String userLastName;
//    @Transient
//    private Long userId;
//}
