package monstre;

import main.Entite;
import main.Heros;
import main.actions.mouvement.Mouvement;

public abstract class Monstre extends Entite {

    private Mouvement[] pattern;
    private int mouvementCourant;
    private String nomMonstre;
    private String pathImageMonstre;
    private int longeurImage;
    private int largeurImage;
    private Mouvement intention;

    public Monstre(String nomMonstre, int pvMax, Mouvement[] pattern, String pathImage, int largeur, int longeur) {
        super(pvMax);
        mouvementCourant = 0;
        this.nomMonstre = nomMonstre;
        this.pattern = pattern;
        pathImageMonstre = pathImage;
        longeurImage = longeur;
        largeurImage = largeur;
        intention = this.pattern[mouvementCourant];

    }

    public void attaquer(Heros heros) {
        pattern[mouvementCourant].effetDeMouvement(heros, this);
        mouvementCourant++;
        if (mouvementCourant == pattern.length) {
            mouvementCourant = 0;
        }
        intention = pattern[mouvementCourant];

    }

    public String getPathImageMonstre() {
        return pathImageMonstre;
    }

    public int getLongeurImage() {
        return longeurImage;
    }

    public int getLargeurImage() {
        return largeurImage;
    }

    public Mouvement getIntention() {
        return intention;
    }

    @Override
    public String toString() {
        return nomMonstre + " : " + super.toString();
    }

}
