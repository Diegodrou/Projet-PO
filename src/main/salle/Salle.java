package main.salle;

import main.Heros;

public abstract class Salle {

    protected boolean active = true;

    /**
     * Performe les actions du Joueur selon le type de salle et l'input du
     * joueur(EdB)
     * 
     * @param heros un objet de type Heros(Le joueur)
     */
    abstract public void performerActionsJoueur(Heros heros);

    /**
     * Performe les action de la salle selon le type de Salle
     * 
     * @param heros un objet de type Heros(le Joueur)
     */
    abstract public void performerActionSalle(Heros heros);

    /**
     * Indique si le joueur est dans la salle
     * 
     * @return Vrai si le joueur est encore dans salle sinon faux
     */
    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Salle : ";
    }

}
