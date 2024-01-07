package main.actions.mouvement;

import main.Entite;

public class Charge extends Mouvement {

    public Charge() {
        super("Heros", 5, 0);
    }

    @Override
    public void effetDeMouvement(Entite cible) {
        effetDegats(cible);
    }

}
