package evolution.service;

import evolution.dao.UserDao;
import evolution.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Admin on 17.04.2017.
 */
@Service
public class SearchService {

    @Autowired
    private UserDao userDao;

    public List<User> searchUser(String string, int limit, int offset) {
        if (string.matches("^[a-zA-Z]+$")) {
            return userDao.findUserByFirstOrLastName(string, limit, offset);
        }
        if (string.matches("^[a-zA-Z]+\\s[a-zA-Z]+$")) {
            String regex[] = string.split(" ");
            return userDao.findUserByFirstLastName(regex[0], regex[1], limit, offset);
        }
        throw new NoResultException();
    }
}
