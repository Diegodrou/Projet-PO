package main.actions.carte;

import main.Entite;

public class Defense extends Carte {

    public Defense() {
        super("Heros", 1, 0, 5);
        setNomCarte("Defense");
    }

    @Override
    public void effetDeCarte(Entite cible, Entite thisEntite) {
        effetBlockage(cible, new int[] { 3 });

    }

}
