package main.actions.mouvement;

import main.Entite;

public class Morsure extends Mouvement {

    public Morsure() {
        super("Heros", 11, 0);
    }

    @Override
    public void effetDeMouvement(Entite cible, Entite thisEntite) {
        effetDegats(cible);
    }

}
