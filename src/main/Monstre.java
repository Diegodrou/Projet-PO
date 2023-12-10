package main;

public class Monstre extends Entite {

    public Monstre(int pvMax) {
        super(pvMax);

    }

    public void attaquer(Heros heros) {

        int blockMoinsDegats = heros.getBlock() - 6;
        if ( blockMoinsDegats > 0) {
            heros.setBlock(blockMoinsDegats);
        }
        else if (blockMoinsDegats == 0){
            heros.setBlock(0);
        }
        else{
            heros.setPv(heros.getPv() + blockMoinsDegats);
            heros.setBlock(0);
        }
    }

    @Override
    public String toString() {
        return "Monstre: " + super.toString();
    }

}
