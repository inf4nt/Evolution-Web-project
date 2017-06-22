package evolution.dao;

import evolution.common.UserRoleEnum;
import evolution.model.user.User;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 09.03.2017.
 */
public interface UserDao extends DefaultDao {

    User find(Long id);

    User findByLogin (String username);

    List<User> findUserByFirstLastName(String p1, String p2);

    List<User> findUserByFirstOrLastName(String p1);

    List<User> findUserByFirstLastName(String p1, String p2, int limit, int offset);

    List<User> findUserByFirstOrLastName(String p1, int limit, int offset);

    User selectIdFirstLastName (long id);

    List<User> findAllUser(int limit, int offset);

    List<User> findAllAdmin(int limit, int offset);

    List<User> findAll (int limit, int offset);

    List<User> findAll ();
}
