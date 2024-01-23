package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.actions.carte.Carte;
import main.actions.carte.Defense;
import main.actions.carte.Frappe;
import main.actions.carte.Heurt;

public class Deck {

    /**
     * Liste de carte qui contient toutes les cartes du deck du joueur.
     */
    private List<Carte> cartes;

    /**
     * Copie de la liste qui contient les cartes du deck que on manipule pour
     * remplir la pioche
     */
    private List<Carte> copieCartes;

    /**
     * Nombre de carte qui on été enleve de copieCartes
     */
    private int nb_util = 0;

    /**
     * Nombres de cartes maximal qui peuve etre enlevé de copieCartes
     */
    private int nb_util_max;

    /**
     * Deck du joueur initallement contient 5 carte Frappe, 4 carte Defense et 1
     * carte Heurt
     */
    public Deck() {
        cartes = new ArrayList<>();
        nb_util = 0;
        nb_util_max = 0;

        for (int i = 0; i < 5; i++) {
            ajouteCarte(new Frappe());
        }
        for (int i = 0; i < 4; i++) {
            ajouteCarte(new Defense());
        }
        ajouteCarte(new Heurt());

        copieCartes = new ArrayList<>(11);

        copieCartes.addAll(cartes);

    }

    /**
     * Fonction qui ser a ajouter un carte au deck du joueur
     * 
     * @param carte un objet de type Carte
     */
    public void ajouteCarte(Carte carte) {
        cartes.add(carte);
        copieCartes = new ArrayList<>();
        copieCartes.addAll(cartes);
        nb_util_max++;
    }

    /**
     * @return Renvoie une carte aleatoirement du Deck(utilisant la liste
     *         copieCartes)
     */
    public Carte getCarteAleatoire() {
        Random r = new Random();
        int nbrandom = r.nextInt(copieCartes.size());
        Carte carte = copieCartes.get(nbrandom);
        copieCartes.remove(nbrandom);
        nb_util++;
        if (nb_util == nb_util_max) {
            copieCartes.clear();
            copieCartes.addAll(cartes);
            nb_util = 0;
        }

        return carte;
    }

    public int nb_carte() {
        return cartes.size();
    }

}
