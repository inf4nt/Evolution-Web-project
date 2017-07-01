//package evolution.dao;
//
//import evolution.model.feed.FeedData;
//import evolution.model.feed.FeedPublication;
//import evolution.model.user.StandardUser;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.util.List;
//
///**
// * Created by Admin on 23.06.2017.
// */
//
//@Service
//@Transactional
//public class FeedDao {
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    private final static String FIND_ALL_BY_USER_ID =  " select new FeedPublication (" +
//            " fd.id, fd.content, fd.tags, " +
//            " fp.id, fp.date, " +
//            " sender.id, sender.firstName, sender.lastName) " +
//            " from FeedPublication fp " +
//            " join FeedData fd on fd.id = fp.feedData.id and (fp.reposted.id is null or fp.reposted.id =:user_id ) " +
//            " join StandardUser sender on sender.id = fp.sender.id " +
//            " where fp.sender.id =:user_id " +
//            " or fp.reposted.id =:user_id " +
//            " order by fp.date desc ";
//
//    private final static String FIND_ALL = " select new FeedPublication (" +
//            " fd.id, fd.content, fd.tags, " +
//            " fp.id, fp.date, " +
//            " sender.id, sender.firstName, sender.lastName) " +
//            " from FeedPublication fp " +
//            " join fp.feedData as fd" +
//            " join fp.sender as sender " +
//            " order by fp.date desc ";
//
//    private final static String DELETE_REPOST = " delete from FeedPublication fp " +
//            " where fp.reposted.id =:user_id and fp.id =:id " ;
//
//    private final static String FIND_FEED =
//            " select new FeedPublication (" +
//            " fd.id, fd.content, fd.tags, " +
//            " fp.id, fp.date, " +
//            " sender.id, sender.firstName, sender.lastName) " +
//            " from FeedPublication fp " +
//            " join fp.feedData fd " +
//            " join fp.sender as sender" +
//            " where fp.id =:id and fp.sender.id =:user_id ";
//
//    private static final String FIND_ALL_NEWS =
//            " select new FeedPublication (" +
//            " fd.id, fd.content, fd.tags, " +
//            " fp.id, fp.date, " +
//            " sender.id, sender.firstName, sender.lastName, " +
//            " reposted.id, reposted.firstName, reposted.lastName ) " +
//            " from FeedPublication fp " +
//            " join fp.feedData as fd " +
//            " join fp.sender as sender " +
//            " left join fp.reposted as reposted " +
//            " where fp.id in (" +
//            " select fp.id from Friends f " +
//            " join FeedPublication fp on fp.sender.id = f.friend.id or fp.reposted.id = f.friend.id " +
//            " where f.user.id = :user_id) " +
//            " or fp.id in ( " +
//            " select fp.id from FeedPublication fp " +
//            " where fp.sender.id =:user_id and fp.reposted is null ) " +
//            " order by fp.date desc ";
//
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Deprecated
//    public List<FeedPublication> findAll(Long userId, int limit, int offset) {
//        Query query = entityManager.createQuery(FIND_ALL_BY_USER_ID);
//        query.setParameter("user_id", userId);
//        query.setMaxResults(limit);
//        query.setFirstResult(offset);
//        return query.getResultList();
//    }
//
//    @Deprecated
//    public List<FeedPublication> findAllNews(Long userId) {
//        Query query = entityManager.createQuery(FIND_ALL_NEWS);
//        query.setParameter("user_id", userId);
//        return query.getResultList();
//    }
//
//    public List<FeedPublication> findAll(Long userId) {
//        Query query = entityManager.createQuery(FIND_ALL_BY_USER_ID);
//        query.setParameter("user_id", userId);
//        return query.getResultList();
//    }
//
//    public void save(FeedData feedData, FeedPublication feedPublication) {
//        Long saved = (Long) entityManager.persist(feedData);
//        feedPublication.setFeedData(new FeedData(saved));
//        return (Long) entityManager.persist(feedPublication);
//    }
//
//    public FeedPublication find(FeedPublication feedPublication) {
//        return session().find(feedPublication.getClass(), feedPublication.getId());
//    }
//
//    @Deprecated
//    public FeedPublication find(Long feedId, Long senderId) {
//        Query query = session().createQuery(FIND_FEED);
//        query.setParameter("id", feedId);
//        query.setParameter("user_id", senderId);
//        return (FeedPublication) query.getSingleResult();
//    }
//
//    public List<FeedPublication> findAll() {
//        Query query = session().createQuery(FIND_ALL);
//        return query.list();
//    }
//
//    public Long save(String content, String tags, Long senderId, Long repostedId) {
//        if (repostedId == null)
//            return save(new FeedData(content, tags), new FeedPublication(new StandardUser(senderId), null));
//        return save(new FeedData(content, tags), new FeedPublication(new StandardUser(senderId), new StandardUser(repostedId)));
//    }
//
//    public Long repost(FeedData feedData, StandardUser sender, StandardUser reposted) {
//        return (Long) session().save(new FeedPublication(feedData, sender, reposted));
//    }
//
//    public Long repost(Long feedId, Long userSenderId, Long userRepostedId) {
//        return repost(new FeedData(feedId), new StandardUser(userSenderId), new StandardUser(userRepostedId));
//    }
//
//    public int deleteRepost(Long feedId, Long userRepostedId) {
//        Query query = session().createQuery(DELETE_REPOST);
//        query.setParameter("id", feedId);
//        query.setParameter("user_id", userRepostedId);
//        return query.executeUpdate();
//    }
//
//    public void delete(FeedData feedData) {
//        session().delete(feedData);
//    }
//
//}
