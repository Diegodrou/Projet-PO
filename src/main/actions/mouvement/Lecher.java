package main.actions.mouvement;

import java.io.File;

import main.Entite;

public class Lecher extends Mouvement {

    public Lecher() {
        super("Heros", 0, 0, "pictures" + File.separator + "intentions" + File.separator + "Debuff.png");
        // TODO Auto-generated constructor stub
    }

    @Override
    public void effetDeMouvement(Entite cible, Entite thisEntite) {
        cible.faible.ajoutePointStatut(1);
    }

}
