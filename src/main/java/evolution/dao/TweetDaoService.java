package evolution.dao;

import evolution.model.tweet.Repost;
import evolution.model.tweet.Tweet;
import evolution.model.tweet.TweetTransient;
import evolution.model.user.StandardUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Infant on 22.07.2017.
 */
@Service
public class TweetDaoService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private RepostRepository repostRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final String FIND_TWEETS_BY_USER_ID = " SELECT\n" +
            "  t.id, t.content, t.tags, t.tweet_date, t.repost_date,\n" +
            "  sender.id AS sid, sender.first_name sfn, sender.last_name AS sln,\n" +
            "  reposted.id rid, reposted.first_name AS rfn, reposted.last_name AS rln,\n" +
            "  rt.id AS if_i_am_repost_this,\n" +
            "  (SELECT\n" +
            "     count(1) AS count_repost\n" +
            "   FROM repost rt\n" +
            "   WHERE rt.tweet_id = t.id)\n" +
            "   FROM (\n" +
            "       -- найти посты моих друзей\n" +
            "       SELECT\n" +
            "         t.id, t.content, t.tags, t.date AS order_date, " +
            "         t.sender_id, NULL AS repost_column, f.friend_id, t.date AS tweet_date, NULL AS repost_date\n" +
            "       FROM friends f\n" +
            "         JOIN tweet t ON t.sender_id = f.friend_id\n" +
            "         LEFT JOIN repost rr ON rr.tweet_id = t.id AND rr.reposted_user_id = :user_id\n" +
            "       WHERE f.user_id = :user_id\n" +
            "       UNION ALL\n" +
            "       -- найти репосты моих друзей\n" +
            "       SELECT\n" +
            "         t.id, t.content, t.tags, rt.date AS order_date," +
            "         t.sender_id, rt.reposted_user_id AS repost_column, f.friend_id, t.date AS tweet_date, rt.date AS repost_date\n" +
            "       FROM friends f\n" +
            "         JOIN repost rt ON  rt.reposted_user_id = f.friend_id\n" +
            "         JOIN tweet t ON t.id = rt.tweet_id\n" +
            "       WHERE f.user_id = :user_id\n" +
            "     ) AS t\n" +
            "  JOIN user_data sender ON t.sender_id = sender.id\n" +
            "  LEFT JOIN user_data reposted ON t.repost_column = reposted.id\n" +
            "  LEFT JOIN repost rt ON rt.tweet_id = t.id AND rt.reposted_user_id = :user_id\n" +
            "  ORDER BY t.order_date DESC ";


    @Transactional
    public List<TweetTransient> findTweetsOfMyFriends(Long userId, int limit, int offset) {
        Query query = entityManager.createNativeQuery(FIND_TWEETS_BY_USER_ID);
        query.setParameter("user_id", userId);
        query.setMaxResults(limit);
        query.setFirstResult(limit);
        List<Object[]> objects = query.getResultList();
        return convert(objects);
    }

    @Transactional
    public List<TweetTransient> findTweetsOfMyFriends(Long userId) {
        Query query = entityManager.createNativeQuery(FIND_TWEETS_BY_USER_ID);
        query.setParameter("user_id", userId);
        List<Object[]> objects = query.getResultList();
        return convert(objects);
    }

    @Transactional
    public Tweet findOneTweet(Long id) {
        return tweetRepository.findOne(id);
    }

    @Transactional
    public Repost findOneRepost(Long id) {
        return repostRepository.findOne(id);
    }

    @Transactional
    public List<Tweet> findAllTweet() {
        return tweetRepository.findAll();
    }

    @Transactional
    public List<Tweet> findAllTweet(Pageable pageable) {
        Page<Tweet> page = tweetRepository.findAll(pageable);
        return page.getContent();
    }

    @Transactional
    public List<Repost> findAllRepost() {
        return repostRepository.findAll();
    }

    @Transactional
    public List<Repost> findAllRepost(Pageable pageable) {
        Page<Repost> page = repostRepository.findAll(pageable);
        return page.getContent();
    }

    private TweetTransient convert(Object[] arr) {
        TweetTransient result = new TweetTransient();
        result.setId(Long.valueOf((Integer)arr[0]));
        result.setContent((String) arr[1]);
        result.setTags((String) arr[2]);
        result.setTweetDate(new Date(((Timestamp)arr[3]).getTime()));
        if (arr[4] != null)
            result.setRepostDate(new Date(((Timestamp)arr[4]).getTime()));
        if (arr[5] != null)
            result.setSender(new StandardUser(((Integer)arr[5]).longValue(), (String) arr[6], (String) arr[7]));
        if (arr[8] != null)
            result.setReposted(new StandardUser(((Integer)arr[8]).longValue(), (String) arr[9], (String) arr[10]));
        result.setCheckRepost(arr[11] != null);
        result.setCountRepost(((BigInteger) arr[12]).longValue());
        return result;
    }

    private List<TweetTransient> convert(List<Object[]> list) {
        List<TweetTransient> result = new ArrayList<>(list.size());
        list.forEach(objects -> {
            result.add(convert(objects));
        });
        return result;
    }

}
