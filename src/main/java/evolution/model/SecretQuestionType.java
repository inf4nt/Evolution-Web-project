package evolution.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 09.03.2017.
 */
@Entity
@Table(name = "secret_question_type")
@ToString
@NoArgsConstructor
@Getter @Setter
public class SecretQuestionType {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "sqt")
    @SequenceGenerator(name = "sqt", sequenceName = "seq_secret_question_type_id", allocationSize = 1)
    @JsonProperty
    private Long id;
    @Column(unique = true, nullable = false)
    @JsonProperty
    private String name;
}
