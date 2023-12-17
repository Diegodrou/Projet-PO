package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public Carte getCarteAleatoire() {
        Random r = new Random();
        int nbrandom = r.nextInt(cartes.size());
        return cartes.get(nbrandom);
    }

}
