package main.actions.carte;

import main.Entite;

public class Frappe extends Carte {

    public Frappe() {
        super("Monstre", 1, 6, 0);
        setNomCarte("Frappe");
    }

    @Override
    public void effetDeCarte(Entite cible, Entite thisEntite) {
        effetDegats(cible);
    }

}
