package evolution.dao;

import evolution.common.FriendStatusEnum;
import evolution.common.UserRoleEnum;

/**
 * Created by Admin on 02.04.2017.
 */
public interface MyQuery {

    String USER_JOIN_FRIEND = "select\n" +
            "  u.id,\n" +
            "  u.first_name,\n" +
            "  u.last_name,\n" +
            "  case\n" +
            "  WHEN f.status = " + FriendStatusEnum.PROGRESS.getId() + " THEN '" + FriendStatusEnum.PROGRESS.toString() +"' \n" +
            "  WHEN f.status = " + FriendStatusEnum.FOLLOWER.getId() + " THEN '" + FriendStatusEnum.FOLLOWER.toString() +"' \n" +
            "  WHEN f.status = " + FriendStatusEnum.REQUEST.getId() + " THEN '" + FriendStatusEnum.REQUEST.toString() +"' \n" +
            "  end as status\n" +
            "from user_data u\n" +
            "join friends f on u.id = f.friend_id";

    String USER_LEFT_JOIN_FRIEND = "select\n" +
            "  u.id,\n" +
            "  u.first_name,\n" +
            "  u.last_name,\n" +
            "  case\n" +
            "  WHEN f.status = " + FriendStatusEnum.PROGRESS.getId() + " THEN '" + FriendStatusEnum.PROGRESS.toString() +"' \n" +
            "  WHEN f.status = " + FriendStatusEnum.FOLLOWER.getId() + " THEN '" + FriendStatusEnum.FOLLOWER.toString() +"' \n" +
            "  WHEN f.status = " + FriendStatusEnum.REQUEST.getId() + " THEN '" + FriendStatusEnum.REQUEST.toString() +"' \n" +
            "  WHEN f.status is null THEN '" + FriendStatusEnum.NO_MATCHES.toString() +"' \n" +
            "  end as status\n" +
            "from user_data u\n" +
            "left join friends f on u.id = f.friend_id";

    String SEARCH_BY_FIRST_LAST_NAME = USER_LEFT_JOIN_FRIEND + " and f.user_id = :auth_user_id\n" +
            "WHERE (  u.first_name LIKE '%'||:p1||'%' and u.last_name LIKE '%'||:p2||'%' and u.id != :auth_user_id)\n" +
            "or (u.first_name LIKE '%'||:p2||'%' and u.last_name LIKE '%'||:p1||'%' and u.id != :auth_user_id)";


    String FIND_MY_FRIEND = USER_JOIN_FRIEND
            + "\n and f.user_id = :par_f_user_id and f.status = " + FriendStatusEnum.PROGRESS.getId();

    String FIND_MY_FOLLOWER = USER_JOIN_FRIEND
            + "\n and f.user_id = :par_f_user_id and f.status = " + FriendStatusEnum.FOLLOWER.getId();

    String FIND_MY_REQUEST = USER_JOIN_FRIEND
            + "\n and f.user_id = :par_f_user_id and f.status = " + FriendStatusEnum.REQUEST.getId();

    String INSERT_INTO_FRIEND = "insert into friends (user_id, friend_id, status)" +
            "\n values (:par_f_user_id, :par_f_friend_id, :par_f_status)";

    String SET_STATUS_FRIEND = "update friends set status = :par_set_f_status " +
            "\n where (user_id = :par_f_user_id and friend_id = :par_f_friend_id and status = :par_where_f_status)";

    String DELETE_REQUEST_FRIEND = "delete FROM friends\n" +
            "WHERE (user_id = :par_f_user_id and friend_id = :par_f_friend_id and status = " + FriendStatusEnum.FOLLOWER.getId() + ")" +
            "\n  or (user_id = :par_f_friend_id and friend_id = :par_f_user_id and status = " + FriendStatusEnum.REQUEST.getId() + ")";

    String FIND_ALL_USER = "from User";

    String DELETE_USER_BY_ID = "delete from User \n where id = :i";

    String FIND_USER_BY_LOGIN = FIND_ALL_USER + "\n where login = :l";

    String FIND_ALL_USER_ID_FIRST_LAST = "select new User(id, login, firstName, lastName) \n from User";

    String FIND_USER_BY_ROLE_ADMIN = FIND_ALL_USER_ID_FIRST_LAST + "\n where role_id = " + UserRoleEnum.ADMIN.getId() + " order by registrationDate desc";

    String FIND_USER_BY_ROLE_USER = FIND_ALL_USER_ID_FIRST_LAST + "\n where role_id = " + UserRoleEnum.USER.getId() + " order by registrationDate desc";

    String FIND_USER_LIKE_LOGIN = FIND_ALL_USER_ID_FIRST_LAST + " \n where login like '%'||:like||'%'";

    String FIND_USER_BY_SQ_AND_SQT_AND_ID = "select *\n" +
            "from user_data\n" +
            "where secret_question = :sq and secret_question_type_id = :sqtId and id = :id";

}
