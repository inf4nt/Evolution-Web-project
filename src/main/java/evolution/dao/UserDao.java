package evolution.dao;

import evolution.model.User;


import java.util.List;

/**
 * Created by Admin on 09.03.2017.
 */
public interface UserDao {

    void save(User user);

    void update (User user);

    List<User> findAll ();

    User findById (long id);

    User findByLogin (String login);

    void deleteById (long id);

    void delete (User user);

    List<User> findLikeLogin (String like);

    List<User> findAllUser();

    List<User> findAllAdmin();

    List<User> findAdminLikeLogin(String like);

    List<User> findUserLikeLogin(String like);

    List<User> findUserLikeFirstNameLastName (String parameter1, String parameter2);
}
