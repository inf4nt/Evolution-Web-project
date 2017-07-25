package evolution.bucket;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import evolution.model.user.StandardUser;
import lombok.*;

import javax.persistence.*;

/**
 * Created by Infant on 05.07.2017.
 */
@Entity
@Table(name = "subscription_user")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class SubscriptionUser {

    @JsonProperty
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_subscription_user")
    @SequenceGenerator(name = "seq_subscription_user", sequenceName = "seq_subscription_user_id", allocationSize = 1)
    private Long id;

    @JsonProperty
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private StandardUser user;

    @JsonProperty
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "follower_user_id")
    private StandardUser followerUser;
}
