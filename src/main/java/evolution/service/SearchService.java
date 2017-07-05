package evolution.service;

import evolution.model.user.StandardUser;
import evolution.model.user.User;
import evolution.repository.StandardUserRepository;
import evolution.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Admin on 17.04.2017.
 */
@Service
public class SearchService {

    @Autowired
    private StandardUserRepository standardUserRepository;

    public List<StandardUser> searchUser(String string, int page, int size) {
        if (string.matches("^[a-zA-Z]+$")) {
            return standardUserRepository.findUserByFirstOrLastName(string, new PageRequest(page, size));
        }
        if (string.matches("^[a-zA-Z]+\\s[a-zA-Z]+$")) {
            String regex[] = string.split(" ");
            return standardUserRepository.findUserByFirstLastName(regex[0], regex[1], new PageRequest(page, size));
        }
        throw new NoResultException();
    }
}
