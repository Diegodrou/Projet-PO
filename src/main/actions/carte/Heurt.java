package main.actions.carte;

import main.Entite;

public class Heurt extends Carte {

    public Heurt() {
        super("Monstre", 2, 8, 0);
        setNomCarte("Heurt");
    }

    @Override
    public void effetDeCarte(Entite cible, Entite thisEntite) {
        effetDegats(cible, thisEntite, new int[] { 2, 1 });
        cible.vulnerabilite.ajoutePointStatut(2);
    }

}
