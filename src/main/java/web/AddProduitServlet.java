package web;

import dao.Produit;
import services.ProduitMetier;
import services.ProduitMetierImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AddProduitServlet extends HttpServlet {

    private static final ProduitMetier metier = ProduitMetierImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String nom         = req.getParameter("nom");
        String description = req.getParameter("description");
        Double prix        = Double.parseDouble(req.getParameter("prix"));

        Produit p = new Produit(nom, description, prix);
        metier.addProduit(p);

        resp.sendRedirect("listProduits");
    }
}