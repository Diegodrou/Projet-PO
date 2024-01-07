/** package principal */
package main;

import java.io.File;

import librairies.StdDraw;
import main.actions.carte.Carte;
import main.salle.Salle;
import main.salle.SalleDeCombat;
import main.strctCarte.Defausse;
import main.strctCarte.Pioche;
import ressources.Affichage;
import ressources.AssociationTouches;
import ressources.Config;

public class Jeu {

	private Heros joueur;
	private boolean over;
	private Deck deck;
	private Pioche pioche;
	private Defausse defausse;
	private int indexSalleCourante;
	private int indexSalleAvant;
	private SalleDeCombat salleC;
	private SalleDeCombat salleR;
	private SalleDeCombat salleB;
	private Salle salleCourante;
	private int[] salles;

	public Jeu() throws Exception {
		joueur = new Heros(70, 3);
		deck = new Deck();
		pioche = new Pioche();
		defausse = new Defausse();
		indexSalleCourante = 0;
		indexSalleAvant = 0;
		salles = new int[] { 1, 1, 1 };
		over = false;
		initisaliserSalle(determinerTypeSalle());

	}

	public boolean isOver() {
		return over;
	}

	public void display() {
		StdDraw.clear();

		// Affichage du fond
		String pathBackground = "pictures" + File.separator + "background.jpg";
		Affichage.image(0, Config.X_MAX, 0, Config.Y_MAX, pathBackground);

		// Affichage du héros
		String pathHeros = "pictures" + File.separator + "Ironclad.png";
		Affichage.image(Config.X_MAX * 0.2 - 183, Config.X_MAX * 0.2 + 183, Config.Y_MAX * 0.5 - 130,
				Config.Y_MAX * 0.5 + 130, pathHeros);

		// Affichage defausse
		afficheDefausseModeGraphique();
		// Affichage pioche
		affichePiocheModeGraphique();
		// Affichage des stats du joueur
		affichageStatsJoueurModeGraphique();
		// Affichage des stats des monstres

		// Affichage carte dans la main
		afficheCarteMainModeGraphique();

		// Affichage finir tour
		Affichage.texteDroite(Config.X_MAX - 50, Config.Y_MAX - 20, "Entrée - Finir tour");

		// Affichage monstres
		affichageMonstresMondeGraphique();

		// Affichage de stats des monstres
		affichageStatsMonstresModeGraphique();

		StdDraw.show(); // montre a l'ecran les changements demandés
	}

	public void initialDisplay() {
		AssociationTouches.init();
		Config.init();
		StdDraw.enableDoubleBuffering(); // rend l'affichage plus fluide: tout draw est mis en buffer et ne s'affiche
											// qu'au prochain StdDraw.show();
		display();
	}

	public void update() {
		if (indexSalleAvant < indexSalleCourante) {
			initisaliserSalle(determinerTypeSalle());
			indexSalleAvant = indexSalleCourante;

		} else {
			gererSalle();
		}

	}

	private int determinerTypeSalle() {
		return salles[indexSalleCourante];
	}

	private void initisaliserSalle(int typeSalle) {
		if (typeSalle == 1) {
			salleC = new SalleDeCombat(2);
			salleC.initialiserPioches(deck, pioche);
			salleCourante = salleC;
		}
	}

	private void gererSalle() {
		if (salleCourante.isActive()) {
			logiqueSalles(1);
		} else {
			indexSalleCourante++;
		}
	}

	private void logiqueSalles(int typeSalle) {
		switch (typeSalle) {
			case 1 -> logiqueSalleDeCombat();
			case 2 -> logiqueSalleRepos();
			case 3 -> logiqueSalleBoss();
			default -> throw new IllegalArgumentException(
					"Le type des salles sont : 1 pour salle de combat , 2 pour salle de repos, 3 pour salle avec Boss final");
		}
	}

	private void logiqueSalleDeCombat() {
		tourDuJoueur();
		// display();
		System.out.println("Tour des monstres");
		salleC.performerActionSalle(joueur);
		affichageModeTexte();
		display();

		if (!joueur.alive() || !salleC.isMonstersAlive()) {
			over = true;
		}
	}

	private void logiqueSalleRepos() {

	}

	private void logiqueSalleBoss() {

	}

	/**
	 * Performe le tour du joueur
	 */
	private void tourDuJoueur() {
		salleC.prepTourDeJoueur(joueur, pioche, defausse);
		display();// Affiche les donnees apres preparation du tour
		System.out.println("Choisit une carte");
		boolean tourDuJoueur = true;
		while (tourDuJoueur) {
			String toucheSuivante = AssociationTouches.trouveProchaineEntree();
			if (toucheSuivante.equals("1")) {
				joueCarte(0);
			} else if (toucheSuivante.equals("2")) {
				joueCarte(1);
			} else if (toucheSuivante.equals("3")) {
				joueCarte(2);
			} else if (toucheSuivante.equals("4")) {
				joueCarte(3);
			} else if (toucheSuivante.equals("5")) {
				joueCarte(4);
			} else if (toucheSuivante.equals("Entrée")) {
				tourDuJoueur = false;
				affichageModeTexte();
				display();
			} else {
				System.out.println("Autre touche");
			}
			System.out.println("Appuyer sur la touche Entrée pour finir le tour");

		}
		salleC.actionsFinTourJoueur(joueur);
	}

