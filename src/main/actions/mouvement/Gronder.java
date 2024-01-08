package main.actions.mouvement;

import main.Entite;

public class Gronder extends Mouvement {

    public Gronder() {
        super("HerosEtMonstre", 0, 6);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void effetDeMouvement(Entite cible, Entite thisEntite) {
        effetBlockage(thisEntite);
        thisEntite.force.ajoutePointStatut(3);

    }

}
