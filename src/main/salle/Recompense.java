package main.salle;

import java.util.List;
import java.util.Random;

import main.actions.carte.Carte;

public class Recompense {

    private List<List<Carte>> cartes;
    // probs[0]:communes; probs[1] = non-communes; probs[2] = rare;
    private double[] probs = { 0.60, 0.30, 0.03 };

    public Recompense(List<List<Carte>> cartes) {
        this.cartes = cartes;
    }

    public Carte getCarte() {
        double p = Math.random();
        System.out.println("p = " + p);
        double probaCumaltive = 0.0;
        for (int i = 0; i < cartes.size(); i++) {
            probaCumaltive += probs[i];
            System.out.println(probaCumaltive);
            if (p <= probaCumaltive) {
                return getRandomCarte(cartes.get(i));
            }
        }

        throw new UnknownError("erreur getRecompense");
    }

    private Carte getRandomCarte(List<Carte> lCarte) {
        Random r = new Random();
        int n = r.nextInt(lCarte.size());
        return lCarte.get(n);
    }
}
