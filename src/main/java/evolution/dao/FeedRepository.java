package evolution.dao;

import evolution.model.news.Feed;

import java.util.List;

/**
 * Created by Admin on 17.06.2017.
 */
public interface FeedRepository extends DefaultDao{

    List<Feed> feed(Long userId, int limit, int offset);

    List<Feed> feed(int limit, int offset);

    int delete(Long feedId, Long userId);

    int repostDelete(Long feedId, Long repostedUserId);

    int deleteFeedOnMyBoard(Long feedId, Long userId);
}
