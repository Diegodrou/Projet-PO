package monstre;

import java.io.File;

import main.actions.mouvement.Charge;
import main.actions.mouvement.Mouvement;

public class PetitSlimePiquant extends Monstre {

    public PetitSlimePiquant() {
        super("Petit slime piquant", 12, new Mouvement[] { new Charge(5, 0) },
                "pictures" + File.separator + "monstres" + File.separator + "Spike_Slime_S.png", 70, 106);

    }

}
