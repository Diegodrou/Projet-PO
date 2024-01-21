package main.salle;

import main.Heros;

public abstract class Salle {

    protected boolean active = true;

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

    public void desactiver() {
        active = false;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Salle : ";
    }

}
