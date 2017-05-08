package evolution.model;







import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by Admin on 18.04.2017.
 */
@Entity
@Table(name = "dialog")
@ToString @NoArgsConstructor @Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dialog implements Serializable{

    public Dialog(Long id, User first, User second) {
        this.dialogPK = new DialogPK();
        this.dialogPK.id = id;
        this.dialogPK.first = first;
        this.dialogPK.second = second;
    }

    public Dialog(Long dialogId, Long firstId, String firstFirstName, String firstLastName,
                  Long secondId, String secondFirstName, String secondLastName) {
        this.dialogPK = new DialogPK(dialogId
                , new User(firstId, firstFirstName, firstLastName)
                , new User(secondId, secondFirstName, secondLastName));

    }

    @EmbeddedId
    @JsonProperty
    private Dialog.DialogPK dialogPK;

    @Embeddable
    @ToString @NoArgsConstructor @AllArgsConstructor @Getter @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DialogPK implements Serializable {

        @Column
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
    }
}





































//@Entity
//@Table(name = "dialog")
//public class Dialog implements Serializable{
//
//    public Dialog() {
//    }
//
//    public Dialog(Long id) {
//        this.id = id;
//    }
//
//    public Dialog(User first, User second) {
//        this.firstUser = first;
//        this.secondUser = second;
//    }
//
//    public Dialog(Long id, Long userId, String userFirstName, String userLastName) {
//        this.id = id;
//        this.secondUser = new User();
//        this.secondUser.setId(userId);
//        this.secondUser.setFirstName(userFirstName);
//        this.secondUser.setLastName(userLastName);
//    }
//
//    public Dialog(Long id, User first, User second) {
//        this.firstUser = first;
//        this.secondUser = second;
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public User getFirstUser() {
//        return firstUser;
//    }
//
//    public void setFirstUser(User firstUser) {
//        this.firstUser = firstUser;
//    }
//
//    public User getSecondUser() {
//        return secondUser;
//    }
//
//    public void setSecondUser(User secondUser) {
//        this.secondUser = secondUser;
//    }
//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("\nDialog\n{\n");
//        sb.append("id=").append(id);
//        sb.append(",\n firstUser=").append(firstUser);
//        sb.append(",\n secondUser=").append(secondUser);
//        sb.append("\n}");
//        return sb.toString();
//    }
//
////    @Id
//    @Column(name = "id")
////    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_dialog")
////    @SequenceGenerator(name = "seq_dialog", sequenceName = "seq_dialog_id", allocationSize = 1)
//    private Long id;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "first")
//    private User firstUser;
//
//    @ManyToOne
//    @JoinColumn(name = "second")
//    private User secondUser;
//
//}
