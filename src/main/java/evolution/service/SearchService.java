package evolution.service;

import evolution.dao.UserDao;
import evolution.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Admin on 17.04.2017.
 */
@Service
public class SearchService {

    public List<User> search(String string) {
        if (string.matches("^[a-zA-Z]+$")) {
            return userDao.findUserByFirstOrLastName(string);
        }
        if (string.matches("^[a-zA-Z]+\\s[a-zA-Z]+$")) {
            String regex[] = string.split(" ");
            return userDao.findUserByFirstLastName(regex[0], regex[1]);
        }
        throw new NoResultException();
    }

    public List<User> searchUser(String string, int limit) {
        if (string.matches("^[a-zA-Z]+$")) {
            return userDao.findUserByFirstOrLastName(string, limit);
        }
        if (string.matches("^[a-zA-Z]+\\s[a-zA-Z]+$")) {
            String regex[] = string.split(" ");
            return userDao.findUserByFirstLastName(regex[0], regex[1], limit);
        }
        throw new NoResultException();
    }

    public List<User> searchUser(String string) {
        if (string.matches("^[a-zA-Z]+$")) {
            return userDao.findUserByFirstOrLastName(string);
        }
        if (string.matches("^[a-zA-Z]+\\s[a-zA-Z]+$")) {
            String regex[] = string.split(" ");
            return userDao.findUserByFirstLastName(regex[0], regex[1]);
        }
        throw new NoResultException();
    }


    @Autowired
    private UserDao userDao;
}
