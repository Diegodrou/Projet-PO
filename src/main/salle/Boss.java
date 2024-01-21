package main.salle;

import monstre.Machouilleur;
import monstre.Monstre;

public class Boss extends SalleDeCombat {

    public Boss() {
        super(new Monstre[] { new Machouilleur(), new Machouilleur(), new Machouilleur() });
        // TODO Auto-generated constructor stub
    }

    public String toString() {
        // TODO Auto-generated method stub
        String txt = "Salle: ";
        txt += "Boss";
        return txt;
    }

}
