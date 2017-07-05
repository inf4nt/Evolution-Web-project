package evolution.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.model.user.StandardUser;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Infant on 05.07.2017.
 */
@Entity
@Table(name = "comment")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString(exclude = "publication")
public class Comment {

    @JsonProperty
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comment")
    @SequenceGenerator(name = "seq_comment", sequenceName = "seq_comment_id", allocationSize = 1)
    private Long id;

    @JsonProperty
    @Column
    private String content;

    @JsonProperty
    @Column(name = "date_comment")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @JsonProperty
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id")
    private StandardUser sender;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id")
    private Publication publication;

    public Comment(String content, StandardUser sender, Date date, Publication publication){
        this.content = content;
        this.sender = sender;
        this.date = date;
        this.publication = publication;
    }

    public Comment(Long id){
        this.id = id;
    }
}
