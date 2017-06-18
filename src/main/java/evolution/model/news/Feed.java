package evolution.model.news;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.model.user.User;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @JsonProperty
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = " feed_id")
    @SequenceGenerator(name = " feed_id", sequenceName = " seq_feed_id", allocationSize = 1)
    private Long id;

    @JsonProperty
    @Column(name = "feed_content")
    private String feedContent;

    @JsonProperty
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Transient
    @JsonProperty
    private RepostFeed.RepostFeedEmbeddable repostFeed;

    public Feed(Long id, String content, Date date,
                Long senderId, String senderFirstName, String senderLastName,
                Long userId, String userFirstName, String userLastName,
                Long respostedUserId) {
        this.id = id;
        this.feedContent = content;
        this.date = date;
        this.sender = new User(senderId, senderFirstName, senderLastName);
        this.user = new User(userId, userFirstName, userLastName);
        this.repostFeed = new RepostFeed.RepostFeedEmbeddable(new User(respostedUserId), this);
    }

    public Feed(Long id, String content, Date date,
                Long senderId, String senderFirstName, String senderLastName,
                Long userId, String userFirstName, String userLastName) {
        this.id = id;
        this.feedContent = content;
        this.date = date;
        this.sender = new User(senderId, senderFirstName, senderLastName);
        this.user = new User(userId, userFirstName, userLastName);
    }

    public Feed(String content, Date date, User sender, User user) {
        this.feedContent = content;
        this.date = date;
        this.sender = sender;
        this.user = sender;
        this.user = user;
    }

    public Feed(Long id) {
        this.id = id;
    }

}
