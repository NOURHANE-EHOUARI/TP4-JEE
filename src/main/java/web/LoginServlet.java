package web;

import dao.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginServlet extends HttpServlet {

    private static final List<Utilisateur> utilisateurs = new ArrayList<>();

    static {
        utilisateurs.add(new Utilisateur(1L, "Nourhane", "Nourhane123", "ADMIN"));
        utilisateurs.add(new Utilisateur(2L, "Nour",  "Nour",  "USER"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String login    = req.getParameter("login");
        String password = req.getParameter("password");

        Utilisateur userTrouve = null;
        for (Utilisateur u : utilisateurs) {
            if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
                userTrouve = u;
                break;
            }
        }

        if (userTrouve != null) {
            HttpSession session = req.getSession();
            session.setAttribute("utilisateur", userTrouve);
            resp.sendRedirect("listProduits");
        } else {
            req.setAttribute("erreur", "Login ou mot de passe incorrect !");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}