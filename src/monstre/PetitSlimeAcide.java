package monstre;

import java.io.File;

import main.actions.mouvement.Charge;
import main.actions.mouvement.Lecher;
import main.actions.mouvement.Mouvement;

public class PetitSlimeAcide extends Monstre {

    public PetitSlimeAcide() {
        super("Petit Slime Acide", 8, new Mouvement[] { new Charge(3, 0), new Lecher() },
                "pictures" + File.separator + "monstres" + File.separator + "Acid-slime-S.png", 140, 216);
        // TODO Auto-generated constructor stub
    }

}
