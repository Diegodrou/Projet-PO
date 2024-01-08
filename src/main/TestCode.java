package main;

import main.actions.carte.Defense;
import main.actions.carte.Frappe;
import main.actions.carte.Heurt;
import main.salle.SalleDeCombat;
import main.strctCarte.Defausse;
import main.strctCarte.Pioche;
import monstre.Monstre;

public class TestCode {

    private static int[] t = new int[5];
    private static int d;

    public static void main(String[] args) {
        testPioche2();
    }

    private static Pioche testPiocheInit() {
        System.out.println("Test initialisation pioche:");
        Deck deck = new Deck();
        Pioche pioche = new Pioche();
        SalleDeCombat salle = new SalleDeCombat(new Monstre[0]);
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

    private static void arrayTest() {
        int a = 1;
        d = 2;
        t[0] = d;
        System.out.println(t[0]);
        d = a;
        System.out.println(d);
        a = 4;
        System.out.println(d);
        System.out.println(t[0]);

    }

    public static void testPioche2() {
        Pioche p = new Pioche();
        Defausse d = new Defausse();
        d.ajouteCarte(new Defense());
        d.ajouteCarte(new Frappe());
        d.ajouteCarte(new Heurt());
        System.out.println(d);
        System.out.println(p);
        p.prendCartesDefausse(d);
        System.out.println("------------------------------------");
        System.out.println(d);
        System.out.println(p);

    }

}
