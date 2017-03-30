package evolution.common;

/**
 * Created by Admin on 23.03.2017.
 */
public enum FriendStatusEnum {

    PROGRESS(1),
    FOLLOWER(2);

    FriendStatusEnum(long i) {
        this.id = i;
    }

    public FriendStatusEnum get (){
        return this;
    }

    public long getId() {
        return id;
    }

    public final long id;
}
