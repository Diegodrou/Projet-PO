package main;

public class Frappe extends Carte {

    public Frappe() {
        super("Monstre", 1, 6, 0);
        setNomCarte("Frappe");
    }

    @Override
    public void effetDeCarte(Entite cible) {
        effetDegats(cible);
    }

}
