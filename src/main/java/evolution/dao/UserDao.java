package evolution.dao;

import evolution.common.UserRoleEnum;
import evolution.model.user.User;
import java.util.List;
import java.util.Map;

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

    String FIND_ALL_USER_ID_FIRST_LAST = "select new User(id, firstName, lastName) \n " + FIND_ALL_USER;

    String FIND_USER_BY_ROLE_USER = FIND_ALL_USER_ID_FIRST_LAST + "\n where role_id = " + UserRoleEnum.USER.getId() +
            " order by id desc";

    String FIND_USER_BY_ROLE_ADMIN = FIND_ALL_USER_ID_FIRST_LAST + "\n where role_id = " + UserRoleEnum.ADMIN.getId() +
            " order by id desc";


    void save(User user);

    void update(User user);

    void delete(User user);

    User find(Long id);

    User findByLogin (String username);

    List<User> findUserByFirstLastName(String p1, String p2);

    List<User> findUserByFirstOrLastName(String p1);

    List<User> findUserByFirstLastName(String p1, String p2, int limit);

    List<User> findUserByFirstOrLastName(String p1, int limit);

    User selectFirstLastName (long id);

    User selectIdFirstLastName (long id);

    List<User> findAllUser(int limit, int offset);

    List<User> findAllAdmin(int limit, int offset);

    Map<String, List<User>> findAll(int limit, int offset);
}
