package evolution.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Admin on 19.05.2017.
 */
@MappedSuperclass
@NoArgsConstructor @ToString(callSuper = true) @Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class UserDefaultData extends AbstractUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    @SequenceGenerator(name = "seq_user", sequenceName = "seq_user_data_id", allocationSize = 1)
    @JsonProperty(value = "userId")
    protected Long id;

    @Column(name = "first_name")
    @JsonProperty
    protected String firstName;
    @Column(name = "last_name")
    @JsonProperty
    protected String lastName;

    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty
    protected Date registrationDate;

    @Formula("(SELECT count(1) from friends f join user_data u on f.user_id = u.id WHERE f.user_id = id and f.status = 1)")
    private Long countFriends;

    @Formula("(SELECT count(1) from friends f join user_data u on f.user_id = u.id WHERE f.user_id = id and f.status = 2)")
    private Long countFollower;

    @Formula("(SELECT count(1) from friends f join user_data u on f.user_id = u.id WHERE f.user_id = id and f.status = 3)")
    private Long countRequest;
}
