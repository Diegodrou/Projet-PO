package main.actions.carte;

import main.Entite;

public class Heurt extends Carte {

    public Heurt() {
        super("Monstre", 2, 8, 0);
        setNomCarte("Heurt");
    }

    @Override
    public void effetDeCarte(Entite cible) {
        effetDegats(cible);
        cible.vulnerabilite.ajoutePointStatut(2);
    }

}
