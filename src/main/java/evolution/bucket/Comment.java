package evolution.bucket;

import com.fasterxml.jackson.annotation.JsonInclude;
import evolution.model.user.StandardUser;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Infant on 05.07.2017.
 */
@Entity
@Table(name = "comment")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString(exclude = "publication")
public class Comment {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comment")
    @SequenceGenerator(name = "seq_comment", sequenceName = "seq_comment_id", allocationSize = 1)
    private Long id;

    @Column
    private String content;

    @Column(name = "date_comment")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id")
    private StandardUser sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publication_id")
    private Comment.Publication publication;

    public Comment(String content, StandardUser sender, Date date, Publication publication){
        this.content = content;
        this.sender = sender;
        this.date = date;
        this.publication = publication;
    }

    public Comment(Long id){
        this.id = id;
    }

    @Entity
    @Table(name = "publication")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
    private static class Publication implements Serializable {

        @Id
        @Column
        private Long id;
    }
}
