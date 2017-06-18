package evolution.model.news;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.model.user.User;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Admin on 17.06.2017.
 */
@Entity
@Table(name = "repost_feed")
@ToString @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RepostFeed implements Serializable{

    @EmbeddedId
    @JsonProperty
    private RepostFeedEmbeddable repostFeed;

    @Embeddable
    @ToString @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class RepostFeedEmbeddable implements Serializable{

        @JsonProperty
        @ManyToOne
        @JoinColumn(name = "user_reposted")
        private User userReposted;

        @JsonProperty
        @ManyToOne
        @JoinColumn(name = "feed_id")
        private Feed feed;
    }
}
