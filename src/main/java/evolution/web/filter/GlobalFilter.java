package evolution.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Admin on 10.06.2017.
 */


public class GlobalFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

//        LOGGER.info(this.getClass().getSimpleName());

//        try {
//            filterChain.doFilter(request, response);
//        } catch (Exception e) {
//            if (request.isUserInRole("ROLE_ADMIN")) {
//                LOGGER.info("Admin catch exception! Return stack trace");
//                filterChain.doFilter(request, response);
//            } else {
//                LOGGER.info("Not admin catch exception. Redirect home page");
//                request.getRequestDispatcher("/view/error/500.jsp").forward(request, response);
//            }
//        }

        filterChain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
