package main;

import main.actions.carte.Carte;

public class Heros extends Entite {
    private int energie;

    private int energieMax;

    private Carte[] main;

    public static final int MAX_NB_CARTES_MAIN = 5;

    private int nb_carte_main;

    private int carteChoisie = -1;

    public Heros(int pvMax, int energieMax) {
        super(pvMax);
        this.energieMax = energieMax;
        this.energie = energieMax;
        main = new Carte[MAX_NB_CARTES_MAIN];
        nb_carte_main = 0;
    }

    // public void utiliserCarte(Monstre cible, Defausse defausse) {
    // main[carteChoisie].effetDeCarte(cible);
    // supprimeCarteMain(defausse);
    // }

    public int getEnergie() {
        return energie;
    }

    public void setEnergie(int energie) {
        this.energie = energie;
    }

    public int getCarteChoisie() {
        return carteChoisie;
    }

    public void setCarteChoisie(int carteChoisie) {
        this.carteChoisie = carteChoisie;
    }

    public int getEnergieMax() {
        return energieMax;
    }

    public Carte[] getMain() {
        return main;
    }

    public Carte getCarteDeLaMain(int index) {
        return main[index];
    }

    public int getNb_carte_main() {
        return nb_carte_main;
    }

    /**
     * Ajoute une carte a la main de l'Heros
     * 
     * @param carte un objet de type Carte
     */
    public void ajouteCarteMain(Carte carte) {
        boolean trouv = false;
        for (int i = 0; i < main.length; i++) {
            if (main[i] == null && trouv == false) {
                main[i] = carte;
                trouv = true;
                nb_carte_main++;
            }
        }
    }

    /**
     * Supprime la carte choisi par le joueur de la main
     */
    public void supprimeCarteMain() {
        main[carteChoisie] = null;
        nb_carte_main--;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Heros: " + super.toString() + "," + "energie: " + getEnergie() + "/" + getEnergieMax();
    }

}
