package main.statut;

import java.io.File;

public abstract class Statut {
    protected int pointStatut;

    private static String pathImageVulne = "pictures" + File.separator + "statuts" + File.separator
            + "Icon_Vulnerable.png";

    private static String pathImageForce = "pictures" + File.separator + "statuts" + File.separator
            + "Strength.png";

    public static String[] imagesStatut = new String[] { pathImageVulne, pathImageForce };

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
