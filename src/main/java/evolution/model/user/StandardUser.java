package evolution.model.user;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.common.FriendStatusEnum;
import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 24.06.2017.
 */
@Entity
@Table(name = "user_data")
@NoArgsConstructor @AllArgsConstructor @ToString @Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardUser implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    @SequenceGenerator(name = "seq_user", sequenceName = "seq_user_data_id", allocationSize = 1)
    @JsonProperty(value = "userId")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

//    @Formula("(SELECT count(1) from friends f join user_data u on f.user_id = u.id WHERE f.user_id = id and f.status = 1)")
//    private Long countFriends;
//
//    @Formula("(SELECT count(1) from friends f join user_data u on f.user_id = u.id WHERE f.user_id = id and f.status = 2)")
//    private Long countFollower;
//
//    @Formula("(SELECT count(1) from friends f join user_data u on f.user_id = u.id WHERE f.user_id = id and f.status = 3)")
//    private Long countRequest;

    public StandardUser(Long id) {
        this.id = id;
    }

//    public StandardUser(Long id, String firstName, String lastName) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.id = id;
//    }
}
