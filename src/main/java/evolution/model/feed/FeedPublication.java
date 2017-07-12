package evolution.model.feed;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.model.user.StandardUser;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Admin on 24.06.2017.
 */
@Entity
@Table(name = "feed_publication")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class FeedPublication {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fp_id")
    @SequenceGenerator(name = "fp_id", sequenceName = "seq_feed_publication", allocationSize = 1)
    private Long id;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "feed_id")
    private FeedData feedData;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "sender_id")
    private StandardUser sender;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "reposted_id")
    private StandardUser reposted;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public FeedPublication(Long id) {
        this.id = id;
    }

    public FeedPublication(FeedData feedData, StandardUser sender, StandardUser reposted) {
        this.feedData = feedData;
        this.sender = sender;
        this.reposted = reposted;
        this.date = new Date();
    }

    public FeedPublication(Long feedId, String content, String tags, Long fpId, Date date,
                           Long senderId, String senderFirstName, String senderLastName) {
        this.feedData = new FeedData(feedId, content, tags);
        this.id = fpId;
        this.date = date;
        this.sender = new StandardUser(senderId, senderFirstName, senderLastName);
    }

    public FeedPublication(Long feedId, String content, String tags, Long fpId, Date date,
                           Long senderId, String senderFirstName, String senderLastName,
                           Long repostedId, String repostedFirstName, String repostedLastName) {
        this.feedData = new FeedData(feedId, content, tags);
        this.id = fpId;
        this.date = date;
        this.sender = new StandardUser(senderId, senderFirstName, senderLastName);
        this.reposted = new StandardUser(repostedId, repostedFirstName, repostedLastName);
    }


    public FeedPublication(StandardUser sender, StandardUser reposted) {
        this.sender = sender;
        this.reposted = reposted;
        this.date = new Date();
    }
}
