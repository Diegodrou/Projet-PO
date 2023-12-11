package main;

public abstract class Carte {
    private String typeDentiteApplicable;
    private int cout;
    private String nomCarte;

    /**
     * Indique si les effets de la  carte s'applique a un monstre ou  a un heros
     * @return un String "Heros" si applicable sur un objet de type Heros, "Monstre" si applicable au monstre
     */
    public String getTypeDentiteApplicable() {
        return typeDentiteApplicable;
    }

    public Carte(String typeDentiteApplicable, int cout) {
        this.typeDentiteApplicable = typeDentiteApplicable;
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
     * @param cible un objet de type entite
     */
    abstract public void effetDeCarte(Entite cible);

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Carte : " + nomCarte +"/cout : " + cout;
    }

    public String getNomCarte() {
        return nomCarte;
    }

    public void setNomCarte(String nomCarte) {
        this.nomCarte = nomCarte;
    }
}
