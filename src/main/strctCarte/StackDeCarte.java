package main.strctCarte;

import java.util.Stack;

import main.Carte;

public abstract class StackDeCarte {
    protected Stack<Carte> cartes;

    public StackDeCarte() {
        cartes = new Stack<>();
    }

    public void ajouteCarte(Carte carte) {
        cartes.add(carte);
    }

    public boolean estVide() {
        return cartes.empty();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        String txt = "Contenu du Stack: \n";
        for (Carte carte : cartes) {
            txt += carte.toString() + "\n";
        }
        return txt;
    }

}
