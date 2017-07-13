package evolution.dao;

import evolution.common.UserRoleEnum;
import evolution.model.user.StandardUser;
import evolution.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Infant on 14.07.2017.
 */
@Service
public class UserDaoService {

    @Autowired
    private StandardUserRepository standardUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public StandardUser selectIdFirstLastNameStandardUser(Long id) {
        return standardUserRepository.selectIdFirstLastName(id);
    }

    @Transactional
    public List<StandardUser> findStandardUserByFirstLastName(String p1, String p2, Pageable pageable) {
        return standardUserRepository.findUserByFirstLastName(p1, p2, pageable);
    }

    @Transactional
    public List<StandardUser> findStandardUserByFirstOrLastName(String p1, Pageable pageable) {
        return standardUserRepository.findUserByFirstOrLastName(p1, pageable);
    }

    @Transactional
    public List<StandardUser> findStandardUsers(Pageable pageable) {
        return standardUserRepository.findUsers(pageable);
    }

    @Transactional
    public User findUserByUsername(String username) {
        return userRepository.findUserByLogin(username);
    }

    @Transactional
    public List<User> findUserByRoleId(Long roleId) {
        return userRepository.findAllByRole(roleId);
    }

    @Transactional
    public List<User> findUserByRoleName(String roleName) {
        return userRepository.findAllByRole(UserRoleEnum.valueOf(roleName).getId());
    }

    @Transactional
    public List<StandardUser> findAll() {
        return standardUserRepository.findAll();
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Transactional
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }
}
