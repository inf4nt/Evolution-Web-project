package evolution.model;



import lombok.Getter;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Admin on 18.04.2017.
 */

@Entity
@Table(name = "message")
@ToString
public class Message
        implements Serializable{

    public Message(){}

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

    public String getDateFormatDispatch() {
        return DateFormat.getInstance().format(dateDispatch);
    }

    @Id
    @Column(name = "message_id", unique = true, nullable = false)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_message")
    @SequenceGenerator(name = "seq_message", sequenceName = "seq_message_id", allocationSize = 1)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @Getter
    private User sender;

    @Column
    @Getter
    private String message;

    @Column(name = "date_dispatch")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    private Date dateDispatch;

    @ManyToOne
    @JoinColumn(name = "dialog_id")
    @Getter
    private MessageDialog dialog;

    @Entity
    @Table(name = "dialog")
    @ToString
    public static class MessageDialog {

        public MessageDialog(){}

        public MessageDialog(Long id){
            this.id = id;
        }

        @Id
        @GeneratedValue
        @Column
        @Getter
        private Long id;
        @ManyToOne
        @JoinColumn(name = "first")
        @Getter
        private User first;
        @ManyToOne
        @JoinColumn(name = "second")
        @Getter
        private User second;

    }
}
