package evolution.model.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.model.dialog.Dialog;
import evolution.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Admin on 09.06.2017.
 */

@Entity
@Table(name = "message")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor @AllArgsConstructor
@ToString @Getter @Setter
public class Message {

    @Id
    @Column(name = "message_id", unique = true, nullable = false)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_message")
    @SequenceGenerator(name = "seq_message", sequenceName = "seq_message_id", allocationSize = 1)
    @JsonProperty(value = "messageId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dialog_id", updatable = false)
    @JsonProperty
    private Dialog dialog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    @JsonProperty
    private User sender;

    @Column(name = "message")
    @JsonProperty
    private String message;

    @Column(name = "date_dispatch")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty
    private Date dateDispatch;

    public Message(Long messageId, String message, Date dateDispatch,
                   Long senderId, String senderFirstName, String senderLastName, Long dialogId) {
        this.dialog = new Dialog(dialogId);
        this.sender = new User(senderId, senderFirstName, senderLastName);
        this.id = messageId;
        this.message = message;
        this.dateDispatch = dateDispatch;
    }

    public Message(Long dialogId, Long messageId, String message, Date dateDispatch,
                   Long senderId, String senderFirstName, String senderLastName,
                   Long imId, String imFirstName, String imLastName) {
        this.dialog = new Dialog(dialogId, new User(imId, imFirstName, imLastName));
        this.sender = new User(senderId, senderFirstName, senderLastName);
        this.id = messageId;
        this.message = message;
        this.dateDispatch = dateDispatch;
    }

    public Message(Long senderId, String message, Date dateDispatch, Long dialogId) {
        this.dialog = new Dialog(dialogId);
        this.sender = new User(senderId);
        this.message = message;
        this.dateDispatch = dateDispatch;
    }
    public Message(User sender, String message, Date dateDispatch, Dialog dialog) {
        this.dialog = dialog;
        this.sender = sender;
        this.message = message;
        this.dateDispatch = dateDispatch;
    }

    public Message(Long dialogId, User sender, String message, Date dateDispatch) {
        this.dialog = new Dialog(dialogId);
        this.sender = sender;
        this.message = message;
        this.dateDispatch = dateDispatch;

    }

    public Message(Long id){
        this.id = id;
    }
}

