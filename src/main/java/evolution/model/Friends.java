package evolution.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.common.FriendStatusEnum;
import lombok.*;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 15.04.2017.
 */

@Entity
@Table(name = "friends")
@ToString @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Friends implements Serializable{

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonProperty
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    @JsonProperty
    private User friendId;

    @Column(name = "status")
    @JsonProperty
    private Long status;
}
