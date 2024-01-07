package main.strctCarte;

import java.util.Collections;
import java.util.Stack;

import main.Carte;

public class Defausse extends StackDeCarte {

    public Defausse() {
        super();
    }

    public void melangeCartes() {
        Collections.shuffle(cartes);
    }

    public Stack<Carte> getCartes() {
        Stack<Carte> tmp = new Stack<>();
        tmp.addAll(cartes);
        return tmp;

    }

    public void viderDefausse() {
        cartes.clear();
    }

}
