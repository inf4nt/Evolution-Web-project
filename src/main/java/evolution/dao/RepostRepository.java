package evolution.dao;

import evolution.model.tweet.Repost;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Infant on 22.07.2017.
 */
public interface RepostRepository extends JpaRepository<Repost, Long>{
}
