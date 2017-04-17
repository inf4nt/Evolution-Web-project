package evolution.model;

import evolution.common.FriendStatusEnum;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 15.04.2017.
 */
@Entity
@Table(name = "friends")
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Friends{\n");
        sb.append("user1Id=").append(userId);
        sb.append(",\n friendId=").append(friendId);
        sb.append(",\n status=").append(getStatus());
        sb.append('}');
        return sb.toString();
    }

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

//    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    private User friendId;

    @Column(name = "status")
    private Long status;
}
