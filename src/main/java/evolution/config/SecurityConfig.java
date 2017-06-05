package evolution.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


/**
 * Created by Admin on 13.12.2016.
 */

@Configuration
@ComponentScan(basePackages = "evolution")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService myUserDetailsService;
    private static final String LOGIN_PAGE = "/welcome";

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable();

        httpSecurity.authorizeRequests()
                .antMatchers("/", "/logout", "/user/**", LOGIN_PAGE)
                .permitAll();

        httpSecurity
                .authorizeRequests()
                .antMatchers("/admin/**")
                .access("hasRole('ROLE_ADMIN')");

        httpSecurity.authorizeRequests().and().formLogin()//
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage(LOGIN_PAGE)
                .successHandler(myAuthenticationSuccessHandler)
                .failureUrl("/welcome?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl(LOGIN_PAGE).deleteCookies("JSESSIONID")
                .and().rememberMe().tokenValiditySeconds(172800);
    }
}

