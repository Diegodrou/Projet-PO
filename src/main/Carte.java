package main;

public abstract class Carte {
    private String typeDentiteApplicable;
    private int cout;
    private String nomCarte;
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

    public Carte(String typeDentiteApplicable, int cout, int nb_degats, int nb_block) {
        this.typeDentiteApplicable = typeDentiteApplicable;
        this.cout = cout;
        this.nb_block = nb_block;
        this.nb_degats = nb_degats;
    }

    public void effetDegats(Entite cible) {
        cible.setPv(cible.getPv() - cible.vulnerabilite.effetDeStatut(nb_degats));
    }

    public void effetBlockage(Entite cible) {
        cible.setBlock(cible.getBlock() + nb_block);
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

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Carte : " + nomCarte + "/cout : " + cout;
    }

    public String getNomCarte() {
        return nomCarte;
    }

    public void setNomCarte(String nomCarte) {
        this.nomCarte = nomCarte;
    }
}
