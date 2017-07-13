package evolution.service.security;


import evolution.dao.UserDaoService;
import evolution.model.user.User;
import lombok.Getter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserDaoService userDaoService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user;

        try {
            user = userDaoService.findUserByUsername(s);
        } catch (NoResultException e) {
            LOGGER.info(e + "\nuser y username = " + s + ", not found");
            throw new UsernameNotFoundException("user " + s + " not found");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        CustomUser customUser = new CustomUser(
                user.getLogin(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                grantedAuthorities,
                user
        );
        return customUser;
    }

    @Getter @ToString
    public class CustomUser extends org.springframework.security.core.userdetails.User {

        public CustomUser(
                String username, String password,
                boolean enabled, boolean accountNonExpired,
                boolean credentialsNonExpired, boolean accountNonLocked,
                Collection<? extends GrantedAuthority> authorities,
                User user) {
            super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
            this.user = user;
        }
        private final User user;
    }
}
