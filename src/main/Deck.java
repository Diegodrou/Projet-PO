package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.actions.carte.Carte;
import main.actions.carte.Defense;
import main.actions.carte.Frappe;
import main.actions.carte.Heurt;

public class Deck {
    private List<Carte> cartes;
    private List<Carte> copieCartes;
    private int nb_util = 0;
    private int nb_util_max;

    public Deck() {
        cartes = new ArrayList<>();
        nb_util = 0;
        nb_util_max = 0;

        for (int i = 0; i < 5; i++) {
            ajouteCarte(new Frappe());
        }
        for (int i = 0; i < 4; i++) {
            ajouteCarte(new Defense());
        }
        ajouteCarte(new Heurt());

        copieCartes = new ArrayList<>(11);

        copieCartes.addAll(cartes);

    }

    public void ajouteCarte(Carte carte) {
        cartes.add(carte);
        nb_util_max++;
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
            nb_util = 0;
        }

        return carte;
    }

    public int nb_carte() {
        return cartes.size();
    }

}
