package main.actions.mouvement;

import main.Entite;
import main.actions.ActionEntite;

public abstract class Mouvement extends ActionEntite {

    protected String imagePath;

    /**
     * Actions des monstres
     * 
     * @param typeDentiteApplicableune une chaine de charactere qui indique qui peut
     *                                 subir les effet de cette
     *                                 action("Heros","Monstre","HerosEtMonstre").
     * 
     * @param nb_degats                un entier representant le quantité de degats
     *                                 qu'engendre l'action a l'entité qui subit les
     *                                 effets de l'action.
     * 
     * @param nb_block                 un entier representant le quantité de point
     *                                 de blocage ajouté a l'entité qui subit les
     *                                 effets de l'action.
     * 
     * @param imagePath                une chaine de charactere qui indique où se
     *                                 trouve l'image de l'intention correspondante
     *                                 au mouvement
     */
    public Mouvement(String typeDentiteApplicable, int nb_degats, int nb_block, String imagePath) {
        super(typeDentiteApplicable, nb_degats, nb_block);
        this.imagePath = imagePath;
        // TODO Auto-generated constructor stub
    }

    public abstract void effetDeMouvement(Entite cible, Entite thisEntite);

    protected int getNb_degats() {
        return nb_degats;
    }

    protected int getNb_block() {
        return nb_block;
    }

    public String getImage() {
        return imagePath;
    }

    @Override
    public void effetDeAction(Entite cible, Entite thisEntite) {
        effetDeMouvement(cible, thisEntite);
    }

}
