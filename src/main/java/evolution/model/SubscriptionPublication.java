package evolution.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Infant on 05.07.2017.
 */
@Entity
@Table(name = "subscription_publication")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class SubscriptionPublication {

    @JsonProperty
    @Id
    @Column
    private String username;

    @JsonProperty
    @Column(name = "category_id")
    private Long category;
}
