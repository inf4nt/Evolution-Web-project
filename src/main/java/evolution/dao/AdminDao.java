package evolution.dao;

import evolution.model.User;

import java.util.List;

/**
 * Created by Admin on 02.04.2017.
 */
public interface AdminDao {

    List<User> findLikeLogin(String like);

    List<User> findAllUser();

    List<User> findAllAdmin();

}
