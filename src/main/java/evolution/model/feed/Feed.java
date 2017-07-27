package evolution.model.feed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import evolution.model.user.StandardUser;
import evolution.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Infant on 26.07.2017.
 */
@Entity
@Table(name = "feed")
@NoArgsConstructor @ToString @Getter @Setter @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Feed implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_feed")
    @SequenceGenerator(name = "seq_feed", sequenceName = "seq_feed_id", allocationSize = 1)
    private Long id;

    @Column(name = "feed_content")
    private String content;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "to_user_id")
    private User toUser;

    @Column
    private String tags;

    public Feed(String content, Date date, User sender, User toUser, String tags) {
        this.content = content;
        this.date = date;
        this.sender = sender;
        this.toUser = toUser;
        this.tags = tags;
    }


    public Feed(Long id, String content, Date date, String tags ,
                Long senderId, String senderFirstName, String senderLastName,
                Long toUserId, String toUserFirstName, String toUserLastName ) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.sender = new User(senderId, senderFirstName, senderLastName);
        this.toUser = new User(toUserId, toUserFirstName, toUserLastName);
        this.tags = tags;
    }


    public String dateFormat() {
        return DateFormat.getInstance().format(date);
    }

    public List<String> listTags() {
        if (tags == null)
            return null;
        return Arrays
                .asList(tags.split("#"))
                .stream()
                .skip(1)
                .collect(Collectors.toList());
    }
}
