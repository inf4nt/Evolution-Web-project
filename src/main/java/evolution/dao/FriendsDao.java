package evolution.dao;

import evolution.common.FriendStatusEnum;
import evolution.model.friend.Friends;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 26.03.2017.
 */
public interface FriendsDao {

    Friends findUserAndFriendStatus(Long authUserId, Long id);

    List<Friends> findFriend(long authUserId, int limit, int offset);

    List<Friends> findFollower(long authUserId, int limit, int offset);

    List<Friends> findRequest(long authUserId, int limit, int offset);

    List<Friends> moreFriend(long authUserId, int limit, int offset);

    List<Friends> moreFollower(long authUserId, int limit, int offset);

    List<Friends> moreRequest(long authUserId, int limit, int offset);

    Map<String, List<Friends>> friend(long authUserId, int limit, int offset);

    Map<String, List<Friends>> friendFollower(long authUserId, int limit, int offset);

    void acceptFriend (long authUserId, long id2);

    void friendRequest (long authUserId, long id2);

    void deleteFriend(long authUserId, long id2);

    void deleteRequest(long authUserId, long id2);

    boolean checkFriends(long authUserId, long id2);

    String CHECK_FRIENDS = "select 1 from Friends f " +
            " where (f.friend.id = :id1 and f.user.id = :id2)" +
            " or (f.friend.id = :id2 and f.user.id =:id1)";

    String FIND_ALL_FRIENDS = "select new Friends " +
            "(uf.id, uf.firstName, uf.lastName," +
            " uu.id, uu.firstName, uu.lastName) from User uf" +
            " join Friends ff on uf.id = ff.friend.id " +
            " join User uu on uu.id = ff.user.id " +
            " where ff.user.id = :id ";


    String USER_JOIN_FRIEND = "select new Friends " +
            "(uf.id, uf.firstName, uf.lastName) " +
            " from User uf" +
            " join Friends ff on uf.id = ff.friend.id " +
            " where ff.user.id = :id ";

    String SET_STATUS_FRIEND = "update Friends \nset status = :status \nwhere user.id = :u \nand friend.id = :f \nand status =:s";

    String DELETE_REQUEST_FRIEND = "delete from Friends where (user.id = :u and friend.id = :f) \n" +
            "or (user.id = :f and friend.id = :u)";

    String MORE_FRIEND = USER_JOIN_FRIEND + " and ff.status = " + FriendStatusEnum.PROGRESS.getId();

    String MORE_FOLLOWER = USER_JOIN_FRIEND + " and ff.status = " + FriendStatusEnum.FOLLOWER.getId();

    String MORE_REQUEST = USER_JOIN_FRIEND + " and ff.status = " + FriendStatusEnum.REQUEST.getId();

    String FIND_FRIEND = FIND_ALL_FRIENDS + " and ff.status = " + FriendStatusEnum.PROGRESS.getId();

    String FIND_FOLLOWER = FIND_ALL_FRIENDS + " and ff.status = " + FriendStatusEnum.FOLLOWER.getId();

    String FIND_REQUEST = FIND_ALL_FRIENDS + " and ff.status = " + FriendStatusEnum.REQUEST.getId();


    String FIND_USER_AND_FRIEND_STATUS = "select new Friends(u.id, u.firstName, u.lastName, f.status) from Friends f " +
            " right join User u on u.id = f.friend.id and f.user.id = :authUserId " +
            " where u.id = :id";
}
