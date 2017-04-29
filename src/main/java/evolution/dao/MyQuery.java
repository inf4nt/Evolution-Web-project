package evolution.dao;

import evolution.common.FriendStatusEnum;
import evolution.common.UserRoleEnum;

/**
 * Created by Admin on 02.04.2017.
 */
public interface MyQuery {

    String USER_LEFT_FRIEND = "select new User (u.id, u.firstName, u.lastName, f.status) " +
            "from User u " +
            "left join Friends f on f.friendId.id = u.id ";

    String SEARCH_BY_FIRST_AND_LAST_NAME = USER_LEFT_FRIEND + " and f.userId.id = :id " +
            " where (lower(u.firstName) like lower (concat('%', :p1, '%')) and lower(u.lastName) like lower(concat('%', :p2, '%'))) " +
            " or (lower(u.lastName) like lower (concat('%', :p1, '%')) and lower(u.firstName) like lower(concat('%', :p2, '%')))";

    String SEARCH_BY_FIRST_OR_LAST_NAME = USER_LEFT_FRIEND +
            " and f.userId.id = :id " +
            "where (lower(u.firstName) like lower (concat('%', :p1, '%'))) or (lower(u.lastName) like lower(concat('%', :p1, '%')))";

    String FIND_PROFILE_AND_FRIEND_STATUS_BY_ID = " select new User (u.id, u.firstName, u.lastName, u.roleId, u.registrationDate, f.status) " +
            " from User as u " +
            " left join Friends as f on f.friendId.id = u.id and f.userId.id = :myId " +
            " where u.id = :secondId ";

    String FIND_ALL_USER = "from User";

    String DELETE_USER_BY_ID = "delete from User \n where id = :i";

    String FIND_USER_BY_LOGIN = "select new User(id, roleId, login, password) " + FIND_ALL_USER + "\n where login = :l";

    String FIND_ALL_USER_ID_FIRST_LAST = "select new User(id, login, firstName, lastName) \n from User";

    String FIND_USER_BY_ROLE_ADMIN = FIND_ALL_USER_ID_FIRST_LAST + "\n where role_id = " + UserRoleEnum.ADMIN.getId() +
            " order by registrationDate desc";

    String FIND_USER_BY_ROLE_USER = FIND_ALL_USER_ID_FIRST_LAST + "\n where role_id = " + UserRoleEnum.USER.getId() +
            " order by registrationDate desc";

    String FIND_USER_LIKE_LOGIN = FIND_ALL_USER_ID_FIRST_LAST + " \n where login like '%'||:like||'%'";

    String FIND_USER_BY_SQ_AND_SQT_AND_ID = FIND_ALL_USER + " where secretQuestionType.id = :sqtId and secretQuestion = :sq and id = :id";

    String FRIEND_JOIN_USER = "select new User(friend.id, friend.firstName, friend.lastName, user.id, user.firstName, user.lastName, f.status) " +
            " from Friends f join f.friendId as friend " +
            " join f.userId as user " +
            " where f.userId.id = :id ";

    String FIND_MY_FRIENDS = FRIEND_JOIN_USER + " and f.status = " + FriendStatusEnum.PROGRESS.getId();

    String FIND_MY_FOLLOWERS = FRIEND_JOIN_USER + " and f.status = " + FriendStatusEnum.FOLLOWER.getId();

    String FIND_MY_REQUEST_FRIEND = FRIEND_JOIN_USER + " and f.status = " + FriendStatusEnum.REQUEST.getId();

    String SELECT_FIRST_LAST_NAME = "select new User(firstName, lastName) " + FIND_ALL_USER + " where id = :id";

    String SELECT_ID_FIRST_LAST_NAME = "select new User(id, firstName, lastName) " + FIND_ALL_USER + " where id = :id";

    String SET_STATUS_FRIEND = "update Friends \nset status = :status \nwhere userId.id = :u \nand friendId.id = :f \nand status =:s";

    String DELETE_REQUEST_FRIEND = "delete from Friends where (userId.id = :u and friendId.id = :f) \n" +
            "or (userId.id = :f and friendId.id = :u)";

}
