package evolution.dao;

import evolution.model.feed.FeedData;
import evolution.model.feed.FeedPublication;
import evolution.model.user.StandardUser;
import evolution.repository.FeedDataRepository;
import evolution.repository.FeedPublicationRepository;
import lombok.Getter;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Admin on 01.07.2017.
 */
@Service
public class FeedDaoService {

    private final Logger LOGGER  = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private FeedDataRepository feedDataRepository;

    @Autowired
    private FeedPublicationRepository feedPublicationRepository;

    @Transactional
    public FeedPublication save(String content, String tags, Long senderId, Long repostedId)  {
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

    public Session session() {
        return entityManager.unwrap(Session.class);
    }
}
