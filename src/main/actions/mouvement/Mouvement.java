package main.actions.mouvement;

import main.Entite;
import main.actions.ActionEntite;

public abstract class Mouvement extends ActionEntite {

    public Mouvement(String typeDentiteApplicable, int nb_degats, int nb_block) {
        super(typeDentiteApplicable, nb_degats, nb_block);
        // TODO Auto-generated constructor stub
    }

    public abstract void effetDeMouvement(Entite cible, Entite thisEntite);

    protected int getNb_degats() {
        return nb_degats;
    }

    protected int getNb_block() {
        return nb_block;
    }

    @Override
    public void effetDeAction(Entite cible, Entite thisEntite) {
        effetDeMouvement(cible, thisEntite);
    }

}
