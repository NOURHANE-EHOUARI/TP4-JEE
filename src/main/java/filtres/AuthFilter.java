package filtres;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest  req        = (HttpServletRequest) request;
        HttpServletResponse resp       = (HttpServletResponse) response;
        String              uri        = req.getRequestURI();
        String              contextPath = req.getContextPath();

        boolean isPublic = uri.equals(contextPath + "/login")
                        || uri.contains("/login")
                        || uri.contains("/logout")
                        || uri.endsWith(".jsp")
                        || uri.endsWith(".css")
                        || uri.endsWith(".js");

        if (isPublic) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session    = req.getSession(false);
        boolean     isConnecte = (session != null && session.getAttribute("utilisateur") != null);

        if (isConnecte) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(contextPath + "/login");
        }
    }

    @Override
    public void destroy() {}
}