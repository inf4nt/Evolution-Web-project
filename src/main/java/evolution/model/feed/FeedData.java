package evolution.model.feed;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Admin on 24.06.2017.
 */
@Entity
@Table(name = "feed_data")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class FeedData implements Serializable {

    @JsonProperty
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feed_id")
    @SequenceGenerator(name = "feed_id", sequenceName = "seq_feed_id", allocationSize = 1)
    private Long id;

    @JsonProperty
    @Column
    private String content;

    @JsonProperty
    @Column
    private String tags;

    public FeedData(Long id){
        this.id = id;
    }

    public FeedData(String content, String tags) {
        this.content = content;
        this.tags = tags;
    }
}
