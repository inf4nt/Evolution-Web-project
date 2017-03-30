package evolution.service;

import evolution.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Admin on 30.03.2017.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = new User();
        user.setId(1l);
        user.setPassword("user");
        user.setName("user");
        user.setRole("user");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()));

        CustomUser customUser = new CustomUser(
                user.getName(),
                user.getPassword(),
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
                boolean enabled, boolean accountNonExpired,
                boolean credentialsNonExpired, boolean accountNonLocked,
                Collection<? extends GrantedAuthority> authorities,
                long id) {
            super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
            this.id = id;
        }

        public long getId() {
            return id;
        }

        private final long id;
    }
}
