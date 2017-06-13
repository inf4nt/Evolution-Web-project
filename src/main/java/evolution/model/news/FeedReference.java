package evolution.model.news;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Admin on 11.06.2017.
 */
@Entity
@Table(name = "feed_reference")
@NoArgsConstructor @AllArgsConstructor
@ToString @Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeedReference implements Serializable{

    @EmbeddedId
    @JsonProperty
    private FeedReferenceEmbedded feedReferenceEmbedded;

    @Embeddable
    @NoArgsConstructor @AllArgsConstructor @ToString @Getter @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FeedReferenceEmbedded implements Serializable{

        @ManyToOne
        @JoinColumn(name = "user_id")
        @JsonProperty
        private User user;

        @ManyToOne
        @JoinColumn(name = "feed_id")
        @JsonProperty
        private Feed feed;

        @Column(name = "type_reference")
        @JsonProperty
        private Long typeReference;

        @Column(name = "date_action")
        @Temporal(TemporalType.TIMESTAMP)
        @JsonProperty
        private Date actionDate;
    }
}