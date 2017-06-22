package evolution.model.dialog;

import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.model.user.User;
import lombok.*;
import javax.persistence.*;


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first")
    @JsonProperty
    private User first;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second")
    @JsonProperty
    private User second;

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
