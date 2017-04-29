package evolution.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Admin on 24.04.2017.
 */
@Entity
@Table(name = "dialog")
public class TestDialog {
        public DialogKey getDialogKey() {
        return dialogKey;
    }

    public void setDialogKey(DialogKey dialogKey) {
        this.dialogKey = dialogKey;
    }

    @EmbeddedId
    private DialogKey dialogKey;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dialog{");
        sb.append("dialogKey=").append(dialogKey);
        sb.append('}');
        return sb.toString();
    }

    @Embeddable
    public static class DialogKey implements Serializable {
        public DialogKey() {
        }

        public DialogKey(Long id) {
            this.id = id;
        }

        public DialogKey(User first, User second) {
            this.firstUser = first;
            this.secondUser = second;
        }

        public DialogKey(Long id, Long userId, String userFirstName, String userLastName) {
            this.id = id;
            this.secondUser = new User();
            this.secondUser.setId(userId);
            this.secondUser.setFirstName(userFirstName);
            this.secondUser.setLastName(userLastName);
        }

        public DialogKey(Long id, User first, User second) {
            this.firstUser = first;
            this.secondUser = second;
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public User getFirstUser() {
            return firstUser;
        }

        public void setFirstUser(User firstUser) {
            this.firstUser = firstUser;
        }

        public User getSecondUser() {
            return secondUser;
        }

        public void setSecondUser(User secondUser) {
            this.secondUser = secondUser;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("\nDialog\n{\n");
            sb.append("id=").append(id);
            sb.append(",\n firstUser=").append(firstUser);
            sb.append(",\n secondUser=").append(secondUser);
            sb.append("\n}");
            return sb.toString();
        }

        @Column(name = "id")
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "first")
        private User firstUser;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "second")
        private User secondUser;
    }

}
