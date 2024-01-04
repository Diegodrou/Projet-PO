package main.strctCarte;

import main.Carte;

public class Pioche extends StackDeCarte {

    private int taillePioche = 10;

    public Pioche() {
        super();
    }

    public Carte prendreCarte() {
        return cartes.pop();

    }

    public void prendCartesDefausse(Defausse defausse) {
        defausse.melangeCartes();
        cartes = defausse.getCartes();
    }

    public int getTaillePioche() {
        return taillePioche;
    }

}
