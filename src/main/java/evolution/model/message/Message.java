package evolution.model.message;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;


/**
 * Created by Admin on 18.04.2017.
 */

@Entity
@Table(name = "message")
@ToString @NoArgsConstructor @Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message
        implements Serializable{

    public Message(Long id){
        this.id = id;
    }

    public Message(Long messageId, String message, Date dateDispatch,
                   Long senderId, String senderFirstName, String senderLastName) {
        this.sender = new User();
        this.id = messageId;
        this.message = message;
        this.dateDispatch = dateDispatch;
        sender.setId(senderId);
        sender.setFirstName(senderFirstName);
        sender.setLastName(senderLastName);
    }

    public Message(String message, Long secondId, String secondFirstName, String secondLastName){
        this.dialog.second = new User(secondId, secondFirstName, secondLastName);
        this.message = message;
    }

    public Message(Long dialog, User sender, String message, Date dateDispatch) {
        MessageDialog messageDialog = new MessageDialog(dialog);
        this.dialog = messageDialog;
        this.sender = sender;
        this.message = message;
        this.dateDispatch = dateDispatch;
    }

    public Message(Long dialogId, Long messageId, String message, Date dateDispatch,
                   Long senderId, String senderFirstName, String senderLastName,
                   Long imId, String imFirstName, String imLastName) {
        this.dialog = new MessageDialog(dialogId);
        this.sender = new User(senderId, senderFirstName, senderLastName);
        this.id = messageId;
        this.message = message;
        this.dateDispatch = dateDispatch;
        this.dialog.second = new User(imId, imFirstName, imLastName);
    }

    public Message (User sender, String message, Date date, MessageDialog dialog){
        this.sender = sender;
        this.message = message;
        this.dateDispatch = date;
        this.dialog = dialog;
    }

    @JsonIgnore
    public String getDateFormatDispatch() {
        return DateFormat.getInstance().format(dateDispatch);
    }

    @Id
    @Column(name = "message_id", unique = true, nullable = false)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_message")
    @SequenceGenerator(name = "seq_message", sequenceName = "seq_message_id", allocationSize = 1)
    @JsonProperty(value = "messageId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonProperty
    private User sender;

    @Column
    @JsonProperty
    private String message;

    @Column(name = "date_dispatch")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty
    private Date dateDispatch;

    @ManyToOne
    @JoinColumn(name = "dialog_id")
    @JsonProperty
    private MessageDialog dialog;

    @Entity
    @Table(name = "dialog")
    @ToString @NoArgsConstructor @Getter @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class MessageDialog {

        public MessageDialog(Long id){
            this.id = id;
        }

        @Id
        @GeneratedValue
        @Column
        @JsonProperty(value = "dialogId")
        private Long id;
        @ManyToOne
        @JoinColumn(name = "first")
        @JsonIgnore
        private User first;
        @ManyToOne
        @JoinColumn(name = "second")
        @JsonProperty
        private User second;
    }
}
