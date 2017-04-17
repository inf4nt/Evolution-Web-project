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

    public List<User> search(String string, Long id) {
        if (string.matches("^[a-zA-Z]+$")) {
            return userDao.searchByFistOrLastName(string, id);
        }
        if (string.matches("^[a-zA-Z]+\\s[a-zA-Z]+$")) {
            String regex[] = string.split(" ");
            return userDao.searchByFistNameLastName(regex[0], regex[1], id);
        }
        throw new NoResultException();
    }

    @Autowired
    private UserDao userDao;
}
