package main;

public class Defense extends Carte {
    private int nb_block;

    public Defense() {
        super("Heros",1);
        this.nb_block = 5;
        setNomCarte("Defense");
    }

    @Override
    public void effetDeCarte(Entite cible) {
        // TODO Auto-generated method stub
        cible.setBlock(cible.getBlock() + nb_block);
    }

}
