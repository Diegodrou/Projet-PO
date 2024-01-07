package monstre;

import main.Entite;
import main.Heros;
import main.actions.mouvement.Mouvement;

public abstract class Monstre extends Entite {

    protected Mouvement[] pattern;
    protected int mouvementCourant;
    private String nomMonstre;

    public Monstre(String nomMonstre, int pvMax, Mouvement[] pattern) {
        super(pvMax);
        mouvementCourant = 0;
        this.nomMonstre = nomMonstre;
        this.pattern = pattern;

    }

    public void attaquer(Heros heros) {
        pattern[mouvementCourant].effetDeMouvement(heros);
        mouvementCourant++;
        if (mouvementCourant == pattern.length) {
            mouvementCourant = 0;
        }
        // int blockMoinsDegats = heros.getBlock() - 6;
        // if (blockMoinsDegats > 0) {
        // heros.setBlock(blockMoinsDegats);
        // } else if (blockMoinsDegats == 0) {
        // heros.setBlock(0);
        // } else {
        // heros.setPv(heros.getPv() + blockMoinsDegats);
        // heros.setBlock(0);
        // }
    }

    @Override
    public String toString() {
        return nomMonstre + " : " + super.toString();
    }

}
