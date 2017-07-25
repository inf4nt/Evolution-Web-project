package evolution.bucket.tweet;

import com.fasterxml.jackson.annotation.JsonInclude;
import evolution.model.user.StandardUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<String> listTags() {
        if (tags == null)
            return null;
        String arr[] = tags.split("#");
        return Arrays.asList(arr).stream().skip(1).collect(Collectors.toList());
    }
}
