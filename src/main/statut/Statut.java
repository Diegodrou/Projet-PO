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
        pointStatut = pointStatut - 1;
    }

    abstract public int effetDeStatut(int n);

    public void ajoutePointStatut(int n) {
        pointStatut += n;
    }
}
