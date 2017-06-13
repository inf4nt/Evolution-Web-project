package evolution.dao;

import evolution.model.news.Feed;
import evolution.model.user.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 11.06.2017.
 */
public interface FeedDao extends DefaultDao {

    Feed findNews(Long id);

    List<Feed> myPost(Long id, int limit, int offset);

    List<Feed> myRepost(Long id, int limit, int offset);

    List<Feed> allPosts(Long id, int limit, int offset);

    Map<String, List<Feed>> allPost(Long id, int limit, int offset);

    void deleteLike(User user, Feed news, Long typReference);

    void saveRepost(User user, Feed news, Long typeReference);

    void deleteRepost(User user, Feed news, Long typReference);

    Map<String, List<Feed>> postInfo(Long newsId, int limit, int offset);
}
