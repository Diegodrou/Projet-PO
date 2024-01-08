package main.salle;

import main.Deck;
import main.Heros;
import main.actions.carte.Carte;
import main.strctCarte.Defausse;
import main.strctCarte.Pioche;
import monstre.Monstre;

public class SalleDeCombat extends Salle {
    private Monstre[] monstres;
    private int cibleDuJoueur;

    private boolean monstersAlive;

    /**
     * Constructeur de la salle de combat(EdB)
     * 
     * @param nb_monstre un entier qui indique le nombre de monstres de la salle
     */
    public SalleDeCombat(Monstre[] monstres) {
        this.monstres = monstres;

        this.monstersAlive = true;
    }

    /**
     * Change la cible du joueur(EdB)
     * 
     * @param cibleDuJoueur un entier qui est un index du tableau qui contient les
     *                      monstres
     */
    public void setCibleDuJoueur(int cibleDuJoueur) {
        this.cibleDuJoueur = cibleDuJoueur;
    }

    @Override
    /**
     * Cette fonction :
     * 1-Identifie si la carte choisi par le joueur est applicable a un monstre ou a
     * l'heros
     * 2-Applique l'effet de la carte a la cible du joueur
     * 3-Met les points de vie des monstre a 0 si ils sont mort
     * 4-Enleve de l'energie au joueur si necessaire
     */
    public void performerActionsJoueur(Heros heros) {
        int monstreChoisie = getCibleDuJoueur();
        int carteChoisie = heros.getCarteChoisie();
        if (carteChoisie >= 0) {
            Carte carte = heros.getMain()[carteChoisie];

            if (carte.getTypeDentiteApplicable() == "Heros") {
                carte.effetDeCarte(heros, heros);
            } else {
                carte.effetDeCarte(monstres[monstreChoisie], heros);
                // met pv du monstre a 0 si negative
                if (!monstres[monstreChoisie].alive()) {
                    monstres[monstreChoisie].setPv(0);
                }
            }

            heros.setEnergie(heros.getEnergie() - carte.getCout());
        }

    }

    @Override
    /**
     * Cette fonctions(EdB) :
     * 1-Regarde et actualise l'etat des monstres
     * 2-Si il y a des montres vivants ils attaquent le joueur
     * 3-si le joueur est mort aprés de l'attaque, elle s'assure de que les points
     * de vie du joueurs soit mis a 0
     * 4-effectue action de fin de tour des monstre
     */
    public void performerActionSalle(Heros heros) {
        // TODO Auto-generated method stub
        checkEtatMonstres();
        if (monstersAlive) {
            attaquerJoueur(heros);
        }
        if (!heros.alive()) {
            heros.setPv(0);
        }

        actionsFinTourMonstres();

    }

    /**
     * Performe l'attaque de chaque monstre de la salle(EdB)
     * 
     * @param heros un objet de type Heros(le joueur)
     */
    private void attaquerJoueur(Heros heros) {
        for (Monstre monstre : monstres) {
            if (monstre.alive()) {
                monstre.attaquer(heros);
            }
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        String txt = super.toString();
        for (Monstre monstre : monstres) {
            txt += monstre.toString() + "; ";
        }

        return txt;
    }

    /**
     * Actualise la variable qui indique si il y a encore des monstres vivant de la
     * salle sont vivant(EdB)
     * 
     */
    private void checkEtatMonstres() {

        int i = 0;

        for (Monstre monstre : monstres) {
            if (!monstre.alive()) {
                i++;
            }
        }
        if (i == monstres.length) {
            monstersAlive = false;
        }

    }

    /**
     * 
     * @return un entier qui represente la cible du joueur dans la salle
     */
    public int getCibleDuJoueur() {
        return cibleDuJoueur;
    }

    /**
     * 
     * @return un boolean qui indique s'il y a des monstres vivants dans salle
     */
    public boolean isMonstersAlive() {
        return monstersAlive;
    }

    /**
     * Met a jour les attributs du joueur et des monstres pour preparer le tour:
     * 1-Le héros pioche 5 cartes
     * 2-Son énergie actuelle devient son énergie par tour.
     * 3-Tous ses points de blocage disparaissent.
     * 4-Les monstres affichent leur nouvelle intention.
     * 
     * @param heros un objet de type Heros (le joueur)
     */
    public void prepTourDeJoueur(Heros heros, Pioche pioche, Defausse defausse) {
        piocheCartes(pioche, heros, defausse);
        resetEnergie(heros);
        heros.setBlock(0);
    }

    private void resetEnergie(Heros heros) {
        heros.setEnergie(heros.getEnergieMax());
    }

    private void decrementeStatutsJoueur(Heros heros) {
        heros.vulnerabilite.decrePointStatut();
    }

    private void decrementeStatutsMonstres() {
        for (int i = 0; i < monstres.length; i++) {
            monstres[i].vulnerabilite.decrePointStatut();
        }
    }

    public void prepTourDesMonstres() {
    }

    public void actionsFinTourMonstres() {
        decrementeStatutsMonstres();
    }

    public void actionsFinTourJoueur(Heros heros) {
        decrementeStatutsJoueur(heros);
    }

    public Monstre[] getMonstres() {
        return monstres;
    }

    private void piocheCartes(Pioche pioche, Heros joueur, Defausse defausse) {
        int nbCarteMain = joueur.getNb_carte_main();
        for (int i = 0; i < (Heros.MAX_NB_CARTES_MAIN - nbCarteMain); i++) {
            if (!pioche.estVide()) {
                joueur.ajouteCarteMain(pioche.prendreCarte());
            } else {
                pioche.prendCartesDefausse(defausse);
                joueur.ajouteCarteMain(pioche.prendreCarte());
            }

        }

    }

    public void initialiserPioches(Deck deck, Pioche pioche) {
        for (int i = 0; i < deck.nb_carte(); i++) {
            pioche.ajouteCarte(deck.getCarteAleatoire());
        }
    }

    public void carteToDefausse(Heros heros, Defausse defausse) {
        defausse.ajouteCarte(heros.getMain()[heros.getCarteChoisie()]);
        heros.supprimeCarteMain();
    }

    public void regarderSiActive() {

    }

    public boolean regarderSiMonstreVivant(int cible) {
        return monstres[cible].alive();
    }

}
