package main.actions.carte;

import main.Entite;
import main.actions.ActionEntite;

public abstract class Carte extends ActionEntite {
    private int cout;

    /**
     * Indique si les effets de la carte s'applique a un monstre ou a un heros
     * 
     * @return un String "Heros" si applicable sur un objet de type Heros, "Monstre"
     *         si applicable au monstre
     */
    public String getTypeDentiteApplicable() {
        return typeDentiteApplicable;
    }

    public Carte(String typeDentiteApplicable, int cout, int nb_degats, int nb_block) {
        super(typeDentiteApplicable, nb_degats, nb_block);
        this.cout = cout;
    }

    /**
     * 
     * @return un entier qui represente le cout d'energie de la carte
     */
    public int getCout() {
        return cout;
    }

    /**
     * Perfome l'effet de la carte sur la cible donnée
     * 
     * @param cible un objet de type entite
     */
    abstract public void effetDeCarte(Entite cible);

    public void effetDeAction(Entite cible) {
        effetDeCarte(cible);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Carte : " + nomAction + "/cout : " + cout;
    }

    public String getNomCarte() {
        return getNomAction();
    }

    public void setNomCarte(String nomCarte) {
        setNomAction(nomCarte);
    }
}
