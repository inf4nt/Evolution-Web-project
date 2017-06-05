package evolution.dao.impl;

import evolution.common.FriendStatusEnum;
import evolution.dao.FriendsDao;
import evolution.model.friend.Friends;
import evolution.model.user.User;
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

    @Autowired
    private SessionFactory sessionFactory;

    public FriendsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void friendRequest(long authUserId, long id2) {
        User authUser = new User(authUserId);
        User user2 = new User(id2);
        Friends request = new Friends(authUser, user2, FriendStatusEnum.REQUEST.getId());
        Friends follower = new Friends(user2, authUser, FriendStatusEnum.FOLLOWER.getId());
        session().save(follower);
        session().save(request);
    }

    @Override
    public void deleteFriend(long authUserId, long id2) {
        Query query = session().createQuery(SET_STATUS_FRIEND);
        query.setParameter("status", FriendStatusEnum.FOLLOWER.getId());
        query.setParameter("u", authUserId);
        query.setParameter("f", id2);
        query.setParameter("s", FriendStatusEnum.PROGRESS.getId());
        query.executeUpdate();

        query = session().createQuery(SET_STATUS_FRIEND);
        query.setParameter("status", FriendStatusEnum.REQUEST.getId());
        query.setParameter("u", id2);
        query.setParameter("f", authUserId);
        query.setParameter("s", FriendStatusEnum.PROGRESS.getId());
        query.executeUpdate();
    }

    @Override
    public void acceptFriend(long authUserId, long id2) {
        Query query = session().createQuery(SET_STATUS_FRIEND);
        query.setParameter("status", FriendStatusEnum.PROGRESS.getId());
        query.setParameter("u", authUserId);
        query.setParameter("f", id2);
        query.setParameter("s", FriendStatusEnum.FOLLOWER.getId());
        query.executeUpdate();

        query = session().createQuery(SET_STATUS_FRIEND);
        query.setParameter("status", FriendStatusEnum.PROGRESS.getId());
        query.setParameter("u", id2);
        query.setParameter("f", authUserId);
        query.setParameter("s", FriendStatusEnum.REQUEST.getId());
        query.executeUpdate();
    }

    @Override
    public void deleteRequest(long authUserId, long id2) {
        Query query = session().createQuery(DELETE_REQUEST_FRIEND);
        query.setParameter("u", authUserId);
        query.setParameter("f", id2);
        query.executeUpdate();
    }

    @Override
    public Friends findUserAndFriendStatus(Long authUserId, Long id) {
        Query query = session().createQuery(FIND_USER_AND_FRIEND_STATUS);
        query.setParameter("authUserId", authUserId);
        query.setParameter("id", id);
        return (Friends) query.getSingleResult();
    }

    @Override
    public List<Friends> findFriend(long authUserId, int limit, int offset) {
        Query query = session().createQuery(FIND_FRIEND);
        query.setParameter("id", authUserId);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.list();
    }

    @Override
    public Map<String, List<Friends>> friend(long authUserId, int limit, int offset) {
        Map<String, List<Friends>> map = new HashMap<>();
        map.put("progress", findFriend(authUserId, limit, offset));
        map.put("follower", findFollower(authUserId, limit, offset));
        map.put("request", findRequest(authUserId, limit, offset));
        return map;
    }

    @Override
    public Map<String, List<Friends>> friendFollower(long authUserId, int limit, int offset) {
        Map<String, List<Friends>> map = new HashMap<>();
        map.put("progress", findFriend(authUserId, limit, offset));
        map.put("follower", findFollower(authUserId, limit, offset));
        return map;
    }

    @Override
    public List<Friends> findFollower(long authUserId, int limit, int offset) {
        Query query = session().createQuery(FIND_FOLLOWER);
        query.setParameter("id", authUserId);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.list();
    }

    @Override
    public List<Friends> findRequest(long authUserId, int limit, int offset) {
        Query query = session().createQuery(FIND_REQUEST);
        query.setParameter("id", authUserId);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.list();
    }

    @Override
    public List<Friends> moreFriend(long authUserId, int limit, int offset) {
        Query query = session().createQuery(MORE_FRIEND);
        query.setParameter("id", authUserId);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.list();
    }

    @Override
    public List<Friends> moreFollower(long authUserId, int limit, int offset) {
        Query query = session().createQuery(MORE_FOLLOWER);
        query.setParameter("id", authUserId);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.list();
    }

    @Override
    public List<Friends> moreRequest(long authUserId, int limit, int offset) {
        Query query = session().createQuery(MORE_REQUEST);
        query.setParameter("id", authUserId);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.list();
    }

    @Override
    public boolean checkFriends(long authUserId, long id2) {
        Query query = session().createQuery(CHECK_FRIENDS);
        query.setParameter("id1", authUserId);
        query.setParameter("id2", id2);
        query.setParameter("status", FriendStatusEnum.PROGRESS.getId());
        if (query.list().size() == 2)
            return true;
        return false;
    }

    public Session session(){
        return sessionFactory.getCurrentSession();
    }
}
