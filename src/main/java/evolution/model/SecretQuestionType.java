package evolution.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 09.03.2017.
 */
@Entity
@Table(name = "secret_question_type")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "SecretQuestionType")
public class SecretQuestionType {

    public SecretQuestionType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SecretQuestionType{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "sqt")
    @SequenceGenerator(name = "sqt", sequenceName = "seq_secret_question_type_id", allocationSize = 1)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
}
