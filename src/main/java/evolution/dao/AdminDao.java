package evolution.dao;

import evolution.common.UserRoleEnum;
import evolution.model.user.User;

import java.util.List;

/**
 * Created by Admin on 02.04.2017.
 */
public interface AdminDao {

    List<User> findAllUser();

    List<User> findAllAdmin();

    List<User> findAllFieldsAll();

    String FIND_ALL_USER_ID_FIRST_LAST = "select new User(id, firstName, lastName) \n from User";

    String FIND_USER_BY_ROLE_USER = FIND_ALL_USER_ID_FIRST_LAST + "\n where role_id = " + UserRoleEnum.USER.getId() +
            " order by registrationDate desc";

    String FIND_USER_BY_ROLE_ADMIN = FIND_ALL_USER_ID_FIRST_LAST + "\n where role_id = " + UserRoleEnum.ADMIN.getId() +
            " order by registrationDate desc";

}
