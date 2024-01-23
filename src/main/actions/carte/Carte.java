package main.actions.carte;

import main.Entite;
import main.actions.ActionEntite;

public abstract class Carte extends ActionEntite {
    private int cout;

    /**
     * @param typeDentiteApplicable une chaine de charactere qui indique qui peut
     *                              subir les effet de cette
     *                              action("Heros","Monstre","HerosEtMonstre").
     * 
     * @param cout                  un entier representant le cout de la carte.
     * 
     * @param nb_degats             un entier representant le quantité de degats
     *                              qu'engendre l'action a l'entité qui subit les
     *                              effets de l'action.
     * 
     * @param nb_block              un entier representant le quantité de point
     *                              de blocage ajouté a l'entité qui subit les
     *                              effets de l'action.
     */
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
    abstract public void effetDeCarte(Entite cible, Entite thisEntite);

    @Override
    public void effetDeAction(Entite cible, Entite thisEntite) {
        effetDeCarte(cible, thisEntite);
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
