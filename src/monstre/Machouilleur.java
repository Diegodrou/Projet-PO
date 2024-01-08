package monstre;

import java.io.File;

import main.actions.mouvement.Charge;
import main.actions.mouvement.Gronder;
import main.actions.mouvement.Morsure;
import main.actions.mouvement.MouvAleatoire;
import main.actions.mouvement.Mouvement;

public class Machouilleur extends Monstre {

    public Machouilleur() {
        super("Mâchouilleur", 40,
                new Mouvement[] { new Charge(7, 5),
                        new MouvAleatoire(new Mouvement[] { new Charge(7, 5), new Morsure(), new Gronder() },
                                new double[] { 0.30, 0.25, 0.45 }) },
                "pictures" + File.separator + "monstres" + File.separator + "Jaw-worm-pretty.png");
    }

}
