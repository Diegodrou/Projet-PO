package main;

import main.strctCarte.Pioche;

public class TestCode {
    public static void main(String[] args) {

        System.out.println(testCreationDeck());
        System.out.println("----------------------------------------------------");
        System.out.println(testPiocheInit());

    }

    private static Pioche testPiocheInit() {
        System.out.println("Test initialisation pioche:");
        Deck deck = new Deck();
        Pioche pioche = new Pioche();
        SalleDeCombat salle = new SalleDeCombat(2);
        salle.initialiserPioches(deck, pioche);
        return pioche;

    }

    private static Deck testCreationDeck() {
        System.out.println("Test creation de Deck:");
        return new Deck();
    }

}
