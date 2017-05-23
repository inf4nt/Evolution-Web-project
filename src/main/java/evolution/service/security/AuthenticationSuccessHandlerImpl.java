package evolution.service.security;



import evolution.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Admin on 02.03.2017.
 */
@Service
public class AuthenticationSuccessHandlerImpl
            implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest httpServletRequest
            , HttpServletResponse httpServletResponse
            , Authentication authentication) throws IOException, ServletException {


        HttpSession httpSession = httpServletRequest.getSession();
        UserDetailsServiceImpl.CustomUser customUser = (UserDetailsServiceImpl.CustomUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        httpSession.setAttribute("authorities", authentication.getAuthorities());

        httpSession.setAttribute("authUser", customUser.getUser());

        httpServletResponse.setStatus(HttpServletResponse.SC_OK);


        httpServletResponse.sendRedirect("/user/id" + customUser.getUser().getId());
//        httpServletResponse.sendRedirect("/user/id/" + customUser.getUser().getId());
    }
}
