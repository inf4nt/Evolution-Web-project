package evolution.dao.impl;

import evolution.common.FeedAttribute;
import evolution.dao.FeedDao;
import evolution.model.news.Feed;
import evolution.model.news.FeedReference;
import evolution.model.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 11.06.2017.
 */
@Repository
@Transactional
public class FeedDaoImpl implements FeedDao {

    private static final String MY_POST = "select new Feed (na.id, na.feedContent, na.date, sender.id, sender.firstName, sender.lastName) " +
            " from Feed na " +
            " join na.sender as sender " +
            " where na.sender.id =:id " +
            " order by na.id desc ";

    private static final String MY_REPOST = "select new Feed (repost.id, repost.feedContent, repost.date, " +
            "sender.id, sender.firstName, sender.lastName, " +
            "ref.feedReferenceEmbedded.actionDate) " +
            " from Feed repost" +
            " join FeedReference ref on ref.feedReferenceEmbedded.feed.id = repost.id and ref.feedReferenceEmbedded.typeReference = :type_ref " +
            " join repost.sender as sender" +
            " where ref.feedReferenceEmbedded.user.id = :id " +
            " order by repost.id desc ";

    private static final String DELETE_REFERENCE = "delete from FeedReference nr " +
            " where nr.feedReferenceEmbedded.user.id =:user_id " +
            " and nr.feedReferenceEmbedded.feed.id =:news_id " +
            " and nr.feedReferenceEmbedded.typeReference =:type_ref ";

    private static final String POST_INFO = "select new Feed (u.id, u.firstName, u.lastName) " +
            " from FeedReference nr " +
            " join nr.feedReferenceEmbedded.user as u " +
            " where nr.feedReferenceEmbedded.feed.id = :news_id and nr.feedReferenceEmbedded.typeReference = :type_ref " +
            " order by nr.feedReferenceEmbedded.actionDate desc ";

    private static final String ALL_POSTS = " select new Feed (na.id, na.feedContent, na.date, sender.id, sender.firstName, sender.lastName)  " +
            " from Feed na " +
            " join na.sender as sender " +
            " where na.id in ( " +
            " select na.id from Feed na " +
            " join FeedReference nr on na.id = nr.feedReferenceEmbedded.feed.id and nr.feedReferenceEmbedded.typeReference =:type_ref " +
            " join na.sender " +
            " where nr.feedReferenceEmbedded.user.id = :id" +
            ") " +
            " or na.id in (" +
            " select na.id from Feed na " +
            " join na.sender " +
            " where na.sender.id = :id " +
            ") order by na.id desc ";

    private SessionFactory sessionFactory;

    @Autowired
    private evolution.dao.Repository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedDaoImpl.class);

    @Autowired
    public FeedDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public evolution.dao.Repository repository() {
        return repository;
    }

    @Override
    public List<Feed> allPosts(Long id, int limit, int offset) {
        Query query = session().createQuery(ALL_POSTS);
        query.setParameter("id", id);
        query.setParameter("type_ref", FeedAttribute.REPOST.getId());
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.list();
    }

    @Override
    public List<Feed> myPost(Long id, int limit, int offset) {
        LOGGER.info("Run get my post");
        Query query = session().createQuery(MY_POST);
        query.setParameter("id", id);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        List<Feed> result = query.list();
        return result;
    }

    @Override
    public List<Feed> myRepost(Long id, int limit, int offset) {
        LOGGER.info("Run get my repost");
        Query  query = session().createQuery(MY_REPOST);
        query.setParameter("id", id);
        query.setParameter("type_ref", FeedAttribute.REPOST.getId());
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        List<Feed> result = query.list();
//        LOGGER.info("Result my repost\n" + result.toString());
        return result;
    }

    @Override
    public Map<String, List<Feed>> allPost(Long id, int limit, int offset) {
        LOGGER.info("Run get all post");
        Map<String, List<Feed>> map = new HashMap<>();
        map.put(FeedAttribute.POST.toString().toLowerCase(), myPost(id, limit, offset));
        map.put(FeedAttribute.REPOST.toString().toLowerCase(), myRepost(id, limit, offset));
//        LOGGER.info("Result get all post\n" + map.toString());
        return map;
    }

    @Override
    public Feed findNews(Long id) {
        return session().find(Feed.class, id);
    }

    @Override
    public void saveRepost(User user, Feed news, Long typeReference) {
        FeedReference reference = new FeedReference(new FeedReference.FeedReferenceEmbedded(user, news, typeReference, new Date()));
        session().save(reference);
    }

    @Override
    public void deleteRepost(User user, Feed news, Long typReference) {
        Query query = session().createQuery(DELETE_REFERENCE);
        query.setParameter("user_id", user.getId());
        query.setParameter("news_id", news.getId());
        query.setParameter("type_ref", typReference);
        query.executeUpdate();
    }

    @Override
    public void deleteLike(User user, Feed news, Long typReference) {
        Query query = session().createQuery(DELETE_REFERENCE);
        query.setParameter("user_id", user.getId());
        query.setParameter("news_id", news.getId());
        query.setParameter("type_ref", typReference);
        query.executeUpdate();
    }

    @Override
    public Map<String, List<Feed>> postInfo(Long newsId, int limit, int offset) {
        LOGGER.info("Run get post info");
        Map<String, List<Feed>> map = new HashMap<>();

        Query query = session().createQuery(POST_INFO);
        query.setParameter("news_id", newsId);
        query.setParameter("type_ref", FeedAttribute.REPOST.getId());

        map.put(FeedAttribute.REPOST.toString().toLowerCase(), query.list());

        query.setParameter("type_ref", FeedAttribute.LIKE.getId());

        map.put(FeedAttribute.LIKE.toString().toLowerCase(), query.list());
        return map;
    }

    public Session session() {
        return sessionFactory.getCurrentSession();
    }
}
