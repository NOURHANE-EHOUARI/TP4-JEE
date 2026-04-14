package web;

import dao.Produit;
import services.ProduitMetier;
import services.ProduitMetierImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class UpdateProduitServlet extends HttpServlet {

    private static final ProduitMetier metier = ProduitMetierImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long   id          = Long.parseLong(req.getParameter("idProduit"));
        String nom         = req.getParameter("nom");
        String description = req.getParameter("description");
        Double prix        = Double.parseDouble(req.getParameter("prix"));

        Produit p = new Produit();
        p.setIdProduit(id);
        p.setNom(nom);
        p.setDescription(description);
        p.setPrix(prix);

        metier.updateProduit(p);
        resp.sendRedirect("listProduits");
    }
}