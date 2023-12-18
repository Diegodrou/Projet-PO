package main.statut;

public class Vulnerabilite extends Statut {

    public Vulnerabilite(int pointStatut) {
        super(pointStatut);
        // TODO Auto-generated constructor stub
    }

    @Override
    public int effetDeStatut(int n) {

        if (pointStatut > 0) {
            return (int) (n + 0.5 * n);
        }
        return n;

    }

}
