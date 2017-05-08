package evolution.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.common.FriendStatusEnum;
import lombok.*;

import javax.persistence.*;

/**
 * Created by Admin on 03.05.2017.
 */

@Entity
@Table(name = "user_data")
@NoArgsConstructor @ToString @Getter @Setter
public class UserFriend {

    public UserFriend(Long id, String firstName, String lastName, Long friendStatus) {
        if (friendStatus != null) {
            if (friendStatus == FriendStatusEnum.FOLLOWER.getId())
                this.status = FriendStatusEnum.FOLLOWER.toString();
            if (friendStatus == FriendStatusEnum.PROGRESS.getId())
                this.status = FriendStatusEnum.PROGRESS.toString();
            if (friendStatus == FriendStatusEnum.REQUEST.getId())
                this.status = FriendStatusEnum.REQUEST.toString();
        }
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @JsonProperty
    private Long id;

    @Column(name = "first_name")
    @JsonProperty
    private String firstName;
    @Column(name = "last_name")
    @JsonProperty
    private String lastName;

    @JsonProperty
    private String status;
}