	private void choisir_cible() {

		if (joueur.getCarteDeLaMain(joueur.getCarteChoisie()).getTypeDentiteApplicable() == "Heros") {
			salleC.performerActionsJoueur(joueur);
			salleC.carteToDefausse(joueur, defausse);
		} else {
			System.out.println("Choisit une cible");
			String toucheSuivante = AssociationTouches.trouveProchaineEntree();
			if (toucheSuivante.equals("1")) {
				effectueActionSurCible(0);
			} else if (toucheSuivante.equals("2")) {
				effectueActionSurCible(1);
			} else {
				System.out.println("Autre touche");
			}
		}

	}

	private void effectueActionSurCible(int cible) {
		System.out.println("Vous avez choisi montre " + (cible + 1));
		salleC.setCibleDuJoueur(cible);
		salleC.performerActionsJoueur(joueur);
		salleC.carteToDefausse(joueur, defausse);
	}

	/**
	 * Joue la carte n+1 du joueur
	 * 1-regarde si le joueur a l'energie suiffisante pour utiliser la carte
	 * 2-s'il a l'energie suiffisante il choisit une cible et affiche le changment
	 * sinon rien se passe
	 * 
	 * @param n un entier representant l'index d'un tableau
	 */
	private void joueCarte(int n) {
		if ((n >= 0 || n < 5) && (joueur.getCarteDeLaMain(n) != null)) {
			if (joueur.getCarteDeLaMain(n).getCout() <= joueur.getEnergie()) {
				System.out.println("Vous avez choisie la carte " + (n + 1));
				joueur.setCarteChoisie(n);
				choisir_cible();
				affichageModeTexte();
				display();
			} else {
				System.out.println("Pas assez d'energie");
			}
		}
		display();
	}

	/**
	 * Affiche le jeu dans le terminal
	 */
	private void affichageModeTexte() {
		afficheStatsJoueursModeTexte();
		affichageStatsSalleModeTexte();
		System.out.println("----------------------------");
	}

	/**
	 * Affiche les stats du joueur
	 */
	private void afficheStatsJoueursModeTexte() {
		System.out.println("-------------");
		System.out.println(joueur);
		System.out.println("-------------");
	}

	/**
	 * Affiche la salle
	 */
	private void affichageStatsSalleModeTexte() {
		System.out.println(salleCourante);
	}

	/**
	 * Affiche les stats du joueur
	 */
	private void affichageStatsJoueurModeGraphique() {
		double initialStatutPosX = Config.X_MAX * 0.2;
		double initialStatutPosX2 = Config.X_MAX * 0.2 + 30;
		double initialStatuPosY = Config.Y_MAX * 0.5 + 130;
		double initialStatuPosY2 = Config.Y_MAX * 0.5 + 150;
		String pathStatutVul = "pictures" + File.separator + "statuts" + File.separator + "Icon_Vulnerable.png";
		Affichage.texteGauche(Config.X_MAX * 0.2 - 100, Config.Y_MAX * 0.5 - 130, joueur.toString());
		// Affichage vulnerabilité
		int pointVul = joueur.vulnerabilite.getPointStatut();
		if (pointVul > 0) {
			Affichage.image(initialStatutPosX, initialStatutPosX2, initialStatuPosY, initialStatuPosY2, pathStatutVul);
			Affichage.texteGauche(initialStatutPosX2, initialStatuPosY, "" + pointVul);
		}

	}

	// Affichage carte dans la main
	private void afficheCarteMainModeGraphique() {
		Carte[] main = joueur.getMain();
		for (int i = 0; i < main.length; i++) {
			if (main[i] != null) {
				Affichage.texteGauche(Config.X_MAX * 0.1 + i * 300, Config.Y_MAX * 0.1,
						(i + 1) + "-" + main[i].toString());
			}
		}
	}

	private void affichePiocheModeGraphique() {
		Affichage.texteGauche(Config.X_MAX * 0.1, Config.Y_MAX * 0.2, "Pioche: " + pioche.nb_carte());

	}

	private void afficheDefausseModeGraphique() {
		Affichage.texteGauche(Config.X_MAX * 0.8, Config.Y_MAX * 0.2, "Defausse: " + defausse.nb_carte());
	}

	private void affichageStatsMonstresModeGraphique() {
		for (int i = 0; i < salleC.getMonstres().length; i++) {
			Affichage.texteGauche(Config.X_MAX * 0.5 + 20 + i * 50, Config.Y_MAX * 0.5 - (130 + i * 50),
					salleC.getMonstres()[i].toString());
		}
	}

	private void affichageMonstresMondeGraphique() {
		double xMinInit = Config.X_MAX * 0.5;
		double yMaxInit = Config.Y_MAX * 0.5;

		for (int i = 0; i < salleC.getMonstres().length; i++) {
			if (i > 0) {
				xMinInit += 40;
				yMaxInit -= 100;
			}

			Affichage.image(xMinInit, xMinInit + 106, yMaxInit - 70, yMaxInit,
					salleC.getMonstres()[i].getPathImageMonstre());
		}
	}

}
