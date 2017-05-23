package evolution.model.secretQuestionType;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Admin on 20.05.2017.
 */
@Entity
@Table(name = "secret_question_type")
@ToString @NoArgsConstructor @Getter @Setter
public class SecretQuestionType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqt")
    @SequenceGenerator(name = "sqt", sequenceName = "seq_secret_question_type_id", allocationSize = 1)
    @JsonProperty
    private Long id;
    @Column(unique = true, nullable = false)
    @JsonProperty
    private String name;
}
