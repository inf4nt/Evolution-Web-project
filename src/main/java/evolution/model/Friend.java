package evolution.model;

/**
 * Created by Admin on 31.03.2017.
 */
public class Friend {

    public Friend() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Friend{");
        sb.append("userId=").append(userId);
        sb.append(", friendId=").append(friendId);
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Friend friend = (Friend) o;

        if (userId != null ? !userId.equals(friend.userId) : friend.userId != null) return false;
        if (friendId != null ? !friendId.equals(friend.friendId) : friend.friendId != null) return false;
        return status != null ? status.equals(friend.status) : friend.status == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (friendId != null ? friendId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    private Long userId;
    private Long friendId;
    private String status;
}
