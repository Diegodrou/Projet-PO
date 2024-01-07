package main.strctCarte;

import main.Carte;

public class Pioche extends StackDeCarte {

    public Pioche() {
        super();
    }

    public Carte prendreCarte() {
        return cartes.pop();

    }

    public void prendCartesDefausse(Defausse defausse) {
        defausse.melangeCartes();
        cartes = defausse.getCartes();
        defausse.viderDefausse();
    }

}
