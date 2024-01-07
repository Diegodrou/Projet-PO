package monstre;

import main.actions.mouvement.Charge;
import main.actions.mouvement.Mouvement;

public class PetitSlimePiquant extends Monstre {

    public PetitSlimePiquant() {
        super("Petit slime piquant", 12, new Mouvement[] { new Charge() });

    }

}
