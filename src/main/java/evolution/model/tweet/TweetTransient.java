package evolution.model.tweet;

import com.fasterxml.jackson.annotation.JsonInclude;
import evolution.model.user.StandardUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

/**
 * Created by Infant on 22.07.2017.
 */
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TweetTransient {

    private Long id;

    private String content;

    private String tags;

    private Date tweetDate;

    private Date repostDate;

    private StandardUser sender;

    private StandardUser reposted;

    private boolean checkRepost;

    private Long countRepost;

}
