package main.salle;

import main.Heros;

public class Repos extends Salle {

    public Repos() {

    }

    /**
     * Soigne le heros de 30% de ses point de vie maximum
     * 
     * @param heros un objet representant le heros(le joueur)
     */
    private void soigner(Heros heros) {
        int quantitePv = (int) (heros.getPVMax() * 0.30);

        if (heros.getPv() + quantitePv > heros.getPVMax()) {
            heros.setPv(heros.getPVMax());
        } else {
            heros.setPv(heros.getPv() + quantitePv);
        }

    }

    @Override
    public void performerActionSalle(Heros heros) {
        soigner(heros);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        String txt = super.toString();
        txt += "Repos";
        return txt;
    }

}
