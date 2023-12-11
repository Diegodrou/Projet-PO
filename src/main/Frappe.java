package main;

public class Frappe extends Carte {
    private int nb_degats;

    public Frappe() {
        super("Monstre", 1);
        this.nb_degats = 6;
        setNomCarte("Frappe");
    }

    @Override
    public void effetDeCarte(Entite cible) {
        cible.setPv(cible.getPv() - nb_degats);

    }

}
