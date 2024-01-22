package main.statut;

public class Faible extends Statut {

    public Faible(int pointStatut) {
        super(pointStatut);
        // TODO Auto-generated constructor stub
    }

    @Override
    public int effetDeStatut(int n) {
        if (pointStatut > 0) {
            return (int) (0.75 * n);
        }
        return n;
    }

}
