package evolution.model;

import javax.persistence.*;

/**
 * Created by Admin on 09.03.2017.
 */
@Entity
@Table(name = "user_role")
public class UserRole {

    public UserRole() {
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
        final StringBuilder sb = new StringBuilder("UserRoleEvolution{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id")
    @SequenceGenerator(name = "seq_id", sequenceName = "seq_uer_id", allocationSize = 1)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
}
