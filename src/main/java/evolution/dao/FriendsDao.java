package evolution.dao;

import evolution.model.User;

import java.util.List;

/**
 * Created by Admin on 26.03.2017.
 */
public interface FriendsDao {

    List<User> findMyFriend(long id);

    List<User> findMyFollower (long id);

    void removeMyFriend (long myId, long remove);

    void acceptFriend (long myId, long friendId);

    void friendRequest (long myId, long friendId);

    List<User> findNewFriend();
}
