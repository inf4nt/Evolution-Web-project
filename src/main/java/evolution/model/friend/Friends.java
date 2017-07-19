package evolution.model.friend;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.model.user.StandardUser;
import lombok.*;
import org.hibernate.annotations.Formula;

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
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_friends")
    @SequenceGenerator(name = "seq_friends", sequenceName = "seq_friends_id", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private StandardUser user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "friend_id")
    private StandardUser friend;

    @Column(name = "status")
    private Long status;

    @Formula("(SELECT count(1) from friends f join user_data u on f.user_id = u.id WHERE f.user_id = friend_id and f.status = 1)")
    private Long countFriends;

    @Formula("(SELECT count(1) from friends f join user_data u on f.user_id = u.id WHERE f.user_id = friend_id and f.status = 2)")
    private Long countFollowers;

    @Formula("(SELECT count(1) from friends f join user_data u on f.user_id = u.id WHERE f.user_id = friend_id and f.status = 3)")
    private Long countRequests;

    public Friends(StandardUser user, StandardUser friend, Long status) {
        this.user = user;
        this.friend = friend;
        this.status = status;
    }

    public Friends(Long id, String firstName, String lastName, Long friendStatus) {
        this.user = new StandardUser(id, firstName, lastName);
        this.status = friendStatus;
    }

    public Friends(Long id, String firstName, String lastName, Long friendStatus, Long countFriends, Long countFollowers, Long countRequests) {
        this.friend = new StandardUser(id, firstName, lastName);
        this.status = friendStatus;
        this.countFollowers = countFollowers;
        this.countFriends = countFriends;
        this.countRequests = countRequests;
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
