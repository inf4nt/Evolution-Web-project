package evolution.model.news;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.model.user.User;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by Admin on 11.06.2017.
 */
@Entity
@Table(name = "feed")
@NoArgsConstructor @ToString @Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Feed {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_news_attribute")
    @SequenceGenerator(name = "seq_news_attribute", sequenceName = "seq_news_attribute_id", allocationSize = 1)
    @JsonProperty
    private Long id;

    @Column(name = "feed_content")
    @JsonProperty
    private String feedContent;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty
    private Date date;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonProperty
    private User sender;

    @Transient
    @JsonProperty
    private FeedReference.FeedReferenceEmbedded newsReference;

    public Feed(Long id) {
        this.id = id;
    }

    public Feed(Long id, String newsContent, Date date, Long senderId, String senderFirstName, String senderLastName) {
        this.id = id;
        this.feedContent = newsContent;
        this.date = date;
        this.sender = new User(senderId, senderFirstName, senderLastName);
    }

    public Feed(Long id, String newsContent, Date date, Long senderId, String senderFirstName, String senderLastName, Date actionDate) {
        this.id = id;
        this.feedContent = newsContent;
        this.date = date;
        this.sender = new User(senderId, senderFirstName, senderLastName);
        this.newsReference = new FeedReference.FeedReferenceEmbedded();
        newsReference.setActionDate(actionDate);
    }

    public Feed(Long userId, String userFirstName, String userLastName){
        this.sender = new User(userId, userFirstName, userLastName);
    }

    public Feed(String newsContent, Date date, User sender) {
        this.feedContent = newsContent;
        this.date = date;
        this.sender = sender;
    }


}
