package evolution.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by Admin on 19.05.2017.
 */
@MappedSuperclass
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractUser implements Serializable, StockUser {

    @Column(name = "login", unique = true, nullable = false)
    @JsonProperty(value = "login")
    protected String login;
    @Column(name = "password", unique = true, nullable = false)
    @JsonProperty
    protected String password;
    @Column(name = "role_id")
    protected Long roleId;
}
