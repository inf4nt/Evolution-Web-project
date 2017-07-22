package evolution.model.tweet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.model.user.StandardUser;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Infant on 22.07.2017.
 */
@Entity
@Table(name = "tweet")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor @AllArgsConstructor @ToString @Getter @Setter
public class Tweet implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tweet")
    @SequenceGenerator(name = "seq_tweet", sequenceName = "seq_tweet_id", allocationSize = 1)
    private Long id;

    @Column
    private String content;

    @Column
    private String tags;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "sender_id")
    private StandardUser sender;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty
    private Date date;
}

