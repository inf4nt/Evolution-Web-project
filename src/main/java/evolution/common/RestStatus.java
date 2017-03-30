package evolution.common;

/**
 * Created by Admin on 28.03.2017.
 */
public enum RestStatus {

    GetFriend(1),
    GetFollower(2),
    AcceptFriend(3);

    RestStatus(long i) {
        this.id = i;
    }

    public RestStatus get (){
        return this;
    }

    public long getId() {
        return id;
    }

    public final long id;
}
