package main.actions.mouvement;

import java.io.File;

import main.Entite;

public class Charge extends Mouvement {

    public Charge(int nb_degats, int nb_block) {
        super("Heros", nb_degats, nb_block,
                "pictures" + File.separator + "intentions" + File.separator + "Attack-Defend-Sword.png");
    }

    @Override
    public void effetDeMouvement(Entite cible, Entite thisEntite) {
        effetDegats(cible, thisEntite, new int[0]);
        effetBlockage(thisEntite);
    }

}
