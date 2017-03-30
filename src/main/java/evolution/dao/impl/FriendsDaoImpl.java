package evolution.dao.impl;

import evolution.common.FriendStatusEnum;
import evolution.dao.FriendsDao;
import evolution.model.User;
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
public class FriendsDaoImpl implements FriendsDao {

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
    public void removeMyFriend(long myId, long remove) {

    }

    @Override
    public void acceptFriend(long myId, long friendId) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createSQLQuery(
                "UPDATE friends\n" +
                " SET status = " + FriendStatusEnum.PROGRESS.getId() +
                " WHERE (user_id = :i1 and friend_id = :i2) " +
                " or (user_id = :i2 and friend_id = :i1) " +
                " and status = " + FriendStatusEnum.FOLLOWER.getId()
        );
        query.setParameter("i1", myId);
        query.setParameter("i2", friendId);
        query.executeUpdate();

    }

    @Override
    public void friendRequest(long myId, long friendId) {

    }

    @Override
    public List<User> findNewFriend() {
        return null;
    }

    private Session hibernateSession;
    @Autowired
    private SessionFactory sessionFactory;
}
