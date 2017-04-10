package evolution.dao;

import evolution.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 26.03.2017.
 */
public interface FriendsDao {

    List<User> findMyFriend(long authUserId);

    List<User> findMyFollower (long authUserId);

    List<User> findMyRequest (long authUserId);

    void acceptFriend (long authUserId, long id2);

    void friendRequest (long authUserId, long id2);

    void deleteFriend(long authUserId, long id2);

    void deleteRequest(long authUserId, long id2);
}
