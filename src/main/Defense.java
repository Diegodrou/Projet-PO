package main;

public class Defense extends Carte {
    private int nb_block;

    public Defense() {
        super("Heros",1);
        this.nb_block = 5;
    }

    @Override
    public void effetDeCarte(Entite cible) {
        // TODO Auto-generated method stub
        cible.setBlock(cible.getBlock() + nb_block);
    }

}
