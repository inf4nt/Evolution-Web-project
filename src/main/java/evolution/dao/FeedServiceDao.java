package evolution.dao;

import evolution.model.feed.Feed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Infant on 26.07.2017.
 */
@Service
public class FeedServiceDao {

    @Autowired
    private FeedRepository feedRepository;

    @Transactional
    public List<Feed> findFeedOfMyFriends(Long userId) {
        return feedRepository.findFeedOfMyFriends(userId);
    }

}
