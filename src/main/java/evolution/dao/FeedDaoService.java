package evolution.dao;

import evolution.model.feed.FeedData;
import evolution.model.feed.FeedPublication;
import evolution.model.user.StandardUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Infant on 13.07.2017.
 */
@Service
public class FeedDaoService {

    @Autowired
    private FeedDataRepository feedDataRepository;

    @Autowired
    private FeedPublicationRepository feedPublicationRepository;

    @Transactional
    public FeedPublication save(FeedPublication feedPublication) {
        feedDataRepository.save(feedPublication.getFeedData());
        return feedPublicationRepository.saveAndFlush(feedPublication);
    }

    @Transactional
    public FeedPublication save(String content, String tags, Long senderId, Long repostedId) {
        FeedData saved = feedDataRepository.save(new FeedData(content, tags));
        FeedPublication feedPublication;
        if (repostedId != null)
            feedPublication = new FeedPublication(new StandardUser(senderId), new StandardUser(repostedId));
        else {
            feedPublication = new FeedPublication(new StandardUser(senderId), null);
        }
        feedPublication.setFeedData(new FeedData(saved.getId()));
        return feedPublicationRepository.saveAndFlush(feedPublication);
    }

    @Transactional
    public void deletePost(Long id, Long senderId) {
        feedDataRepository.deletePost(id, senderId);
    }

    @Transactional
    public List<FeedPublication> findByTag(String tag) {
        return feedPublicationRepository.findByTag(tag);
    }

    @Transactional
    public List<FeedPublication> findAllNews(Long userId, Pageable pageable) {
        return feedPublicationRepository.findAllNews(userId, pageable);
    }

    @Transactional
    public List<FeedPublication> findAll(Long userId, Pageable pageable) {
        return feedPublicationRepository.findAll(userId, pageable);
    }

    @Transactional
    public List<FeedPublication> findAll(Long userId) {
        return feedPublicationRepository.findAll(userId);
    }

    @Transactional
    public List<FeedPublication> findAllFeedPublication() {
        return feedPublicationRepository.findAll();
    }

    @Transactional
    public FeedPublication findOneFeedPublication(Long id) {
        return feedPublicationRepository.findOne(id);
    }
}
