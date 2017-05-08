package evolution.dao;

import evolution.model.SecretQuestionType;
import evolution.model.User;
import evolution.model.UserFriend;


import java.util.List;

/**
 * Created by Admin on 09.03.2017.
 */
public interface UserDao {

    void save(User user);

    void update (User user);

    User findById (long id);

    User findByLogin (String login);

    void deleteById (long id);

    void delete (User user);

    List<User> findUserByFirstLastName(String p1, String p2);

    List<User> findUserByFirstOrLastName(String p1);

    User findBySecretQuestionAndSecretQuestionType (long id, String secretQuestion, long sqtId);

    User selectFirstLastName (long id);

    User selectIdFirstLastName (long id);

    UserFriend findUserAndFriendStatus(long myId, long secondId);


    String FIND_USER_AND_FRIEND_STATUS = "select new UserFriend (u.id, u.firstName, u.lastName, f.status ) from UserFriend u" +
            " left join Friends f on u.id = f.friendId.id and f.userId.id = :authUserId" +
            " where u.id = :userId";

    String FIND_USER_BY_FIRST_LAST_NAME = "select new User(u.id, u.firstName, u.lastName )from User u " +
            " where (lower(u.firstName) like lower (concat('%', :p1, '%')) and lower(u.lastName) like lower(concat('%', :p2, '%'))) " +
            " or (lower(u.lastName) like lower (concat('%', :p1, '%')) and lower(u.firstName) like lower(concat('%', :p2, '%')))";

    String FIND_USER_BY_FIRST_OR_LAST_NAME = " select new User(u.id, u.firstName, u.lastName )from User u " +
            " where (lower(u.firstName) like lower (concat('%', :p1, '%'))) or (lower(u.lastName) like lower(concat('%', :p1, '%')))";



}
