package main.strctCarte;

import java.util.Stack;

import main.Carte;

public class Pioche extends StackDeCarte {

    public Pioche() {
        super();
    }

    public Carte prendreCarte() {
        return cartes.pop();

    }

    public void setCartes(Stack<Carte> defausse) {
        cartes = defausse;
    }

}
