package evolution.service.security;


import evolution.dao.UserDao;
import evolution.model.User;
import evolution.service.builder.UserBuilderService;
import evolution.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Admin on 01.03.2017.
 */
@Service
public class UserDetailsServiceImpl
        implements UserDetailsService {

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user;

        if (s.equals("defaultUser@evolution.com") )
            user = userBuilderService.getDefaultUser();
        else if (s.equals("defaultAdmin@evolution.com"))
            user = userBuilderService.getDefaultAdmin();
        else {
            try {
                user = userDao.findByLogin(s);
            } catch (NoResultException e) {
                throw new UsernameNotFoundException("user " + s + " not found");
            }
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + userRoleService.getRole(user.getRoleId())));

        CustomUser customUser = new CustomUser(
                user.getLogin(),
                user.getPassword(),
//                user.getFirstName(),
//                user.getLastName(),
                true,
                true,
                true,
                true,
                grantedAuthorities,
                user.getId()
        );
        return customUser;
    }

    public class CustomUser extends org.springframework.security.core.userdetails.User {

        public CustomUser(
                String username, String password,
//                String firstName, String lastName,
                boolean enabled, boolean accountNonExpired,
                boolean credentialsNonExpired, boolean accountNonLocked,
                Collection<? extends GrantedAuthority> authorities,
                long id) {
            super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
            this.id = id;
//            this.firstName = firstName;
//            this.lastName = lastName;
        }

        public long getId() {
            return id;
        }

//        public String getFirstName() {
//            return firstName;
//        }
//
//        public String getLastName() {
//            return lastName;
//        }

        private final long id;
//        private final String firstName;
//        private final String lastName;
    }

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserBuilderService userBuilderService;
}
