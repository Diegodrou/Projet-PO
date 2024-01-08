package monstre;

import main.Entite;
import main.Heros;
import main.actions.mouvement.Mouvement;

public abstract class Monstre extends Entite {

    private Mouvement[] pattern;
    private int mouvementCourant;
    private String nomMonstre;
    private String pathImageMonstre;

    public Monstre(String nomMonstre, int pvMax, Mouvement[] pattern, String pathImage) {
        super(pvMax);
        mouvementCourant = 0;
        this.nomMonstre = nomMonstre;
        this.pattern = pattern;
        pathImageMonstre = pathImage;

    }

    public void attaquer(Heros heros) {
        pattern[mouvementCourant].effetDeMouvement(heros, this);
        mouvementCourant++;
        if (mouvementCourant == pattern.length) {
            mouvementCourant = 0;
        }

    }

    public String getPathImageMonstre() {
        return pathImageMonstre;
    }

    @Override
    public String toString() {
        return nomMonstre + " : " + super.toString();
    }

}
