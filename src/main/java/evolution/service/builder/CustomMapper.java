package evolution.service.builder;

import evolution.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 02.04.2017.
 */
@Service
public class CustomMapper {

    public User userForFriends(Object object){
        User user = new User();
        Object rows[] = (Object[]) object;
        user.setId(Long.parseLong(rows[0].toString()));
        user.setFirstName(rows[1].toString());
        user.setLastName(rows[2].toString());
        try {
//            user.setFriendStatus(rows[3].toString());
        } catch (ArrayIndexOutOfBoundsException e){}
        return user;
    }

    public List<User> listUserForFriends (List list) {
        System.out.println(list.size());
        List result = new ArrayList();
        for (Object a: list)
            result.add(userForFriends(a));
        return result;
    }




}
