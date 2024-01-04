package main;

public class Heros extends Entite {
    private int energie;

    private int energieMax;

    private Carte[] main;

    public static final int MAX_NB_CARTES_MAIN = 5;

    private int carteChoisie = -1;

    public Heros(int pvMax, int energieMax) {
        super(pvMax);
        this.energieMax = energieMax;
        this.energie = energieMax;
        main = new Carte[MAX_NB_CARTES_MAIN];
    }

    public void utiliserCarte(Monstre cible) {
        main[carteChoisie].effetDeCarte(cible);
    }

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
            }
        }
    }

    /**
     * Supprime la carte specifié de la main
     * 
     * @param index un entier représentat l'indice d'une carte dans la main(un
     *              tableau)
     */
    private void supprimeCarteMain(int index) {

    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Heros: " + super.toString() + "," + "energie: " + getEnergie() + "/" + getEnergieMax();
    }

}
