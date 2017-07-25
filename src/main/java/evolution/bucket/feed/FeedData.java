package evolution.bucket.feed;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 24.06.2017.
 */
@Entity
@Table(name = "feed_data")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class FeedData implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feed_id")
    @SequenceGenerator(name = "feed_id", sequenceName = "seq_feed_id", allocationSize = 1)
    private Long id;

    @Column
    private String content;

    @Column
    private String tags;

    public FeedData(Long id){
        this.id = id;
    }

    public FeedData(String content, String tags) {
        this.content = content;
        this.tags = tags;
    }

    public List<String> listTags() {
        if (tags == null)
            return null;
        String arr[] = tags.split("#");
//        List<String> list = new ArrayList<>(Arrays.asList(arr));
//        list.remove(0);
//        return list;

        return Arrays.asList(arr).stream().skip(1).collect(Collectors.toList());

    }

}
