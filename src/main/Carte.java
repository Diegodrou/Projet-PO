package main;

public abstract class Carte {
    private String typeDentiteApplicable;
    private int cout;


    public String getTypeDentiteApplicable() {
        return typeDentiteApplicable;
    }

    public Carte(String typeDentiteApplicable, int cout) {
        this.typeDentiteApplicable = typeDentiteApplicable;
        this.cout = cout;
    }

    public int getCout() {
        return cout;
    }

    

    abstract public void effetDeCarte(Entite cible);
}
