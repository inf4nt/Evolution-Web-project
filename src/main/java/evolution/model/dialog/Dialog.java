package evolution.model.dialog;

import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.model.message.Message;
import evolution.model.user.User;
import lombok.*;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Admin on 09.06.2017.
 */
@Entity
@Table(name = "dialog")
@NoArgsConstructor @AllArgsConstructor
@ToString @Getter @Setter
public class Dialog {

    @Id
    @Column(name = "id")
    @JsonProperty
    private Long id;
    @ManyToOne
    @JoinColumn(name = "first")
    @JsonProperty
    private User first;
    @ManyToOne
    @JoinColumn(name = "second")
    @JsonProperty
    private User second;

    @OneToMany(mappedBy = "dialog", fetch = FetchType.LAZY)
    private List<Message> listMessage;

    public Dialog(Long id) {
        this.id = id;
    }

    public Dialog(Long id, User second) {
        this.id = id;
        this.second = second;
    }

    public Dialog(User first, User second) {
        this.first = first;
        this.second = second;
    }
}
