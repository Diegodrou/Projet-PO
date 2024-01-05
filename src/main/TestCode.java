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
        for (int i = 0; i < 10; i++) {
            salle.initialiserPioches(deck, pioche);
            System.out.println(pioche.nb_carte());
            System.out.println("----------------------------");
        }
        return pioche;

    }

    private static Deck testCreationDeck() {
        System.out.println("Test creation de Deck:");
        return new Deck();
    }

}
