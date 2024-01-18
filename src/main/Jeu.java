/** package principal */
package main;

import java.awt.Color;
import java.io.File;

import librairies.StdDraw;
import main.actions.carte.Carte;
import main.salle.Salle;
import main.salle.SalleDeCombat;
import main.statut.Statut;
import main.strctCarte.Defausse;
import main.strctCarte.Pioche;
import monstre.Machouilleur;
import monstre.Monstre;
import monstre.PetitSlimePiquant;
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
	private boolean alerte_energie = false;
	private boolean alerte_choisit_carte = false;
	private boolean affichageDegatBool = false;
	private int nb_cible = 0;

	// position joueur
	private double x_joueur = Config.X_MAX * 0.2 - 183;
	private double y_joueur = Config.Y_MAX * 0.5 - 130;// 338.5
	private double x2_joueur = x_joueur + 366;
	private double y2_joueur = y_joueur + 260;

	// position monstre 1
	private double x1 = Config.X_MAX * 0.4;
	private double y1 = Config.Y_MAX * 0.5;

	// position monstre 2
	private double x2 = Config.X_MAX * 0.6;
	private double y2 = Config.Y_MAX * 0.6;

	// position monstre 3
	private double x3 = Config.X_MAX * 0.8;
	private double y3 = Config.Y_MAX * 0.5 - 60;

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
		affichageJoueur();
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
		affichageStatutsMonstreModeGraphique();

		affichageDegats();

		// Alertes
		if (alerte_energie) {
			alerte_energie = false;
			Affichage.texteGaucheR(Config.X_MAX - 1000, Config.Y_MAX - 750, "Pass assez d'energie");
		}

		if (alerte_choisit_carte && joueur.getEnergie() > 0) {
			alerte_choisit_carte = false;
			Affichage.texteGaucheV(Config.X_MAX - 1000, Config.Y_MAX - 780, "Choisit une carte");
		}

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
			salleC = new SalleDeCombat(new Monstre[] { new PetitSlimePiquant(), new Machouilleur() });
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

		System.out.println("Choisit une carte");
		alerte_choisit_carte = true;
		display();// Affiche les donnees apres preparation du tour
		boolean tourDuJoueur = true;
		while (tourDuJoueur) {
			alerte_choisit_carte = true;
			display();
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
		affichageDegatBool = true;
		nb_cible = cible;
		System.out.println("Vous avez choisi montre " + (cible + 1));
		if (salleC.regarderSiMonstreVivant(cible)) {
			salleC.setCibleDuJoueur(cible);
			salleC.performerActionsJoueur(joueur);
			salleC.carteToDefausse(joueur, defausse);
		}

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
				alerte_energie = true;

			}
		}
		// display();
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
		double x = Config.X_MAX * 0.2 - 100;
		double y = Config.Y_MAX * 0.5 - 130;
		double longeurImage = 20;
		double largeurImage = 20;

		Affichage.texteGauche(x, y, joueur.toString());
		// Affichage statut
		for (int a = 0; a < Statut.nbStatuts; a++) {
			switch (a) {
				case 0:
					break;
				case 1:
					x += 25;
					break;
			}

			if (Integer.valueOf(cherchePointStatutMonstre(joueur, a)) > 0) {
				Affichage.image(x, x + longeurImage, y - largeurImage, y, Statut.imagesStatut[a]);
				Affichage.texteGauche(x + 22, y, cherchePointStatutMonstre(joueur, a));
			}
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
		double x = 0;
		double y = 0;
		;
		double espace = 20;
		for (int i = 0; i < salleC.getMonstres().length; i++) {
			switch (i) {
				case 0:
					x = x1;
					y = y1 - salleC.getMonstre(i).getLargeurImage() - espace;
					break;
				case 1:
					x = x2;
					y = y2 - salleC.getMonstre(i).getLargeurImage() - espace;
					break;
				case 2:
					x = x3;
					y = y3 - salleC.getMonstre(i).getLargeurImage() - espace;
					break;
			}

			if (salleC.regarderSiMonstreVivant(i)) {
				Affichage.texteGauche(x, y,
						salleC.getMonstres()[i].toString());
			}

		}
	}

	private void affichageMonstresMondeGraphique() {
		double xMinInit = 0;
		double yMaxInit = 0;
		double espaceX = 20;
		double espaceY = 35;

		for (int i = 0; i < salleC.getMonstres().length; i++) {
			switch (i) {
				case 0:
					xMinInit = x1;
					yMaxInit = y1;
					break;
				case 1:
					xMinInit = x2;
					yMaxInit = y2;
					break;
				case 2:
					xMinInit = x3;
					yMaxInit = y3;
					break;
			}

			if (salleC.regarderSiMonstreVivant(i)) {
				Monstre m = salleC.getMonstre(i);
				Affichage.image(xMinInit, xMinInit + m.getLongeurImage(),
						yMaxInit - m.getLargeurImage(), yMaxInit,
						m.getPathImageMonstre());
				affichageItentionMonstre(xMinInit + (m.getLongeurImage() / 2) - espaceX, yMaxInit + espaceY, m);
			}

		}
	}

	private void affichageStatutsMonstreModeGraphique() {
		double x = 0;
		double y = 0;
		double espace = 40;
		double longeurImage = 20;
		double largeurImage = 20;

		for (int i = 0; i < salleC.getMonstres().length; i++) {
			if (salleC.getMonstre(i).alive()) {
				switch (i) {
					case 0:
						x = x1;
						y = y1 - salleC.getMonstre(i).getLargeurImage() - espace;
						break;
					case 1:
						x = x2;
						y = y2 - salleC.getMonstre(i).getLargeurImage() - espace;
						break;
					case 2:
						x = x3;
						y = y3 - salleC.getMonstre(i).getLargeurImage() - espace;
						break;
				}
				for (int a = 0; a < 2; a++) {
					switch (a) {
						case 0:
							break;
						case 1:
							x += 25;
							break;
					}

					if (Integer.valueOf(cherchePointStatutMonstre(salleC.getMonstre(i), a)) > 0) {
						Affichage.image(x, x + longeurImage, y - largeurImage, y, Statut.imagesStatut[a]);
						Affichage.texteGauche(x + 22, y, cherchePointStatutMonstre(salleC.getMonstre(i), a));
					}

				}
			}

		}
	}

	private String cherchePointStatutMonstre(Entite e, int numStatut) {
		return switch (numStatut) {
			case 0 -> "" + e.vulnerabilite.getPointStatut();
			case 1 -> "" + e.force.getPointStatut();
			default -> throw new IllegalArgumentException();
		};
	}

	private void affichageItentionMonstre(double x, double y, Monstre m) {
		Affichage.image(x, x + 40, y - 40, y, m.getIntention().getImage());
	}

	private void affichageJoueur() {
		String pathHeros = "pictures" + File.separator + "Ironclad.png";
		Affichage.image(Config.X_MAX * 0.2 - 183, x2_joueur, Config.Y_MAX * 0.5 - 130, y2_joueur, pathHeros);
	}

	private void affichageDegats() {

		Monstre m = salleC.getMonstre(nb_cible);
		if (affichageDegatBool) {
			affichageDegatBool = false;
			if (m.alive()) {
				switch (nb_cible) {
					case 0 ->
						Affichage.rectangleContour(x1, x1 + m.getLongeurImage(), y1 - m.getLargeurImage(), y1,
								new Color(255, 0, 0));
					case 1 -> Affichage.rectangleContour(x2, x2 + m.getLongeurImage(), y2 - m.getLargeurImage(), y2,
							new Color(255, 0, 0));
				}
			}
		}

	}
}
