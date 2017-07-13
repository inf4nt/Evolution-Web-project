package evolution.dao;

import evolution.model.feed.FeedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Admin on 29.06.2017.
 */
interface FeedDataRepository extends JpaRepository<FeedData, Long> {

    @Transactional
    @Modifying
    @Query(" delete from FeedData fd " +
           " where fd.id = ( " +
           " select fp.feedData.id from FeedPublication fp where fp.id = :id and fp.sender.id = :senderId" +
           " ) ")
    void deletePost(@Param("id") Long id, @Param("senderId") Long senderId);

}
