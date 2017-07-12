package evolution.model.friend;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.model.user.StandardUser;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Admin on 15.04.2017.
 */

@Entity
@Table(name = "friends")
@ToString @NoArgsConstructor @AllArgsConstructor @Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Friends implements Serializable{

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private StandardUser user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "friend_id")
    private StandardUser friend;

    @Column(name = "status")
    private Long status;

    public Friends(Long id, String firstName, String lastName, Long friendStatus) {
        this.user = new StandardUser(id, firstName, lastName);
        this.status = friendStatus;
    }

    public Friends(Long id, String firstName, String lastName) {
        this.user = new StandardUser(id, firstName, lastName);
    }

    public Friends(Long friendId, String friendFirstName, String friendLastName,
                   Long userId, String userFirstName, String userLastName) {
        this(userId, userFirstName, userLastName, null);
        this.friend = new StandardUser(friendId, friendFirstName, friendLastName);
    }

    public Friends(Long friendId, String friendFirstName, String friendLastName,
                   Long userId, String userFirstName, String userLastName, Long status) {
        this(userId, userFirstName, userLastName, status);
        this.friend = new StandardUser(friendId, friendFirstName, friendLastName);
    }

}
