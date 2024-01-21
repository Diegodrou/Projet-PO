package main.actions.mouvement;

import java.io.File;

import main.Entite;

public class MouvAleatoire extends Mouvement {

    private Mouvement[] mouvements;
    private double[] probs;

    public MouvAleatoire(Mouvement[] mouvs, double[] probs) {
        super("HerosEtMonstre", 0, 0,
                "pictures" + File.separator + "intentions" + File.separator + "Intent_-_Unknown.png");
        mouvements = mouvs;
        this.probs = probs;
    }

    @Override
    public void effetDeMouvement(Entite cible, Entite thisEntite) {
        Mouvement mouvement = mouvAleatoire();
        nb_degats = mouvement.getNb_degats();
        nb_block = mouvement.getNb_block();
        mouvement.effetDeMouvement(cible, thisEntite);
    }

    private Mouvement mouvAleatoire() {
        double p = Math.random();
        double probaCumaltive = 0.0;
        for (int i = 0; i < probs.length; i++) {
            probaCumaltive += probs[i];
            if (p <= probaCumaltive) {
                return mouvements[i];
            }
        }

        throw new UnknownError("erreur mouvAleatoire");
    }

}
