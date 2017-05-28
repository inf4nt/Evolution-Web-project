package evolution.model.userToken;


import evolution.model.user.User;
import lombok.*;

/**
 * Created by Admin on 24.05.2017.
 */
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
@ToString
public class UserToken {
    private String token;
    private User user;
}
