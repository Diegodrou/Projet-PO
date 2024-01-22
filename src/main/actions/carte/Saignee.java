package main.actions.carte;

import main.Entite;
import main.Heros;

public class Saignee extends Carte {

    public Saignee() {
        super("Heros", 0, 0, 0);
        setNomCarte("Saignée");
    }

    @Override
    public void effetDeCarte(Entite cible, Entite thisEntite) {
        Heros h = (Heros) cible;
        h.setPv(h.getPv() - 3);
        h.setEnergie(h.getEnergie() + 2);
    }

}
