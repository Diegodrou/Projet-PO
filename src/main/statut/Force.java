package main.statut;

public class Force extends Statut {

    public Force(int pointStatut) {
        super(pointStatut);
        // TODO Auto-generated constructor stub
    }

    @Override
    public int effetDeStatut(int n) {
        return n + pointStatut;
    }

}
