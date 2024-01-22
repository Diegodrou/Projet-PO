package main.actions;

import main.Entite;

public abstract class ActionEntite {
    protected String typeDentiteApplicable;
    protected String nomAction;
    protected int nb_block;
    protected int nb_degats;

    public ActionEntite(String typeDentiteApplicable, int nb_degats, int nb_block) {
        this.typeDentiteApplicable = typeDentiteApplicable;
        this.nb_block = nb_block;
        this.nb_degats = nb_degats;
    }

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
    abstract public void effetDeAction(Entite cible, Entite thisEntite);

    public void effetDegats(Entite cible, Entite thisEntite, int[] ordre) {
        int blockMoinsDegats = cible.getBlock()
                - applicationEffetStatuts(nb_degats, ordre, cible, thisEntite);
        if (blockMoinsDegats > 0) {
            cible.setBlock(blockMoinsDegats);
        } else if (blockMoinsDegats == 0) {
            cible.setBlock(0);
        } else {
            cible.setPv(cible.getPv() + blockMoinsDegats);
            cible.setBlock(0);
        }
    }

    public void effetBlockage(Entite cible, int[] ordre) {
        cible.setBlock(cible.getBlock() + applicationEffetStatuts(nb_block, ordre, cible, cible));
    }

    protected String getNomAction() {
        return nomAction;
    }

    protected void setNomAction(String nomAction) {
        this.nomAction = nomAction;
    }

    private int applicationEffetStatuts(int nb_degats, int[] ordre, Entite cible, Entite thisEntite) {
        if (ordre.length == 0) {
            return nb_degats;
        }

        int sum = nb_degats;
        for (int i : ordre) {
            switch (i) {
                case 1 -> sum = cible.vulnerabilite.effetDeStatut(sum);
                case 2 -> sum = thisEntite.force.effetDeStatut(sum);
                case 3 -> sum = cible.faible.effetDeStatut(sum);
                default -> throw new IllegalArgumentException("Aucun statut est associé a ce nombre");
            }
        }

        return sum;
    }
}
