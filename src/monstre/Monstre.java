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

    /**
     * Constructeur du type Monstre:
     * 
     * @param nomMonstre une chaine du charactere qui indique le nom du monstre
     * 
     * @param pvMax      un eniter qui represente les points de vie maximal du
     *                   monstre
     * 
     * @param pattern    un tableau de Mouvements qui represente le pattern du
     *                   monstre
     * @param pathImage  une chaine de charactere qui contient où se trouve l'image
     *                   du monstre.
     * 
     * @param largeur    un eniter qui represente la largeur de l'image
     * @param longeur    un entier qui represente la longeur de l'image
     */
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

    /**
     * Fonction qui permet d'attaquer l'heros
     * 
     * @param heros un objet de type heros qui represente le joueur.
     */
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
