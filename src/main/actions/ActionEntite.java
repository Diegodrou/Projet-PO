package main.actions;

import main.Entite;

public abstract class ActionEntite {
    protected String typeDentiteApplicable;
    protected String nomAction;
    protected int nb_block;
    protected int nb_degats;

    /**
     * Cette class porte la fonctionalité de base d'une action qui peut etre mené
     * par une entité(Le heros utilise des cartes, les monstre font des mouvments)
     * 
     * @param typeDentiteApplicable une chaine de charactere qui indique qui peut
     *                              subir les effet de cette
     *                              action("Heros","Monstre","HerosEtMonstre")
     * @param nb_degats             un entier representant le quantité de degats
     *                              qu'engendre l'action a l'entité qui subit les
     *                              effets de l'action
     * 
     * 
     * @param nb_block              un entier representant le quantité de point de
     *                              blocage ajouté a l'entité qui subit les effets
     *                              de l'action
     */
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
     * @param cible      un objet de type entité
     * @param thisEntite un objet de type entité qu'utilise l'action ou la carte
     */
    abstract public void effetDeAction(Entite cible, Entite thisEntite);

    /**
     * Cette fonction applique des dégats a l'entité cible(prenant en compte les
     * points des statuts)
     * 
     * @param cible      un objet de type entité qui va subir les degats
     * @param thisEntite un objet de type entite qui fait l'action(dans le cas que
     *                   une action a des effets dans la cible et l'entite qui fait
     *                   l'action )
     * 
     * @param ordre      un tableau d'entier qui represente l'ordre d'application
     *                   des effet des differents statuts
     */
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

    /**
     * Cette fonction applique ajoute a l'entité cible des point de blockage(prenant
     * en compte les
     * points des statuts)
     * 
     * @param cible un objet de type entité a qui on va appliquer les points de
     *              blockage('entite qui a fait l'action)
     * 
     * @param ordre un tableau d'entier qui represente l'ordre d'application
     *              des effet des differents statuts
     */
    public void effetBlockage(Entite cible, int[] ordre) {
        cible.setBlock(cible.getBlock() + applicationEffetStatuts(nb_block, ordre, cible, cible));
    }

    protected String getNomAction() {
        return nomAction;
    }

    protected void setNomAction(String nomAction) {
        this.nomAction = nomAction;
    }

    /**
     * Renvoi le nombre de dégats après d'appliquer les effets des statuts
     * 
     * @param nb_degats  un entier qui represente le nombre de degats
     * 
     * @param ordre      un tableau d'entier qui represente l'ordre d'application
     *                   des effet des differents statuts
     * 
     * @param cible      un objet de type entité qui va subir les degats
     * 
     * @param thisEntite un objet de type entite qui fait l'action(dans le cas que
     *                   une action a des effets dans la cible et l'entite qui fait
     *                   l'action )
     * @return Le nombre de degats apres avoir appliquer(ou pas) les effets des
     *         statut
     */
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
