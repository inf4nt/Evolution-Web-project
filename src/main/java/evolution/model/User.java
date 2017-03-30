package evolution.model;

import org.hibernate.annotations.*;


import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.util.List;

/**
 * Created by Admin on 09.03.2017.
 */





@Entity
@Table (name = "user_data")
public class User {

    public User() {
    }

    public User(Long id, Date registrationDate, SecretQuestionType secretQuestionType, Long roleId, String login, String password, String secretQuestion, String firstName, String lastName) {
        this.registrationDate = registrationDate;
        this.secretQuestionType = secretQuestionType;
        this.roleId = roleId;
        this.login = login;
        this.password = password;
        this.secretQuestion = secretQuestion;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public User(Long id, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public User(Long id, String login, String firstName, String lastName) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
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
            sb.append(", role=").append(roleId);
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
    @Type(type = "date")
    private Date registrationDate;
    @ManyToOne(targetEntity = SecretQuestionType.class)
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
}
