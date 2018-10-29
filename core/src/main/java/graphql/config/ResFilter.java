package graphql.config;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/graphql")
public class ResFilter implements Filter {

    @Autowired
    private RequestSession session;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        chain.doFilter(request, response);

        // Resolve custom post headers
        if(session != null && session.getHeaders() != null) {
            session.getHeaders().forEach(httpServletResponse::setHeader);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // ...
    }

    @Override
    public void destroy() {
        // ...
    }
}
