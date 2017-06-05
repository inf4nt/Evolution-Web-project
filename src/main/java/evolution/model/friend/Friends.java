package evolution.model.friend;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.model.user.User;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonProperty
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    @JsonProperty
    private User friend;

    @Column(name = "status")
    @JsonProperty
    private Long status;

    public Friends(Long id, String firstName, String lastName, Long friendStatus) {
        this.user = new User(id, firstName, lastName);
        this.status = friendStatus;
    }

    public Friends(Long id, String firstName, String lastName) {
        this.user = new User(id, firstName, lastName);
    }

    public Friends(Long friendId, String friendFirstName, String friendLastName,
                   Long userId, String userFirstName, String userLastName) {
        this(userId, userFirstName, userLastName, null);
        this.friend = new User(friendId, friendFirstName, friendLastName);
    }

    public Friends(Long friendId, String friendFirstName, String friendLastName,
                   Long userId, String userFirstName, String userLastName, Long status) {
        this(userId, userFirstName, userLastName, status);
        this.friend = new User(friendId, friendFirstName, friendLastName);
    }

}
