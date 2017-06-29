package evolution.repository;

import evolution.model.feed.FeedPublication;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Created by Admin on 29.06.2017.
 */
public interface FeedPublicationRepository extends JpaRepository<FeedPublication, Long> {

    @Query("select new FeedPublication (" +
            " fd.id, fd.content, fd.tags, " +
            " fp.id, fp.date, " +
            " sender.id, sender.firstName, sender.lastName) " +
            " from FeedPublication fp " +
            " join FeedData fd on fd.id = fp.feedData.id and (fp.reposted.id is null or fp.reposted.id =:user_id ) " +
            " join StandardUser sender on sender.id = fp.sender.id " +
            " where fp.sender.id =:user_id " +
            " or fp.reposted.id =:user_id " +
            " order by fp.date desc ")
    List<FeedPublication> findAll(@Param("user_id") Long userId, Pageable pageable);

    @Query("select new FeedPublication (" +
            " fd.id, fd.content, fd.tags, " +
            " fp.id, fp.date, " +
            " sender.id, sender.firstName, sender.lastName) " +
            " from FeedPublication fp " +
            " join FeedData fd on fd.id = fp.feedData.id and (fp.reposted.id is null or fp.reposted.id =:user_id ) " +
            " join StandardUser sender on sender.id = fp.sender.id " +
            " where fp.sender.id =:user_id " +
            " or fp.reposted.id =:user_id " +
            " order by fp.date desc ")
    List<FeedPublication> findAll(@Param("user_id") Long userId);


    @Query( " select new FeedPublication (" +
            " fd.id, fd.content, fd.tags, " +
            " fp.id, fp.date, " +
            " sender.id, sender.firstName, sender.lastName, " +
            " reposted.id, reposted.firstName, reposted.lastName ) " +
            " from FeedPublication fp " +
            " join fp.feedData as fd " +
            " join fp.sender as sender " +
            " left join fp.reposted as reposted " +
            " where fp.id in (" +
            " select fp.id from Friends f " +
            " join FeedPublication fp on fp.sender.id = f.friend.id or fp.reposted.id = f.friend.id " +
            " where f.user.id = :user_id) " +
            " or fp.id in ( " +
            " select fp.id from FeedPublication fp " +
            " where fp.sender.id =:user_id and fp.reposted is null ) " +
            " order by fp.date desc ")
    List<FeedPublication> findAllNews(@Param("user_id") Long userId, Pageable pageable);


    @Query(" select new FeedPublication (" +
            " fd.id, fd.content, fd.tags, " +
            " fp.id, fp.date, " +
            " sender.id, sender.firstName, sender.lastName) " +
            " from FeedPublication fp " +
            " join fp.feedData fd " +
            " join fp.sender as sender" +
            " where fp.id =:id and fp.sender.id =:user_id ")
    FeedPublication find(@Param("id") Long feedId, @Param("user_id") Long senderId);


    @Query(" delete from FeedPublication fp " +
            " where fp.reposted.id =:user_id and fp.id =:id ")
    void deleteRepost(@Param("id") Long feedId, @Param("user_id") Long userRepostedId);
}