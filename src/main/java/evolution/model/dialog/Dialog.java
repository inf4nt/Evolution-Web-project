package evolution.model.dialog;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.model.user.StandardUser;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dialog {

    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_dialog")
    @SequenceGenerator(name = "seq_dialog", sequenceName = "seq_dialog_id", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "first")
    private User first;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "second")
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

    public Dialog (Long dialogId, Long firstId, String firstFirstName, String firstLastName,
                   Long secondId,String secondFirstName ,String secondLastName) {
        this.id = dialogId;
        this.first = new User(firstId, firstFirstName, firstLastName);
        this.second = new User(secondId, secondFirstName, secondLastName);
    }


}
