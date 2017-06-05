package evolution.dao;

import evolution.model.user.User;
import java.util.List;

/**
 * Created by Admin on 09.03.2017.
 */
public interface UserDao {

    String FIND_USER_BY_USERNAME = "from User where login = :l";

    String FIND_ALL_USER = " from User ";

    String SELECT_FIRST_LAST_NAME = "select new User(firstName, lastName) " + FIND_ALL_USER + " where id = :id";

    String SELECT_ID_FIRST_LAST_NAME = "select new User(id, firstName, lastName) " + FIND_ALL_USER + " where id = :id";

    String FIND_USER_BY_FIRST_LAST_NAME = "select new User(u.id, u.firstName, u.lastName )from User u " +
            " where (lower(u.firstName) like lower (concat('%', :p1, '%')) and lower(u.lastName) like lower(concat('%', :p2, '%'))) " +
            " or (lower(u.lastName) like lower (concat('%', :p1, '%')) and lower(u.firstName) like lower(concat('%', :p2, '%')))";

    String FIND_USER_BY_FIRST_OR_LAST_NAME = " select new User(u.id, u.firstName, u.lastName )from User u " +
            " where (lower(u.firstName) like lower (concat('%', :p1, '%'))) or (lower(u.lastName) like lower(concat('%', :p1, '%')))";

    void save(User user);

    void update (User user);

    User find(Long id);

    User findByLogin (String login);

    void delete (User user);

    List<User> findUserByFirstLastName(String p1, String p2);

    List<User> findUserByFirstOrLastName(String p1);

    List<User> findUserByFirstLastName(String p1, String p2, int limit);

    List<User> findUserByFirstOrLastName(String p1, int limit);

    User selectFirstLastName (long id);

    User selectIdFirstLastName (long id);
}
