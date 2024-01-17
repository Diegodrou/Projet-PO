package main.actions.mouvement;

import java.io.File;

import main.Entite;

public class Gronder extends Mouvement {

    public Gronder() {
        super("HerosEtMonstre", 0, 6,
                "pictures" + File.separator + "intentions" + File.separator + "Intent_-_Buff_-_Defend.png");
        // TODO Auto-generated constructor stub
    }

    @Override
    public void effetDeMouvement(Entite cible, Entite thisEntite) {
        effetBlockage(thisEntite);
        thisEntite.force.ajoutePointStatut(3);

    }

}
