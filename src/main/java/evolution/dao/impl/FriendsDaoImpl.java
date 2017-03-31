package evolution.dao.impl;

import evolution.common.FriendStatusEnum;
import evolution.dao.FriendsDao;
import evolution.model.Friend;
import evolution.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 26.03.2017.
 */
@Repository
@Transactional
public class FriendsDaoImpl
            implements FriendsDao {

    public FriendsDaoImpl(){

    }

    public FriendsDaoImpl(Session hibernateSession) {
        this.hibernateSession = hibernateSession;
    }

    @Override
    public List<User> findMyFriend(long id) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createSQLQuery(
            "select *\n" +
                    "from  USER_DATA WHERE id in(\n" +
                    "  SELECT f.friend_id from USER_DATA u join FRIENDS f on u.ID = f.user_id and f.user_id = :i and f.STATUS = " + FriendStatusEnum.PROGRESS.getId() +
                    "  \nUNION\n" +
                    "  SELECT f.user_id  from USER_DATA u join FRIENDS f on u.ID = f.friend_id and f.friend_id = :i and f.STATUS = " + FriendStatusEnum.PROGRESS.getId() +
                    ")"
        )
        .addEntity(User.class);
        query.setParameter("i", id);
        List list = query.list();
        return list;
    }

    @Override
    public List<User> findMyFollower(long id) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createSQLQuery(
                "select *\n" +
                        "from  USER_DATA WHERE id in(\n" +
                        "  SELECT f.friend_id from USER_DATA u join FRIENDS f on u.ID = f.user_id and f.user_id = :i and f.STATUS = " + FriendStatusEnum.FOLLOWER.getId() +
                        "  \nUNION\n" +
                        "  SELECT f.user_id  from USER_DATA u join FRIENDS f on u.ID = f.friend_id and f.friend_id = :i and f.STATUS = " + FriendStatusEnum.FOLLOWER.getId() +
                        ")"
        )
        .addEntity(User.class);
        query.setParameter("i", id);
        List list = query.list();
        return list;
    }

    @Override
    public void removeMyFriend(long id1, long id2) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createSQLQuery("UPDATE friends\n" +
                " SET status = " + FriendStatusEnum.FOLLOWER.getId() +
                " WHERE (user_id = :id1 and friend_id = :id2 and friends.status = " + FriendStatusEnum.PROGRESS.getId() + ")" +
                " or (user_id = :id2 and friend_id = :id1 and friends.status = " + FriendStatusEnum.PROGRESS.getId() + ")");
        query.setParameter("id1", id1);
        query.setParameter("id2", id2);
        query.executeUpdate();
    }

    @Override
    public boolean checkIsFriend(long id1, long id2) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createSQLQuery("select 1\n" +
                " from friends\n" +
                " WHERE (friend_id = :id1 and user_id = :id2 and status = " + FriendStatusEnum.PROGRESS.getId() + " )\n" +
                " or (friend_id = :id2 and user_id = :id1 and status = " + FriendStatusEnum.PROGRESS.getId() + " )" );
        query.setParameter("id1", id1);
        query.setParameter("id2", id2);
        try {
            query.getSingleResult();
        } catch (NoResultException nre){
            return false;
        }
        return true;
    }

    @Override
    public void removeFollower(long id1, long id2) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createSQLQuery("delete FROM friends\n" +
                " WHERE (user_id = :id1 and friend_id = :id2 and status = " + FriendStatusEnum.FOLLOWER.getId() + ")" +
                " or (user_id = :id2 and friend_id = :id1 and status = " + FriendStatusEnum.FOLLOWER.getId() + ")");
        query.setParameter("id1", id1);
        query.setParameter("id2", id2);
        query.executeUpdate();
    }


    @Override
    public void acceptFriend(long id1, long id2) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createSQLQuery(
                "UPDATE friends\n" +
                " SET status = " + FriendStatusEnum.PROGRESS.getId() +
                " WHERE (user_id = :i1 and friend_id = :i2) " +
                " or (user_id = :i2 and friend_id = :i1) " +
                " and status = " + FriendStatusEnum.FOLLOWER.getId()
        );
        query.setParameter("i1", id1);
        query.setParameter("i2", id2);
        query.executeUpdate();
    }

    @Override
    public void friendRequest(long id1, long id2) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createSQLQuery("INSERT into friends(user_id, friend_id, status)\n" +
                "    VALUES (:id1, :id2, " + FriendStatusEnum.FOLLOWER.getId() + ")");
        query.setParameter("id1", id1);
        query.setParameter("id2", id2);
        query.executeUpdate();
    }

    public static class FriendsMapper {
        public static Friend mapper (Object a){
            Friend friend = new Friend();
            Object rows[] = (Object[]) a;
            friend.setUserId(Long.parseLong(rows[0].toString()));
            friend.setFriendId(Long.parseLong(rows[1].toString()));
            long statusId = Long.parseLong(rows[2].toString());
            if (statusId == FriendStatusEnum.FOLLOWER.getId())
                friend.setStatus(FriendStatusEnum.FOLLOWER.toString());
            if (statusId == FriendStatusEnum.PROGRESS.getId())
                friend.setStatus(FriendStatusEnum.PROGRESS.toString());
            return friend;
        }

        public static List<Friend> listFriend (List list) {
            List result = new ArrayList();
            for (Object a: list)
                result.add(mapper(a));
            return result;
        }
    }


    @Override
    public List<User> findNewFriend() {
        return null;
    }

    private Session hibernateSession;
    @Autowired
    private SessionFactory sessionFactory;
}
