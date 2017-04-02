package evolution.dao.impl;

import evolution.common.FriendStatusEnum;
import evolution.dao.FriendsDao;
import evolution.dao.MyQuery;
import evolution.model.User;
import evolution.service.builder.CustomMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 26.03.2017.
 */
@Repository
@Transactional
public class FriendsDaoImpl
            implements FriendsDao {

    @Override
    public List<User> findMyFriend(long id) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createSQLQuery(MyQuery.FIND_MY_FRIEND);
        query.setParameter("par_f_user_id", id);
        return customMapper.listUserForFriends(query.list());
    }

    @Override
    public List<User> findMyFollower(long id) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createSQLQuery(MyQuery.FIND_MY_FOLLOWER);
        query.setParameter("par_f_user_id", id);
        return customMapper.listUserForFriends(query.list());
    }

    @Override
    public List<User> findMyRequest(long id) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createSQLQuery(MyQuery.FIND_MY_REQUEST);
        query.setParameter("par_f_user_id", id);
        return customMapper.listUserForFriends(query.list());
    }

    @Override
    public void friendRequest(long authUserId, long id2) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createSQLQuery(MyQuery.INSERT_INTO_FRIEND);
        query.setParameter("par_f_user_id", authUserId);
        query.setParameter("par_f_friend_id", id2);
        query.setParameter("par_f_status", FriendStatusEnum.REQUEST.getId());
        query.executeUpdate();


        query = hibernateSession.createSQLQuery(MyQuery.INSERT_INTO_FRIEND);
        query.setParameter("par_f_user_id", id2);
        query.setParameter("par_f_friend_id", authUserId);
        query.setParameter("par_f_status", FriendStatusEnum.FOLLOWER.getId());
        query.executeUpdate();
    }

    @Override
    public void deleteFriend(long authUserId, long id2) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createSQLQuery(MyQuery.SET_STATUS_FRIEND);
        query.setParameter("par_f_user_id", authUserId);
        query.setParameter("par_f_friend_id", id2);
        query.setParameter("par_set_f_status", FriendStatusEnum.FOLLOWER.getId());
        query.setParameter("par_where_f_status", FriendStatusEnum.PROGRESS.getId());
        query.executeUpdate();


        query = hibernateSession.createSQLQuery(MyQuery.SET_STATUS_FRIEND);
        query.setParameter("par_f_user_id", id2);
        query.setParameter("par_f_friend_id", authUserId);
        query.setParameter("par_set_f_status", FriendStatusEnum.REQUEST.getId());
        query.setParameter("par_where_f_status", FriendStatusEnum.PROGRESS.getId());
        query.executeUpdate();
    }

    @Override
    public void acceptFriend(long authUserId, long id2) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createSQLQuery(MyQuery.SET_STATUS_FRIEND);
        query.setParameter("par_f_user_id", authUserId);
        query.setParameter("par_f_friend_id", id2);
        query.setParameter("par_where_f_status", FriendStatusEnum.FOLLOWER.getId());
        query.setParameter("par_set_f_status", FriendStatusEnum.PROGRESS.getId());
        query.executeUpdate();


        query = hibernateSession.createSQLQuery(MyQuery.SET_STATUS_FRIEND);
        query.setParameter("par_f_user_id", id2);
        query.setParameter("par_f_friend_id", authUserId);
        query.setParameter("par_where_f_status", FriendStatusEnum.REQUEST.getId());
        query.setParameter("par_set_f_status", FriendStatusEnum.PROGRESS.getId());
        query.executeUpdate();

    }

    @Override
    public void deleteRequest(long authUserId, long id2) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createSQLQuery(MyQuery.DELETE_REQUEST_FRIEND);
        query.setParameter("par_f_user_id", id2);
        query.setParameter("par_f_friend_id", authUserId);
        query.executeUpdate();
    }

    private Session hibernateSession;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private CustomMapper customMapper;
}
