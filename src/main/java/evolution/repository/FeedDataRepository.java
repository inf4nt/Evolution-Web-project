package evolution.repository;

import evolution.model.feed.FeedData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Admin on 29.06.2017.
 */
public interface FeedDataRepository extends JpaRepository<FeedData, Long> {
}
