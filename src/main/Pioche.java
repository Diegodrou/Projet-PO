package main;

import java.util.Stack;

public class Pioche {
    private Stack<Carte> cartes;

    public Pioche() {
        cartes = new Stack<>();
    }

    public Carte prendreCarte() {
        return cartes.pop();
    }

}
