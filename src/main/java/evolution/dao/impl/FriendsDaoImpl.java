package evolution.dao.impl;

import evolution.common.FriendStatusEnum;
import evolution.dao.FriendsDao;
import evolution.dao.MyQuery;
import evolution.model.Friends;
import evolution.model.User;
import evolution.service.builder.CustomMapper;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 26.03.2017.
 */
@Repository
@Transactional
@NoArgsConstructor
public class FriendsDaoImpl
            implements FriendsDao {

    public FriendsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void friendRequest(long authUserId, long id2) {
        hibernateSession = sessionFactory.getCurrentSession();
        User authUser = new User(authUserId);
        User user2 = new User(id2);
        Friends request = new Friends(authUser, user2, FriendStatusEnum.REQUEST.getId());
        Friends follower = new Friends(user2, authUser, FriendStatusEnum.FOLLOWER.getId());
        hibernateSession.save(follower);
        hibernateSession.save(request);
    }

    @Override
    public void deleteFriend(long authUserId, long id2) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(MyQuery.SET_STATUS_FRIEND);
        query.setParameter("status", FriendStatusEnum.FOLLOWER.getId());
        query.setParameter("u", authUserId);
        query.setParameter("f", id2);
        query.setParameter("s", FriendStatusEnum.PROGRESS.getId());
        query.executeUpdate();

        query = hibernateSession.createQuery(MyQuery.SET_STATUS_FRIEND);
        query.setParameter("status", FriendStatusEnum.REQUEST.getId());
        query.setParameter("u", id2);
        query.setParameter("f", authUserId);
        query.setParameter("s", FriendStatusEnum.PROGRESS.getId());
        query.executeUpdate();
    }

    @Override
    public void acceptFriend(long authUserId, long id2) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(MyQuery.SET_STATUS_FRIEND);
        query.setParameter("status", FriendStatusEnum.PROGRESS.getId());
        query.setParameter("u", authUserId);
        query.setParameter("f", id2);
        query.setParameter("s", FriendStatusEnum.FOLLOWER.getId());
        query.executeUpdate();

        query = hibernateSession.createQuery(MyQuery.SET_STATUS_FRIEND);
        query.setParameter("status", FriendStatusEnum.PROGRESS.getId());
        query.setParameter("u", id2);
        query.setParameter("f", authUserId);
        query.setParameter("s", FriendStatusEnum.REQUEST.getId());
        query.executeUpdate();
    }

    @Override
    public void deleteRequest(long authUserId, long id2) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(MyQuery.DELETE_REQUEST_FRIEND);
        query.setParameter("u", authUserId);
        query.setParameter("f", id2);
        query.executeUpdate();
    }

    @Override
    public List<User> findMyFriend(long id) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(MyQuery.FIND_MY_FRIENDS);
        query.setParameter("id", id);
        return query.list();
    }

    @Override
    public List<User> findMyFollower(long id) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(MyQuery.FIND_MY_FOLLOWERS);
        query.setParameter("id", id);
        return query.list();
    }

    @Override
    public List<User> findMyRequest(long id) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(MyQuery.FIND_MY_REQUEST_FRIEND);
        query.setParameter("id", id);
        return query.list();
    }

    private Session hibernateSession;
    @Autowired
    private SessionFactory sessionFactory;
}
