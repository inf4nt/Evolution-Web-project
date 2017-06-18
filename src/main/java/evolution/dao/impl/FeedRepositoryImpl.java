package evolution.dao.impl;


import evolution.dao.FeedRepository;
import evolution.model.news.Feed;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 17.06.2017.
 */

@Repository
@Transactional
public class FeedRepositoryImpl
                        implements FeedRepository {

    private final static String FEED_USER_ID = "select new Feed (f.id, f.feedContent, f.date, " +
            "   sender.id, sender.firstName, sender.lastName," +
            "   user.id, user.firstName, user.lastName, " +
            "   rf.repostFeed.userReposted.id ) " +
            " from Feed f " +
            " left join RepostFeed rf on rf.repostFeed.feed.id = f.id and rf.repostFeed.userReposted.id = :id" +
            " join f.sender as sender " +
            " join f.user as user " +
            " where f.id in ( " +
            " select f.id from Feed f " +
            " join f.sender as sender " +
            " join User u on f.user.id = u.id and u.id = :id " +
            " where f.user.id = :id)" +
            " or f.id in ( " +
            " select f.id from Feed f " +
            " join RepostFeed rf on rf.repostFeed.feed.id = f.id and rf.repostFeed.userReposted.id = :id " +
            " join f.sender " +
            " join rf.repostFeed.userReposted) " +
            " order by f.id desc ";

    private final static String FEED = "select new Feed (f.id, f.feedContent, f.date, " +
            "   sender.id, sender.firstName, sender.lastName," +
            "    user.id, user.firstName, user.lastName ) " +
            " from Feed f" +
            " join f.sender as sender " +
            " join f.user as user " +
            " where f.id in ( " +
            " select f.id from Feed f " +
            " join f.sender as sender " +
            " join User u on f.user.id = u.id ) " +
            " or f.id in ( " +
            " select f.id from Feed f " +
            " join RepostFeed rf on rf.repostFeed.feed.id = f.id " +
            " join f.sender " +
            " join rf.repostFeed.userReposted ) " +
            " order by f.id desc ";

    private final static String DELETE_FEED = " delete from Feed f " +
            " where f.id = :feedId " +
            " and f.sender.id = :userId ";

    private final static String DELETE_REPOST = " delete from RepostFeed " +
            " where repostFeed.feed.id = :feedId " +
            " and repostFeed.userReposted.id = :repostedUserId ";

    private final static String DELETE_FEED_ON_MY_BOARD = " delete from Feed f " +
            " where f.id = :feedId " +
            " and f.user.id = :userId ";

    private SessionFactory sessionFactory;

    @Autowired
    private evolution.dao.Repository repository;

    @Autowired
    public FeedRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public evolution.dao.Repository repository() {
        return repository;
    }

    @Override
    public List<Feed> feed(Long userId, int limit, int offset) {
        Query query = session().createQuery(FEED_USER_ID);
        query.setParameter("id", userId);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.list();
    }

    @Override
    public List<Feed> feed(int limit, int offset) {
        Query query = session().createQuery(FEED);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.list();
    }

    @Override
    public int delete(Long feedId, Long userId) {
        Query query = session().createQuery(DELETE_FEED);
        query.setParameter("feedId", feedId);
        query.setParameter("userId", userId);
        return query.executeUpdate();
    }

    @Override
    public int repostDelete(Long feedId, Long repostedUserId) {
        Query query = session().createQuery(DELETE_REPOST);
        query.setParameter("feedId", feedId);
        query.setParameter("repostedUserId", repostedUserId);
        return query.executeUpdate();
    }

    @Override
    public int deleteFeedOnMyBoard(Long feedId, Long userId) {
        Query query = session().createQuery(DELETE_FEED_ON_MY_BOARD);
        query.setParameter("feedId", feedId);
        query.setParameter("userId", userId);
        return query.executeUpdate();
    }

    public Session session(){
        return sessionFactory.getCurrentSession();
    }
}
