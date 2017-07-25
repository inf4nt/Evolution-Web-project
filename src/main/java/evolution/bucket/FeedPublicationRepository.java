//package evolution.bucket;
//
//import evolution.bucket.feed.FeedPublication;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
///**
// * Created by Admin on 29.06.2017.
// */
//interface FeedPublicationRepository extends JpaRepository<FeedPublication, Long> {
//
//    @Query("select new FeedPublication ( " +
//            " fd.id, fd.content, fd.tags, " +
//            " fp.id, fp.date, " +
//            " sender.id, sender.firstName, sender.lastName) " +
//            " from FeedPublication fp " +
//            " join FeedData fd on fd.id = fp.feedData.id and (fp.reposted.id is null or fp.reposted.id =:user_id ) " +
//            " join StandardUser sender on sender.id = fp.sender.id " +
//            " where fp.sender.id =:user_id " +
//            " or fp.reposted.id =:user_id " +
//            " order by fp.date desc ")
//    List<FeedPublication> findAll(@Param("user_id") Long userId, Pageable pageable);
//
//    @Query(" select new FeedPublication ( " +
//            " fd.id, fd.content, fd.tags, " +
//            " fp.id, fp.date, " +
//            " sender.id, sender.firstName, sender.lastName) " +
//            " from FeedPublication fp " +
//            " join FeedData fd on fd.id = fp.feedData.id and (fp.reposted.id is null or fp.reposted.id =:user_id ) " +
//            " join StandardUser sender on sender.id = fp.sender.id " +
//            " where fp.sender.id =:user_id " +
//            " or fp.reposted.id =:user_id " +
//            " order by fp.date desc ")
//    List<FeedPublication> findAll(@Param("user_id") Long userId);
//
//    @Deprecated
//    @Query(
//            " select fp " +
//            " from FeedPublication fp " +
//            " join fetch fp.feedData as fd " +
//            " join fetch fp.sender as sender " +
//            " left join fetch fp.reposted as reposted " +
//            " where fp.id in ( " +
//            " select fp.id from Friends f" +
//            " join FeedPublication fp on fp.sender.id = f.friend.id and fp.reposted is null " +
//            " or fp.reposted.id = f.friend.id " +
//            " where f.user.id =:user_id) " +
//            " or fp.id in ( " +
//            " select fp.id from FeedPublication fp " +
//            " where fp.sender.id = :user_id and fp.reposted is null " +
//            " or fp.reposted.id =:user_id) " +
//            " order by fp.date desc ")
//    List<FeedPublication> findAllNews(@Param("user_id") Long userId, Pageable pageable);
//
//    @Deprecated
//    @Query(" select new FeedPublication (" +
//            " fd.id, fd.content, fd.tags, " +
//            " fp.id, fp.date, " +
//            " sender.id, sender.firstName, sender.lastName) " +
//            " from FeedPublication fp " +
//            " join fp.feedData fd " +
//            " join fp.sender as sender" +
//            " where fp.id =:id and fp.sender.id =:user_id ")
//    FeedPublication findBySenderAndFeedPublicationId(@Param("id") Long feedId, @Param("user_id") Long senderId);
//
//    @Query("select fp " +
//            " from FeedPublication fp " +
//            " where fp.feedData.id =:feedId and fp.reposted.id = :reposted")
//    FeedPublication findFeedPublicationByFeedDataAndReposted(@Param("feedId") Long feedId, @Param("reposted") Long repostedId);
//
//    @Query(" select new FeedPublication (" +
//            " fd.id, fd.content, fd.tags, " +
//            " fp.id, fp.date, " +
//            " sender.id, sender.firstName, sender.lastName) " +
//            " from FeedPublication fp " +
//            " join FeedData fd on fd.id = fp.feedData.id and fp.reposted is null " +
//            " join fp.sender as sender " +
//            " where fp.feedData.tags like lower (concat('%', :tag, '%')) " +
//            " order by fp.id desc ")
//    List<FeedPublication> findByTag(@Param("tag") String tag);
//
//    @Transactional
//    @Modifying
//    @Query(" delete from FeedPublication fp " +
//            " where fp.reposted.id =:user_id and fp.id =:id ")
//    void deleteRepost(@Param("id") Long feedId, @Param("user_id") Long userRepostedId);
//
//
//    @Query("select fp " +
//            " from Friends f " +
//            " join fetch FeedPublication fp on f.friend.id = fp.sender.id and fp.reposted is null or f.friend.id = fp.reposted.id " +
//            " join fetch fp.feedData " +
//            " join fetch fp.sender " +
//            " left join fetch fp.reposted " +
//            " where f.user.id =:user_id " +
//            " order by fp.date desc ")
//    List<FeedPublication> findNews(@Param("user_id") Long userId, Pageable pageable);
//
//    @Query(" select fp " +
//            " from FeedPublication fp " +
//            " join fetch fp.sender " +
//            " left join fetch fp.reposted " +
//            " join fetch fp.feedData " +
//            " where fp.sender.id =:user_id and fp.reposted is null or fp.reposted.id =:user_id " +
//            " order by fp.date desc ")
//    List<FeedPublication> findMyPostRepost(@Param("user_id") Long userId, Pageable pageable);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////    private static final String GET_TWEETS_AND_REPOSTS_FROM_USER = "SELECT" +
////            " * " +
////            "FROM (SELECT t.tweet_id, t.body, t.date, t.user_id AS author_id, t.user_id,\n" +
////            "(SELECT COUNT(*) FROM likes WHERE tweet_id = t.tweet_id) AS like_count,\n" +
////            "(SELECT COUNT(*) FROM reposts WHERE tweet_id = t.tweet_id) AS repost_count,\n" +
////
////            "( SELECT login " +
////            " FROM users au " +
////            " JOIN tweets at ON au.user_id = at.user_id" +
////            " WHERE at.tweet_id = t.tweet_id) AS author_login, false AS reposted\n" +
////
////            "FROM tweets t WHERE t.user_id = ?\n" +
////
////            "UNION ALL" +
////
////            " SELECT tr.tweet_id, tr.body, r.date, tr.user_id AS author_id, r.user_id,\n" +
////            "(SELECT COUNT(*) FROM likes WHERE tweet_id = tr.tweet_id) AS like_count,\n" +
////            "(SELECT COUNT(*) FROM reposts WHERE tweet_id = tr.tweet_id) AS repost_count,\n" +
////            "(SELECT login FROM users au JOIN tweets at ON au.user_id = at.user_id WHERE at.tweet_id = tr.tweet_id) AS author_login, true AS reposted\n" +
////            "FROM tweets tr JOIN reposts r ON tr.tweet_id = r.tweet_id WHERE r.user_id = ? LIMIT ?, ?) x ORDER BY x.date DESC, x.tweet_id DESC;";
////
////
////    private static final String GET_TWEETS_AND_REPOSTS_FROM_FOLLOWING = "SELECT * FROM (SELECT f_t.tweet_id, f_t.body, f_t.date, f_t.user_id AS author_id, f_t.user_id,\n" +
////            "(SELECT COUNT(*) FROM likes WHERE tweet_id = f_t.tweet_id) AS like_count,\n" +
////            "(SELECT COUNT(*) FROM reposts WHERE tweet_id = f_t.tweet_id) AS repost_count,\n" +
////            "(SELECT login FROM users au JOIN tweets at ON au.user_id = at.user_id WHERE at.tweet_id = f_t.tweet_id) AS author_login, false AS reposted\n" +
////            "FROM tweets f_t JOIN followers f_f ON f_f.following_id = f_t.user_id WHERE f_f.user_id = ?\n" +
////            "UNION ALL SELECT f_tr.tweet_id, f_tr.body, f_rr.date, f_tr.user_id AS author_id, f_rr.user_id,\n" +
////            "(SELECT COUNT(*) FROM likes WHERE tweet_id = f_tr.tweet_id) AS like_count,\n" +
////            "(SELECT COUNT(*) FROM reposts WHERE tweet_id = f_tr.tweet_id) AS repost_count,\n" +
////            "(SELECT login FROM users au JOIN tweets at ON au.user_id = at.user_id WHERE at.tweet_id = f_tr.tweet_id) AS author_login, true AS reposted\n" +
////            "FROM reposts f_rr JOIN tweets f_tr ON f_tr.tweet_id = f_rr.tweet_id JOIN followers f_fr ON f_rr.user_id = f_fr.following_id WHERE f_fr.user_id = ? LIMIT ?, ?) x ORDER BY x.date DESC, x.tweet_id DESC;";
//}
