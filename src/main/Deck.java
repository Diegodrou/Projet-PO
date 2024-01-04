package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import main.strctCarte.Pioche;

public class Deck {
    private List<Carte> cartes;
    private List<Carte> copieCartes;
    private int nb_util = 0;
    private int nb_util_max;

    public Deck() {
        this.cartes = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            ajouteCarte(new Frappe());
        }
        for (int i = 0; i < 4; i++) {
            ajouteCarte(new Defense());
        }
        ajouteCarte(new Heurt());

        this.copieCartes = new ArrayList<>(11);

        this.copieCartes.addAll(cartes);

        this.nb_util_max = cartes.size();

    }

    public void ajouteCarte(Carte carte) {
        cartes.add(carte);
    }

    public Carte getCarteAleatoire() {
        Random r = new Random();
        int nbrandom = r.nextInt(copieCartes.size());
        Carte carte = copieCartes.get(nbrandom);
        copieCartes.remove(nbrandom);
        nb_util++;
        if (nb_util == nb_util_max) {
            copieCartes.clear();
            copieCartes.addAll(cartes);
        }

        return carte;
    }

}
