package evolution.dao;

import evolution.model.User;


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

    List<User> searchByFistNameLastName(String like, long authUserId);
}
