package evolution.dao;

import evolution.model.feed.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    List<Feed> findFeedOfMyFriends(@Param("user_id") Long userId);



}
