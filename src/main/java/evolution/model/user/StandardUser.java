package evolution.model.user;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 24.06.2017.
 */
@Entity
@Table(name = "user_data")
@NoArgsConstructor @AllArgsConstructor @ToString @Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardUser implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    @SequenceGenerator(name = "seq_user", sequenceName = "seq_user_data_id", allocationSize = 1)
    @JsonProperty(value = "userId")
    private Long id;

    @JsonProperty
    @Column(name = "first_name")
    private String firstName;

    @JsonProperty
    @Column(name = "last_name")
    private String lastName;

    public StandardUser(Long id) {
        this.id = id;
    }
}
