package dao;

import java.util.ArrayList;
import java.util.List;

public class ProduitDAOImpl implements ProduitDAO {

    private static List<Produit> produits = new ArrayList<>();
    private static Long compteur = 1L;

    private static ProduitDAOImpl instance = new ProduitDAOImpl();

    private ProduitDAOImpl() {
        Produit p1 = new Produit("Ordinateur", "PC portable Dell", 8500.0);
        p1.setIdProduit(compteur++);
        produits.add(p1);

        Produit p2 = new Produit("Souris", "Souris sans fil", 150.0);
        p2.setIdProduit(compteur++);
        produits.add(p2);
    }

    public static ProduitDAOImpl getInstance() {
        return instance;
    }

    @Override
    public void add(Produit p) {
        p.setIdProduit(compteur++);
        produits.add(p);
    }

    @Override
    public void delete(Long id) {
        produits.removeIf(p -> p.getIdProduit().equals(id));
    }

    @Override
    public void update(Produit updated) {
        for (int i = 0; i < produits.size(); i++) {
            if (produits.get(i).getIdProduit().equals(updated.getIdProduit())) {
                produits.set(i, updated);
                break;
            }
        }
    }

    @Override
    public Produit getById(Long id) {
        for (Produit p : produits) {
            if (p.getIdProduit().equals(id)) return p;
        }
        return null;
    }

    @Override
    public List<Produit> getAll() {
        return produits;
    }
}