package main.statut;

public abstract class Statut {
    protected int pointStatut;

    public Statut(int pointStatut) {
        this.pointStatut = pointStatut;
    }

    public int getPointStatut() {
        return pointStatut;
    }

    public void decrePointStatut() {
        if (pointStatut > 0) {
            pointStatut = pointStatut - 1;
        } else {
            pointStatut = 0;
        }

    }

    abstract public int effetDeStatut(int n);

    public void ajoutePointStatut(int n) {
        pointStatut += n;
    }
}
