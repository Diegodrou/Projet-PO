package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    private List<Carte> cartes;

    public Deck() {
        this.cartes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ajouteCarte(new Frappe());
        }
        for (int i = 0; i < 4; i++) {
            ajouteCarte(new Defense());
        }
        ajouteCarte(new Heurt());

    }

    public void ajouteCarte(Carte carte) {
        cartes.add(carte);
    }

}
