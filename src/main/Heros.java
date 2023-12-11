package main;

public class Heros extends Entite {
    private int energie;
    

    private int energieMax;
    

    private Carte[] main;
    

    private int carteChoisie = -1;
    



    public Heros(int pvMax, int energieMax) {
        super(pvMax);
        this.energieMax = energieMax;
        this.energie = energieMax;
        main = new Carte[2];
        main[0] = new Frappe();
        main[1] = new Defense();

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

    public Carte getCarteDeLaMain(int index){
        return main[index];
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Heros: " + super.toString() + "," +"energie: " + getEnergie() + "/" + getEnergieMax();
    }

}
