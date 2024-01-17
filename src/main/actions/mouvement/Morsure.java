package main.actions.mouvement;

import java.io.File;

import main.Entite;

public class Morsure extends Mouvement {

    public Morsure() {
        super("Heros", 11, 0,
                "pictures" + File.separator + "intentions" + File.separator + "Intent_-_Aggressive_-_Sword.png");
    }

    @Override
    public void effetDeMouvement(Entite cible, Entite thisEntite) {
        effetDegats(cible, thisEntite, new int[0]);
    }

}
