package main;

public abstract class Salle {
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

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Salle : ";
    }

}
