package main.actions;

import main.Entite;

public abstract class ActionEntite {
    private String typeDentiteApplicable;
    private String nomAction;
    private int nb_block;
    private int nb_degats;

    /**
     * Indique si les effets de la carte s'applique a un monstre ou a un heros
     * 
     * @return un String "Heros" si applicable sur un objet de type Heros, "Monstre"
     *         si applicable au monstre
     */
    public String getTypeDentiteApplicable() {
        return typeDentiteApplicable;
    }

    /**
     * Perfome l'effet de la carte sur la cible donnée
     * 
     * @param cible un objet de type entite
     */
    abstract public void effetDeAction(Entite cible);

    public void effetDegats(Entite cible) {
        cible.setPv(cible.getPv() - cible.vulnerabilite.effetDeStatut(nb_degats));
    }

    public void effetBlockage(Entite cible) {
        cible.setBlock(cible.getBlock() + nb_block);
    }

    public String getNomAction() {
        return nomAction;
    }

    public void setNomCarte(String nomAction) {
        this.nomAction = nomAction;
    }
}
