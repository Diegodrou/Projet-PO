package main.actions.carte;

import main.Entite;

public class Gourdin extends Carte {

    public Gourdin() {
        super("Monstre", 3, 32, 0);
        setNomCarte("Gourdin");
    }

    @Override
    public void effetDeCarte(Entite cible, Entite thisEntite) {
        effetDegats(cible, thisEntite, new int[] { 2, 1 });
    }

}
