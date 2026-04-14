package web;

import dao.Produit;
import services.ProduitMetier;
import services.ProduitMetierImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class EditProduitServlet extends HttpServlet {

    private static final ProduitMetier metier = ProduitMetierImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long id   = Long.parseLong(req.getParameter("id"));
        Produit p = metier.getProduitById(id);

        req.setAttribute("produitEdit", p);
        req.setAttribute("listeProduits", metier.getAllProduits());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}