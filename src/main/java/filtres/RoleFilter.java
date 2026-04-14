package filtres;

import dao.Utilisateur;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class RoleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest  req         = (HttpServletRequest) request;
        HttpServletResponse resp        = (HttpServletResponse) response;
        String              uri         = req.getRequestURI();
        String              contextPath = req.getContextPath();
        boolean isPublic = uri.contains("/login")
                        || uri.contains("/logout")
                        || uri.endsWith(".jsp")
                        || uri.endsWith(".css")
                        || uri.endsWith(".js");

        if (isPublic) {
            chain.doFilter(request, response);
            return;
        }
        HttpSession session = req.getSession(false);
        Utilisateur user    = (session != null)
                              ? (Utilisateur) session.getAttribute("utilisateur")
                              : null;

        if (user == null) {
            resp.sendRedirect(contextPath + "/login");
            return;
        }
        boolean isAdminOnly = uri.contains("/addProduit")
                           || uri.contains("/deleteProduit")
                           || uri.contains("/editProduit")
                           || uri.contains("/updateProduit");

        if (isAdminOnly && !"ADMIN".equals(user.getRole())) {
            resp.sendRedirect(contextPath + "/access-denied.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}
}