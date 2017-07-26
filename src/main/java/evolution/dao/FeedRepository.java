package evolution.dao;

import evolution.model.feed.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Infant on 26.07.2017.
 */
public interface FeedRepository extends JpaRepository<Feed, Long> {

    @Query(value = "select f from Friends fr " +
            " join fetch Feed f on f.sender.id = fr.friend.id " +
            " join fetch f.sender " +
            " where fr.user.id = :user_id " +
            " order by f.date desc ")
    List<Feed> findFeedsOfMyFriends(@Param("user_id") Long userId);

    @Query(" select f from Feed f" +
            " where f.sender.id = :user_id " +
            " order by f.date desc ")
    List<Feed> findMyFeeds(@Param("user_id") Long userId);

    @Transactional
    @Modifying
    @Query("delete from Feed f" +
            " where f.id = :id and f.sender.id = :sender_id")
    void delete (@Param("id") Long feedId, @Param("sender_id") Long senderId);

}
