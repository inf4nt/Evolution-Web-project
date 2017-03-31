package evolution.dao;

import evolution.model.User;

import java.util.List;

/**
 * Created by Admin on 26.03.2017.
 */
public interface FriendsDao {

    List<User> findMyFriend(long id);

    List<User> findMyFollower (long id);

    void removeMyFriend (long id1, long id2);

    void acceptFriend (long id1, long id2);

    void friendRequest (long id1, long id2);

    List<User> findNewFriend();

    boolean checkIsFriend(long id1, long id2);

    void removeFollower (long id1, long id2);
}
