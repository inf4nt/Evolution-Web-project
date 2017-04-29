package evolution.model;

import evolution.common.FriendStatusEnum;
import lombok.ToString;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 15.04.2017.
 */

@Entity
@Table(name = "friends")
@ToString
public class Friends implements Serializable{

    public Friends() {
    }

    public User getUserId() {
        return userId;
    }

    public void setUser1Id(User user1Id) {
        this.userId = user1Id;
    }

    public User getFriendId() {
        return friendId;
    }

    public void setFriendId(User friendId) {
        this.friendId = friendId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }


    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    private User friendId;

    @Column(name = "status")
    private Long status;
}
