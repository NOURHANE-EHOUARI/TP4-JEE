package web;

import dao.Produit;
import services.ProduitMetier;
import services.ProduitMetierImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListProduitServlet extends HttpServlet {

    private static final ProduitMetier metier = ProduitMetierImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("idProduit");
        List<Produit> liste;

        if (idParam != null && !idParam.isEmpty()) {
            Produit p = metier.getProduitById(Long.parseLong(idParam));
            liste = new ArrayList<>();
            if (p != null) liste.add(p);
        } else {
            liste = metier.getAllProduits();
        }

        req.setAttribute("listeProduits", liste);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}